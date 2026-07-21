package pousadaselva.model;

public class PacoteTuristico {

    // Atributos privados (Encapsulamento)
    private String nome;
    private String descricao;
    private double valor;

    // Construtor
    public PacoteTuristico(String nome, String descricao, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Método para exibir o pacote no menu
    public void exibirDetalhes() {
        System.out.println("🌿 Pacote: " + this.nome);
        System.out.println("   Detalhes: " + this.descricao);
        System.out.println("   Valor Adicional: R$ " + this.valor);
    }

    // Getters para a calculadora de tarifas acessar os valores depois
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}