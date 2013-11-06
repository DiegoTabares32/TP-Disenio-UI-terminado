package home

import dominio.Banda
import dominio.Compra
import dominio.Comprador
import dominio.Entrada
import dominio.Espectador

class HomeClientes {

	static def homeUbicaciones //= HomeUbicaciones.INSTANCE
	static def homeButacas //= HomeButacas.INSTANCE
	static def homeNoches //= HomeNoches.INSTANCE
	static def INSTANCE = getInstance()
	def clientes = [] as Set

	private HomeClientes(){
		super()
		homeUbicaciones = HomeUbicaciones.INSTANCE
		homeButacas = HomeButacas.INSTANCE
		homeNoches = HomeNoches.INSTANCE
		//this.init()
	}

	static def HomeClientes getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomeClientes()
		}
		return INSTANCE
	}

	def getIds(){
		def allIDs = clientes.collect{ it.id}
		return allIDs
		//return allIDs.unique(false)
	}

	def init(){

		def banda1 = new Banda("Radiohead", 4, 200)

		def cliente1 = new Comprador("Juan", "Perez")
		def cliente2 = new Comprador("John", "Smith")
		//def cliente3 = new Comprador("Juana", "Gonzalez")
		//def cliente4 = new Comprador("Jane", "Doe")

		def espectador1 = new Espectador('Jonas', 'Castillo', 23, 'Masculino')
		def espectador2 = new Espectador('Melina', 'Miranda', 23, 'Femenino')
		def espectador3 = new Espectador("Juan", "Perez", 25, 'Masculino')

		def entrada1 = new Entrada(cliente1, espectador1, homeUbicaciones.get(1), homeButacas.get(1), banda1, homeNoches.get(1))
		def entrada2 = new Entrada(cliente1, espectador2, homeUbicaciones.get(2), homeButacas.get(2), banda1, homeNoches.get(2))
		def entrada3 = new Entrada(cliente1, espectador3, homeUbicaciones.get(3), homeButacas.get(3), banda1, homeNoches.get(3))
		def entrada4 = new Entrada(cliente1, espectador1, homeUbicaciones.get(6), homeButacas.get(6), banda1, homeNoches.get(4))

		def compra1 = new Compra()
		def compra2 = new Compra()
		def compra3 = new Compra()

		compra1.fechaCompra = Date.parse( "yyyy-MM-dd", "2012-05-09" )
		compra1.entradasCompradas <<  [entrada1, entrada2]
		compra2.fechaCompra = Date.parse( "yyyy-MM-dd", "2013-08-08" )
		compra2.entradasCompradas << entrada3
		compra3.fechaCompra = Date.parse( "yyyy-MM-dd", "2013-06-17" )
		compra3.entradasCompradas << entrada4

		cliente1.compras << compra1
		cliente1.compras << compra2
		cliente2.compras << compra3

		clientes << cliente1
		clientes << cliente2

	}

	def get(id){
		return this.clientes.find{ cliente -> String.valueOf(cliente.id) == id}
	}
	
	def add(comprador){	
		def clientela = this.clientes.find{ it -> it.dni == comprador.dni }
		try{
			clientela.compras.addAll(comprador.compras)
		}catch(NullPointerException)
		{
			this.clientes << comprador
		}
		
		
	} 
	
	def getAll(){
		return this.clientes
	}
	
	def search(nombre, apellido){
//		def cliente
//		cliente = this.clientes.find{ it -> it.nombre == nombre && it.apellido == apellido}
//		if (cliente != null)
//			cliente.compras
	}

	def getClientes(nombreFestival){
		return this.clientes.findAll{ c -> c.compras.any{ co -> co.entradasCompradas.any{ e -> e.nombreFestival == nombreFestival} } }
	}
	
	def filtrarPorFecha(compras,String fechaInicio, String fechaFin){
			def comprasRet = []as Set
			
			
			def inicio = fechaInicio.replace('/', '')
			def fin = fechaFin.replace('/', '')
			def begin = Integer.valueOf(inicio)
			def end = Integer.valueOf(fin)
			def fechaCompraAux
			def fecha
			for(c in compras){
				fechaCompraAux = c.fechaCompra.replace('/', '')
				 fecha = Integer.valueOf(fechaCompraAux) 
				if(fecha <= end && fecha >= begin)
				{
					comprasRet << c
				}
			}
//			comprasRet = compras2.findAll{ compra -> 
//				 c = compra.fechaCompra.replace('/', '')
//				 fecha = Integer.valueOf(c) 
//				fecha <= end && fecha >= begin}
			def entradasRet = comprasRet.collect{ compra -> compra.entradasCompradas}
			def retornoEntradas = [] as Set
			for(entrada in entradasRet){
				for(e in entrada){
					retornoEntradas << e
				}
			}
			return retornoEntradas
	
//		if (fechaInicio != null & fechaFin != null){
//			def comprasSinFiltrar = []
//			comprasSinFiltrar = this.search(nombre, apellido)
//			def comprasFiltradas = []
//			def fechaInicioParse = Date.parse( "dd/MM/yyyy", fechaInicio )
//			def fechaFinParse = Date.parse( "dd/MM/yyyy", fechaFin )
//			comprasFiltradas = comprasSinFiltrar.findAll{it -> it.fechaCompra > fechaInicioParse && it.fechaCompra < fechaFinParse}
//			//return comprasFiltradas.sort()
//		}
	}
}
