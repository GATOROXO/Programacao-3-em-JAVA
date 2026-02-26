package aula9;

import java.util.List;

public class blblioteca {

    public static void main(String[] args) {
        // Para rodar isso, certifique-se de que o banco de dados 'biblioteca' existe
        // e possui uma tabela 'livros' (id INT AUTO_INCREMENT PRIMARY KEY, titulo VARCHAR(255), autor VARCHAR(255)).
        
        LivroDAO dao = new LivroDAO();

        System.out.println("=== Inserindo Livros ===");
        Livro l1 = new Livro("Dom Quixote", "Miguel de Cervantes");
        Livro l2 = new Livro("A Revolucao dos Bichos", "George Orwell");
        dao.inserir(l1);
        dao.inserir(l2);

        System.out.println("\n=== Listando Livros ===");
        List<Livro> livros = dao.listar();
        for (Livro l : livros) {
            System.out.println(l);
        }

        System.out.println("\n=== Buscando Livro ===");
        Livro buscado = dao.buscarPorTitulo("Dom Quixote");
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado);
            
            System.out.println("\n=== Atualizando Livro ===");
            buscado.setAutor("Miguel de Cervantes (Traduzido)");
            dao.atualizar(buscado);
        } else {
            System.out.println("Livro nao encontrado para busca.");
        }

        System.out.println("\n=== Removendo Primeiro Livro Listado ===");
        if (!livros.isEmpty()) {
            dao.remover(livros.get(0).getId());
        }

        System.out.println("\n=== Listando Livros Apos Alteracoes ===");
        for (Livro l : dao.listar()) {
            System.out.println(l);
        }
    }
}
