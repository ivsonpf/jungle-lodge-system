package pousadaselva;

public abstract class Acomodacao { 
    
    // Atributos Protected (#)
    protected int numeroDoQuarto;
    protected double preco;
    protected boolean disponibilidade;
    protected double tamanhoMetrosQuadrados;
    protected int qtdCamaKing;
    protected int qtdCamaCasal;
    protected int qtdCamaSolteiro;
    protected int quantidadeRedes; 

    // Construtor
    public Acomodacao(int numeroDoQuarto, double preco, double tamanho, 
                      int qtdCamaKing, int qtdCamaCasal, int qtdCamaSolteiro, int quantidadeRedes) {
        this.numeroDoQuarto = numeroDoQuarto;
        this.preco = preco;
        this.tamanhoMetrosQuadrados = tamanho;
        this.qtdCamaKing = qtdCamaKing;
        this.qtdCamaCasal = qtdCamaCasal;
        this.qtdCamaSolteiro = qtdCamaSolteiro;
        this.quantidadeRedes = quantidadeRedes;
        this.disponibilidade = true; 
    }

    public void reservarQuarto() {
        if (this.disponibilidade) {
            this.disponibilidade = false;
            System.out.println("Sucesso: O Quarto " + this.numeroDoQuarto + " foi reservado!");
        } else {
            System.out.println("Aviso: O Quarto " + this.numeroDoQuarto + " já está ocupado.");
        }
    }

    public void liberarQuarto() {
        if (!this.disponibilidade) {
            this.disponibilidade = true;
            System.out.println("Checkout realizado: O Quarto " + this.numeroDoQuarto + " está livre novamente.");
        } else {
            System.out.println("Aviso: O Quarto " + this.numeroDoQuarto + " já se encontra livre.");
        }
    }

    public double calcularTotalDiarias(int dias) {
        return this.preco * dias;
    }
    
    public void exibirDetalhes() {
        System.out.println("\n==============================");
        System.out.println(" Detalhes do Quarto: " + this.numeroDoQuarto);
        System.out.println("==============================");
        System.out.println("Status: " + (this.disponibilidade ? "Disponível" : "Ocupado"));
        System.out.println("Preço da Diária: R$ " + this.preco);
        System.out.println("Tamanho: " + this.tamanhoMetrosQuadrados + " m²");
        
        if (this.qtdCamaKing > 0) System.out.println("Camas King Size: " + this.qtdCamaKing);
        if (this.qtdCamaCasal > 0) System.out.println("Camas de Casal: " + this.qtdCamaCasal);
        if (this.qtdCamaSolteiro > 0) System.out.println("Camas de Solteiro: " + this.qtdCamaSolteiro);
        if (this.quantidadeRedes > 0) System.out.println("Armadores de Rede: " + this.quantidadeRedes);
    }
}