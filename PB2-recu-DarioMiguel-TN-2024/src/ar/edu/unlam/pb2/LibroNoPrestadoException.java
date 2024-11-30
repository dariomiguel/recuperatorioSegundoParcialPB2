package ar.edu.unlam.pb2;

public class LibroNoPrestadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LibroNoPrestadoException(String mensaje) {
		super(mensaje);
	}
}
