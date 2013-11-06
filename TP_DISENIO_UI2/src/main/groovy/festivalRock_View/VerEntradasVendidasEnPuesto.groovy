package festivalRock_View

import java.awt.Color;

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

import aplicationModel.BuscadorPuestoVentas
import dominio.Entrada
import dominio.Planificacion
import dominio.PuestoDeVenta

class VerEntradasVendidasEnPuesto extends SimpleWindow<BuscadorPuestoVentas>{

	
	VerEntradasVendidasEnPuesto(WindowOwner owner){
		super(owner, new BuscadorPuestoVentas())
		modelObject.search()	
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		title = "Entradas Vendidas por Puesto"
		taskDescription = "Ingrese los parametros de busqueda"

		super.createMainTemplate(mainPanel)

		this.createResultsGrid(mainPanel)
		this.createGridActions(mainPanel)
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
		def table = new Table<Entrada>(mainPanel, Entrada.class)
		table.with {
			heigth = 200
			width = 600
			bindItemsToProperty("resultados")
			
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
	protected void describeResultsGrid(Table<Entrada> table) {
		new Column<Entrada>(table) //
				.setTitle("Cliente")
				.setFixedSize(150)
				.bindContentsToProperty("comprador")

		new Column<Entrada>(table) 
				.setTitle("Nro. Entrada")
				.setFixedSize(50)
				.bindContentsToProperty("id")
		
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

	/**
	 * El panel principal de búsuqeda permite filtrar por número o nombre
	 */
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel)
		searchFormPanel.setLayout(new ColumnLayout(2))
		
		new Label(searchFormPanel).setText("Festival: ")
		Selector<BuscadorPuestoVentas> unSelector1 = new Selector<BuscadorPuestoVentas>(searchFormPanel).allowNull(false);
		unSelector1.bindValueToProperty("festival");
		unSelector1.bindItemsToProperty("festivales")
		
		new Label(searchFormPanel).setText("Puesto de Venta");
		Selector<BuscadorPuestoVentas> unSelector = new Selector<PuestoDeVenta>(searchFormPanel).allowNull(false);
		unSelector.bindValueToProperty("id");
		unSelector.bindItemsToProperty("ids")
//		new Label(searchFormPanel).with {
//			text = "Puesto de Venta"
//			foreground = Color.BLUE
//		}
//		new TextBox(searchFormPanel).bindValueToProperty("id")
	}
	
	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel)
		actionsPanel.setLayout(new HorizontalLayout())

		new Button(actionsPanel) //
				.setCaption("Cerrar")
				.onClick({ this.close() } as Action)
	}
		
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
				.setCaption("Buscar")
				.onClick({ modelObject.search() } as Action)
				.setAsDefault()
				.disableOnError()

		new Button(actionsPanel) //
				.setCaption("Limpiar")
				.onClick({ modelObject.clear() } as Action)
	}
}
