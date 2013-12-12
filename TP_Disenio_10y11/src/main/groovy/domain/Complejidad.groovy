package domain

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
abstract class Complejidad {

	@Id
	def id
	
	public abstract def getCosto(def tiempo)
	
	public abstract def getDiasAtraso(def tiempo)
	
}
