package aplicationModel

import home.HomeClientes;
import home.HomeEntrada;
import home.HomePlanificaciones;

import java.io.Serializable;


@org.uqbar.commons.utils.Observable
class BuscadorBandasPorCliente implements Serializable{

	static def homeClientes	
	static def homeEntrada
	static def homePlanificaciones 
	def clientes = [] as Set
	def clienteSeleccionado
	def festivales = [] as Set
	def festival
	def bandas = [] as Set
	
	def BuscadorBandasPorCliente(){
		super()
		homeEntrada = HomeEntrada.INSTANCE
		homePlanificaciones = HomePlanificaciones.INSTANCE
		homeClientes = HomeClientes.INSTANCE		
	}
	
	def search(){
		// WORKAROUND para que refresque la grilla en las actualizaciones
		festivales = []
		bandas = []
		// FIN WORKAROUND
		festivales = homePlanificaciones.getAll()
	}
	
	def searcClientes(){
		if(festival != null){
			//clientes = homeEntrada.getClientes(festival.nombreFestival)
			clientes = homeClientes.getClientes(festival.nombreFestival)			
		}
	}
	
	def searchBandas(){
		bandas = homeEntrada.getBandas(clienteSeleccionado.dni, festival.nombreFestival)
	}
	
	def clear(){
		clientes = []
		clienteSeleccionado = null
		festivales = [] 
		festival = null
		bandas = []
	    this.search()
	}

}
