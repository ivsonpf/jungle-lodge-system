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

        try {
            imagemFundo = ImageIO.read(new File("img/fundo_tela_catalogo-v3.png"));   
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
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

        // --- PAINEL CONTAINER PARA OS 3 CARDS ---
        JPanel painelCards = new JPanel();
        // 1 linha, 3 colunas, com um espaço (gap) de 50px entre os blocos
        painelCards.setLayout(new GridLayout(1, 3, 50, 0)); 
        painelCards.setOpaque(false); // Transparente para mostrar o fundo do rio/árvore
        
        // Posição centralizada na tela
        painelCards.setBounds(80, 100, 1100, 480); 

        // Criando os três blocos de conteúdo
        painelCards.add(criarCardAcomodacao(
            "🌊 CABANA FLUTUANTE", "101", "850,00", "40.0", 
            "Camas King Size: 1<br>Armadores de Rede: 2", 
            "Profundidade do Rio: 12.5m<br>Barco de Acesso: Sim", 
            "[Ambiental] Esgoto tratado por biodigestor flutuante selado."
        ));

        painelCards.add(criarCardAcomodacao(
            "🌳 BANGALÔ NA ÁRVORE", "201", "1.200,00", "35.0", 
            "Camas King: 1 | Solteiro: 1<br>Armadores de Rede: 1", 
            "Altura do solo: 15.0m<br>Acesso: Ponte Suspensa", 
            "[Ambiental] Tubulação camuflada até o biodigestor central."
        ));

        painelCards.add(criarCardAcomodacao(
            "🛖 OCA FAMILIAR", "301", "950,00", "60.0", 
            "Camas King: 1 | Solteiro: 3<br>Redes: 4", 
            "Altura das estacas: 3.5m<br>Água na cheia: Alcança 2.0m", 
            "[Ambiental] Filtro biológico de raízes instalado abaixo do piso."
        ));

        painelFundo.add(painelCards);
        add(painelFundo);
    }

    // --- FUNÇÃO PARA GERAR OS BLOCOS (CARDS) ---
    private JPanel criarCardAcomodacao(String titulo, String num, String preco, String tam, String camas, String extra, String ambiental) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(15, 30, 20)); // Fundo sólido sem ghosting
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(212, 175, 55), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Montando o texto com HTML
        String texto = "<html>" +
            "<h2 style='color:#D4AF37; text-align:center;'>" + titulo + "</h2>" +
            "<hr style='border: 1px solid #D4AF37;'>" +
            "<p style='color:white;'><b>Quarto:</b> " + num + " | <b>Status:</b> Disponível</p><br>" +
            "<p style='color:white;'><b>Diária:</b> R$ " + preco + "</p>" +
            "<p style='color:white;'><b>Tamanho:</b> " + tam + " m²</p><br>" +
            "<p style='color:white;'>" + camas + "</p><br>" +
            "<p style='color:white;'>" + extra + "</p><br><br>" +
            "<p style='color:#90EE90; text-align:center;'><i>" + ambiental + "</i></p>" +
            "</html>";

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setVerticalAlignment(SwingConstants.TOP); // Joga o texto para cima
        card.add(lblTexto, BorderLayout.CENTER);

        // Botão de ação no final do card
        JButton btnReservar = new JButton("Reservar " + num);
        btnReservar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnReservar.setBackground(new Color(212, 175, 55)); // Dourado
        btnReservar.setForeground(new Color(15, 30, 20)); // Letra verde escura
        btnReservar.setFocusPainted(false);
        btnReservar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        card.add(btnReservar, BorderLayout.SOUTH);

        return card;
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