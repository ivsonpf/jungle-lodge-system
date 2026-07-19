package pousadaselva;

public class CabanaFlutuante extends Acomodacao implements RegrasSustentabilidade {

    // Atributos exclusivos dessa acomodação
    protected boolean possuiBarcoDeAcesso;
    protected double profundidadeDoRio;

    // Construtor
    public CabanaFlutuante(int numeroDoQuarto, double preco, double tamanho, 
                           int qtdCamaKing, int qtdCamaCasal, int qtdCamaSolteiro, int quantidadeRedes,
                           boolean possuiBarcoDeAcesso, double profundidadeDoRio) {
        
        // O super envia os dados base para o construtor da classe mãe (Acomodacao)
        super(numeroDoQuarto, preco, tamanho, qtdCamaKing, qtdCamaCasal, qtdCamaSolteiro, quantidadeRedes);
        
        // Inicializa os atributos que só a Cabana Flutuante tem
        this.possuiBarcoDeAcesso = possuiBarcoDeAcesso;
        this.profundidadeDoRio = profundidadeDoRio;
    }

    // Polimorfismo: Reescrevendo a ficha técnica
    @Override
    public void exibirDetalhes() {
        System.out.println("\n=== 🌊 CABANA FLUTUANTE ===");
        
        // Chama a impressão padrão da classe mãe (Quarto, preço, tamanho, camas)
        super.exibirDetalhes(); 
        
        // Adiciona as informações exclusivas da água
        System.out.println("Profundidade do Rio no local: " + this.profundidadeDoRio + "m");
        System.out.println("Possui Barco de Acesso: " + (this.possuiBarcoDeAcesso ? "Sim" : "Não"));
        System.out.println("==============================");
    }

    // --- MÉTODOS OBRIGATÓRIOS DA INTERFACE (RegrasSustentabilidade) ---
    
    @Override
    public void avaliarTratamentoEsgoto() {
        System.out.println("[Ambiental] Cabana " + this.numeroDoQuarto + ": Esgoto tratado por biodigestor flutuante selado.");
    }

    @Override
    public boolean verificarCotaDeAguaDiaria() {
        System.out.println("[Ambiental] Cabana " + this.numeroDoQuarto + ": Cota de água captada da chuva está dentro do limite verde.");
        return true;
    }
}