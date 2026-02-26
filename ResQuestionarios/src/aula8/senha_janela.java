package aula8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class senha_janela extends JFrame implements ActionListener {
    /*Desenvolva uma janela contendo: Um JLabel e um JTextField para o nome de usuário.
    Um JLabel e um JPasswordField para senha. Um JButton "Entrar". Ao clicar em "Entrar",
    exiba uma JOptionPane com uma mensagem de boas-vindas que inclua o nome digitado
    */
    // Declaração dos componentes da interface gráfica
    private JLabel label1, label2;
    private JTextField campoUsuario;
    private JPasswordField Ps1;
    private JButton btn1;
    private JPanel panel1; // nao usei

    // Construtor que inicializa a janela e seus componentes
    senha_janela(){

        // Configuração padrão da janela principal
        this.setTitle("Exercicio 2");
        this.setSize(800, 600);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o layout para organizar os componentes
        this.getContentPane().setLayout(new FlowLayout());

        // ===== ÁREA DE USUÁRIO =====
        // Cria o rótulo para o campo de usuário
        this.label1 = new JLabel("Usuario: ", JLabel.CENTER);

        // Cria o campo de texto para o usuário digitar o nome
        this.campoUsuario = new JTextField(15); // Define largura do campo (15 colunas)
        this.campoUsuario.setFont(new Font("ARIAL", Font.PLAIN, 14)); // Configura a fonte
        this.campoUsuario.setEditable(true); // Permite edição do campo

        // Adiciona os componentes de usuário à janela
        this.getContentPane().add(label1);
        this.getContentPane().add(campoUsuario);

        // ===== ÁREA DE SENHA =====
        // Cria o rótulo para o campo de senha
        this.label2 = new JLabel("Senha:", JLabel.CENTER);

        // Cria o campo de senha (caracteres não aparecem)
        this.Ps1 = new JPasswordField(15); // Define largura do campo (15 colunas)
        this.Ps1.setFont(new Font("ARIAL", Font.PLAIN, 14)); // Configura a fonte
        this.Ps1.setEditable(true); // Permite edição do campo

        // Adiciona os componentes de senha à janela
        this.getContentPane().add(label2);
        this.getContentPane().add(Ps1);

        // ===== BOTÃO DE ENTRADA =====
        // Cria o botão "Entrar"
        this.btn1 = new JButton("Entrar");
        this.btn1.setBackground(Color.BLUE); // Define cor de fundo
        this.btn1.setFont(new Font("ARIAL", Font.PLAIN, 14)); // Configura a fonte
        this.btn1.setMnemonic(KeyEvent.VK_B); // Define atalho (Alt+B)
        this.btn1.addActionListener(this); // Registra o listener para ouvir cliques

        // Adiciona o botão à janela
        this.getContentPane().add(btn1);

        // Torna a janela visível
        this.setVisible(true);
    }

    // Método chamado quando o botão "Entrar" é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtém o nome do usuário digitado
        String nomeUsuario = this.campoUsuario.getText();

        // Obtém a senha digitada
        String senhaUsuario = new String(this.Ps1.getPassword());

        // Exibe uma mensagem de boas-vindas com o nome do usuário
        JOptionPane.showMessageDialog(
                this,
                "Bem-vindo, " + nomeUsuario + "!",
                "Login Realizado",
                JOptionPane.INFORMATION_MESSAGE
        );}

    public static void main(String[] args) {
        // Cria uma instância da janela de login
        // O construtor automaticamente exibe a janela
        new senha_janela();
    }
}
