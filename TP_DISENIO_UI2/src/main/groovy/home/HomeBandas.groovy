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
		bandas << new Banda("Airbourne", 1, 40 ) //id 6
		bandas << new Banda("Motorhead", 2, 10 ) //id 7
		bandas << new Banda("Dream Evil", 3, 10 ) //id 8
		bandas << new Banda("Great White", 4, 35 ) //id 9
		bandas << new Banda("Carajo", 1, 25 ) //id 10
		bandas << new Banda("Rata Blanca", 2, 50 ) //id 11
		bandas << new Banda("Catupecu Machu", 3, 15 ) //id 12
		bandas << new Banda("Guazones", 4, 10 ) //id 13
		bandas << new Banda("Los Tipitos", 1, 14 ) //id 14
		bandas << new Banda("Dos Minutos", 2, 20 ) //id 15
		bandas << new Banda("Kapanga", 3, 25 ) //id 16
		bandas << new Banda("Rush", 4, 30 ) //id 17
		bandas << new Banda("Dokken", 1, 60 ) //id 18
		bandas << new Banda("Ratt", 2, 40 ) //id 19
		bandas << new Banda("Rev Theory", 3, 45 ) //id 20
		
	}
	
	def get(id){
		return this.bandas.find{ it.id == id }
	}
	
	def getFromMToN(m,n){
		def bandasReturn = this.bandas.toList()
		def ret = [] as Set
		for(m; m < n; m++){
			ret  << bandasReturn.get(m)
		}
		return ret
	}
	
	def getAll(){
		return this.bandas
	}
	
}
