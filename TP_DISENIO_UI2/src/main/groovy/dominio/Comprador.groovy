package dominio
import org.uqbar.commons.utils.Observable;

@Observable
class Comprador {
	
	static def ID = 1
	def nombre
	def apellido
	def compras = []
	def id
	def dni
	/*
	 * Inicializador
	 */
	
	def Comprador(nombre, apellido, dni){
		this.nombre = nombre
		this.apellido = apellido
		this.dni = dni
		this.id = ID
		ID++
	}
	
	/*
	 * Metodos Compra
	 */

	def agregarCompra(compra){
		this.compras << compra
	}
	
	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return this.nombre+' '+this.apellido
	}
}
