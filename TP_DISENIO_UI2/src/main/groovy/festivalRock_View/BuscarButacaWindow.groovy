package festivalRock_View

import aplicationModel.BuscadorButacas

import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action

import dominio.Butaca
import dominio.Entrada;


class BuscarButacaWindow extends SimpleWindow<BuscadorButacas>{

	BuscarButacaWindow(WindowOwner owner){
		super(owner, new BuscadorButacas())
		this.search()
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		title = "Buscador de butacas"
		taskDescription = "Elija la fecha del festival para seleccionar la butaca."

		super.createMainTemplate(mainPanel)

		this.createResultsGrid(mainPanel)
		this.createGridActions(mainPanel)
	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel)
		actionsPanel.setLayout(new HorizontalLayout())

		Button comprar = new Button(actionsPanel)
		comprar.setCaption("Comprar")
				.onClick({ this.comprar()} as Action)
			//	.onClick({ this.comprarEntrada() } as Action)

		new Button(actionsPanel) //
				.setCaption("Cerrar")
				.onClick({ this.close() } as Action)

		new Button(actionsPanel)
				.setCaption("Cancelar compra")
				.onClick({modelObject.add()} as Action)
				.onClick({this.close()} as Action)
		//
		//		Button remove = new Button(actionsPanel)
		//		remove.setCaption("Borrar")
		//		remove.onClick( { modelObject.eliminarCelularSeleccionado() } as Action)

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("butacaSeleccionada")
		//		remove.bindEnabled(elementSelected)
		comprar.bindEnabled(elementSelected)
	}

	// *************************************************************************
	// ** RESULTADOS DE LA BUSQUEDA
	// *************************************************************************

	/**
	 * Se crea la grilla en el panel de abajo El binding es: el contenido de la grilla en base a los
	 * resultados de la bÃºsqueda Cuando el usuario presiona Buscar, se actualiza el model, y Ã©ste a su vez
	 * dispara la notificaciÃ³n a la grilla que funciona como Observer
	 */
	protected void createResultsGrid(Panel mainPanel) {
		def table = new Table<Butaca>(mainPanel, Butaca.class)
		table.with {
			heigth = 200
			width = 600
			bindItemsToProperty("butacas")
			bindValueToProperty("butacaSeleccionada")
		}
		this.describeResultsGrid(table)
	}

	/**
	 * Define las columnas de la grilla Cada columna se puede bindear 1) contra una propiedad del model, como
	 * en el caso del nÃºmero o el nombre 2) contra un transformer que recibe el model y devuelve un tipo
	 * (generalmente String), como en el caso de Recibe Resumen de Cuenta
	 *
	 * @param table
	 */
	protected void describeResultsGrid(Table<Butaca> table) {
		new Column<Butaca>(table) //
				.setTitle("Numero")
				.setFixedSize(150)
				.bindContentsToProperty("numeroButaca")

		new Column<Butaca>(table) //
				.setTitle("Ubicacion")
				.setFixedSize(100)
				.bindContentsToProperty("ubicacion")

	}

	/**
	 * El panel principal de búsuqeda permite filtrar por número o nombre
	 */
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel)
		searchFormPanel.setLayout(new ColumnLayout(2))
		
		new Label (searchFormPanel).setText("Elija Festival: ")
		Selector<BuscadorButacas> selectorFestival = new Selector<BuscadorButacas>(searchFormPanel).allowNull(false)
		selectorFestival.bindValueToProperty("planificacion")
		selectorFestival.bindItemsToProperty("planificaciones")
		
		new Label(searchFormPanel).setText("Fechas disponibles");
		Selector<BuscadorButacas> unSelector = new Selector<BuscadorButacas>(searchFormPanel).allowNull(false);
		unSelector.bindValueToProperty("fecha");
		unSelector.bindItemsToProperty("fechas")
		new Label(searchFormPanel).setText("Si usted tiene entradas reservadas ingrese la contraseña por favor.")
		new TextBox(searchFormPanel).bindValueToProperty("contrasenia")
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		this.agregarBotonBuscar(actionsPanel)

		new Button(actionsPanel) //
				.setCaption("Limpiar")
				.onClick({ modelObject.clear() } as Action)
	}

	def agregarBotonBuscar(panel){
		new Button(panel)
		.setCaption("Buscar")
		.onClick({ this.search() } as Action)
		.setAsDefault()
		.disableOnError()
	}
	
	def comprarEntrada(){
		modelObject.remove()
		new ComprarEntradaWindow(this, modelObject).open()
		modelObject.clear()
	}

	def comprar(){
		new ComprarButacaWindow(this, modelObject, this.esVip()).open()
		this.search()
	}
	
	def search(){
		modelObject.search()
	}
	
	def esVip(){
		return false
	}
	
}
