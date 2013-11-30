package dominio

import home.HomeClientes;
import home.HomeEntrada;
import home.HomePuestos;

import org.uqbar.commons.utils.Observable;

import excepciones.ButacaNoEncontradaException


@Observable class Planificacion {
	
	static def homeEntradas
	static def homeClientes
	def nochesConcierto = [] as Set
	def descuentosAplicables = [] as Set
	
	def nombreFestival
	
	def Planificacion(nombre){
		this.nombreFestival = nombre
		homeClientes = HomeClientes.INSTANCE
		homeEntradas = HomeEntrada.INSTANCE
	}
	
//	def getFechas(){
//		return this.nochesConcierto.collect{ noche -> noche.fecha}.unique(false)
//	}
	
	def tieneBanda(nombreBanda){
		return nochesConcierto.any{ n -> n.tieneBanda(nombreBanda)}
	}
		
	def getBandas(){
		def bandas = nochesConcierto.first().bandas
		return bandas//TODO no creo q con eso valla	
	}
	
	def getFechas(){
		return this.nochesConcierto
	}
	
	def getButacasNoVip(fecha){
		def butacas = this.getButacasDisponibles(fecha)
		return butacas.findAll{ butaca -> !(butaca.esVip()) }
	}
	
	def getButacasVip(fecha){
		def butacas = this.getButacasDisponibles(fecha)
		return butacas.findAll{ butaca -> butaca.esVip()}
	}
	
//	static def instance
//	
//		def static synchronized getInstance() {
//			if (!instance) {
//				instance = new Planificacion()				
//			}
//			instance
//		}
	
	/*
	 * Metodos noche
	 */
	
	def agregarNoche(noche){
		nochesConcierto << noche
	}
	
	def getButacasDisponibles(def fecha){
		def nochesR = this.nochesConcierto.findAll{ it -> it.fecha == fecha}
		def butacas = [] as Set
		if(nochesR != null){
			for(noche in nochesR){
				butacas.addAll(noche.butacas)
			}
			return butacas
		}
	}
	
	def butacasVip(){
		def butacasVip = [] as Set
		Noche noche = this.nochesConcierto.first()
		noche.butacas.each {
			if(this.buscarButacaEnTodasLasNoches(it)){
				butacasVip << it
				
			}
			
		}
		return butacasVip
	}
		
	/*
	 * Metodos butaca
	 */
	
	//Este metodo verifica que para todas las noches la butaca seleccionada, este disponible [entrada VIP]
	def buscarButacaEnTodasLasNoches(butaca){
		//FIXME no hagan condicion == true!!!
		def nochesQuePoseenButaca = this.nochesConcierto.findAll{ it.esButacaDisponible(butaca) }
		//FIXME no hagan if condicion return true else return false!!!!!
		return nochesQuePoseenButaca.size() == this.nochesConcierto.size()
	}
	
	/*
	 * Metodos de descuentos
	 */
	def aplicarDescuentos(compra){
		this.descuentosAplicables.each{ it.aplicarDescuento(compra) }
		
	}
	
	/*
	 * Metodos de compra entradas no reservadas.
	 */

	def verificarDisponibilidadButacas(noches,butacas){
		//def butaca = butacas.first()
		def butaca = butacas
		if( noches.size() == 1){
			if( !noches.first().esButacaDisponible( butaca ) )
				false
			else
				true
		}
		else{
			if( !this.buscarButacaEnTodasLasNoches( butaca ) )
				false
			else
				true
		}
	}
	
	//Pensandolo desde una posible interfaz grafica, planificacion recibe
	//el pedido de compra junto con todos los datos necesarios, como colecciones
	//que funcionen como pilas -> hay correspondencia en el orden de las noches
	//las butacas y los espectadores. Estamos trabajando con un sublistado de noches
	//y butacas copia de los listados originales.
	def comprarEntradas(noches, butacas, espectadores, comprador, medioDePago){
		def butacasAux = []
		butacasAux.addAll(butacas)
		def compra = new Compra(new Date())
		
		noches.each{
			if ( this.verificarDisponibilidadButacas( [it], butacasAux.first() ) ){
				butacasAux.remove(butacas.head())
				compra.entradasCompradas << this.generarEntrada( it, butacas.first(), comprador, espectadores.first() )
				butacas.remove( butacas.head() )
				espectadores.remove( espectadores.first() )
			}
			else{
				compra.entradasCompradas.each{
					it.deshacerCompra()
				}
				throw new ButacaNoEncontradaException()
			}
		}
		
		if ( compra.entradasCompradas != null ){
			this.aplicarDescuentos(compra)
			medioDePago.efectuarPago(compra, comprador)
			comprador.agregarCompra(compra)
		}
	}
	
	def comprarEntrada(noche, butaca, espectador, comprador, medioDePago, puestoVenta){
		
		def compra = new Compra(new Date().format("dd/MM/yyyy"))
		compra.entradasCompradas << this.generarEntrada( noche, butaca, comprador, espectador )
		noche.comprarButaca(butaca)
		this.aplicarDescuentos(compra)
		medioDePago.efectuarPago(compra, comprador)
		comprador.agregarCompra(compra)
	//agrego el cliente en el home para la busqueda posterior
		homeClientes.add(comprador)
		puestoVenta.agregarCompra(compra)		
	
	}
	
	def comprarEntradaVip(butaca, espectador, comprador, medioDePago, puestoVenta){
		def compra = new Compra(new Date().format("dd/MM/yyyy"))
	
		for(noche in this.nochesConcierto){
			compra.entradasCompradas << this.generarEntrada(noche, butaca, comprador, espectador)
		}			
		this.aplicarDescuentos(compra)
		medioDePago.efectuarPago(compra, comprador)
		comprador.agregarCompra(compra)
	//agrego el cliente en el home para la busqueda posterior
		homeClientes.add(comprador)
		puestoVenta.agregarCompra(compra)
	
	}
	
	def generarEntrada(noches, butacas, comprador, espectador){
		def entrada = new Entrada(noches, butacas, comprador, espectador, this.nombreFestival)
		noches.each{
			it.sacarButacaNoReservada( butacas )
		}
		homeEntradas.add(entrada)
		entrada
	}
	
	/*
	 * Metodos reservadas
	 */
	
	//ACLARACION 1: las entradas reservadas solo se "compran" para una sola noche a la vez.
	//ACLARACION 2: al generar las entradas reservadas no se incluye la contraseña porque
	//las mismas no se pueden cancelar en ningun momento una vez generadas.
	
	def generarEntradaReservada(noche, butaca, espectadores, contrasenia, comprador){
		if( noche.buscarButacaReservada(butaca, contrasenia) ){
			noche.desbloquearButaca(butaca, contrasenia)
			def entrada = new Entrada(butaca, noche, comprador, espectadores.pop())
		}
	}
	
	def comprarEntradasReservadas(noche, butacasReservadas, espectadores, contrasenia, comprador){
		def compra = new Compra(new Date())
		butacasReservadas.each{
			compra.entradasEspeciales << this.generarEntradaReservada(noche, it, espectadores, contrasenia, comprador)
		}
		comprador.agregarCompra(compra)
	}
	
	def reservarButacas(noche, butacas, contrasenia){
		butacas.each{
			noche.reservarButaca(it, contrasenia)
		}
	}
	
	@Override
	public String toString(){
		return this.nombreFestival
	} 
}
