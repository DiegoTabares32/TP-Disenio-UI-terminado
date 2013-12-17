package domain

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Complejidad")
class Facil extends Complejidad{
	
	
	public def getCosto(def tiempo){
		return tiempo * 25
	}

	@Override
	public def getDiasAtraso(def tiempo) {
		return 5
	}
}