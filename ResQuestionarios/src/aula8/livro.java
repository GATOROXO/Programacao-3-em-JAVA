package aula8;

import java.util.Date;

public class livro {
    private String nome;
    private String autor;
    private String ano;

    public livro(String nome, String autor, String ano) {
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getAno() {
        return ano;
    }
}
