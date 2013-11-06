package home

import com.sun.org.apache.bcel.internal.generic.RETURN;

import dominio.Banda
import dominio.Butaca
import dominio.Comprador
import dominio.Entrada
import dominio.Espectador
import dominio.Noche
import dominio.Ubicacion

class HomeEntrada {
	
	def entradas = [] as Set
	static def INSTANCE = getInstance()
	
	private HomeEntrada(){
		super()		
		//this.init()
	}

	static def HomeEntrada getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomeEntrada()
		}
		return INSTANCE
	}
	
	def init(){
		def ubicacion = new Ubicacion("azul", 5)
		def butacas = new Butaca(23, ubicacion, null)
		def banda1 = new Banda("Radiohead", 4, 200)
		this.entradas << new Entrada(
						new Comprador('Testa','Ferro'), new Espectador ('Jonas', 'Castillo', 23, 'Masculino'),
						ubicacion, butacas, banda1,	new Noche( '13/09/2013', butacas, banda1))
		this.entradas << new Entrada(
			new Comprador('Juan','Gomez'), new Espectador ('Julio', 'Cruz', 23, 'Masculino'),
			ubicacion, butacas, banda1,	new Noche( '13/09/2013', butacas, banda1))
		this.entradas << new Entrada(
			new Comprador('Tesone','Pablo'), new Espectador ('Gabriela', 'Saballa', 23, 'Masculino'),
			ubicacion, butacas, banda1,	new Noche( '13/09/2013', butacas, banda1))
	}
	
	def add(entrada){
		this.entradas << entrada
	}
	
	def get(id){
		return this.entradas.find{ it -> it.id == Integer.valueOf(id)}
	}
	
	def remove(entradaACancelar){
		this.entradas.remove(entradaACancelar)
	}
	
	def buscar(def fecha){
//		entradas.findAll {
//			entrada -> entrada.getFecha().equals(fecha)
//		}
		return this.entradas
	}
	
//	def getClientes(nombreFestival){
//		def entradasAux = this.entradas.findAll{ e -> e.nombreFestival == nombreFestival}
//		if(entradasAux != null){
//			return entradasAux.collect{ it.comprador}
//		}
//	}
	
	def getBandas(dni, nombreFestival){
		def entradasAux = this.entradas.findAll{ e -> e.comprador.dni == dni && e.nombreFestival == nombreFestival}
		def noches = entradasAux.collect{ it.noches }
		def bandas = [] as Set
		for(noche in noches){
			bandas.addAll(noche.bandas)
		}
		return bandas
	}
}
