package pousadaselva;

public class OcaFamiliar extends Acomodacao implements RegrasSustentabilidade {

    // Atributos exclusivos (Estrutura de palafita)
    protected double alturaDaPalafita;
    protected double nivelDaAguaNaCheia;

    // Construtor
    public OcaFamiliar(int numeroDoQuarto, double preco, double tamanho, 
                       int qtdCamaKing, int qtdCamaCasal, int qtdCamaSolteiro, int quantidadeRedes,
                       double alturaDaPalafita, double nivelDaAguaNaCheia) {
        
        super(numeroDoQuarto, preco, tamanho, qtdCamaKing, qtdCamaCasal, qtdCamaSolteiro, quantidadeRedes);
        
        this.alturaDaPalafita = alturaDaPalafita;
        this.nivelDaAguaNaCheia = nivelDaAguaNaCheia;
    }

    // Polimorfismo
    @Override
    public void exibirDetalhes() {
        System.out.println("\n=== 🛖 OCA FAMILIAR (PALAFITA) ===");
        super.exibirDetalhes(); 
        
        System.out.println("Altura das estacas (Palafita): " + this.alturaDaPalafita + "m");
        System.out.println("Nível da água na época da cheia: Alcança até " + this.nivelDaAguaNaCheia + "m da base");
        System.out.println("==============================");
    }

    // --- MÉTODOS DA INTERFACE ---
    @Override
    public void avaliarTratamentoEsgoto() {
        System.out.println("[Ambiental] Oca " + this.numeroDoQuarto + ": Filtro biológico de raízes instalado abaixo do piso elevado.");
    }

    @Override
    public boolean verificarCotaDeAguaDiaria() {
        System.out.println("[Ambiental] Oca " + this.numeroDoQuarto + ": Reuso de água cinza ativo. Cota diária sustentável alcançada.");
        return true;
    }
}