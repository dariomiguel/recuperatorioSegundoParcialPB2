package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

public class SistemaDeGestionBibliotecaTest {

//	Recuperatorio Segundo Parcial 
//	o Prestar un libro a un estudiante: Verificar que un libro se puede prestar a un 
//	estudiante y que el libro no está disponible después del préstamo. 
//  *****Cada libro tiene un título, autor, ISBN y estado de disponibilidad
	@Test
	public void prestarUnLibroAUnEstudiante() throws LibroYaPrestadoException {
		GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();
		Libro libro1 = new Libro("Titulo1", "Autor1", "ISBN1");
		gestionBiblioteca.agregar(libro1);
		Boolean comprobarEstadoDeLibro = gestionBiblioteca.sePuedePrestar(libro1);

		Usuario estudiante = new Estudiante("Estudiante1", 37150);
		gestionBiblioteca.agregarEstudiante(37150, estudiante);

		// Se puede prestar?
		assertTrue(comprobarEstadoDeLibro);

		try {
			gestionBiblioteca.prestar("ISBN1", estudiante, "19/11/2024");
			comprobarEstadoDeLibro = gestionBiblioteca.sePuedePrestar(libro1);

			// Se puede prestar luego de estar ya prestado?
			assertFalse(comprobarEstadoDeLibro);

		} catch (SeIntentaPrestarMasLibrosDeLoPermitidoException e) {
			e.printStackTrace();
		}
//
//		
	}

//	o Lanzar excepción por préstamo excedido: Verificar que se lanza 
//	una excepción cuando un estudiante o profesor intenta prestar más libros de 
//	los permitidos. 
	@Test(expected = SeIntentaPrestarMasLibrosDeLoPermitidoException.class)
	public void lanzarExcepcionPorPrestamoExcedido() throws SeIntentaPrestarMasLibrosDeLoPermitidoException, LibroYaPrestadoException {
		GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();

		Libro libro1 = new Libro("Titulo1", "Autor1", "ISBN1");
		gestionBiblioteca.agregar(libro1);
		Libro libro2 = new Libro("Titulo2", "Autor2", "ISBN2");
		gestionBiblioteca.agregar(libro2);
		Libro libro3 = new Libro("Titulo3", "Autor3", "ISBN3");
		gestionBiblioteca.agregar(libro3);
		Libro libro4 = new Libro("Titulo4", "Autor4", "ISBN4");
		gestionBiblioteca.agregar(libro4);
		Libro libro5 = new Libro("Titulo5", "Autor5", "ISBN5");
		gestionBiblioteca.agregar(libro5);
		Libro libro6 = new Libro("Titulo6", "Autor6", "ISBN6");
		gestionBiblioteca.agregar(libro6);

		Usuario estudiante = new Estudiante("Estudiante1", 37150);
		gestionBiblioteca.agregarEstudiante(37150, estudiante);

		gestionBiblioteca.prestar("ISBN1", estudiante, "19/11/2024");
		gestionBiblioteca.prestar("ISBN2", estudiante, "19/11/2024");
		gestionBiblioteca.prestar("ISBN3", estudiante, "19/11/2024");
		gestionBiblioteca.prestar("ISBN4", estudiante, "19/11/2024");
		gestionBiblioteca.prestar("ISBN5", estudiante, "19/11/2024");

		try {
			gestionBiblioteca.prestar("ISBN6", estudiante, "19/11/2024");
		} catch (SeIntentaPrestarMasLibrosDeLoPermitidoException e) {
			throw e;
		}
	}

//	o Préstamo de un libro no disponible: Verificar que se lanza una excepción 
//	cuando se intenta prestar un libro que ya ha sido prestado.
	@Test(expected = LibroYaPrestadoException.class)
	public void prestamoDeUnLibroNoDisponible() throws LibroYaPrestadoException, SeIntentaPrestarMasLibrosDeLoPermitidoException {

		GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();
		Libro libro1 = new Libro("Titulo1", "Autor1", "ISBN1");
		gestionBiblioteca.agregar(libro1);
		Usuario estudiante = new Estudiante("Estudiante1", 37150);
		gestionBiblioteca.agregarEstudiante(37150, estudiante);

		gestionBiblioteca.prestar("ISBN1", estudiante, "19/11/2024");
		gestionBiblioteca.prestar("ISBN1", estudiante, "19/11/2024");

	}

//	o Devolver un libro no prestado: Verificar que se lanza una excepción cuando se 
//	intenta devolver un libro que no ha sido prestado. 

	@Test(expected = LibroNoPrestadoException.class)
	public void devolverUnLibroNoPrestado() throws  LibroNoPrestadoException{

		GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();
		Libro libro1 = new Libro("Titulo1", "Autor1", "ISBN1");
		gestionBiblioteca.agregar(libro1);
		
		Usuario estudiante = new Estudiante("Estudiante1", 37150);
		gestionBiblioteca.agregarEstudiante(37150, estudiante);

		gestionBiblioteca.devolver("ISBN1", estudiante, "19/11/2024");

	}

}
