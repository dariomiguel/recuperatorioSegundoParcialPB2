package ar.edu.unlam.pb2;

public abstract class Usuario implements PosibilidadDePedirPrestadoUnLibro {
	
	protected String nombre;
	protected Integer dni;
	protected Integer cantidadDeLibrosEnPoder;
	
	public Usuario(String nombre, Integer dni) {
		
		this.nombre = nombre;
		this.dni = dni;
		this.cantidadDeLibrosEnPoder = 0;
	}
	
	abstract Integer tipoDeUsuario();
}
