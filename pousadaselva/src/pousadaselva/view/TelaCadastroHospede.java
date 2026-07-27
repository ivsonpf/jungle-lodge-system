package pousadaselva.view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroHospede extends JFrame {
    private Image imagemFundo;

    public TelaCadastroHospede() {
        setTitle("Samaúma Pousada Boutique - Nova Reserva");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        imagemFundo = GerenciadorDeImagens.obterImagemOriginal("img/tela-cadastro-v2.png");

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

        Color corDourada = new Color(212, 175, 55);
        Font fonteLabel = new Font("SansSerif", Font.BOLD, 16);
        Font fonteInput = new Font("SansSerif", Font.PLAIN, 18);

        // --- BOTÃO VOLTAR ---
        JButton btnVoltar = criarBotaoComImagem("img/botao-voltar.png", 140, 80);
        btnVoltar.setBounds(30, 20, 140, 80);
        btnVoltar.addActionListener(e -> {
            TelaMenuPrincipal menu = new TelaMenuPrincipal();
            menu.setVisible(true);
            dispose();
        });
        painelFundo.add(btnVoltar);

        // =========================================================
        // BLOCO SUPERIOR: DADOS DO HÓSPEDE
        // =========================================================
        
        // 1. CPF / PASSAPORTE
        JLabel lblDoc = criarLabel("CPF / Passaporte:", corDourada, fonteLabel, 200, 100, 150, 30);
        
        JLabel fundoDoc = criarImagemLabel("img/caixa-input-v3.png", 300, 210);
        fundoDoc.setBounds(200, 130, 330, 40); // Posição da arte
        
        JTextField txtDoc = criarInputTransparente(fonteInput);
        txtDoc.setBounds(220, 130, 260, 40); // Posição do texto 

        // Botão Buscar (COM SKIN)
        JButton btnBuscar = criarBotaoComImagem("img/botao-buscar.png", 130, 70);
        btnBuscar.setBounds(520, 130, 120, 40);

        painelFundo.add(lblDoc); 
        painelFundo.add(txtDoc); painelFundo.add(fundoDoc); 
        painelFundo.add(btnBuscar);
        
        // 2. NOME COMPLETO
        JLabel lblNome = criarLabel("Nome Completo:", corDourada, fonteLabel, 200, 190, 150, 30);
        
        JLabel fundoNome = criarImagemLabel("img/caixa-input.png", 600, 210);
        fundoNome.setBounds(140, 220, 600, 40);

        JTextField txtNome = criarInputTransparente(fonteInput);
        txtNome.setBounds(220, 220, 448, 40);

        painelFundo.add(lblNome); 
        painelFundo.add(txtNome); painelFundo.add(fundoNome);

        // 3. ESTRANGEIRO (CHECKBOX COM SKIN)
        JCheckBox chkEstrangeiro = criarCheckBoxComSkin("Hóspede Estrangeiro?", corDourada, fonteLabel, 200, 280, 250, 40);
        painelFundo.add(chkEstrangeiro);

        // 4. PAÍS DE ORIGEM
        JLabel lblPais = criarLabel("País de Origem:", corDourada, fonteLabel, 500, 280, 150, 30);
        
        JLabel fundoPais = criarImagemLabel("img/caixa-input.png", 280, 210);
        fundoPais.setBounds(600, 275, 250, 40);
        
        JTextField txtPais = criarInputTransparente(fonteInput);
        txtPais.setBounds(640, 275, 200, 40);

        painelFundo.add(lblPais); 
        painelFundo.add(txtPais); painelFundo.add(fundoPais);

        // =========================================================
        // BLOCO INFERIOR ESQUERDO: ACOMODAÇÕES
        // =========================================================
        
        // QUARTO (ComboBox Colorido)
        JLabel lblQuarto = criarLabel("Selecione o Quarto:", corDourada, fonteLabel, 100, 460, 200, 30);
        String[] quartos = {"101 - Cabana Flutuante", "201 - Bangalô na Árvore", "301 - Oca Familiar"};
        JComboBox<String> cmbQuartos = new JComboBox<>(quartos);
        cmbQuartos.setBounds(100, 490, 250, 40);
        estilizarComboBox(cmbQuartos, fonteInput, corDourada);
        
        // DIÁRIAS (CAIXA QUADRADA NOVA)
        JLabel lblDiarias = criarLabel("Diárias:", corDourada, fonteLabel, 380, 460, 100, 30);
        
        JLabel fundoDiarias = criarImagemLabel("img/caixa-input-quadrado.png", 60, 40);
        fundoDiarias.setBounds(380, 490, 60, 40);
        
        JTextField txtDiarias = criarInputTransparente(fonteInput);
        txtDiarias.setBounds(400, 490, 28, 40); // Texto centralizado dentro do quadrado
        
        // HÓSPEDES (ComboBox Colorido com Lógica)
        JLabel lblPessoas = criarLabel("Hóspedes:", corDourada, fonteLabel, 480, 460, 100, 30);
        JComboBox<String> cmbPessoas = new JComboBox<>();
        cmbPessoas.setBounds(480, 490, 80, 40);
        estilizarComboBox(cmbPessoas, fonteInput, corDourada);

        // Lógica de Capacidade
        cmbQuartos.addActionListener(e -> {
            int index = cmbQuartos.getSelectedIndex();
            cmbPessoas.removeAllItems(); 
            if (index == 0) {
                cmbPessoas.addItem("1"); cmbPessoas.addItem("2");
            } else if (index == 1) { 
                cmbPessoas.addItem("1"); cmbPessoas.addItem("2"); cmbPessoas.addItem("3");
            } else if (index == 2) { 
                cmbPessoas.addItem("1"); cmbPessoas.addItem("2"); cmbPessoas.addItem("3"); 
                cmbPessoas.addItem("4"); cmbPessoas.addItem("5");
            }
        });
        cmbQuartos.setSelectedIndex(0); 

        painelFundo.add(lblQuarto); painelFundo.add(cmbQuartos);
        painelFundo.add(lblDiarias); painelFundo.add(txtDiarias); painelFundo.add(fundoDiarias);
        painelFundo.add(lblPessoas); painelFundo.add(cmbPessoas);

        // =========================================================
        // BLOCO INFERIOR DIREITO: PACOTES
        // =========================================================

        JCheckBox chkTrilha = criarCheckBoxComSkin("Trilha Ecológica (R$ 150)", corDourada, fonteLabel, 750, 450, 350, 40);
        JCheckBox chkJacare = criarCheckBoxComSkin("Focagem de Jacarés (R$ 200)", corDourada, fonteLabel, 750, 500, 350, 40);
        JCheckBox chkEncontro = criarCheckBoxComSkin("Encontro das Águas (R$ 350)", corDourada, fonteLabel, 750, 550, 350, 40);

        painelFundo.add(chkTrilha); painelFundo.add(chkJacare); painelFundo.add(chkEncontro);

        // =========================================================
        // BOTÃO FINALIZAR 
        // =========================================================
        JButton btnFinalizar = criarBotaoComImagem("img/botao-finalizar.png", 190, 90);
        btnFinalizar.setBounds(1030, 570, 220, 60);
        
        btnFinalizar.addActionListener(e -> {
            TelaExtratoReserva telaExtrato = new TelaExtratoReserva();
            telaExtrato.setVisible(true);
            dispose(); 
        });
        
        painelFundo.add(btnFinalizar);

        add(painelFundo);

        revalidate(); 
        repaint();    
    }

    // =========================================================
    // FUNÇÕES AUXILIARES CONECTADAS AO GERENCIADOR
    // =========================================================

    private JLabel criarLabel(String texto, Color cor, Font fonte, int x, int y, int larg, int alt) {
        JLabel label = new JLabel(texto);
        label.setForeground(cor);
        label.setFont(fonte);
        label.setBounds(x, y, larg, alt);
        return label;
    }

    private JLabel criarImagemLabel(String caminho, int largura, int altura) {
        JLabel labelImagem = new JLabel();
        Icon icone = GerenciadorDeImagens.obterIcone(caminho, largura, altura);
        if (icone != null) labelImagem.setIcon(icone);
        return labelImagem;
    }

    private JTextField criarInputTransparente(Font fonte) {
        JTextField campo = new JTextField();
        campo.setFont(fonte);
        campo.setForeground(Color.WHITE); 
        campo.setCaretColor(Color.WHITE); 
        campo.setOpaque(false); 
        campo.setBorder(null);  
        return campo;
    }

    private JCheckBox criarCheckBoxComSkin(String texto, Color cor, Font fonte, int x, int y, int largura, int altura) {
        JCheckBox chk = new JCheckBox(texto);
        
        chk.setBounds(x, y, largura, altura); 
        
        chk.setOpaque(false);
        chk.setForeground(cor);
        chk.setFont(fonte);
        chk.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Icon iconeVazio = GerenciadorDeImagens.obterIcone("img/Check-vazio-v2.png", 30, 30);
        Icon iconeMarcado = GerenciadorDeImagens.obterIcone("img/check-selecionado-v3.png", 30, 30);
            
        if (iconeVazio != null) chk.setIcon(iconeVazio);
        if (iconeMarcado != null) chk.setSelectedIcon(iconeMarcado);
        
        return chk;
    }

    private void estilizarComboBox(JComboBox<String> cmb, Font fonte, Color corTexto) {
        Color fundoVerde = new Color(15, 30, 15); 
        
        cmb.setFont(fonte);
        cmb.setBackground(fundoVerde);
        cmb.setForeground(corTexto);
        cmb.setFocusable(false); 
        
        cmb.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btnSeta = new javax.swing.plaf.basic.BasicArrowButton(
                    javax.swing.SwingConstants.SOUTH,
                    fundoVerde, 
                    fundoVerde, 
                    corTexto,   
                    fundoVerde  
                );
                btnSeta.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, corTexto));
                return btnSeta;
            }
        }); 
        
        cmb.setBorder(BorderFactory.createLineBorder(corTexto, 1)); 
    }

    private JButton criarBotaoComImagem(String caminhoImagem, int largura, int altura) {
        JButton botao = new JButton();
        
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
        SwingUtilities.invokeLater(() -> new TelaCadastroHospede().setVisible(true));
    }
}