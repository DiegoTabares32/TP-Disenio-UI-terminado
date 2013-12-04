package domain

class Facil implements Complejidad{

	public def getCosto(def tiempo){
		return tiempo * 25
	}

	@Override
	public def getDiasAtraso(def tiempo) {
		return 5
	}
}
