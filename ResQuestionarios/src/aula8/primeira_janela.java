/**
 * Classe que cria uma janela principal (JFrame) com:
 * - Um JLabel exibindo uma mensagem de boas-vindas
 * - Um JButton com texto "Fechar" que encerra o programa quando clicado
 */
package aula8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class primeira_janela extends JFrame implements ActionListener {
    private final JLabel label1;
    private final JButton button1;
    /**
     * Construtor que inicializa e configura a janela principal
     */
    public primeira_janela() {
        //titulo da janela
        this.setTitle("Exercicio_1 Teste");
        //Tamanho da janela em pixels (largura x altura)
        this.setSize(800, 600);
        // Posição inicial da janela na tela (coordenadas x, y)
        this.setLocation(400, 200);
        // Define se a janela pode ser redimensionada pelo usuário (true = sim, false = não)
        this.setResizable(true);
        // Define o gerenciador de layout como FlowLayout (organiza componentes em linha)
        this.getContentPane().setLayout(new FlowLayout());

        // ===== CONFIGURAÇÃO DO LABEL "BEM VINDO" =====
        // Declaração dos componentes da interface gráfica
        // Labels para exibir textos
        label1 = new JLabel("bem vindo", JLabel.CENTER);  // Cria label com texto centralizado
        label1.setFont(new Font("Times New Roman", Font.BOLD, 25));  // Define fonte, estilo e tamanho
        this.getContentPane().add(label1);  // Adiciona o label à janela

        // ===== CONFIGURAÇÃO DO BOTÃO "FECHAR" =====
        // Botão para fechar a janela
        button1 = new JButton("Fechar");  // Cria o botão com texto "Fechar"
        button1.setBackground(Color.RED);  // Define cor de fundo como vermelho
        button1.setMnemonic(KeyEvent.VK_B);  // Define atalho de teclado (Alt + B)
        button1.setActionCommand("Fechar");  // Define o comando da ação do botão
        button1.setFocusable(false);  // Remove o foco visual ao clicar
        button1.addActionListener(this);  // Adiciona listener para fechar ao clicar
        this.getContentPane().add(button1);  // Adiciona o botão à janela

        // Define a cor de fundo da janela como branco
        this.getContentPane().setBackground(Color.WHITE);/**/
        // Define o comportamento ao fechar: encerra o programa
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e ){
        if (e.getSource() == this.button1) this.dispose();
    }

    /**
     * Método principal que inicia a aplicação
     * @param ignoredArgs Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] ignoredArgs) {
        primeira_janela janela = new primeira_janela();  // Cria uma instância da janela
        janela.setVisible(true);  // Torna a janela visível na tela
    }

}