package pousadaselva.model;

public class HospedeNacional extends Hospede {
    
   // Construtor
    public HospedeNacional(String nome, String cpf) {
        // Envia o nome e o CPF para serem armazenados na superclasse
        super(nome, cpf); 
    }

    // Polimorfismo: Como a classe mãe é abstrata, somos obrigados a implementar esse método
    @Override
    public void exibirDetalhes() {
        System.out.println("👤 Hóspede Nacional: " + this.nome);
        System.out.println("   CPF: " + this.documento);
    }
}