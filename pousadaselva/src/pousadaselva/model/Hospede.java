package pousadaselva.model;

// A palavra 'abstract' significa que não podemos criar um "Hospede" genérico, 
// ele OBRIGATORIAMENTE terá que ser Nacional ou Estrangeiro.
public abstract class Hospede {
    
    // protected permite que as classes filhas enxerguem esses dados
    protected String nome;
    protected String documento; // Vai servir para CPF (Nacional) ou Passaporte (Estrangeiro)
    
    // Construtor da classe mãe
    public Hospede(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }
    
    // Método abstrato: Força as classes filhas a terem sua própria versão de como exibir os dados
    public abstract void exibirDetalhes();
    
    // Getters para quando precisarmos puxar apenas o nome ou documento depois
    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }
}