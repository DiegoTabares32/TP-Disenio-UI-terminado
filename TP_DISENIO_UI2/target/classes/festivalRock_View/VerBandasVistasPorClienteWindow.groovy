package festivalRock_View

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import aplicationModel.BuscadorBandasPorCliente
import aplicationModel.BuscadorPuestoVentas;
import dominio.Banda
import dominio.Compra;
import dominio.Comprador;
import dominio.Entrada
import dominio.Noche
import dominio.PuestoDeVenta

class VerBandasVistasPorClienteWindow extends SimpleWindow<BuscadorBandasPorCliente>{
	
	VerBandasVistasPorClienteWindow(WindowOwner owner){
		super(owner, new BuscadorBandasPorCliente())
		modelObject.search()
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		title = "Bandas vistas por Cliente"
		taskDescription = "Seleccione el festival y a continuacion un cliente de la grilla"

		super.createMainTemplate(mainPanel)

		this.createResultsGrid(mainPanel)
		//this.createFilterPanel(mainPanel)
		this.createGridActions(mainPanel)
	}

	protected void createResultsGrid(Panel mainPanel) {
		def table = new Table<Comprador>(mainPanel, Comprador.class)
		table.with {
			heigth = 80
			width = 400
			bindItemsToProperty("clientes")
			bindValueToProperty("clienteSeleccionado")
		}
		this.describeResultsGrid(table)
		
		table = new Table<Banda>(mainPanel, Banda.class)
		table.with {
			heigth = 80
			width = 400
			bindItemsToProperty("bandas")
//			bindValueToProperty("clienteSeleccionado")
		}
		this.describeResultsGrid2(table)
		
	}
	
	protected void createGridActions(Panel mainPanel) {

		Panel actionsPanel = new Panel(mainPanel)
		actionsPanel.setLayout(new HorizontalLayout())

		Button filtrar = new Button(actionsPanel)
		filtrar.setCaption("Ver Bandas")
				.onClick({ this.verBandas() } as Action)

		new Button(actionsPanel) //
				.setCaption("Cerrar")
				.onClick({ this.close() } as Action)

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.		
		NotNullObservable persona = new NotNullObservable("clienteSeleccionado")		
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

	def describeResultsGrid2(Table<Banda> table){
		new Column<Banda>(table)
						.setTitle("Nombre")
						.setFixedSize(150)
						.bindContentsToProperty("nombreBanda")											
						
		new Column<Banda>(table)  
						.setTitle("Categoria")
						.setFixedSize(200)
						.bindContentsToProperty("categoria")
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
				.setCaption("Buscar Clientes")
				.onClick({ this.buscarClientes() } as Action)
				.setAsDefault()
				.disableOnError()

		new Button(actionsPanel) //
				.setCaption("Limpiar")
				.onClick({ modelObject.clear() } as Action)

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel)
		searchFormPanel.setLayout(new ColumnLayout(2))
		
		new Label (searchFormPanel).setText("Elija Festival: ")
		Selector<BuscadorBandasPorCliente> selectorFestival = new Selector<BuscadorBandasPorCliente>(searchFormPanel).allowNull(false)
		selectorFestival.bindValueToProperty("festival")
		selectorFestival.bindItemsToProperty("festivales")

	}


	def verBandas(){
		modelObject.searchBandas()		
	}

	def buscarClientes(){
		modelObject.searcClientes()
	}
}