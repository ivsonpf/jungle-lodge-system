package pousadaselva.view;

import javax.swing.*;
import java.awt.*;

public class TelaPacotes extends JFrame {
    private Image imagemFundo;

    public TelaPacotes() {
        setTitle("Samaúma Pousada Boutique - Pacotes Turísticos");
        setSize(1280, 720); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);

        // Puxa o fundo direto do cofre na memória RAM!
        imagemFundo = GerenciadorDeImagens.obterImagemOriginal("img/tela-pacotes.png");

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
        btnVoltar.setBounds(30, 30, 140, 80); 

        btnVoltar.addActionListener(e -> {
            TelaMenuPrincipal menu = new TelaMenuPrincipal();
            menu.setVisible(true);
            dispose(); 
        });
        
        painelFundo.add(btnVoltar);

        // =========================================================
        // 1. CARD DA ESQUERDA (Trilha Ecológica)
        // =========================================================
        JPanel card1 = criarConteudoPacote("Caminhada guiada de 2h na selva", "150,00");
        card1.setBounds(105, 270, 340, 240); // Reduzi a altura da caixa invisível para não invadir o botão
        painelFundo.add(card1);

        JButton btnReservar1 = criarBotaoComImagem("img/botao-reservar-catalogo.png", 180, 90);
        btnReservar1.setBounds(190, 510, 180, 90); // AJUSTE AQUI O BOTÃO 1 (X, Y, Largura, Altura)
        btnReservar1.addActionListener(e -> abrirCadastro());
        painelFundo.add(btnReservar1);

        // =========================================================
        // 2. CARD DO CENTRO (Focagem de Jacarés)
        // =========================================================
        JPanel card2 = criarConteudoPacote("Passeio noturno de barco", "200,00");
        card2.setBounds(460, 270, 340, 240); 
        painelFundo.add(card2);

        JButton btnReservar2 = criarBotaoComImagem("img/botao-reservar-catalogo.png", 180, 90);
        btnReservar2.setBounds(535, 510, 180, 90); // AJUSTE AQUI O BOTÃO 2
        btnReservar2.addActionListener(e -> abrirCadastro());
        painelFundo.add(btnReservar2);

        // =========================================================
        // 3. CARD DA DIREITA (Encontro das Águas)
        // =========================================================
        JPanel card3 = criarConteudoPacote("Passeio de lancha até o encontro dos rios", "350,00");
        card3.setBounds(825, 280, 340, 240); 
        painelFundo.add(card3);

        JButton btnReservar3 = criarBotaoComImagem("img/botao-reservar-catalogo.png", 180, 90);
        btnReservar3.setBounds(910, 510, 180, 90); // AJUSTE AQUI O BOTÃO 3
        btnReservar3.addActionListener(e -> abrirCadastro());
        painelFundo.add(btnReservar3);

        add(painelFundo);

        // --- A MÁGICA DA ATUALIZAÇÃO FORÇADA ---
        // Garante que a tela renderize perfeitamente na primeira abertura
        revalidate(); 
        repaint();    
    }

    // --- FUNÇÃO PARA ABRIR TELA DE CADASTRO (Evita repetir código) ---
    private void abrirCadastro() {
        TelaCadastroHospede telaCadastro = new TelaCadastroHospede();
        telaCadastro.setVisible(true);
        dispose();
    }

    // --- FUNÇÃO APENAS PARA INJETAR O TEXTO AGORA ---
    private JPanel criarConteudoPacote(String detalhes, String valor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setOpaque(false); 
        
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20)); 

        String texto = "<html><center>" +
            "<b style='color:white; font-size:15px;'>Detalhes do Passeio:</b><br><br>" +
            "<i style='color:white; font-size:14px;'>" + detalhes + "</i><br><br>" +
            "<b style='color:#D4AF37; font-size:17px;'>Valor Adicional: R$ " + valor + "</b>" +
            "</center></html>";

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setVerticalAlignment(SwingConstants.CENTER); 
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblTexto, BorderLayout.CENTER);

        return card; // Retorna SÓ o texto, sem botão!
    }

    // --- FUNÇÃO PARA RENDERIZAR SKINS EM BOTÕES CONECTADA AO GERENCIADOR ---
    private JButton criarBotaoComImagem(String caminhoImagem, int largura, int altura) {
        JButton botao = new JButton();

        // Puxa do nosso cofre global!
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
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            new TelaPacotes().setVisible(true);
        });
    }
}