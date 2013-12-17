package domain

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;

@Entity
@Table(name="Complejidad")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
abstract class Complejidad {

	@Id
	@GeneratedValue
	private Long Id
	
	@Column
	String descripcion
	
	
	public abstract def getCosto(tiempo)
	
	public abstract def getDiasAtraso(tiempo)
	
}