package domain

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Complejidad")
class Media extends Complejidad{
	
	public def getCosto(tiempo){
		def anterior = new Facil().getCosto(tiempo)
		return  anterior + anterior * 0.05  
	}

	@Override
	public def getDiasAtraso(def tiempo) {
		return tiempo * 0.1
	}

}