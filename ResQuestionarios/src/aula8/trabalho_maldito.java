package aula8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class trabalho_maldito extends JFrame implements ActionListener {

    // 1. Periféricos de Entrada
    private JTextField txtTitulo, txtDescricao, txtEspecifico, txtBusca;
    private JComboBox<String> comboTipo;

    // 2. Periféricos de Saída e Controle
    private JTextArea areaResultados;
    private JButton btnAdicionar, btnListar, btnFiltrar;

    // 3. Memória RAM (Seus Arrays)
    private final ArrayList<livro> livros;
    private final ArrayList<filme> filmes;

    public trabalho_maldito() {
        livros = new ArrayList<>();
        filmes = new ArrayList<>();

        // Personalização exigida no PDF (Cor exclusiva no Título/Barra Superior)
        this.setTitle("Sistema de Gestão");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10)); // O barramento principal

        // ===== ZONA NORTE: ENTRADA DE DADOS =====
        // Usamos um GridLayout só para o painel de cima ficar alinhado
        JPanel painelTop = new JPanel(new GridLayout(4, 2, 5, 5));

        painelTop.setBackground(new Color(173, 216, 230)); // Um azul claro

        painelTop.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Livro", "Filme"}); //
        painelTop.add(comboTipo);

        painelTop.add(new JLabel("Título/Nome:"));
        txtTitulo = new JTextField();
        painelTop.add(txtTitulo);

        painelTop.add(new JLabel("Descrição/Autor:"));
        txtDescricao = new JTextField();
        painelTop.add(txtDescricao);

        painelTop.add(new JLabel("Dado Específico (Ano/Duração):"));
        txtEspecifico = new JTextField();
        painelTop.add(txtEspecifico);

        this.add(painelTop, BorderLayout.NORTH);

        // ===== ZONA CENTRO: SAÍDA DE DADOS =====
        areaResultados = new JTextArea(); //
        areaResultados.setEditable(false);
        // Colocamos a JTextArea dentro de um ScrollPane para ganhar barra de rolagem
        JScrollPane scroll = new JScrollPane(areaResultados);
        this.add(scroll, BorderLayout.CENTER);

        // ===== ZONA SUL: BOTÕES E BUSCA =====
        JPanel painelBotton = new JPanel(new FlowLayout());

        btnAdicionar = new JButton("Adicionar"); //
        btnAdicionar.addActionListener(this);

        btnListar = new JButton("Listar"); //
        btnListar.addActionListener(this);

        txtBusca = new JTextField(15);
        btnFiltrar = new JButton("Filtrar"); //
        btnFiltrar.addActionListener(this);

        painelBotton.add(btnAdicionar);
        painelBotton.add(btnListar);
        painelBotton.add(new JLabel("Busca:"));
        painelBotton.add(txtBusca);
        painelBotton.add(btnFiltrar);

        this.add(painelBotton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    // A rotina central de interrupção
    @Override
    public void actionPerformed(ActionEvent e) {
        Object origem = e.getSource();

        try {
            if (origem == btnAdicionar) {
                adicionarRegistro();
            } else if (origem == btnListar) {
                listarConteudo();
            } else if (origem == btnFiltrar) {
                filtrarConteudo();
            }
        } catch (Exception ex) {
            // Tratamento de exceção com JOptionPane conforme exigido
            JOptionPane.showMessageDialog(this, "Erro fatal: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarRegistro() throws Exception {
        // Validação das portas de entrada
        String titulo = txtTitulo.getText().trim();
        String desc = txtDescricao.getText().trim();
        String especifico = txtEspecifico.getText().trim();

        if (titulo.isEmpty() || desc.isEmpty() || especifico.isEmpty()) {
            throw new Exception("Nenhum campo pode ficar vazio no hardware.");
        }

        // Lê o seletor do MUX (ComboBox)
        String tipoSelecionado = comboTipo.getSelectedItem().toString();

        if (tipoSelecionado.equals("Livro")) {
            // ADAPTE AQUI PARA O CONSTRUTOR DA SUA CLASSE LIVRO
            livros.add(new livro(titulo, desc, especifico));
        } else {
            // ADAPTE AQUI PARA O CONSTRUTOR DA SUA CLASSE FILME
            filmes.add(new filme(titulo, desc, especifico));
        }

        // Limpa os registradores após salvar
        txtTitulo.setText("");
        txtDescricao.setText("");
        txtEspecifico.setText("");

        JOptionPane.showMessageDialog(this, tipoSelecionado + " gravado na RAM com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarConteudo() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- REGISTROS DE MEMÓRIA ---\n\n");

        sb.append("[LIVROS]\n");
        for (livro l : livros) {
            sb.append("Título: ").append(l.getNome()).append(" | Autor: ").append(l.getAutor()).append("\n");
        }

        sb.append("\n[FILMES]\n");
        for (filme f : filmes) {
            sb.append("Nome: ").append(f.getNome()).append(" | Desc: ").append(f.getDescricao()).append("\n");
        }

        // Joga a string montada direto no monitor central
        areaResultados.setText(sb.toString());
    }

    private void filtrarConteudo() {
        String termo = txtBusca.getText().trim().toLowerCase();
        if(termo.isEmpty()) return;

        StringBuilder sb = new StringBuilder();
        sb.append("--- RESULTADOS DA BUSCA: '").append(termo).append("' ---\n\n");

        for (livro l : livros) {
            if (l.getNome().toLowerCase().contains(termo)) {
                sb.append("LIVRO: ").append(l.getNome()).append("\n");
            }
        }
        for (filme f : filmes) {
            if (f.getNome().toLowerCase().contains(termo)) {
                sb.append("FILME: ").append(f.getNome()).append("\n");
            }
        }
        areaResultados.setText(sb.toString());
    }

    public static void main(String[] args) {
        new trabalho_maldito();
    }
}