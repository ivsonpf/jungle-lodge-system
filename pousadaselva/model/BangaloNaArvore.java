package pousadaselva.model;

import pousadaselva.service.RegrasSustentabilidade;

public class BangaloNaArvore extends Acomodacao implements RegrasSustentabilidade {

    // Atributos exclusivos
    protected double alturaDoSolo;
    protected String tipoDeAcesso;

    // Construtor
    public BangaloNaArvore(int numeroDoQuarto, double preco, double tamanho, 
                           int qtdCamaKing, int qtdCamaCasal, int qtdCamaSolteiro, int quantidadeRedes,
                           double alturaDoSolo, String tipoDeAcesso) {
        
        super(numeroDoQuarto, preco, tamanho, qtdCamaKing, qtdCamaCasal, qtdCamaSolteiro, quantidadeRedes);
        
        this.alturaDoSolo = alturaDoSolo;
        this.tipoDeAcesso = tipoDeAcesso;
    }

    // Polimorfismo
    @Override
    public void exibirDetalhes() {
        System.out.println("\n=== 🌳 BANGALÔ NA ÁRVORE ===");
        super.exibirDetalhes(); 
        
        System.out.println("Altura do solo: " + this.alturaDoSolo + "m");
        System.out.println("Acesso através de: " + this.tipoDeAcesso);
        System.out.println("==============================");
    }

    // --- MÉTODOS DA INTERFACE ---
    @Override
    public void avaliarTratamentoEsgoto() {
        System.out.println("[Ambiental] Bangalô " + this.numeroDoQuarto + ": Tubulação de esgoto camuflada descendo pela estrutura da árvore até o biodigestor central.");
    }

    @Override
    public boolean verificarCotaDeAguaDiaria() {
        System.out.println("[Ambiental] Bangalô " + this.numeroDoQuarto + ": Água bombeada com energia solar. Limite de 100L/dia não excedido.");
        return true;
    }
}