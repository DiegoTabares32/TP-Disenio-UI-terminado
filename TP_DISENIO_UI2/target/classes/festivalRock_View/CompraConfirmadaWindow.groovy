package festivalRock_View

import dominio.Butaca

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action

class CompraConfirmadaWindow extends SimpleWindow<Butaca>{

	public CompraConfirmadaWindow(owner, model) {
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
		title = "Confirmacion de su compra"
		new Label(mainPanel).setText("Gracias por su compra")
		/*mainPanel.with {
			layout = new VerticalLayout()
			new Label(it).with { //
				text = "Gracias por su compra!" //
			}

		}*/
	}

}
