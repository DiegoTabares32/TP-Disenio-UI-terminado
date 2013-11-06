package aplicationModel



import java.util.List;

import dominio.Noche
import home.HomeBandas;
import home.HomeNoches
import home.HomePlanificaciones;


@org.uqbar.commons.utils.Observable
class BuscadorDeBandas implements Serializable {   

	static def homeNoches = HomeNoches.getInstance()
	static def homeBandas = HomeBandas.INSTANCE
	static def homePlanificaciones = HomePlanificaciones.INSTANCE   
	def resultados = [] as Set
	def nombre
	def nombres = homeBandas.getAll()
	def nombreFestival = "                            "
	
	def search() {
		resultados = []
		
		resultados = homePlanificaciones.getBandasDelFestival(nombre.nombreBanda,this)
	}
	
}
