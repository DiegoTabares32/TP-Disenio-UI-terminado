package domain

import javax.persistence.Entity;

@Entity
class Facil extends Complejidad{

	public Facil(){
		super()
		this.id = "facil"
	}
	
	
	public def getCosto(def tiempo){
		return tiempo * 25
	}

	@Override
	public def getDiasAtraso(def tiempo) {
		return 5
	}
}
