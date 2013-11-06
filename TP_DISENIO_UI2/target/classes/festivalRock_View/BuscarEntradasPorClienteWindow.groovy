package festivalRock_View

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.windows.SimpleWindow
import aplicationModel.BuscadorClientes
import dominio.Compra
import dominio.Comprador
import dominio.Entrada;

import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.lacar.ui.model.Action
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

class BuscarEntradasPorClienteWindow extends SimpleWindow<BuscadorClientes>{

	BuscarEntradasPorClienteWindow(WindowOwner owner){
		super(owner, new BuscadorClientes())
		modelObject.search()
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		title = "Entradas Vendidas por Cliente"
		taskDescription = "Ingrese los parametros de busqueda del cliente sobre el cual quiere ver las compras que ha realizado."

		super.createMainTemplate(mainPanel)

		this.createResultsGrid(mainPanel)
		this.createFilterPanel(mainPanel)
		this.createGridActions(mainPanel)
	}

	protected void createResultsGrid(Panel mainPanel) {
		def table = new Table<Comprador>(mainPanel, Comprador.class)
		table.with {
			heigth = 80
			width = 400
			bindItemsToProperty("resultados")
			bindValueToProperty("clienteSeleccionado")
		}
		this.describeResultsGrid(table)
		
		table = new Table<Entrada>(mainPanel, Entrada.class)
		table.with {
			heigth = 80
			width = 400
			bindItemsToProperty("entradasCliente")
//			bindValueToProperty("clienteSeleccionado")
		}
		this.describeResultsGrid2(table)
		
	}

	protected void createFilterPanel(Panel mainPanel){
		Panel filterFormPanel = new Panel(mainPanel)
		filterFormPanel.setLayout(new ColumnLayout(2))
		new Label(filterFormPanel).setText("Ingrese la fecha desde la cual desea filtrar en formato dd/MM/yyyy");
		new TextBox(filterFormPanel).bindValueToProperty("fechaInicio")
		new Label(filterFormPanel).setText("Ingrese la fecha hasta la cual desea filtrar en formato dd/MM/yyyy");
		new TextBox(filterFormPanel).bindValueToProperty("fechaFin")
	}
	
	protected void createGridActions(Panel mainPanel) {

		Panel actionsPanel = new Panel(mainPanel)
		actionsPanel.setLayout(new HorizontalLayout())

		Button filtrar = new Button(actionsPanel)
		filtrar.setCaption("Filtrar compra")
				.onClick({ this.filtrarCompras() } as Action)

		new Button(actionsPanel) //
				.setCaption("Cerrar")
				.onClick({ this.close() } as Action)

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected1 = new NotNullObservable("fechaInicio")
		NotNullObservable elementSelected2 = new NotNullObservable("fechaFin")
		NotNullObservable persona = new NotNullObservable("clienteSeleccionado")
		filtrar.bindEnabled(elementSelected1)
		filtrar.bindEnabled(elementSelected2)
		filtrar.bindEnabled(persona)
	}

	protected void describeResultsGrid(Table<Comprador> table) {
		new Column<Comprador>(table) //
				.setTitle("Nombre")
				.setFixedSize(150)
				.bindContentsToProperty("nombre")

		new Column<Comprador>(table) //
				.setTitle("Apellido")
				.setFixedSize(100)
				.bindContentsToProperty("apellido")
		
		new Column<Comprador>(table) //
				.setTitle("D.N.I")
				.setFixedSize(100)
				.bindContentsToProperty("dni")
				

	}

	def describeResultsGrid2(Table<Entrada> table){
		new Column<Entrada>(table) //
			.setTitle("Cliente")
			.setFixedSize(150)
			.bindContentsToProperty("comprador")
		
		new Column<Entrada>(table) //
				.setTitle("Butaca")
				.setFixedSize(100)
				.bindContentsToProperty("butacas")
				
		new Column<Entrada>(table) //
				.setTitle("Fecha de compra")
				.setFixedSize(100)
				.bindContentsToProperty("fechaCompra")
				
		new Column<Entrada>(table) //
				.setTitle("Noche")
				.setFixedSize(100)
				.bindContentsToProperty("noches")
	}

	@Override
	protected void addActions(Panel actionsPanel) {
//		new Button(actionsPanel)
//				.setCaption("Buscar")
//				.onClick({ this.filtrarCompras() } as Action)
//				.setAsDefault()
//				.disableOnError()

		new Button(actionsPanel) //
				.setCaption("Limpiar")
				.onClick({ modelObject.clear() } as Action)

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel)
		searchFormPanel.setLayout(new ColumnLayout(2))
//
//		new Label(searchFormPanel).setText("Ingrese apellido");
//		new TextBox(searchFormPanel).bindValueToProperty("apellido")
//		new Label(searchFormPanel).setText("Ingrese nombre");
//		new TextBox(searchFormPanel).bindValueToProperty("nombre")

	}


	def filtrarCompras(){
		modelObject.filtrarEntradas()		
	}

}
