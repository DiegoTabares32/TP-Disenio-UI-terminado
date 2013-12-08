package persistence

import static persistence.ModelManager.*
import domain.Proyecto

public class HomeProyecto {

		public void save(Proyecto proyecto) {
				getManager().persist(proyecto);
		}
			
		public List<Proyecto> all() {
				return getManager().createQuery("from Proyecto").getResultList();
		}

}
