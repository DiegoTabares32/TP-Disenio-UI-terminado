package festivalRock_View

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action

import aplicationModel.BuscadorDeBandas
import dominio.Banda
import dominio.Noche
import dominio.Planificacion;

class BandasParticipantesWindow extends SimpleWindow<BuscadorDeBandas> {
	
	BandasParticipantesWindow (WindowOwner owner) {
		super(owner, new BuscadorDeBandas())

	//	modelObject.search()
	}
	
	@Override
	public void createContents(Panel mainPanel) { 
		
		Panel panel = new Panel(mainPanel)
		panel.setLayout(new HorizontalLayout())
		new Label(panel).setText("Ingrese los parametros de busqueda")
		
		mainPanel.setLayout(new VerticalLayout())
		
		this.createFormPanel(mainPanel)
	
		
		this.createResultsGrid(mainPanel)	
		
		
		Panel actionsPanel = new Panel(mainPanel)
		actionsPanel.setLayout(new HorizontalLayout())
		this.addActions(actionsPanel)
	}


	
	@Override
	protected void createFormPanel(Panel mainPanel){
		Panel searchFormPanel = new Panel(mainPanel)
		searchFormPanel.setLayout(new ColumnLayout(2))
		
		new Label(searchFormPanel).with {
				text = "Nombre de banda: "
			}
		//new TextBox(searchFormPanel).bindValueToProperty("nombre")
		Selector<BuscadorDeBandas> unSelector = new Selector<Banda>(searchFormPanel).allowNull(false);
		unSelector.bindValueToProperty("nombre");
		unSelector.bindItemsToProperty("nombres")
		
		new Label(searchFormPanel).setText("Festival de esas bandas")
		new Label(searchFormPanel).bindValueToProperty("nombreFestival")
	
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
			
			new Button(actionsPanel)
				.setCaption("Buscar")
				.onClick({ modelObject.search() } as Action)
				
			new Button(actionsPanel)
				.setCaption("Volver")
				.onClick({this.close()} as Action)
	
	}
	
	
	//*****************************************************
	//RESULTADOS DE LA BUSQUEDA
	//*****************************************************
	
	def createResultsGrid (Panel mainPanel) {
		def table = new Table<Banda>(mainPanel, Banda.class)
		table.with {
			heigth = 200
			width = 600
			bindItemsToProperty("resultados")
			//bindValueToProperty()
		}
		this.describeResultsGrid(table)
	}
	
	def describeResultsGrid(Table<Banda> table) {  
		new Column<Banda>(table)
						.setTitle("Nombre")
						.setFixedSize(150)
						.bindContentsToProperty("nombreBanda")											
						
//		new Column<Banda>(table)  
//						.setTitle("Categoria")
//						.setFixedSize(200)
//						.bindContentsToProperty("categoria")
//						
//		new Column<Banda>(table)
//						.setTitle("Precio de Categoria")
//						.setFixedSize(200)
//						.bindContentsToProperty("precioCategoria")
	}


		
}
