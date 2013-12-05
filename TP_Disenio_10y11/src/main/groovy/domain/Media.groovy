package domain

import javax.persistence.Entity;

@Entity
class Media extends Complejidad{
	
	public Media(){
		super()
		this.id = "media"
	}
	
	public def getCosto(def tiempo){
		def anterior = new Facil().getCosto(tiempo)
		return  anterior + anterior * 0.05  
	}

	@Override
	public def getDiasAtraso(def tiempo) {
		return tiempo * 0.1
	}

}
