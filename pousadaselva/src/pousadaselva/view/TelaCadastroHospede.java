package pousadaselva.view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroHospede extends JFrame {

    public TelaCadastroHospede() {
        // Configurações da Janela
        setTitle("Jungle Lodge - Cadastro de Hóspedes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        
        // Criando o painel principal com um layout de grade (5 linhas, 2 colunas)
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(5, 2, 10, 15)); // 10 e 15 são os espaços entre os itens
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens

        // Criando os rótulos (Labels) e as caixas de texto (TextFields)
        JLabel lblNome = new JLabel("Nome Completo:");
        JTextField txtNome = new JTextField();

        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblEmail = new JLabel("E-mail:");
        JTextField txtEmail = new JTextField();

        // Criando os botões
        JButton btnSalvar = new JButton("Salvar Hóspede");
        JButton btnLimpar = new JButton("Limpar");

        // Adicionando tudo no painel na ordem (Label na esquerda, Texto na direita)
        painelFormulario.add(lblNome);
        painelFormulario.add(txtNome);
        painelFormulario.add(lblCpf);
        painelFormulario.add(txtCpf);
        painelFormulario.add(lblTelefone);
        painelFormulario.add(txtTelefone);
        painelFormulario.add(lblEmail);
        painelFormulario.add(txtEmail);
        painelFormulario.add(btnLimpar);
        painelFormulario.add(btnSalvar);

        // Adiciona o painel à janela principal
        add(painelFormulario);
    }

    public static void main(String[] args) {
        // Roda a interface gráfica
        SwingUtilities.invokeLater(() -> {
            new TelaCadastroHospede().setVisible(true);
        });
    }
}