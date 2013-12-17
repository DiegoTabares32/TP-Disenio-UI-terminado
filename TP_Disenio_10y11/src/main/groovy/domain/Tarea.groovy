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
	
//	public abstract setTiempo(def tiempo);
//		
//	public abstract setComplejidad(Complejidad complejidad);
//	
	public abstract def getMaxDiasDeAtraso();
	
	public abstract getTiempoTotal();
//	
//	public abstract def getCompletitud();
//	
//	public void setCompletitud(def completitud){
//		this.completitud = completitud
//	}
	
	public def getCostoTotal(){
		return this.complejidad.getCosto(this.tiempo);
	}
	
	public def getCantidadSubtareas(){
		return 0
	}

}