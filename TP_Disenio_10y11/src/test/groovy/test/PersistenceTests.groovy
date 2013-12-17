package test;

import static org.junit.Assert.*
import static persistence.ModelManager.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import persistence.HomeProyecto
import domain.Proyecto

class PersistenceTests {

		
        private HomeProyecto homeProyecto = new HomeProyecto();        
        
        @Before
        public void initialize() {
			beginTransaction();
                for (int i = 0; i < 10; i++) {
                        Proyecto jugador = new Proyecto();
                        homeProyecto.save(jugador);
                }
        }
        
        @Test
        public void testAllProyectos() {
                assertEquals(10, homeProyecto.all().size());
        }

		@After
		public void end(){
			rollback()
		}
}