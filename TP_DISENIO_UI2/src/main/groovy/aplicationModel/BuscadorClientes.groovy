package aplicationModel

import home.HomeClientes

@org.uqbar.commons.utils.Observable
class BuscadorClientes implements Serializable{
	
	static def homeClientes = HomeClientes.INSTANCE
	def resultados = [] as Set
	def nombre
	def apellido
	def fechaInicio
	def fechaCompra
	def fechaFin
	def clienteSeleccionado
	def entradasCliente
	
	def search(){
		// WORKAROUND para que refresque la grilla en las actualizaciones
		resultados = []
		// FIN WORKAROUND
		resultados = homeClientes.getAll()
	}
	
	def filtrarEntradas(){
				
		entradasCliente = []
		//							tiene que devolver las entradas
		try{
			entradasCliente = homeClientes.filtrarPorFecha(clienteSeleccionado.compras, fechaInicio, fechaFin)
		}catch(NullPointerException){
			entradasCliente = []
		}
		
	}
	
	def clear(){
		this.nombre = null
		this.apellido = null
		this.fechaInicio = null
		this.fechaFin = null
		this.entradasCliente = [] 
		this.search()
	}

}
