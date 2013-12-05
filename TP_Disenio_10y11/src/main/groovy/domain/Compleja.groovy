package domain

import javax.persistence.Entity;

@Entity
class Compleja extends Complejidad{

	public Compleja(){
		super()
		this.id = "compleja" 
	}
	
	public def getCosto(def tiempo){
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
	public def getDiasAtraso(def tiempo) {
		return tiempo * 0.2 + 8
	}
}
