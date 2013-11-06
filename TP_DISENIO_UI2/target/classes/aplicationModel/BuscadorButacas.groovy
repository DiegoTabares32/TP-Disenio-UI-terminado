package aplicationModel

import javassist.bytecode.stackmap.BasicBlock.Catch;
import dominio.Planificacion;
import home.HomeButacas;
import home.HomeNoches;
import home.HomePlanificaciones;

@org.uqbar.commons.utils.Observable
class BuscadorButacas implements Serializable{

	static def homeButacas = HomeButacas.INSTANCE
	static def homePlanificaciones //= HomePlanificaciones.INSTANCE
	static def homeNoches = HomeNoches.INSTANCE
	def butacas = [] as Set // son las butacas
	def planificaciones = [] as Set
	def planificacion
	def fecha
	def fechas = [] as Set  //= homeNoches.getFechas() 
	def butacaSeleccionada
	def contrasenia = null
	def butacasAComprar = [] as Set
	
//	{	
//		(Planificacion.getInstance()).agregarNoche(homeNoches.get(1))
//		(Planificacion.getInstance()).agregarNoche(homeNoches.get(2))
//		(Planificacion.getInstance()).agregarNoche(homeNoches.get(3))
//		(Planificacion.getInstance()).agregarNoche(homeNoches.get(4))
//		(Planificacion.getInstance()).agregarNoche(homeNoches.get(5))
//	}

	def BuscadorButacas(){
		super()
		homePlanificaciones = HomePlanificaciones.INSTANCE
		planificaciones = homePlanificaciones.getAll()
	}
	
	def search(){
		// WORKAROUND para que refresque la grilla en las actualizaciones
		butacas = []
		// FIN WORKAROUND
	//	resultados = homeNoches.search(fecha, contrasenia)//con esta fecha busca la noche cuya fecha coincida y devuelve las butacas
		try{
			fechas = planificacion.getFechas()
		}catch(NullPointerException)
		{
			fechas = ["--/--/----"] as Set //para q el control quede con el tamaño
		}
		try{
			butacas = planificacion.getButacasNoVip(fecha.fecha)
		}catch(NullPointerException){
			butacas = [] as Set
		}
	//	butacas = (Planificacion.getInstance()).getButacasDisponibles(fecha)
	}
	
	def searchVip(){
		butacas = []
		try{
			fechas = planificacion.getFechas()
		}catch(NullPointerException)
		{
			fechas = ["--/--/----"] as Set
		}
		try{
			butacas = planificacion.getButacasVip(fecha.fecha)
		}catch(NullPointerException){
			butacas = [] as Set
		}
	}

	def remove(){
		homeNoches.remove(fecha, butacaSeleccionada)
		butacasAComprar << butacaSeleccionada
	}

	def add(){
		homeNoches.add()
	}

	def finalizarCompra(){
		homeNoches.finalizarCompra()
		butacasAComprar = []
	}

	def clear(){
		this.fecha = null
		this.contrasenia = null
		this.search()
	}
}
