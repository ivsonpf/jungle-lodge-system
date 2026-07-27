package pousadaselva.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GerenciadorDeImagens {
    
    // O cofre estático na RAM
    private static HashMap<String, ImageIcon> cacheImagens = new HashMap<>();

    // 1. Método para Botões e Checkboxes (Redimensiona a imagem)
    public static ImageIcon obterIcone(String caminho, int largura, int altura) {
        String chave = caminho + "_" + largura + "x" + altura;
        
        if (cacheImagens.containsKey(chave)) {
            return cacheImagens.get(chave); 
        }
        
        try {
            ImageIcon iconeOriginal = new ImageIcon(caminho);
            Image imagemEscalada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            ImageIcon iconePronto = new ImageIcon(imagemEscalada);
            
            cacheImagens.put(chave, iconePronto);
            return iconePronto;
        } catch (Exception e) {
            System.out.println("Arte não encontrada no Gerenciador: " + caminho);
            return null;
        }
    }

    // 2. NOVO MÉTODO: Para o Fundo da Tela (Tamanho original, sem redimensionar)
    public static Image obterImagemOriginal(String caminho) {
        String chave = caminho + "_original";
        
        // Se o fundo já foi carregado antes, devolve da RAM instantaneamente
        if (cacheImagens.containsKey(chave)) {
            return cacheImagens.get(chave).getImage();
        }
        
        // Se for a primeira vez, carrega do HD e guarda no cofre
        try {
            ImageIcon iconeOriginal = new ImageIcon(caminho);
            cacheImagens.put(chave, iconeOriginal); // Guarda
            return iconeOriginal.getImage(); // Devolve a imagem
        } catch (Exception e) {
            System.out.println("Fundo não encontrado no Gerenciador: " + caminho);
            return null;
        }
    }
}