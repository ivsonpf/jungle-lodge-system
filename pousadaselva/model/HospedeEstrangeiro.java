package pousadaselva.model;

public class HospedeEstrangeiro extends Hospede {

    // Atributo exclusivo da classe filha
    private String paisOrigem;

    // Construtor
    public HospedeEstrangeiro(String nome, String passaporte, String paisOrigem) {
        // Envia o nome e o Passaporte para a superclasse
        super(nome, passaporte);
        this.paisOrigem = paisOrigem;
    }

    // Getter exclusivo
    public String getPaisOrigem() {
        return paisOrigem;
    }

    // Polimorfismo
    @Override
    public void exibirDetalhes() {
        System.out.println("✈️ Hóspede Estrangeiro: " + this.nome);
        System.out.println("   Passaporte: " + this.documento);
        System.out.println("   País de Origem: " + this.paisOrigem);
    }
}