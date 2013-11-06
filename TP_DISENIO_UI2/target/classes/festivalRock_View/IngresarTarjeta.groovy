package festivalRock_View

import aplicationModel.CompradorEntradas
import aplicationModel.RegistroDatos

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action

class IngresarTarjeta extends SimpleWindow<CompradorEntradas>{

	def IngresarTarjeta(owner, model){
		super(owner, model)
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
			.setCaption("Ok")
			.onClick({ this.close() } as Action)
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		TextBox txt = new TextBox(mainPanel)
		txt.bindValueToProperty("numeroTarjeta")
	}

}
