package biblioteca;

public class livro {
 private String titulo;
 private String autor;
 private Integer anoPublicado;
 
 public livro() {
	 
 }
 public livro(String titulo, String autor, Integer anoPublicado) {
	
	setTitulo(titulo);
	setAutor(autor);
	setAnoPublicado(anoPublicado);
 }
 public String getTitulo() {
	return titulo;
 }
 public void setTitulo(String titulo) {
	 if(titulo == "") {
		 System.out.println("ERRO:título inválido.");
	 }
	 else {
		 this.titulo = titulo;
	 }
 }
 public String getAutor() {
	return autor;
 }
 public void setAutor(String autor) {
	 if(autor == "") {
		 System.out.println("ERRO:título inválido.");
	 }
	 else {
		 this.autor = autor;
	 }
 }
 public Integer getAnoPublicado() {
	return anoPublicado;
 }
 public void setAnoPublicado(Integer anoPublicado) {
	 int ano_atual= 2025;
	 if(anoPublicado>ano_atual) {
		 System.out.println("ERRO:ano inválido.");
	 }
	 else {
		 this.anoPublicado = anoPublicado;
	 }
 }
 @Override
 public String toString() {
	return "livro [titulo=" + titulo + ", autor=" + autor + ", anoPublicado=" + anoPublicado + "]";
 }
 
}
