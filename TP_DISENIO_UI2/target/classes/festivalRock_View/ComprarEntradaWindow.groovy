package festivalRock_View

import aplicationModel.BuscadorButacas
import dominio.Butaca

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action



class ComprarEntradaWindow extends SimpleWindow<BuscadorButacas>{

	public ComprarEntradaWindow(owner, model) {
		super(owner, model)
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		title = "Butacas seleccionadas"
		taskDescription = "¿Que desea hacer con su seleccion de butacas?"
	
		super.createMainTemplate(mainPanel)

		this.createResultsGrid(mainPanel)
	}
	
	protected void createResultsGrid(Panel mainPanel) {
		def table = new Table<Butaca>(mainPanel, Butaca.class)
		table.with {
			heigth = 200
			width = 600
			bindItemsToProperty("butacasAComprar")
			bindValueToProperty("butacaSeleccionada")
		}
		this.describeResultsGrid(table)
	}
	
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

	@Override
	protected void createFormPanel(Panel mainPanel) {
		//
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel) //
				.setCaption("Seguir Comprando")
				.onClick({ this.close() } as Action)
		new Button(actionsPanel) //
				.setCaption("Cancelar Compra")
				.onClick({this.cancelarCompraWindow()} as Action)
		new Button(actionsPanel) //
				.setCaption("Finalizar Compra")
				.onClick({this.compraConfirmadaWindow()} as Action)
		new Button(actionsPanel)
				.setCaption("Ingresar Datos")
				.onClick({this.registrarDatosWindow()} as Action)


	}

	def cancelarCompraWindow(){
		new CancelarCompraWindow(this, modelObject).open()
		modelObject.add()
		this.close()
	}
	
	def compraConfirmadaWindow(){
		new CompraConfirmadaWindow(this, modelObject).open()
		modelObject.finalizarCompra()
		this.close()
	}
	
	def registrarDatosWindow(){
		modelObject.butacasAComprar
		new RegistrarDatosWindow(this, modelObject).open()
	}
	
}
