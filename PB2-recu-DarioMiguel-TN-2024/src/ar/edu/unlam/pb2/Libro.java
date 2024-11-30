package ar.edu.unlam.pb2;

public class Libro {

	private String isbn;
	private Boolean estado;
	private String titulo;
	private String autor;
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Libro(String titulo, String autor,String isbn) {
		super();
		this.isbn = isbn;
		this.estado = true;
		this.titulo = titulo;
		this.autor = autor;
	}

}
