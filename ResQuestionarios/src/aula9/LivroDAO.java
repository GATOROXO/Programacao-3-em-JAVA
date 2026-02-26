package aula9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.execute();
            System.out.println("Livro inserido com sucesso: " + livro.getTitulo());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return livros;
    }

    public Livro buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM livros WHERE titulo LIKE ?";
        Livro livro = null;
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + titulo + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("autor")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por titulo: " + e.getMessage());
        }
        return livro;
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getId());
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum livro alterado. Verifique o ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Livro removido com sucesso (ID: " + id + ")");
            } else {
                System.out.println("Livro nao encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }
}
