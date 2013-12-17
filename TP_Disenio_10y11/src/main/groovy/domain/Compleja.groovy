package domain

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Complejidad")
class Compleja extends Complejidad{

	
	public def getCosto(tiempo){
		def anterior = tiempo * 25 
		def base = anterior + anterior * 0.07
		def dias = tiempo - 10
		def costo
		if(dias <= 0){
			costo = base
		}else{
			costo = base + 10 * dias
		}
		return costo
	}

	@Override
	public def getDiasAtraso(tiempo) {
		return tiempo * 0.2 + 8
	}
}