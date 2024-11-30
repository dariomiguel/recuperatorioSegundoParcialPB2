package ar.edu.unlam.pb2;

public class Estudiante extends Usuario {
	
	public Estudiante(String nombre, Integer dni) {
		super(nombre, dni);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer cantidadDeVecesQuePidioLibros() {
		return cantidadDeLibrosEnPoder;
	}

	@Override
	public Void sumarLibroPrestado() {
		this.cantidadDeLibrosEnPoder ++;
		return null;
		
	}

	@Override
	public Integer tipoDeUsuario() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	

}
