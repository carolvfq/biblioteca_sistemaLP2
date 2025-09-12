package application;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import entities.DVD;
import entities.Emprestimo;
import entities.ItemDoAcervo;
import entities.Livro;
import entities.Revista;
import entities.StatusLivro;
import entities.Usuario;

import java.time.LocalDate;
//Não acontece nada. Não faz semtido , ele é sempre algo específico .Nesse caso eessa classe deveria ser abstrata, para que não pudesse ser instanciada  .//

public class Biblioteca{

    private List<ItemDoAcervo> acervo;
    private List<Usuario> listaUsuarios;
    private List<Emprestimo> registroDeEmprestimos;



    public Biblioteca(){

        this.acervo = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.registroDeEmprestimos = new ArrayList<>();
    }


    public void realizarEmprestimo(String UserUsuario, String titulo) {
        Usuario usuarioDoEmprestimo = pesquisarUsuarioPorUser(UserUsuario);
        if (usuarioDoEmprestimo == null) {
            System.out.println("Erro: esse usuário não está cadastrado.");
            return;
        }
        ItemDoAcervo livroDoEmprestimo = pesquisarItemPorTitulo(titulo);
        if (livroDoEmprestimo == null) {
            System.out.println("Erro: esse livro não está cadastrado.");
            return;
        }
        if (livroDoEmprestimo.getStatus() == StatusLivro.EMPRESTADO) {
            System.out.println("Erro: esse livro já está emprestado.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate devolucaoPrevista = hoje.plusDays(livroDoEmprestimo.getPrazo());

        livroDoEmprestimo.setStatus(StatusLivro.EMPRESTADO);
        Emprestimo emprestimo = new Emprestimo(livroDoEmprestimo, usuarioDoEmprestimo, hoje, devolucaoPrevista );
        registroDeEmprestimos.add(emprestimo);
        System.out.println("Emprestimo cadastrado com sucesso.");
        System.out.println( "O livro " + livroDoEmprestimo.getTitulo() + " foi emprestado para o usuario " + usuarioDoEmprestimo.getName() + " na data " + emprestimo.getDataEmprestimo() + " e a data de devolução é " + emprestimo.getDataDevolucaoPrevista() );
    }
    public void cadastrarItem(ItemDoAcervo item){
        this.acervo.add(item);
        System.out.println("Livro "+ item.getTitulo() + " adicionado no acervo");
    }

    public void cadastrarUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
        System.out.println("Usuario "+ usuario.getUser() + " adicionado no acervo");
    }

    public ItemDoAcervo pesquisarItemPorTitulo(String titulo){
        for ( ItemDoAcervo item : this.acervo){
            if(item.getTitulo().equalsIgnoreCase(titulo)) {
                return item ;
            }
        }
        return null;
    }

    public Usuario pesquisarUsuarioPorUser(String User) {
        for(Usuario usuario : this.listaUsuarios) {
            if(usuario.getUser().equals(User)) {
                return usuario;
            }
        }
        return null;
    }
    public List<ItemDoAcervo> pesquisarItemPorTermo(String termo) {
        List<ItemDoAcervo> listaDeLivros = new ArrayList<>();
        for (var item : acervo) {
            if(item.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                listaDeLivros.add(item);
            }
        }
        return listaDeLivros;
    }

    public void listarAcervo() {
        System.out.println("Livros no Acervo");
        for (ItemDoAcervo item : acervo) {
            System.out.println(item);
        }
    }

    private Emprestimo buscarEmprestimoAtivoPorItem(ItemDoAcervo item){
        for (var emprestimo : registroDeEmprestimos) {
            if(emprestimo.getItem().getTitulo().equalsIgnoreCase(item.getTitulo())) {
                System.out.println(item.getStatus());
                return emprestimo;
            }
        }
        return null;
    }

    public void realizarDevolucao(String titulo) {
        ItemDoAcervo item = pesquisarItemPorTitulo(titulo);
        if(item == null) {
            System.out.println("Erro: esse item não está cadastrado.");
            return;
        }
        Emprestimo emprestimo = buscarEmprestimoAtivoPorItem(item);
        if(emprestimo == null) {
            System.out.println("Erro: esse emprestimo não existe.");
            return;
        }
        LocalDate hoje = LocalDate.now().plusDays(8);
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje);

        if(dias > 0) {
            double multa = dias * item.getValorMultaPorDiaAtraso();
            System.out.println("Item devolvido. Você precisa pagar uma multa de R$" + multa);
        } else {
            System.out.println("Item devolvido.");
        }
        emprestimo.getItem().setStatus(StatusLivro.DISPONIVEL);
        emprestimo.setDataDevolucaoPrevista(hoje);
    }
         public List<ItemDoAcervo> buscar(String termo){
        	 List<ItemDoAcervo> listaResultados = new ArrayList<>();
        	 String termoLowerCase = termo.toLowerCase();
        	 for (ItemDoAcervo item : this.acervo) {
        	        
        	        if (item.getTitulo().toLowerCase().contains(termoLowerCase)) {
        	            listaResultados.add(item);
        	        }
        	        else if (item instanceof Livro) {
        	            Livro livro = (Livro) item;
        	            if (livro.getAutor().toLowerCase().contains(termoLowerCase)) {
        	                listaResultados.add(item);
        	            }
        	        }
        	    }
        	    return listaResultados;}

    public static void main(String[] args) {
        Livro livroJavaComoProgramar = new Livro("Java Como Programar", "Deitel", 2014);
        Livro livroMemoria = new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881);
        Usuario meuUsuario = new Usuario("Thiago", "Thiago Carvalho", "123");
        Biblioteca minhaBiblioteca = new Biblioteca();
        minhaBiblioteca.cadastrarItem(livroJavaComoProgramar);
        minhaBiblioteca.cadastrarItem(livroMemoria);
        minhaBiblioteca.cadastrarUsuario(meuUsuario);
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.realizarEmprestimo("Thiago", "Memórias Póstumas de Brás Cubas");
        minhaBiblioteca.buscarEmprestimoAtivoPorItem(livroMemoria);
        Revista revistaveja = new Revista("veja",2024 ,1 );
        System.out.println(revistaveja);
        System.out.println(livroJavaComoProgramar);
        DVD dvdReiLeao = new DVD("O Rei Leão", 1994, 89);
        minhaBiblioteca.cadastrarItem(dvdReiLeao);
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.realizarEmprestimo(meuUsuario.getUser(), dvdReiLeao.getTitulo());
        minhaBiblioteca.realizarDevolucao("o Rei Leão");
        minhaBiblioteca.listarAcervo();
        ItemDoAcervo ItemGenerico = new ItemDoAcervo("Titulo Generico", 2025);
        Livro livro1 = new Livro("A Batalha do Apocalipse", "Eduardo Spohr", 2007);
        Livro livro2 = new Livro("O Fim da Infância", "Arthur C. Clarke", 1953);
        Revista revista1 = new Revista("National Geographic", 2023, 150);
        minhaBiblioteca.cadastrarItem(livro1);
        minhaBiblioteca.cadastrarItem(livro2);
        minhaBiblioteca.cadastrarItem(revista1);
        List<ItemDoAcervo> resultadosPorAutor = minhaBiblioteca.buscar("Spohr");
        
        
        for(ItemDoAcervo item : resultadosPorAutor) {
        	System.out.println(item);
        }
       
};}