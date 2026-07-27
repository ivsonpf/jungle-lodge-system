package pousadaselva.main;

import pousadaselva.model.Acomodacao;
import pousadaselva.model.CabanaFlutuante;
import pousadaselva.model.BangaloNaArvore;
import pousadaselva.model.OcaFamiliar;
import pousadaselva.model.Hospede;
import pousadaselva.model.HospedeNacional;
import pousadaselva.model.HospedeEstrangeiro;
import pousadaselva.model.PacoteTuristico;
import pousadaselva.model.Reserva;
import pousadaselva.exception.PeriodoInvalidoException;
import pousadaselva.service.RegrasSustentabilidade;
import pousadaselva.service.CalculadoraTarifa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        // 1. BANCO DE DADOS EM MEMÓRIA (Coleções exigidas pelo edital)
        ArrayList<Acomodacao> listaQuartos = new ArrayList<>();
        ArrayList<PacoteTuristico> listaPacotes = new ArrayList<>();
        ArrayList<Reserva> listaReservas = new ArrayList<>();

        // Populando as Acomodações
        listaQuartos.add(new CabanaFlutuante(101, 850.00, 40.0, 1, 0, 0, 2, true, 12.5));
        listaQuartos.add(new BangaloNaArvore(201, 1200.00, 35.0, 1, 0, 1, 1, 15.0, "Ponte Suspensa"));
        listaQuartos.add(new OcaFamiliar(301, 950.00, 60.0, 1, 0, 3, 4, 3.5, 2.0));

        // Populando os Pacotes Turísticos
        listaPacotes.add(new PacoteTuristico("Trilha Ecológica", "Caminhada guiada de 2h na selva", 150.00));
        listaPacotes.add(new PacoteTuristico("Focagem de Jacarés", "Passeio noturno de barco", 200.00));
        listaPacotes.add(new PacoteTuristico("Encontro das Águas", "Passeio de lancha até o encontro dos rios", 350.00));

        CalculadoraTarifa calculadora = new CalculadoraTarifa();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("🌿 BEM-VINDO AO SISTEMA POUSADA SELVA 🌿");

        // 2. MENU INTERATIVO EXPANDIDO (AGORA COM CRUD COMPLETO)
        do {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1 - Ver Catálogo de Acomodações");
            System.out.println("2 - Ver Pacotes Turísticos");
            System.out.println("3 - Cadastrar Nova Reserva");
            System.out.println("4 - Listar Reservas Ativas");
            System.out.println("5 - Atualizar Diárias de uma Reserva");
            System.out.println("6 - Cancelar/Remover Reserva");
            System.out.println("7 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                
                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Catálogo Disponível ---");
                        for (Acomodacao quarto : listaQuartos) {
                            quarto.exibirDetalhes();
                            if (quarto instanceof RegrasSustentabilidade) {
                                RegrasSustentabilidade eco = (RegrasSustentabilidade) quarto;
                                eco.avaliarTratamentoEsgoto();
                            }
                        }
                        break;
                        
                    case 2:
                        System.out.println("\n--- Pacotes Turísticos ---");
                        for (int i = 0; i < listaPacotes.size(); i++) {
                            System.out.print("[" + i + "] ");
                            listaPacotes.get(i).exibirDetalhes();
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Cadastro de Reserva ---");
                        
                        // A. DADOS DO HÓSPEDE
                        System.out.print("Nome do Hóspede: ");
                        String nome = scanner.nextLine();
                        
                        System.out.print("É estrangeiro? (1-Sim / 2-Não): ");
                        int tipoHospede = scanner.nextInt();
                        scanner.nextLine();
                        
                        Hospede hospede;
                        if (tipoHospede == 1) {
                            System.out.print("Passaporte: ");
                            String passaporte = scanner.nextLine();
                            System.out.print("País de Origem: ");
                            String pais = scanner.nextLine();
                            hospede = new HospedeEstrangeiro(nome, passaporte, pais);
                        } else {
                            System.out.print("CPF: ");
                            String cpf = scanner.nextLine();
                            hospede = new HospedeNacional(nome, cpf);
                        }

                        // B. ESCOLHA DA ACOMODAÇÃO
                        System.out.print("Digite o Número do Quarto: ");
                        int numQuarto = scanner.nextInt();
                        Acomodacao acomodacaoEscolhida = null;

                        for (Acomodacao q : listaQuartos) {
                            if (q.getNumeroDoQuarto() == numQuarto) {
                                acomodacaoEscolhida = q;
                                break;
                            }
                        }

                        if (acomodacaoEscolhida == null) {
                            System.out.println("❌ Quarto não encontrado. Operação cancelada.");
                            break;
                        }

                        // C. DURAÇÃO
                        System.out.print("Quantidade de Diárias: ");
                        int diarias = scanner.nextInt();

                        // D. CRIANDO A RESERVA COM TRATAMENTO DE EXCEÇÃO
                        try {
                            Reserva novaReserva = new Reserva(hospede, acomodacaoEscolhida, diarias);

                            // E. ADICIONANDO PACOTES (Opcional)
                            System.out.print("Deseja adicionar pacotes de passeio? (1-Sim / 2-Não): ");
                            int addPacote = scanner.nextInt();
                            if (addPacote == 1) {
                                System.out.print("Digite o código do pacote (0 a " + (listaPacotes.size()-1) + "): ");
                                int codPacote = scanner.nextInt();
                                if (codPacote >= 0 && codPacote < listaPacotes.size()) {
                                    novaReserva.adicionarPacote(listaPacotes.get(codPacote));
                                } else {
                                    System.out.println("Código de pacote inválido.");
                                }
                            }

                            // F. FINALIZANDO E CALCULANDO
                            listaReservas.add(novaReserva);
                            novaReserva.exibirResumoReserva();
                            calculadora.processarFatura(novaReserva);
                            
                        } catch (PeriodoInvalidoException ex) {
                            System.out.println("\n❌ ERRO NA RESERVA: " + ex.getMessage());
                            System.out.println("Operação cancelada. Tente novamente com um período válido.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Reservas Ativas ---");
                        if (listaReservas.isEmpty()) {
                            System.out.println("Nenhuma reserva cadastrada no momento.");
                        } else {
                            for (Reserva r : listaReservas) {
                                r.exibirResumoReserva();
                            }
                        }
                        break;
                        
                    case 5:
                        System.out.println("\n--- Atualizar Reserva ---");
                        System.out.print("Digite o documento (CPF/Passaporte) do hóspede: ");
                        String docAtualizar = scanner.nextLine();
                        boolean encontradaUpdate = false;

                        for (Reserva r : listaReservas) {
                            if (r.getHospede().getDocumento().equals(docAtualizar)) {
                                encontradaUpdate = true;
                                System.out.println("Reserva encontrada! Diárias atuais: " + r.getQuantidadeDiarias());
                                System.out.print("Digite a nova quantidade de diárias: ");
                                int novasDiarias = scanner.nextInt();
                                
                                try {
                                    r.setQuantidadeDiarias(novasDiarias); // Usa o setter com tratamento de erro
                                    System.out.println("✅ Reserva atualizada com sucesso!");
                                    calculadora.processarFatura(r); // Recalcula e mostra a tarifa nova
                                } catch (PeriodoInvalidoException ex) {
                                    System.out.println("❌ ERRO NA ATUALIZAÇÃO: " + ex.getMessage());
                                }
                                break;
                            }
                        }
                        if (!encontradaUpdate) {
                            System.out.println("Reserva não encontrada para o documento informado.");
                        }
                        break;

                    case 6:
                        System.out.println("\n--- Cancelar Reserva ---");
                        System.out.print("Digite o documento (CPF/Passaporte) do hóspede: ");
                        String docRemover = scanner.nextLine();
                        boolean encontradaDelete = false;

                        // Percorre a lista para achar e deletar
                        for (int i = 0; i < listaReservas.size(); i++) {
                            if (listaReservas.get(i).getHospede().getDocumento().equals(docRemover)) {
                                listaReservas.remove(i);
                                encontradaDelete = true;
                                System.out.println("🗑️ Reserva cancelada e removida do sistema com sucesso!");
                                break;
                            }
                        }
                        if (!encontradaDelete) {
                            System.out.println("Reserva não encontrada para o documento informado.");
                        }
                        break;
                        
                    case 7:
                        System.out.println("\n🌿 Encerrando o Sistema Pousada Selva. Até logo! 🌿");
                        break;
                        
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro de entrada: Por favor, digite valores válidos.");
                scanner.nextLine(); 
            }

        } while (opcao != 7);

        scanner.close();
    }
}