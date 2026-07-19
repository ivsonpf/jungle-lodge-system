package pousadaselva;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("🌿 BEM-VINDO AO SISTEMA POUSADA SELVA 🌿\n");

        // 1. Criando a lista (O nosso "banco de dados" na memória)
        ArrayList<Acomodacao> listaQuartos = new ArrayList<>();

        // 2. Instanciando as acomodações com seus dados 
        // Ordem: (numero, preco, tamanho, camaKing, camaCasal, camaSolteiro, redes, [atributos exclusivos])
        CabanaFlutuante cabana1 = new CabanaFlutuante(101, 850.00, 40.0, 1, 0, 0, 2, true, 12.5);
        BangaloNaArvore bangalo1 = new BangaloNaArvore(201, 1200.00, 35.0, 1, 0, 1, 1, 15.0, "Ponte Suspensa");
        OcaFamiliar oca1 = new OcaFamiliar(301, 950.00, 60.0, 1, 0, 3, 4, 3.5, 2.0);

        // 3. Adicionando as acomodações na nossa lista genérica
        listaQuartos.add(cabana1);
        listaQuartos.add(bangalo1);
        listaQuartos.add(oca1);

        // 4. Simulando reservas (Testando os métodos herdados da classe mãe)
        System.out.println("--- Teste de Sistema de Reservas ---");
        cabana1.reservarQuarto();
        cabana1.reservarQuarto(); // Tentando reservar de novo para forçar a mensagem de "já ocupado"
        System.out.println();

        // 5. O Polimorfismo e as Interfaces em ação (O loop mágico)
        System.out.println("--- Catálogo de Acomodações ---");
        
        for (Acomodacao quarto : listaQuartos) {
            
            // A mágica 1: O Java sabe chamar a ficha técnica correta de cada filha
            quarto.exibirDetalhes();
            
            // A mágica 2: Testando o contrato de sustentabilidade
            // Como a interface foi assinada pelas filhas, usamos o 'instanceof' para 
            // garantir que o quarto atual possui as regras ambientais antes de testar
            if (quarto instanceof RegrasSustentabilidade) {
                RegrasSustentabilidade quartoEco = (RegrasSustentabilidade) quarto;
                quartoEco.avaliarTratamentoEsgoto();
                quartoEco.verificarCotaDeAguaDiaria();
            }
        }
        
        System.out.println("\n🌿 Sistema encerrado. 🌿");
    }
}