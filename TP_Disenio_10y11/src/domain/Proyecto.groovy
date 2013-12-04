package domain

class Proyecto {
	
	private List<Tarea> tareas;
	
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
		return this.tareas.sum { t -> t.completitud }
	}
	
	public def getTiempoTotal(){
		return this.tareas.sum{ t-> t.getTiempoTotal()}
	}
}
