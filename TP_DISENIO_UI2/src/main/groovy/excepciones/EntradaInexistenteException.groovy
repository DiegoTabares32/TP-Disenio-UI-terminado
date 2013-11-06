package excepciones

class EntradaInexistenteException extends RuntimeException {

	public String getMessage() {
		return "id inexistente"
	}
}
