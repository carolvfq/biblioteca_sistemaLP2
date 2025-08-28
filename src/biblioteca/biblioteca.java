package biblioteca;

import java.util.ArrayList;
import java.util.List;

import entities.usuario;

import java.util.ArrayList;
import java.util.List;

public class biblioteca {

    private List<livro> acervo;
    private List<usuario> listaUsuarios;

    public biblioteca() {
        acervo = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
    }

    public void cadastrarLivro(livro livro) {
        acervo.add(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado no acervo.");
    }

//    public void cadastrarLivro(livro livro) {
//        if(livrosNoAcervo < acervo.length) {
//            acervo[livrosNoAcervo] = livro;
//            System.out.println("Livro " + livro.getTitulo() + " adicionado no acervo.");
//            livrosNoAcervo++;
//        } else {
//            System.out.println("Acervo lotado!");
//        }
//    }

    public static void main(String[] args) {
        biblioteca biblioteca = new biblioteca();
        livro l = new livro("Java como Programar", "Deitel", 2014);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
    }

}
