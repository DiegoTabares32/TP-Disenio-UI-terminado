package aplicationModel

import excepciones.EntradaInexistenteException
import home.HomeEntrada;

import org.uqbar.commons.utils.Observable;

@Observable
class CanceladorEntradas {

	def idEntrada
	static def homeEntradas
	def error
	
	def CanceladorEntradas(){
		super()
		homeEntradas = HomeEntrada.INSTANCE	
	}
	
	def cancelarEntrada(){
		error = ""
		def entradaACancelar
		try{		
			entradaACancelar = homeEntradas.get(idEntrada)
		}catch(NumberFormatException){
			error = "Ingrese solo numeros"
		}
		if(entradaACancelar != null){
			entradaACancelar.deshacerCompra()
			homeEntradas.remove(entradaACancelar)
			
		}else{
			throw new EntradaInexistenteException()
			
		}	
		
	}

	def error(mensaje){
		this.error = mensaje
	}	
}

