package festivalRock_View

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.Action

import dominio.Planificacion

class BuscadorWindow extends MainWindow<Planificacion>{
		
	BuscadorWindow(){
		
		super(new Planificacion());
	}
	
	@Override
	public void createContents(Panel mainPanel) {
		
		mainPanel.with {
			title = "Bienvenido a la app de \\m/ Festival de Rock \\m/"
			layout = new VerticalLayout()
		}		
		
		new Label(mainPanel).setText("¿Qué desea hacer?")
		Panel aPanel = new Panel(mainPanel);
		aPanel.setLayout(new HorizontalLayout());
		this.addActions(aPanel)
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
				.setCaption("Buscar Vip")
				.onClick({this.vip()} as Action)	

		new Button(actionsPanel) //
				.setCaption("Comprar butacas")
				.onClick({ this.normal() } as Action)
				
		new Button(actionsPanel)
				.setCaption("Entradas Vendidas en Puesto")
				.onClick({ this.entradasVendidasEnUnPuesto()} as Action)
				
		new Button(actionsPanel)
				.setCaption("Filtrar entradas por cliente")
				.onClick({this.filtrarEntradas()} as Action)
		
		new Button(actionsPanel)
				.setCaption("Cancelar Entrada")
				.onClick({this.cancelarEntrada()} as Action)
				
		new Button(actionsPanel)
				.setCaption("Bandas Participes")
				.onClick({ this.bandasParticipantes() } as Action)
				   
		new Button(actionsPanel)
				.setCaption("Bandas Vistas por Cliente")
				.onClick({ this.bandasVistasPorCliente() } as Action)
	}

	def bandasVistasPorCliente(){
		new VerBandasVistasPorClienteWindow(this).open()
	}
	
	def bandasParticipantes(){
		new BandasParticipantesWindow(this).open()
	}
	
	def vip(){
		new BuscarButacaVipWindow(this).open()
	}
	
	def normal(){
		new BuscarButacaWindow(this).open()	
	}
		
	def entradasVendidasEnUnPuesto(){
		new VerEntradasVendidasEnPuesto(this).open()
	}
	
	def filtrarEntradas(){
		new BuscarEntradasPorClienteWindow(this).open()
	}
	
	def cancelarEntrada(){
		new CancelarEntradaWindow(this).open()
	}
	
	static void main(String[] args) {
		new BuscadorWindow().startApplication()		
	}

}

