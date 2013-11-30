package aplicationModel

import dominio.Comprador
import dominio.Espectador
import home.HomeClientes;
import home.HomePuestos;

import mediosDePago.Efectivo
import mediosDePago.Tarjeta
import org.uqbar.commons.utils.Observable;

@Observable
class CompradorEntradas {

	def error = "                          "
	def homePuestos
	def homeClientes
	def puestos
	def puesto
	def planificacion
	def fecha //(es la noche)
	def butaca
	def comprador
	def espectador
	def numeroTarjeta
	def medioDePago
	def nombre 
	def apellido
	def dni
	
	def esVip
	
	def CompradorEntradas(BuscadorButacas buscadorButacas, bool){
		planificacion = buscadorButacas.planificacion
		fecha = buscadorButacas.fecha
		butaca = buscadorButacas.butacaSeleccionada
		homePuestos = HomePuestos.INSTANCE
		puestos = homePuestos.getIds()
		medioDePago = new Efectivo()
		esVip = bool
		homeClientes = HomeClientes.INSTANCE
	}
	
	class PaymentGateway {
		def pagar(precioFinal, nombre, apellido, numero) {
		  //compro bien
		}
	}
	
	def comprar(){
		if(numeroTarjeta != null){
			medioDePago = new Tarjeta(numeroTarjeta, new PaymentGateway())
		}
		espectador = new Espectador( nombre, apellido)	
		comprador = new Comprador(nombre, apellido, dni)		
		
		if(esVip){			
			planificacion.comprarEntradaVip(butaca, espectador, comprador, medioDePago, puesto)
		}else{
			planificacion.comprarEntrada(fecha, butaca, espectador, comprador, medioDePago, puesto)
		}
		
	}	
	
	def error(def message){
		this.error = message
	}	
	
}
