package pousadaselva.model;

import pousadaselva.exception.PeriodoInvalidoException;
import java.util.ArrayList;

public class Reserva {

    // Composição: A reserva "tem um" hóspede, "tem uma" acomodação e "tem pacotes"
    private Hospede hospede;
    private Acomodacao acomodacao;
    private int quantidadeDiarias; // Simplificando o Check-in/Check-out para focar na POO
    private ArrayList<PacoteTuristico> pacotes;

    // Construtor com a Exceção Personalizada
    public Reserva(Hospede hospede, Acomodacao acomodacao, int quantidadeDiarias) throws PeriodoInvalidoException {
        
        // REGRA DE NEGÓCIO: A diária não pode ser zero ou negativa
        if (quantidadeDiarias <= 0) {
            throw new PeriodoInvalidoException("A reserva deve ter pelo menos 1 diária!");
        }

        this.hospede = hospede;
        this.acomodacao = acomodacao;
        this.quantidadeDiarias = quantidadeDiarias;
        
        // Inicializa a lista de pacotes vazia
        this.pacotes = new ArrayList<>();
        
        // Muda o status da acomodação para ocupada
        this.acomodacao.reservarQuarto();
    }

    // Método para adicionar pacotes extras na reserva
    public void adicionarPacote(PacoteTuristico pacote) {
        this.pacotes.add(pacote);
        System.out.println("✅ Pacote '" + pacote.getNome() + "' adicionado à reserva de " + hospede.getNome());
    }

    // Método para exibir o resumo
    public void exibirResumoReserva() {
        System.out.println("\n🎫 === RESUMO DA RESERVA ===");
        System.out.println("Hóspede: " + this.hospede.getNome());
        System.out.println("Documento: " + this.hospede.getDocumento());
        System.out.println("Acomodação: Quarto " + this.acomodacao.getNumeroDoQuarto() + " (R$ " + this.acomodacao.getPreco() + "/dia)");
        System.out.println("Duração: " + this.quantidadeDiarias + " diária(s)");
        
        if (!pacotes.isEmpty()) {
            System.out.println("Pacotes Adicionais:");
            for (PacoteTuristico p : pacotes) {
                System.out.println(" - " + p.getNome() + " (R$ " + p.getValor() + ")");
            }
        } else {
            System.out.println("Pacotes Adicionais: Nenhum");
        }
        System.out.println("=============================");
    }

    // Getters necessários para a calculadora de tarifas acessar os dados depois
    public Hospede getHospede() { return hospede; }
    public Acomodacao getAcomodacao() { return acomodacao; }
    public int getQuantidadeDiarias() { return quantidadeDiarias; }
    public ArrayList<PacoteTuristico> getPacotes() { return pacotes; }

    // Método para o Update (Atualizar a reserva) reutilizando a regra de negócio
    public void setQuantidadeDiarias(int quantidadeDiarias) throws pousadaselva.exception.PeriodoInvalidoException {
        if (quantidadeDiarias <= 0) {
            throw new pousadaselva.exception.PeriodoInvalidoException("A reserva deve ter pelo menos 1 diária!");
        }
        this.quantidadeDiarias = quantidadeDiarias;
    }
}