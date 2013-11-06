package dominio

import excepciones.FechaExcedidaException
import org.uqbar.commons.utils.Observable
				
@Observable class Entrada {

	def id
	static def ID = 1
	
	// ubicacion y banda1 son para jarcodear una entrada y poder setear sus atributos
	def ubicacion
	def banda1
	
	def Entrada(comprador, espectador, ubicacion, butaca, banda, noche){
		this.comprador = comprador
		this.espectador  = espectador
		this.ubicacion = ubicacion
		this.butacas = butaca
		this.banda1 = banda
		this.noches = noche
	
	}
	
	def comprador
	def espectador
	/* Noches y butacas son colecciones, para poder implementar polimorficamente entrada y entradaVip. 
	 * Si es una entrada vip tendra la misma butaca, una por cada noche del recital.
	 * Si es una entrada comun tendra una sola butaca.*/
	def butacas = []
	def noches = []
	def fechaCompra
	def descuentosAcumulados
	def nombreFestival

	def Entrada(noches, butacas, comprador, espectador, festival){
		this.butacas = butacas
		this.noches = noches
		this.fechaCompra = new Date().format("dd/MM/yyyy")
		this.comprador = comprador
		this.espectador = espectador
		this.descuentosAcumulados = 0
		this.nombreFestival = festival
		this.id = ID
		ID++
	}

	def getPrecioTotalButaca(){
		def butaca = this.butacas
		return ( butaca.getPrecioBase()  )
	}
	
	def getPrecioTotalPorBanda(){
		def totalAcumulado = 0
		this.noches.each {
			totalAcumulado += it.buscarPrecioMaxCategoria()
		}
		totalAcumulado
	}
	
	def getPrecioSinDescuento(){
		this.getPrecioTotalButaca() + this.getPrecioTotalPorBanda()
	}

	def calcularPrecioConDescuento(){
		this.getPrecioSinDescuento() - this.descuentosAcumulados
	}
	
	def deshacerCompra(){
		
		def fecha = new Date().format("dd/MM/yyyy")
		Date fecha2 = Date.parse("dd/MM/yyyy", fecha)
//		def fechaString = fecha.replace('/', '')
//		def n = this.noches.fecha.replace('/', '')
//		def fhoy = Integer.valueOf(fechaString)
//		def fnoche = Integer.valueOf(n)
		
		def fechaNoche = Date.parse("dd/MM/yyyy", this.noches.fecha)
		fecha.plus(10)
		def valor = fecha2.compareTo(fechaNoche)
		
		if(valor <=0){
			noches.agregarButacaNoReservada(butacas)
		}else{
			throw new FechaExcedidaException()
		}		
//		if((fnoche - fhoy) < 10){
//			throw new FechaExcedidaException()
//		}else{
//			noches.agregarButacaNoReservada(butacas)
//		}
//		
		
	}
	
	def getFecha(){
		Noche noche = this.noches.first()
		return noche.fecha
	}
	
	
	def String toString(){
		return this.butacas	
	}
	
}
