package test;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import domain.Compleja
import domain.Complejidad
import domain.Facil
import domain.Media
import domain.Proyecto
import domain.Tarea
import domain.TareaCompuesta
import domain.TareaSimple

class Tests {

	private Proyecto proyecto
	private Complejidad complejidadFacil
	private Complejidad complejidadMedia
	private Complejidad complejidadCompleja
	private Tarea tareaSimple1
	private Tarea tareaSimple2
	private Tarea tareaSimple3
	private Tarea tareaCompuesta	
	
	@Before
	public void init(){
		this.proyecto = new Proyecto()
		this.complejidadCompleja = new Compleja()
		this.complejidadFacil = new Facil()
		this.complejidadMedia = new Media()
		this.tareaSimple1 = new TareaSimple(complejidadFacil, 0.4)
		this.tareaSimple2 = new TareaSimple(complejidadMedia, 0.6)
		this.tareaSimple3 = new TareaSimple(complejidadCompleja, 0.2)
		this.tareaCompuesta = new TareaCompuesta()
		this.tareaCompuesta.addTarea(tareaSimple1)
		this.tareaCompuesta.addTarea(tareaSimple2)
		this.proyecto.addTarea(tareaCompuesta)
		this.proyecto.addTarea(tareaSimple3)
	}
	
	@Test
	public void testGetCostoTotalProyecto() {
		assertEquals( this.proyecto.getCostoTotal(), 312.00)
		
	}
	
	@Test 
	public void testGetMaxDiasAtraso(){
		assertEquals(this.proyecto.getMaxDiasDeAtraso(), 14.2)
	}
	
	@Test
	public void testGetCompletitud(){
		assertEquals(proyecto.getCompletitud(), 1.2)
	}

	@Test
	public void testGetTiempoTotal(){
		assertEquals(proyecto.getTiempoTotal(), 12)
	}
	
}
