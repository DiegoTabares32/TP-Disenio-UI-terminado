package domain

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.eclipse.xtend.lib.Property;

@MappedSuperclass
abstract class Complejidad {

	@Id
	@Property
	def id
	
	public abstract def getCosto(def tiempo)
	
	public abstract def getDiasAtraso(def tiempo)
	
}
