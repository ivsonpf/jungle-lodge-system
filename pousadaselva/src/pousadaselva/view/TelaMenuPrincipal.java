package pousadaselva.view;

import javax.swing.*;
import java.awt.*;

public class TelaMenuPrincipal extends JFrame {
    private Image imagemFundo;

    public TelaMenuPrincipal() {
        setTitle("Samaúma Pousada Boutique - Menu Principal");
        setSize(1280, 720); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        imagemFundo = GerenciadorDeImagens.obterImagemOriginal("img/fundo_samauma-v2.png");

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

        // --- 2. CONFIGURANDO O PAINEL DE BOTÕES ---
        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new GridLayout(1, 4, 25, 0)); 
        painelMenu.setOpaque(false);
        
        // Suas coordenadas perfeitas
        painelMenu.setBounds(100, 510, 1050, 120); 

        int larguraArte = 245;
        int alturaArte = 120;

        // --- 3. CRIANDO OS BOTÕES COM AS SKINS ---
        JButton btnCatalogo = criarBotaoComImagem("img/botao1-menuprincipal.png", larguraArte, alturaArte);
        JButton btnPacotes = criarBotaoComImagem("img/botao2-menuprincipal.png", larguraArte, alturaArte);
        JButton btnNovaReserva = criarBotaoComImagem("img/botao3-menuprincipal.png", larguraArte, alturaArte);
        JButton btnListarReservas = criarBotaoComImagem("img/botao4-menuprincipal.png", larguraArte, alturaArte);

        // --- 4. AÇÕES DOS BOTÕES (COM FECHAMENTO DE TELA) ---
        
        // Botão 1: Abre Catálogo e fecha o Menu
        btnCatalogo.addActionListener(e -> {
            TelaCatalogo telaCatalogo = new TelaCatalogo();
            telaCatalogo.setVisible(true);
            dispose(); // Fecha a tela atual (Menu)
        });

        // Botão 2: Abre Pacotes Turísticos e fecha o Menu
        btnPacotes.addActionListener(e -> {
            TelaPacotes telaPacotes = new TelaPacotes();
            telaPacotes.setVisible(true);
            dispose(); // Fecha a tela atual (Menu)
        });

        // Botão 3: Abre Cadastro e fecha o Menu
        btnNovaReserva.addActionListener(e -> {
            TelaCadastroHospede telaCadastro = new TelaCadastroHospede();
            telaCadastro.setVisible(true);
            dispose(); // Fecha a tela atual (Menu)
        });

        // Adicionando os botões na tela
        painelMenu.add(btnCatalogo);
        painelMenu.add(btnPacotes);
        painelMenu.add(btnNovaReserva);
        painelMenu.add(btnListarReservas);

        painelFundo.add(painelMenu);
        add(painelFundo);
        
        // --- A MÁGICA DA ATUALIZAÇÃO FORÇADA ---
        // Garante que o menu principal seja renderizado perfeitamente na primeira abertura
        revalidate(); 
        repaint();    
    }

    // --- FUNÇÃO PARA CRIAR O BOTÃO INVISÍVEL COM SKIN (ATUALIZADA PARA CACHE) ---
    private JButton criarBotaoComImagem(String caminhoImagem, int largura, int altura) {
        JButton botao = new JButton();

        // Puxa direto do nosso Gerenciador de Imagens!
        Icon icone = GerenciadorDeImagens.obterIcone(caminhoImagem, largura, altura);
        if (icone != null) botao.setIcon(icone);

        // Removendo o visual padrão 
        botao.setContentAreaFilled(false);  
        botao.setBorderPainted(false);      
        botao.setFocusPainted(false);       
        botao.setOpaque(false);             
        
        // Colocando o cursor de mãozinha
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

        return botao;
    }

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new TelaMenuPrincipal().setVisible(true);
        });
    }
}