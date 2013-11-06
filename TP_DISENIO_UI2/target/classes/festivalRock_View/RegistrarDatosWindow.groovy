package festivalRock_View

import aplicationModel.BuscadorButacas
import aplicationModel.RegistroDatos

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.lacar.ui.impl.jface.radiogroup.RadioGroup
import org.uqbar.lacar.ui.model.Action

class RegistrarDatosWindow extends SimpleWindow<RegistroDatos>{

	public RegistrarDatosWindow(owner, model){
		super(owner, new RegistroDatos())
	}
		
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel)
		searchFormPanel.setLayout(new VerticalLayout())
		new Label(searchFormPanel).setText("Nombre: ");
		new TextBox(searchFormPanel).bindValueToProperty("nombre")
		new Label(searchFormPanel).setText("Apellido: ");
		new TextBox(searchFormPanel).bindValueToProperty("apellido")
		
		new Label(searchFormPanel).setText("Puesto de Venta");
		new TextBox(searchFormPanel).bindValueToProperty("puesto")
		
		new Button(mainPanel)
			.setCaption("Paga con tarjeta?")
			.onClick({ this.numeroTarjeta() } as Action)
			
		
	}
	
	def numeroTarjeta(){
		new IngresarTarjeta(this, modelObject).open()
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}

}
