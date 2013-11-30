package festivalRock_View

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.ErrorsPanel;
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action
import org.uqbar.ui.view.ErrorViewer;

import excepciones.EntradaInexistenteException;
import excepciones.FechaExcedidaException;
import aplicationModel.CanceladorEntradas;

import aplicationModel.CanceladorEntradas

class CancelarEntradaWindow extends SimpleWindow<CanceladorEntradas>{

//	ErrorsPanel errorPanel
	
	def CancelarEntradaWindow(owner){
		super(owner, new CanceladorEntradas())
		taskDescription = "Ingrese el numero de entrada que desea cancelar"
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
			.setCaption("Ok")
			.onClick({ this.cancelar() } as Action)
		
		new Button(actionsPanel)
			.setCaption("Volver")
			.onClick({ this.close() } as Action)
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		//errorPanel = this.createErrorsPanel(mainPanel)
		
		new Label(mainPanel).setWidth(300).bindValueToProperty("error")
		TextBox txt = new TextBox(mainPanel)
		txt.bindValueToProperty("idEntrada")
	}

	def cancelar(){
		
		//taskDescription = ""
		try{
			modelObject.cancelarEntrada()
			//errorPanel.okMessage = "Entrada Cancelada!"
			modelObject.error("Entrada Cancelada!")
		}catch(EntradaInexistenteException e){
			//informar el error //TODO
			modelObject.error(e.getMessage())			
		}
		catch(FechaExcedidaException e2){
			modelObject.error(e2.getMessage())
		}
		
	}
}
