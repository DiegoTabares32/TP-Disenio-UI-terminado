package dominio

import groovy.transform.TupleConstructor;
import org.uqbar.commons.utils.Observable;

@Observable
@TupleConstructor
class Compra {

	def fechaCompra
	def entradasCompradas = []
	def entradasEspeciales = []
	def descuento = 0

	def calcularPrecioFinal(){
		this.getMontoTotalEntradas() - this.descuento
	}

	def getMontoTotalEntradas(){
		def precioTotal = 0
		this.entradasCompradas.each{precioTotal += it.calcularPrecioConDescuento()}
		precioTotal
	}

	def deshacerCompra(){
		entradasCompradas.each{it.deshacerCompra()}
		entradasCompradas = []
	}

	/*PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {			
		return this.fechaCompra+'-'+ this.entradasCompradas
	}*/

}
