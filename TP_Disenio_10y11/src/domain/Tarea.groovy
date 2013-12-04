package domain

abstract class Tarea {
	
	protected def tiempo;
	protected Complejidad complejidad	
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
