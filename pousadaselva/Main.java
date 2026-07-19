package pousadaselva;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        // 1. Criando a lista e adicionando as acomodações (Nosso banco de dados em memória)
        ArrayList<Acomodacao> listaQuartos = new ArrayList<>();
        listaQuartos.add(new CabanaFlutuante(101, 850.00, 40.0, 1, 0, 0, 2, true, 12.5));
        listaQuartos.add(new BangaloNaArvore(201, 1200.00, 35.0, 1, 0, 1, 1, 15.0, "Ponte Suspensa"));
        listaQuartos.add(new OcaFamiliar(301, 950.00, 60.0, 1, 0, 3, 4, 3.5, 2.0));

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("🌿 BEM-VINDO AO SISTEMA POUSADA SELVA 🌿");

        // 2. O Menu Interativo
        do {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1 - Ver Catálogo de Acomodações");
            System.out.println("2 - Fazer uma Reserva");
            System.out.println("3 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                // Lê a opção do usuário
                opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Catálogo Disponível ---");
                        for (Acomodacao quarto : listaQuartos) {
                            quarto.exibirDetalhes();
                            // Teste ambiental da interface
                            if (quarto instanceof RegrasSustentabilidade) {
                                RegrasSustentabilidade eco = (RegrasSustentabilidade) quarto;
                                eco.avaliarTratamentoEsgoto();
                            }
                        }
                        break;
                        
                    case 2:
                        System.out.print("\nDigite o Número do Quarto que deseja reservar: ");
                        int numQuarto = scanner.nextInt();
                        boolean quartoEncontrado = false;

                        // Busca o quarto na lista
                        for (Acomodacao quarto : listaQuartos) {
                            if (quarto.getNumeroDoQuarto() == numQuarto) {
                                quarto.reservarQuarto();
                                quartoEncontrado = true;
                                break;
                            }
                        }

                        if (!quartoEncontrado) {
                            System.out.println("Aviso: Quarto não encontrado no sistema.");
                        }
                        break;
                        
                    case 3:
                        System.out.println("\n🌿 Encerrando o Sistema Pousada Selva. Até logo! 🌿");
                        break;
                        
                    default:
                        System.out.println("Opção inválida! Escolha 1, 2 ou 3.");
                }
            } catch (Exception e) {
                // A BLINDAGEM: Se o usuário digitar uma letra, o sistema cai aqui e não trava!
                System.out.println("Erro: Por favor, digite apenas números.");
                scanner.nextLine(); // Limpa o "lixo" que o usuário digitou
            }

        } while (opcao != 3); // O menu repete até o usuário escolher 3

        scanner.close(); // Boa prática: fechar o scanner ao final
    }
}