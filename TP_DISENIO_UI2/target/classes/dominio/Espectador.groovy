package dominio

import groovy.transform.TupleConstructor;
import org.uqbar.commons.utils.Observable;

@Observable
@TupleConstructor
class Espectador {
	
	def nombre
	def apellido
	def edad
	def sexo
	
	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return this.nombre+' '+this.apellido
	}
}
