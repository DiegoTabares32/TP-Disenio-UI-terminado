package home

import dominio.Ubicacion

class HomeUbicaciones {

	static def INSTANCE = getInstance()
	def ubicaciones = [] as Set
	
	private HomeUbicaciones(){
		super()
		this.init()
	}
	
	static def HomeUbicaciones getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomeUbicaciones()
		}
		return INSTANCE
	}
	
	def init(){
		ubicaciones << new Ubicacion("azul", 5) //id 1
		ubicaciones << new Ubicacion("rojo", 6) //id 2
		ubicaciones << new Ubicacion("azul", 9) //id 3
		ubicaciones << new Ubicacion("verde", 8) //id 4
		ubicaciones << new Ubicacion("verde", 5) //id 5
		ubicaciones << new Ubicacion("rojo", 7) //id 6
		ubicaciones << new Ubicacion("verde", 11) //id 7
		ubicaciones << new Ubicacion("rojo", 4) //id 8
	}
	
	def get(def id){
		return this.ubicaciones.find{ubi -> ubi.id == id}
	}
}
