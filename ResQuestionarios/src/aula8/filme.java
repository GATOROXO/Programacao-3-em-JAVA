package aula8;

import java.util.Date;

public class filme {
    private String nome;
    private String descricao;
    private String data_de_lancamento;

    public filme(String nome, String descricao, String data_de_lancamento) {
        this.nome = nome;
        this.descricao = descricao;
        this.data_de_lancamento = data_de_lancamento;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData_de_lancamento() {
        return data_de_lancamento;
    }
}
