package ar.edu.unlam.pb2;

import java.util.HashMap;

public class GestionBiblioteca {

	private HashMap<String, Libro> gestionLibrosEnStock = new HashMap<>();
	private HashMap<Integer, Usuario> gestionDeUsuarios = new HashMap<>();

	public HashMap<String, Libro> getGestionLibrosEnStock() {
		return gestionLibrosEnStock;
	}

	public HashMap<Integer, Usuario> getGestionDeUsuarios() {
		return gestionDeUsuarios;
	}

	public void setGestionDeUsuarios(HashMap<Integer, Usuario> gestionDeUsuarios) {
		this.gestionDeUsuarios = gestionDeUsuarios;
	}

	public void setGestionLibrosEnStock(HashMap<String, Libro> gestionLibrosEnStock) {
		this.gestionLibrosEnStock = gestionLibrosEnStock;
	}

	public void agregar(Libro libro1) {

		HashMap<String, Libro> gestionDeLibros = this.getGestionLibrosEnStock();

		if (!(gestionDeLibros.containsKey(libro1.getIsbn()))) {
			gestionDeLibros.put(libro1.getIsbn(), libro1);
			this.setGestionLibrosEnStock(gestionDeLibros);
		}

	}

	public Boolean sePuedePrestar(Libro libro1) {

		HashMap<String, Libro> gestionDeLibros = this.getGestionLibrosEnStock();
		Boolean respuesta = false;

		if (gestionDeLibros.containsKey(libro1.getIsbn())) {

			respuesta = libro1.getEstado();

		}

		return respuesta;
	}

	public void prestar(String isbnDeLibroAprestar, Usuario usuario, String fecha)
			throws SeIntentaPrestarMasLibrosDeLoPermitidoException, LibroYaPrestadoException {
		HashMap<String, Libro> gestionDeLibros = this.getGestionLibrosEnStock();
		HashMap<Integer, Usuario> gestionUsuarios = this.getGestionDeUsuarios();
		Usuario usuarioQueLePrestanElLibro = gestionUsuarios.get(usuario.dni);

		if (gestionDeLibros.containsKey(isbnDeLibroAprestar)) {
			if (gestionDeLibros.get(isbnDeLibroAprestar).getEstado()) {
				Libro libroBuscadoPorIsbn = gestionLibrosEnStock.get(isbnDeLibroAprestar);
				if (gestionUsuarios.containsKey(usuario.dni)) {

					if (usuarioQueLePrestanElLibro.cantidadDeLibrosEnPoder < usuario.tipoDeUsuario() ) {
						libroBuscadoPorIsbn.setEstado(false);
						usuario.sumarLibroPrestado();
						gestionDeLibros.put(isbnDeLibroAprestar, libroBuscadoPorIsbn);
						gestionUsuarios.put(usuario.dni, usuarioQueLePrestanElLibro);
					} else {
						throw new SeIntentaPrestarMasLibrosDeLoPermitidoException("No se puede prestar mas libros");
					}

				}
			}else {
				throw new LibroYaPrestadoException("EL libro ya no esta disponible");
			}
		}
	}

	public void agregarEstudiante(Integer dniUsuarioNuevo, Usuario usuarioNuevo) {
		HashMap<Integer, Usuario> gestionUsuarios = this.getGestionDeUsuarios();

		if (!(gestionUsuarios.containsKey(dniUsuarioNuevo))) {
			gestionDeUsuarios.put(dniUsuarioNuevo, usuarioNuevo);

		}

	}

	public Estudiante buscarEstudiante(Integer dni) {
		HashMap<Integer, Usuario> gestionUsuarios = this.getGestionDeUsuarios();
		if ((gestionUsuarios.containsKey(dni))) {
			return (Estudiante) gestionDeUsuarios.get(dni);
		}

		return null;
	}

	public void devolver(String isbnDeLibroAprestar, Usuario usuario, String fecha) 
		throws LibroNoPrestadoException {
			HashMap<String, Libro> gestionDeLibros = this.getGestionLibrosEnStock();

			if (gestionDeLibros.containsKey(isbnDeLibroAprestar)) {
				if (gestionDeLibros.get(isbnDeLibroAprestar).getEstado()) {
					throw new LibroNoPrestadoException("EL libro esta disponible");
				}
			}
		
	}

}
