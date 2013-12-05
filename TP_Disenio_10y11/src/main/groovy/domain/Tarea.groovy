package domain

import javax.persistence.*

import org.eclipse.xtend.lib.Property;


@MappedSuperclass
abstract class Tarea {
	
	@Column
	@Property
	protected def tiempo;
	
	@OneToOne
	@Column
	@Property
	protected Complejidad complejidad
	
	@Column
	@Property	
	protected def completitud
	
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

}
