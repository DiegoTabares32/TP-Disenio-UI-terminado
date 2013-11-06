package home

import dominio.Banda

class HomeBandas {

	static def INSTANCE = getInstance()
	def bandas = [] as Set
	
	private HomeBandas(){
		super()
		this.init()
	}
	
	static def HomeBandas getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomeBandas()
		}
		return INSTANCE
	}
	
	def init(){
		bandas << new Banda("Metallica", 1, 30)// id 1
		bandas << new Banda("Iron Maiden", 2, 25 ) //id 2
		bandas << new Banda("Pantera", 3, 35 ) //id 3
		bandas << new Banda("Dream Theater", 4, 50 ) //id 4
		bandas << new Banda("Skid Row", 2, 15 ) //id 5
		bandas << new Banda("AC/DC", 1, 40 ) //id 6
	}
	
	def get(id){
		return this.bandas.find{ it.id == id }
	}
	
	def getAll(){
		return this.bandas
	}
}
