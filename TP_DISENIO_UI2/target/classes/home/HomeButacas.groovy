package home

import dominio.Butaca

class HomeButacas {

	static def homeUbicaciones //= HomeUbicaciones.INSTANCE
	static def INSTANCE = getInstance()
	def butacas = [] as Set
	def butacaSeleccionada
	
	private HomeButacas(){
		super()
		homeUbicaciones = HomeUbicaciones.INSTANCE
		this.init()
	}
	
	static def HomeButacas getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomeButacas()
		}
		return INSTANCE
	}
	
	def init(){
		def butaca = new Butaca(1, homeUbicaciones.get(1), null)// id 1
		butaca.setVip(true)
		butacas << butaca
		butaca = new Butaca(2, homeUbicaciones.get(2), null)// id 2
		butaca.setVip(true)
		butacas << butaca
		butaca = new Butaca(3, homeUbicaciones.get(3), null)// id 3
		butaca.setVip(true)
		butacas << butaca		
		butacas << new Butaca(4, homeUbicaciones.get(4), null)// id 4
		butacas << new Butaca(5, homeUbicaciones.get(5), null)// id 5
		butacas << new Butaca(6, homeUbicaciones.get(6), "87897")// id 6
		butacas << new Butaca(7, homeUbicaciones.get(7), "87897")// id 7
		butacas << new Butaca(8, homeUbicaciones.get(8), "87897")// id 8
		
		
	}
	
	def get(def id){
		return this.butacas.find{butaca -> butaca.id == id}
	}
	
	def getAll(){
		return this.butacas
	}
	
}
