package excepciones

class FechaExcedidaException extends RuntimeException{
	
	public String getMessage() {
		return "Cancelaciones: max 10 dias antes del Festival"
	}		   
}
