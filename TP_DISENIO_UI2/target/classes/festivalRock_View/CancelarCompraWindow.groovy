package festivalRock_View

import dominio.Butaca

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action

class CancelarCompraWindow extends SimpleWindow<Butaca>{

	public CancelarCompraWindow(owner, model) {
		super(owner, model)
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel) //
				.setCaption("Cerrar")
				.onClick({ this.close() } as Action)
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		title = "Cancelacion"
		new Label(mainPanel).setText("Su compra ha sido cancelada con exito.")
		/*mainPanel.with {
		 layout = new VerticalLayout()
		 new Label(it).with { //
		 text = "Gracias por su compra!" //
		 }
		 }*/
	}

}
