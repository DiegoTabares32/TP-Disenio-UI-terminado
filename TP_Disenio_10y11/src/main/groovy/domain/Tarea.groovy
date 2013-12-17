package domain

import javax.persistence.*



@Entity
@Table(name="Tarea")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
abstract class Tarea {
	
	@Id
	@GeneratedValue
	private Long id
	
	@Column
	protected Long tiempo;
	
	@ManyToOne 
	protected Complejidad complejidad
	
	@Column
	protected Long completitud
	
	public abstract def getMaxDiasDeAtraso();
	
	public abstract def getTiempoTotal();

	public getCompletitud(){
		return completitud
	}

	
	public def getCostoTotal(){
		return this.complejidad.getCosto(this.tiempo);
	}
	
	public abstract def getCantidadSubtareas();

}