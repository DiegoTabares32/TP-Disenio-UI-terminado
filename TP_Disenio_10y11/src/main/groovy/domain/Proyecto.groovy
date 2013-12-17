package domain

import javax.persistence.*


@Entity
@Table(name = "Proyecto")
class Proyecto {
	
	@Id
	@GeneratedValue
	private Long Id
	
	@OneToMany
	@JoinColumn(name="Id_Proyecto")
	private Set<Tarea> tareas;
	
	public Proyecto(){
		super()
		this.tareas = new ArrayList<Tarea>()
	}
	
	public def getCostoTotal(){
		return tareas.sum { t -> t.getCostoTotal() }
	}
	
	public void addTarea(Tarea tarea){
		this.tareas.add(tarea)
	}

	public def getMaxDiasDeAtraso(){
		return this.tareas.sum{ t -> t.getMaxDiasDeAtraso() }
	}
	
	public def getCompletitud(){
		return this.tareas.sum { t -> t.getCompletitud }
	}
	
	public def getTiempoTotal(){
		return this.tareas.sum{ t-> t.getTiempoTotal()}
	}
	
}