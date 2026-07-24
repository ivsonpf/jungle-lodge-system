package pousadaselva.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TelaCadastroHospede extends JFrame {
    private Image imagemFundo;

    public TelaCadastroHospede() {
        setTitle("Samaúma Pousada Boutique - Nova Reserva");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- CARREGANDO O FUNDO DA TELA ---
        try {
            imagemFundo = ImageIO.read(new File("img/tela-cadastro-v2.png"));
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
        txtDoc.setBounds(220, 130, 260, 40); // Posição do texto (Ajuste X e Largura para não encostar na borda)

        // Botão Buscar (COM SKIN)
        JButton btnBuscar = criarBotaoComImagem("img/botao-buscar.png", 130, 70);
        btnBuscar.setBounds(520, 130, 120, 40);

        painelFundo.add(lblDoc); 
        painelFundo.add(txtDoc); painelFundo.add(fundoDoc); // Ordem importa: Texto primeiro para ficar por cima!
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
        // ---> AJUSTE AQUI: Os dois últimos números (300 e 40) são a Largura e a Altura do componente!
        // Se o texto "Hóspede Estrangeiro?" ficar cortado, aumente o 300 para 350, 400, etc.
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

        // Checkboxes com as suas skins customizadas
        // ---> AJUSTE AQUI: Novamente, os dois últimos números (350 e 40) são a Largura e a Altura de cada um!
        JCheckBox chkTrilha = criarCheckBoxComSkin("Trilha Ecológica (R$ 150)", corDourada, fonteLabel, 750, 450, 350, 40);
        JCheckBox chkJacare = criarCheckBoxComSkin("Focagem de Jacarés (R$ 200)", corDourada, fonteLabel, 750, 500, 350, 40);
        JCheckBox chkEncontro = criarCheckBoxComSkin("Encontro das Águas (R$ 350)", corDourada, fonteLabel, 750, 550, 350, 40);

        painelFundo.add(chkTrilha); painelFundo.add(chkJacare); painelFundo.add(chkEncontro);

        // =========================================================
        // BOTÃO FINALIZAR (COM SKIN E AÇÃO DE NAVEGAÇÃO)
        // =========================================================
        JButton btnFinalizar = criarBotaoComImagem("img/botao-finalizar.png", 190, 90);
        btnFinalizar.setBounds(1030, 570, 220, 60);
        
        // Evento de clique para abrir o extrato
        btnFinalizar.addActionListener(e -> {
            TelaExtratoReserva telaExtrato = new TelaExtratoReserva();
            telaExtrato.setVisible(true);
            dispose(); // Fecha a tela de cadastro atual
        });
        
        painelFundo.add(btnFinalizar);

        add(painelFundo);

        revalidate(); 
        repaint();    
    }

    // =========================================================
    // FUNÇÕES AUXILIARES DE ESTILIZAÇÃO E DESACOPLAMENTO
    // =========================================================

    private JLabel criarLabel(String texto, Color cor, Font fonte, int x, int y, int larg, int alt) {
        JLabel label = new JLabel(texto);
        label.setForeground(cor);
        label.setFont(fonte);
        label.setBounds(x, y, larg, alt);
        return label;
    }

    // Cria apenas a imagem de fundo (Sua arte PNG)
    private JLabel criarImagemLabel(String caminho, int largura, int altura) {
        JLabel labelImagem = new JLabel();
        try {
            ImageIcon iconeOriginal = new ImageIcon(caminho);
            Image imgEscalada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            System.out.println("Arte não encontrada: " + caminho);
        }
        return labelImagem;
    }

    // Cria apenas o campo de digitação totalmente invisível
    private JTextField criarInputTransparente(Font fonte) {
        JTextField campo = new JTextField();
        campo.setFont(fonte);
        campo.setForeground(Color.WHITE); // Texto em branco
        campo.setCaretColor(Color.WHITE); // Tracinho de digitar piscando em branco
        campo.setOpaque(false); // Remove o fundo cinza
        campo.setBorder(null);  // Arranca a borda do Java
        return campo;
    }

    // Cria os checkboxes e injeta as imagens Check-vazio.png e check-selecionado.png
    // Agora aceitando largura e altura!
    private JCheckBox criarCheckBoxComSkin(String texto, Color cor, Font fonte, int x, int y, int largura, int altura) {
        JCheckBox chk = new JCheckBox(texto);
        
        // ---> AJUSTE AQUI: O setBounds agora usa a variável "largura" e "altura" que você passa lá em cima
        chk.setBounds(x, y, largura, altura); 
        
        chk.setOpaque(false);
        chk.setForeground(cor);
        chk.setFont(fonte);
        chk.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            // O ícone da imagem dourada continua 30x30, mas o componente total se ajusta à largura que você definiu
            Icon iconeVazio = new ImageIcon(new ImageIcon("img/Check-vazio-v2.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            Icon iconeMarcado = new ImageIcon(new ImageIcon("img/check-selecionado-v3.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            
            chk.setIcon(iconeVazio);
            chk.setSelectedIcon(iconeMarcado);
        } catch (Exception e) {
            System.out.println("Skins do CheckBox não encontradas.");
        }
        return chk;
    }

    // Pinta o ComboBox (Caixa de Seleção) com as cores do seu tema
    // Pinta o ComboBox e ARRICA o visual padrão do Sistema Operacional
    // Pinta o ComboBox (Caixa de Seleção) com as cores do seu tema e customiza a seta
    private void estilizarComboBox(JComboBox<String> cmb, Font fonte, Color corTexto) {
        Color fundoVerde = new Color(15, 30, 15); // O verde bem escuro da caixa
        
        cmb.setFont(fonte);
        cmb.setBackground(fundoVerde);
        cmb.setForeground(corTexto);
        cmb.setFocusable(false); 
        
        // --- A MÁGICA PARA CUSTOMIZAR A SETINHA ---
        cmb.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Cria uma setinha nova (Direção, Fundo, Sombra, Cor da Seta, Brilho)
                JButton btnSeta = new javax.swing.plaf.basic.BasicArrowButton(
                    javax.swing.SwingConstants.SOUTH,
                    fundoVerde, // Cor do fundo do botão (sumindo com o branco!)
                    fundoVerde, // Cor da sombra
                    corTexto,   // Cor do triângulo da seta (Dourado)
                    fundoVerde  // Cor do brilho
                );
                // Adiciona uma linha dourada fina só na esquerda para separar a seta do texto
                btnSeta.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, corTexto));
                return btnSeta;
            }
        }); 
        
        // Borda dourada em volta da caixa inteira
        cmb.setBorder(BorderFactory.createLineBorder(corTexto, 1)); 
    }

    private JButton criarBotaoComImagem(String caminhoImagem, int largura, int altura) {
        JButton botao = new JButton();
        try {
            ImageIcon iconeOriginal = new ImageIcon(caminhoImagem);
            Image imagemEscalada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            botao.setIcon(new ImageIcon(imagemEscalada));
        } catch (Exception e) {
            System.out.println("Arte do botão não encontrada: " + caminhoImagem);
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
        SwingUtilities.invokeLater(() -> new TelaCadastroHospede().setVisible(true));
    }
}