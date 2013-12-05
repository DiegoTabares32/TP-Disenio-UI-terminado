package domain

import javax.persistence.*

@Entity
class TareaSimple extends Tarea {

	public TareaSimple(Complejidad complejidad , def completitud){
		super()
		this.complejidad = complejidad
		this.tiempo = 4
		this.completitud = completitud
	}
	
//	def setTiempo(def tiempo){
//		this.tiempo = tiempo
//	}
//	
//	def setComplejidad(Complejidad complejidad){
//		this.complejidad = complejidad
//	}

	@Override
	def getMaxDiasDeAtraso() {
		return this.complejidad.getDiasAtraso(this.tiempo)
	}

	@Override
	def getTiempoTotal(){
		return this.tiempo
	}
	
//	@Override
//	def getCompletitud() {
//		return completitud
//	}
	
}
