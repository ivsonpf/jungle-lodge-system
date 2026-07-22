package pousadaselva.service;

import pousadaselva.model.Reserva;
import pousadaselva.model.PacoteTuristico;
import pousadaselva.model.HospedeEstrangeiro;

public class CalculadoraTarifa {

    // Método que recebe uma reserva pronta e processa toda a matemática
    public void processarFatura(Reserva reserva) {
        
        // 1. Cálculo base: Diária x Quantidade de Dias
        double totalDiarias = reserva.getQuantidadeDiarias() * reserva.getAcomodacao().getPreco();
        
        // 2. Cálculo dos pacotes (soma o valor de todos os pacotes extras contratados)
        double totalPacotes = 0.0;
        for (PacoteTuristico p : reserva.getPacotes()) {
            totalPacotes += p.getValor();
        }
        
        double subtotal = totalDiarias + totalPacotes;
        double totalFinal = subtotal;
        
        System.out.println("\n💰 === EXTRATO E FATURAMENTO ===");
        System.out.println("Subtotal Diárias: R$ " + totalDiarias);
        System.out.println("Subtotal Pacotes: R$ " + totalPacotes);
        
        // 3. REGRA DE NEGÓCIO DO EDITAL: Tarifa diferenciada (Ex: 10% de taxa de conversão/turismo)
        // O uso do 'instanceof' mostra domínio avançado de POO
        if (reserva.getHospede() instanceof HospedeEstrangeiro) {
            double taxaEstrangeiro = subtotal * 0.10; // Calcula 10%
            totalFinal += taxaEstrangeiro;
            System.out.println("Taxa Turista Estrangeiro (10%): R$ " + taxaEstrangeiro);
        } else {
            System.out.println("Taxa Turista Estrangeiro: Isento (Hóspede Nacional)");
        }
        
        System.out.println("-------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + totalFinal);
        System.out.println("===============================");
    }
}