package festivalRock_View

import aplicationModel.CompradorEntradas


import java.awt.Dialog
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action

class ComprarButacaWindow  extends SimpleWindow<CompradorEntradas>{

	
	
	ComprarButacaWindow(owner, buscadorButacas, bool){
		super(owner, new CompradorEntradas(buscadorButacas, bool))
		taskDescription = "Complete los campos"
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Aceptar")
		.onClick({ this.cerrar(this) } as Action)
		.setAsDefault()
		.disableOnError()
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel)
		
		searchFormPanel.setLayout(new VerticalLayout())
		new Label(searchFormPanel).setWidth(300).bindValueToProperty("error")
		new Label(searchFormPanel).setText("Nombre: ");
		new TextBox(searchFormPanel).bindValueToProperty("nombre")
		new Label(searchFormPanel).setText("Apellido: ");
		new TextBox(searchFormPanel).bindValueToProperty("apellido")
		new Label(searchFormPanel).setText("D.N.I.: ");
		new TextBox(searchFormPanel).bindValueToProperty("dni")
		
		
		new Label(searchFormPanel).setText("Puesto de Venta");
		Selector<CompradorEntradas> selectorPuesto = new Selector<CompradorEntradas>(searchFormPanel).allowNull(false)
		selectorPuesto.bindValueToProperty("puesto")
		selectorPuesto.bindItemsToProperty("puestos")
		
		new Button(mainPanel)
			.setCaption("Paga con tarjeta?")
			.onClick({ this.numeroTarjeta() } as Action)
			
		
	}
	
	def numeroTarjeta(){
		new IngresarTarjeta(this, modelObject).open()
	}
		
	def cerrar(ComprarButacaWindow ventana){
		if(modelObject.nombre == null || modelObject.apellido == null || modelObject.puesto == null || modelObject.dni == null){
			//informar el error
			modelObject.error("Debe completar los campos!!")
		}else{
			//comprar
			modelObject.error( "")
			this.comprar()
			ventana.close()		
		}
	}
	
	def comprar(){
		modelObject.comprar()
	}
}
