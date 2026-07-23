package pousadaselva.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TelaCatalogo extends JFrame {
    private Image imagemFundo;

    public TelaCatalogo() {
        setTitle("Samaúma Pousada Boutique - Catálogo de Acomodações");
        setSize(1280, 720); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);

        // Carrega a imagem de fundo principal
        try {
            imagemFundo = ImageIO.read(new File("img/tela-catalogo.png")); 
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem de fundo: " + e.getMessage());
        }

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

        // --- NOVO BOTÃO VOLTAR COM A SUA SKIN ---
        // Coloquei a proporção de 140x80 como base, mas você pode ajustar esses dois números como preferir!
        JButton btnVoltar = criarBotaoComImagem("img/botao-voltar.png", 140, 80);
        
        // Posição na tela: X=30, Y=30 (Canto superior esquerdo) e o tamanho exato da arte
        btnVoltar.setBounds(30, 30, 140, 80);

        // Ação para reabrir o Menu e fechar o Catálogo
        btnVoltar.addActionListener(e -> {
            TelaMenuPrincipal menu = new TelaMenuPrincipal();
            menu.setVisible(true);
            dispose(); 
        });
        
        painelFundo.add(btnVoltar);

        // --- PAINEL CONTAINER TRANSPARENTE PARA OS TEXTOS ---
        JPanel painelCards = new JPanel();
        painelCards.setLayout(new GridLayout(1, 3, 45, 0)); 
        painelCards.setOpaque(false); 
        
        painelCards.setBounds(65, 270, 1150, 420); 

        // Adicionando os textos das acomodações
        painelCards.add(criarConteudoQuarto(
            "101", "850,00", "40.0", 
            "Camas King Size: 1<br>Armadores de Rede: 2", 
            "Profundidade do Rio: 12.5m<br>Barco de Acesso: Sim", 
            "[Ambiental] Esgoto tratado por biodigestor flutuante selado."
        ));

        painelCards.add(criarConteudoQuarto(
            "201", "1.200,00", "35.0", 
            "Camas King: 1 | Solteiro: 1<br>Armadores de Rede: 1", 
            "Altura do solo: 15.0m<br>Acesso: Ponte Suspensa", 
            "[Ambiental] Tubulação camuflada até o biodigestor central."
        ));

        painelCards.add(criarConteudoQuarto(
            "301", "950,00", "60.0", 
            "Camas King: 1 | Solteiro: 3<br>Redes: 4", 
            "Altura das estacas: 3.5m<br>Água na cheia: Alcança 2.0m", 
            "[Ambiental] Filtro biológico de raízes instalado abaixo do piso."
        ));

        painelFundo.add(painelCards);
        add(painelFundo);
    }

    // --- FUNÇÃO PARA INJETAR O TEXTO E O BOTÃO DE RESERVA ---
    private JPanel criarConteudoQuarto(String num, String preco, String tam, String camas, String extra, String ambiental) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setOpaque(false); 
        card.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); 

        String texto = "<html>" +
            "<p style='color:white; font-size:12px;'><b>Quarto:</b> " + num + " | <b>Status:</b> Disponível</p><br>" +
            "<p style='color:white; font-size:12px;'><b>Diária:</b> R$ " + preco + "</p>" +
            "<p style='color:white; font-size:12px;'><b>Tamanho:</b> " + tam + " m²</p><br>" +
            "<p style='color:white; font-size:12px;'>" + camas + "</p><br>" +
            "<p style='color:white; font-size:12px;'>" + extra + "</p><br>" +
            "<p style='color:#90EE90; text-align:center; font-size:11px;'><i>" + ambiental + "</i></p>" +
            "</html>";

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setVerticalAlignment(SwingConstants.TOP); 
        card.add(lblTexto, BorderLayout.CENTER);

        JPanel painelBotao = new JPanel();
        painelBotao.setOpaque(false);

        // NOME CORRIGIDO E ALTURA AJUSTADA PARA 90PX COMO VOCÊ DEFINIU
        JButton btnReservar = criarBotaoComImagem("img/botao-reservar-catalogo.png", 180, 90);
        
        btnReservar.addActionListener(e -> {
            TelaCadastroHospede telaCadastro = new TelaCadastroHospede();
            telaCadastro.setVisible(true);
            dispose();
        });

        painelBotao.add(btnReservar);
        card.add(painelBotao, BorderLayout.SOUTH);

        return card;
    }

    // --- FUNÇÃO PARA RENDERIZAR SKINS EM BOTÕES ---
    private JButton criarBotaoComImagem(String caminhoImagem, int largura, int altura) {
        JButton botao = new JButton();

        try {
            ImageIcon iconeOriginal = new ImageIcon(caminhoImagem);
            Image imagemEscalada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            botao.setIcon(new ImageIcon(imagemEscalada));
        } catch (Exception e) {
            System.out.println("Não foi possível carregar a arte: " + caminhoImagem);
        }

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
            new TelaCatalogo().setVisible(true);
        });
    }
}