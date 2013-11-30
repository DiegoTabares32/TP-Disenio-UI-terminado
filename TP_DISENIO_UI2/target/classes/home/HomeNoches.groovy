package home

import dominio.Noche

class HomeNoches {

	static def homeButacas //= HomeButacas.INSTANCE
	static def homeBandas // = HomeBandas.INSTANCE
	static def INSTANCE = getInstance()
	def noches = [] as Set
//	def butacasAComprar = [] as Set

	private HomeNoches(){
		super()
		homeButacas = HomeButacas.INSTANCE
		homeBandas = HomeBandas.INSTANCE
		this.init()
	}

	static def HomeNoches getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomeNoches()
		}
		return INSTANCE
	}

	def init(){
		noches << new Noche("23/05/2013", homeButacas.getAll(), homeBandas.getFromMToN(0,6)) //id 1
		noches << new Noche("24/05/2013", homeButacas.getAll(), homeBandas.getFromMToN(0,6)) //id 2
		noches << new Noche("25/05/2013", homeButacas.getAll(), homeBandas.getFromMToN(0,6)) //id 3
		noches << new Noche("26/05/2013", homeButacas.getAll(), homeBandas.getFromMToN(0,6)) //id 4
		noches << new Noche("27/05/2013", homeButacas.getAll(), homeBandas.getFromMToN(0,6)) //id 5
		noches << new Noche("28/05/2013", homeButacas.getAll(), homeBandas.getFromMToN(0,6)) //id 6
		noches << new Noche("1/11/2013", homeButacas.getAll(), homeBandas.getFromMToN(6,10)) //id 7
		noches << new Noche("2/11/2013", homeButacas.getAll(), homeBandas.getFromMToN(6,10)) //id 8
		noches << new Noche("3/11/2013", homeButacas.getAll(), homeBandas.getFromMToN(6,10)) //id 9
		noches << new Noche("4/11/2013", homeButacas.getAll(), homeBandas.getFromMToN(6,10)) //id 10
		noches << new Noche("5/11/2013", homeButacas.getAll(), homeBandas.getFromMToN(6,10)) //id 11
		noches << new Noche("10/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(10,16)) //id 12
		noches << new Noche("11/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(10,16)) //id 13
		noches << new Noche("12/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(10,16)) //id 14
		noches << new Noche("13/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(10,16)) //id 15
		noches << new Noche("14/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(10,16)) //id 16
		noches << new Noche("15/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(10,16)) //id 17
		noches << new Noche("6/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(16,20)) //id 18
		noches << new Noche("7/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(16,20)) //id 19
		noches << new Noche("8/10/2013", homeButacas.getAll(), homeBandas.getFromMToN(16,20)) //id 20		
	}

	def getFechas(){
		return this.noches.collect{ it.fecha }.unique(false)
	}

	def search(fecha, contrasenia){
		//butacas de esa fecha
		def nochesR = this.noches.findAll{ it -> it.fecha == fecha}
		def butacas = [] as Set
		if(nochesR != null){
			for(noche in nochesR){
				butacas.addAll(noche.butacas)
			}
			return butacas.findAll{it -> it.contrasenia == contrasenia}
		}
	}

//	def remove(fecha, butacaARemover){
//		def nochesR = []
//		nochesR = noches.findAll{it.fecha == fecha}
//		def nocheRR = nochesR.find{it.butacas.contains(butacaARemover)}
//		noches.remove(nocheRR)
//		butacasAComprar << nocheRR
//	}
//	
//	def add(){
//		noches.addAll(butacasAComprar)
//	}
	
//	def finalizarCompra(){
//		butacasAComprar = []
//	}
	
	def get(def id){
		return this.noches.find{noche -> noche.id == id}
	}
}
