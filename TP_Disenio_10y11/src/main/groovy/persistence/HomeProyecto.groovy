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

	def getTareasConMasDeDosSubtareas(Proyecto proyecto){
		def resultados
		EntityManager session = getManager()
		session.open
		try {
			resultados = proyecto.tareas.each{ t ->
				if (t.getCantidadSubtareas() > 2{  masDeDosSubtareas << t  }  }
		} catch(HibernateException e) {
			throw new RuntimeException(e)
		} finally {
			session.close()
		}
		resultados
	}


	def getTareasMenoresADiezDias(Proyecto proyecto){
		def resultados
		EntityManager session = getManager()
		session.open
		try {
			resultados = proyecto.tareas.each{ t ->
				if (t.getTiempoTotal() < 10){
					resultados<< t
				}
			}
		} catch(HibernateException e) {
			throw new RuntimeException(e)
		} finally {
			session.close()
		}
		resultados
	}

}