package pousadaselva.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TelaMenuPrincipal extends JFrame {
    private Image imagemFundo;

    public TelaMenuPrincipal() {
        setTitle("Samaúma Pousada Boutique - Menu Principal");
        // Aumentei o tamanho da janela para HD (1280x720) para acomodar a nova imagem widescreen
        setSize(1280, 720); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lembre-se de renomear a imagem lá na pasta img!
        try {
            imagemFundo = ImageIO.read(new File("img/fundo_samauma-v2.png")); 
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

        // --- NOVO PAINEL DE BOTÕES (MENU INFERIOR) ---
        JPanel painelMenu = new JPanel();
        // Mudou para: 1 linha, 4 colunas, com 20px de espaço entre eles na horizontal
        painelMenu.setLayout(new GridLayout(1, 4, 20, 0)); 
        painelMenu.setOpaque(false);

        // Posição ajustada para a parte de baixo da tela (Y=520) e bem largo (1000px)
        painelMenu.setBounds(140, 520, 1000, 50);

        // Removi os números como você preferiu antes
        JButton btnCatalogo = new JButton("Catálogo de Acomodações");
        JButton btnPacotes = new JButton("Pacotes Turísticos");
        JButton btnNovaReserva = new JButton("Cadastrar Nova Reserva");
        JButton btnListarReservas = new JButton("Listar Reservas Ativas");

        estilizarBotao(btnCatalogo);
        estilizarBotao(btnPacotes);
        estilizarBotao(btnNovaReserva);
        estilizarBotao(btnListarReservas);

        painelMenu.add(btnCatalogo);
        painelMenu.add(btnPacotes);
        painelMenu.add(btnNovaReserva);
        painelMenu.add(btnListarReservas);

        painelFundo.add(painelMenu);
        add(painelFundo);
    }

    private void estilizarBotao(JButton botao) {
        botao.setFont(new Font("SansSerif", Font.BOLD, 15));
        
        // Fundo verde bem escuro para mesclar com o papel
        botao.setBackground(new Color(15, 30, 20)); 
        
        // Letra em Dourado para combinar com a logo
        Color corDourada = new Color(212, 175, 55);
        botao.setForeground(corDourada);
        
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // --- TEXTO CENTRALIZADO ---
        botao.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Borda fina dourada com espaço interno
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corDourada, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
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