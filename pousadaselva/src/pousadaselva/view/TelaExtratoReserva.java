package pousadaselva.view;

import javax.swing.*;
import java.awt.*;

public class TelaExtratoReserva extends JFrame {
    private Image imagemFundo;

    public TelaExtratoReserva() {
        setTitle("Samaúma Pousada Boutique - Extrato da Reserva");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        imagemFundo = GerenciadorDeImagens.obterImagemOriginal("img/tela-extrato-cadastro-v2.png");

        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagemFundo != null) {
                    g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        painelFundo.setLayout(null);

        // --- BOTÃO VOLTAR ---
        JButton btnVoltar = criarBotaoComImagem("img/botao-voltar.png", 140, 80);
        btnVoltar.setBounds(30, 20, 140, 80);
        btnVoltar.addActionListener(e -> {
            // Abre o Menu Principal e fecha o extrato
            TelaMenuPrincipal menu = new TelaMenuPrincipal();
            menu.setVisible(true);
            dispose();
        });
        painelFundo.add(btnVoltar);

        // =========================================================
        // BLOCO CENTRAL: O RECIBO / EXTRATO
        // =========================================================

        // A Área de Texto onde vamos "imprimir" os dados
        JTextArea txtExtrato = new JTextArea();
        txtExtrato.setBounds(430, 120, 400, 500); // Centralizado na tela (Ajuste conforme sua arte!)
        txtExtrato.setFont(new Font("Monospaced", Font.PLAIN, 15)); // Fonte de "recibo" (espaçamento igual)
        txtExtrato.setForeground(Color.WHITE);
        txtExtrato.setOpaque(false); // Fundo transparente para mostrar sua imagem
        txtExtrato.setEditable(false); // O usuário não pode digitar aqui, só ler
        txtExtrato.setHighlighter(null); // Impede de selecionar o texto com o mouse

        // --- TEXTO FICTÍCIO PARA VOCÊ TESTAR O LAYOUT ---
        String recibo = 
            "--------------------------------------------------\n" +
            " DADOS DO TITULAR\n" +
            "--------------------------------------------------\n" +
            " Hóspede: Ivson Freire\n" +
            " Doc/Passaporte: 123.456.789-00\n" +
            " Estrangeiro: Não\n" +
            "\n" +
            "--------------------------------------------------\n" +
            " DETALHES DA ESTADIA\n" +
            "--------------------------------------------------\n" +
            " Acomodação: 101 - Cabana Flutuante\n" +
            " Diárias: 3 noites\n" +
            " Quantidade de Hóspedes: 2\n" +
            " Subtotal Acomodação:           R$ 2.550,00\n" +
            "\n" +
            "--------------------------------------------------\n" +
            " EXPERIÊNCIAS NA SELVA\n" +
            "--------------------------------------------------\n" +
            " - Trilha Ecológica\n" +
            " - Focagem de Jacarés\n" +
            " Subtotal Pacotes:              R$   350,00\n" +
            "\n" +
            "==================================================\n" +
            " TOTAL A PAGAR:                 R$ 2.900,00\n" +
            "==================================================\n";
            
        txtExtrato.setText(recibo);
        painelFundo.add(txtExtrato);

        // --- BOTÃO CONFIRMAR E SALVAR ---
        JButton btnConfirmar = criarBotaoComImagem("img/botao-salvar-v2.png", 220, 100); 
        btnConfirmar.setBounds(530, 590, 220, 100); // Centralizado embaixo do recibo
        btnConfirmar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Reserva Salva com Sucesso no Banco de Dados!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Aqui entrará o código do INSERT no banco no futuro
            TelaMenuPrincipal menu = new TelaMenuPrincipal();
            menu.setVisible(true);
            dispose(); // Fecha o extrato
        });
        painelFundo.add(btnConfirmar);

        add(painelFundo);
    }

    // =========================================================
    // Função Auxiliar CONECTADA AO GERENCIADOR DE IMAGENS
    // =========================================================
    private JButton criarBotaoComImagem(String caminhoImagem, int largura, int altura) {
        JButton botao = new JButton();
        
        // Puxa do nosso cofre global em vez de ler do HD de novo!
        Icon icone = GerenciadorDeImagens.obterIcone(caminhoImagem, largura, altura);
        if (icone != null) botao.setIcon(icone);
        
        botao.setContentAreaFilled(false);  
        botao.setBorderPainted(false);      
        botao.setFocusPainted(false);       
        botao.setOpaque(false);             
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        return botao;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaExtratoReserva().setVisible(true));
    }
}