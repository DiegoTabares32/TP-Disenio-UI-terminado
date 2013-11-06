package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Ubicacion {
	
	def static ID = 1
	
	def id
	def sector
	def fila
	
	def Ubicacion(def sector, def fila){
		this.sector = sector
		this.fila = fila
		this.id = ID
		ID++
	}
	
	def calcularPrecioBase(){
		//TODO Valor harcodeado, la tipica clase PONELE! 
		return fila*3
	}
	
	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return "Sector: "+ this.sector+" Fila: "+this.fila
	}
}
