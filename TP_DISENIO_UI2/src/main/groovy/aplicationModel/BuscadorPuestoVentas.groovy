package aplicationModel

import home.HomePlanificaciones;
import home.HomePuestos;

@org.uqbar.commons.utils.Observable
class BuscadorPuestoVentas implements Serializable {
	
	static def homePuestos 
	def resultados = [] as Set
	def id 
	def ids 
	static def homePlanificaciones 
	def festivales = [] as Set 
	def festival
	
	def BuscadorPuestoVentas(){
		super()
		homePuestos = HomePuestos.INSTANCE
		ids = homePuestos.getIds() 
		homePlanificaciones = HomePlanificaciones.INSTANCE
		festivales = homePlanificaciones.getAll()
	}
	
	
	def search(){
		// WORKAROUND para que refresque la grilla en las actualizaciones
		resultados = []
		// FIN WORKAROUND
		try{
			resultados = homePuestos.search(id.id, festival.nombreFestival)
			
		}catch(NullPointerException){
			resultados = []
		}
	}
	
	def clear(){
		this.id = null
		this.festival = null
		this.search()
	}
}