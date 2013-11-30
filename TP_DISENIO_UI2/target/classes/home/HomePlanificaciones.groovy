package home

import dominio.Planificacion

class HomePlanificaciones {

	static def homeNoches 
	static def INSTANCE = getInstance()
	def planificaciones = [] as Set
	
	private HomePlanificaciones(){
		super()
		homeNoches = HomeNoches.INSTANCE
		this.init()
	}	
	
	static def HomePlanificaciones getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomePlanificaciones()
		}
		return INSTANCE
	}
	
	def init(){
		def planificacion = new Planificacion("Quilmes Rock")
		planificacion.agregarNoche(homeNoches.get(1))
		planificacion.agregarNoche(homeNoches.get(2))
		planificacion.agregarNoche(homeNoches.get(3))
		planificacion.agregarNoche(homeNoches.get(4))
		planificacion.agregarNoche(homeNoches.get(5))
		planificacion.agregarNoche(homeNoches.get(6)) //6
		planificaciones << planificacion
		planificacion = new Planificacion("Personal Fest")
		
		planificacion.agregarNoche(homeNoches.get(7)) //7
		planificacion.agregarNoche(homeNoches.get(8)) //8
		planificacion.agregarNoche(homeNoches.get(9)) //9
		planificacion.agregarNoche(homeNoches.get(10)) //10
		planificacion.agregarNoche(homeNoches.get(11)) //11
		
		planificaciones << planificacion
		planificacion = new Planificacion("Movistar Fest")
		
		planificacion.agregarNoche(homeNoches.get(12)) //12
		planificacion.agregarNoche(homeNoches.get(13)) //13
		planificacion.agregarNoche(homeNoches.get(14)) //14
		planificacion.agregarNoche(homeNoches.get(15)) //15
		planificacion.agregarNoche(homeNoches.get(16)) //16
		planificacion.agregarNoche(homeNoches.get(17)) //17
		
		planificaciones << planificacion
		planificacion = new Planificacion("Cosquin Rock")
		
		
		planificacion.agregarNoche(homeNoches.get(18)) //18
		planificacion.agregarNoche(homeNoches.get(19)) //19
		planificacion.agregarNoche(homeNoches.get(20)) //20
		planificaciones << planificacion
		
	}	
	
	def getAll(){
		return this.planificaciones		
	}
	
	def getBandasDelFestival(nombreBanda, buscadorBandas){
		def p = this.planificaciones.find{ p -> p.tieneBanda(nombreBanda)}
		buscadorBandas.nombreFestival = p.nombreFestival
		return p.getBandas()
	}
}
