package aula8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Implemente uma interface com: Dois campos JTextField para números.
//a. Um JComboBox com as opções “Somar”, “Subtrair”, “Multiplicar” e “Dividir”.
//b. Um JCheckBox para escolher se o resultado deve aparecer em uma JOptionPane. Um botão “Calcular”.
//c. Ao clicar, mostre o resultado conforme a operação escolhida e a opção do checkbox.


public class calculadora extends JFrame implements ActionListener {

    // 1. Declaração Global (Os Periféricos)
    private JTextField txtNum1, txtNum2, txtResultado;
    private JComboBox<String> comboOp;
    private JCheckBox chkPopup;
    private JButton btnCalcular;

    public calculadora() {
        this.setTitle("Exercício 3 Calculadora");
        this.setSize(350, 250);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usando GridLayout (6 linhas, 2 colunas) para alinhar tudo perfeitamente
        this.setLayout(new GridLayout(6, 2, 5, 5));

        // --- LINHA 1: Número 1 ---
        this.add(new JLabel("Número 1:"));
        this.txtNum1 = new JTextField();
        this.add(this.txtNum1);

        // --- LINHA 2: Número 2 ---
        this.add(new JLabel("Número 2:"));
        this.txtNum2 = new JTextField();
        this.add(this.txtNum2);

        // --- LINHA 3: Operação (JComboBox) ---
        this.add(new JLabel("Operação:"));
        String[] operacoes = {"Somar", "Subtrair", "Multiplicar", "Dividir"}; // As opções
        this.comboOp = new JComboBox<>(operacoes);
        this.add(this.comboOp);

        // --- LINHA 4: Opção de Tela (JCheckBox) ---
        this.add(new JLabel("Exibição:"));
        this.chkPopup = new JCheckBox("Mostrar em Popup");
        this.add(this.chkPopup);

        // --- LINHA 5: Botão de Ação ---
        this.add(new JLabel("")); // Espaço vazio para empurrar o botão para a direita
        this.btnCalcular = new JButton("Calcular");
        this.btnCalcular.addActionListener(this); // O interrupt
        this.add(this.btnCalcular);

        // --- LINHA 6: Resultado na Tela ---
        this.add(new JLabel("Resultado:"));
        this.txtResultado = new JTextField();
        this.txtResultado.setEditable(false); // Trava o campo para o usuário não digitar
        this.add(this.txtResultado);

        this.setVisible(true);
    }

    // 2. A Rotina de Interrupção (O Cálculo)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCalcular) {

            // Tratamento de Erro (Try-Catch): Impede o programa de "crashar" se digitarem letras
            try {
                // Lê o ASCII das caixas e converte para Binário/Double
                double n1 = Double.parseDouble(this.txtNum1.getText());
                double n2 = Double.parseDouble(this.txtNum2.getText());
                double resultado = 0;

                // Lê qual item está selecionado no MUX (JComboBox)
                String opSelecionada = this.comboOp.getSelectedItem().toString();

                // Roteamento da ULA
                if (opSelecionada.equals("Somar")) {
                    resultado = n1 + n2;
                } else if (opSelecionada.equals("Subtrair")) {
                    resultado = n1 - n2;
                } else if (opSelecionada.equals("Multiplicar")) {
                    resultado = n1 * n2;
                } else if (opSelecionada.equals("Dividir")) {
                    if (n2 == 0) throw new ArithmeticException("Divisão por zero!");
                    resultado = n1 / n2;
                }
                // Lê o bit de Flag (JCheckBox) para decidir onde mostrar a saída
                if (this.chkPopup.isSelected()) {
                    // Se a flag for 1, mostra no pop-up
                    JOptionPane.showMessageDialog(this, "O resultado é: " + resultado, "Cálculo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Se a flag for 0, converte o double de volta pra String e mostra no campinho de baixo
                    this.txtResultado.setText(String.valueOf(resultado));
                }

            } catch (NumberFormatException erro) {
                // Se o parse falhar (ex: usuário digitou "A"), o processador joga essa exceção
                JOptionPane.showMessageDialog(this, "Por favor, digite apenas números!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (ArithmeticException erro) {
                // Evita o travamento clássico de divisão por zero
                JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro Matemático", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new calculadora();
    }
}
