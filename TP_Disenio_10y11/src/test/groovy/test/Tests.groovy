package test;

import static org.junit.Assert.*
import static persistence.ModelManager.*

import org.junit.After;
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
import persistence.HomeProyecto

class Tests {

	private Proyecto proyecto
	private Complejidad complejidadFacil
	private Complejidad complejidadMedia
	private Complejidad complejidadCompleja
	private Tarea tareaSimple1
	private Tarea tareaSimple2
	private Tarea tareaSimple3
	private Tarea tareaSimple4
	private Tarea tareaCompuesta	
	private HomeProyecto homeProyecto = new HomeProyecto();
	
	
	@Before
	public void initialize() {
		beginTransaction();
			for (int i = 0; i < 10; i++) {
					Proyecto jugador = new Proyecto();
					homeProyecto.save(jugador);
			}
	}
	
	@Before
	public void init(){
		this.proyecto = new Proyecto()
		this.complejidadCompleja = new Compleja()
		this.complejidadFacil = new Facil()
		this.complejidadMedia = new Media()
		this.tareaSimple1 = new TareaSimple(complejidadFacil, 0.4)
		this.tareaSimple2 = new TareaSimple(complejidadMedia, 0.6)
		this.tareaSimple3 = new TareaSimple(complejidadCompleja, 0.2)
		this.tareaSimple4 = new TareaSimple(complejidadCompleja, 0.3)
		this.tareaCompuesta = new TareaCompuesta()
		this.tareaCompuesta.addTarea(tareaSimple1)
		this.tareaCompuesta.addTarea(tareaSimple2)
		this.tareaCompuesta.addTarea(tareaSimple3)
		this.proyecto.addTarea(tareaCompuesta)
		this.proyecto.addTarea(tareaSimple3)
	}
	

	
	@Test
	public void testAllProyectos() {
			assertEquals(10, homeProyecto.all().size());
	}

	@Test
	public void testGetCostoTotalProyecto() {
		assertEquals(this.proyecto.getCostoTotal(), 419.00, 0)
		
	}
	
	@Test 
	public void testGetMaxDiasAtraso(){
		assertEquals(this.proyecto.getMaxDiasDeAtraso(), 23.0, 0)
	}
	
	@Test //revisar
	public void testGetCompletitud(){
		assertEquals(proyecto.getCompletitud(), 1.6, 0)
	}

	@Test
	public void testGetTiempoTotal(){
		assert(proyecto.getTiempoTotal() == 16)
	}
	
	//@Test
	public void etTareasConMasDeDosSubtareas(){
		def resultados = homeProyecto.get(proyecto)
		assertEquals(resultados.size(), 1)
	}
	
	//@Test
	public void testGetTareasMenoresADiezDias(){
		def resultados = homeProyecto.getTareasMenoresADiezDias(proyecto)
		assertEquals(resultados.size(), 4)
	}
	
	@After
	public void end(){
		rollback()
	}
	
	
	
}