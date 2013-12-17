package persistence

import static persistence.ModelManager.*
import domain.Proyecto
import domain.Tarea
import javax.persistence.EntityManager
import org.hibernate.HibernateException

public class HomeProyecto {


	public void save(Proyecto proyecto) {
		getManager().persist(proyecto);
	}

	public List<Proyecto> all() {
		return getManager().createQuery("from Proyecto").getResultList();
	}

	def get(Proyecto proyecto, condicion){
		def resultados = []
		EntityManager session = getManager()
		session.open
		try {
			resultados = proyecto.tareas.each{ t ->
				if (condicion(t)){
					resultados.add(t)
				}
			}
		} finally {
			session.close()
		}
		resultados
	}

	def getTareasMenoresADiezDias(proyecto){
		this.get(proyecto, {t -> t.getTiempoTotal() < 10})
		 
	}
	
	def getTareasConMasDeDosSubtareas(proyecto){
		this.get(proyecto, {t -> t.getCantidadSubtareas() > 2})
		 
	}
	
}