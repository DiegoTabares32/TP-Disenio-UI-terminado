package dominio

import org.uqbar.commons.utils.Observable;

import excepciones.ContraseniaNoCoincideException
import groovy.transform.TupleConstructor

@Observable
class Butaca {
	
	static def ID = 1
	
	def esVip 
	def id
	def numeroButaca
	def ubicacion
	def contrasenia
	
	def setVip(booleano){
		this.esVip = booleano
	}
	
	def esVip(){
		return this.esVip
	}
	
	def Butaca(numeroButaca, ubicacion, contrasenia){
		this.numeroButaca = numeroButaca
		this.ubicacion = ubicacion
		this.contrasenia = contrasenia
		this.id = ID
		this.esVip = false
		ID++
	}
	
	def getPrecioBase() {
		this.ubicacion.calcularPrecioBase()
	}
	
	def reservarButaca(contrasenia){
		this.setContrasenia(contrasenia)
	}
	
	def desbloquearButaca(contrasenia){
		if(this.contrasenia != contrasenia)			
			throw new ContraseniaNoCoincideException()
	}

	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return this.numeroButaca+'-'+this.ubicacion
	}
}
