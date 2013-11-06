package dominio

import org.uqbar.commons.utils.Observable;

@Observable
class PuestoDeVenta {
	
	def id
	def entradasVendidas = [] as Set
	def compras = [] as Set
	
	def PuestoDeVenta(def id){
		this.id = id	
	}
	
	def agregarEntradaVendida(def entrada){
		this.entradasVendidas << entrada
	}
	
	def agregarEntradas(def entradas){
		this.entradasVendidas.addAll(entradas)
	}
	
	def agregarCompra(Compra compra){
		this.compras << compra
		this.agregarEntradas(compra.entradasCompradas)
	}
	
	@Override
	def String toString(){
		return this.id
	}
}

