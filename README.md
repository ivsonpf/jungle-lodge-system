# 🌿 Jungle Lodge System (PousadaSelva)

Um sistema em **Java** desenvolvido para o gerenciamento de reservas de uma pousada de ecoturismo na Amazônia. Este projeto foi construído com foco estrito na aplicação prática dos pilares da **Programação Orientada a Objetos (POO)**.

O sistema simula o catálogo e a gestão de acomodações perfeitamente integradas à natureza, com regras de negócio focadas em sustentabilidade e preservação ambiental.

---

## 🎯 Objetivo Acadêmico

Este projeto tem como finalidade demonstrar o domínio sobre os seguintes conceitos de POO:

*   **Abstração e Encapsulamento:** Utilização de uma classe mãe abstrata (`Acomodacao`) com modificadores de acesso `protected` para proteger os dados sensíveis dos quartos (como preço e disponibilidade), permitindo o acesso direto apenas pelas subclasses herdeiras.
*   **Herança (`extends`):** Criação de subclasses específicas que herdam características gerais de conforto e estrutura da classe mãe, reduzindo a repetição de código.
*   **Polimorfismo (`@Override`):** Sobrescrita do método `exibirDetalhes()` para que cada tipo de acomodação apresente sua ficha técnica de forma única e personalizada no terminal.
*   **Interfaces (`implements`):** Implementação de um "contrato" obrigatório (`RegrasSustentabilidade`) garantindo que todas as acomodações do sistema possuam métodos de tratamento de esgoto e controle de cota de água diária.
*   **Coleções Genéricas:** Uso de `ArrayList<Acomodacao>` para gerenciar dinamicamente a frota de quartos na memória durante a execução do programa.

---

## 🛖 Arquitetura das Acomodações

O sistema conta com três tipos exclusivos de estadias, inspiradas na arquitetura e vivência amazônica:

1.  **🌊 Cabana Flutuante:** Estruturas modulares sobre as águas, possuindo atributos exclusivos como profundidade do rio no local e disponibilidade de barco de acesso.
2.  **🌳 Bangalô na Árvore:** Acomodações nas copas das árvores, destacando-se pela altura do solo e métodos de acesso (ex: pontes suspensas).
3.  **🛖 Oca Familiar (Palafitas):** Inspirada nas habitações ribeirinhas e nativas, une a estrutura suspensa das estacas de madeira com a proximidade dos rios, considerando o nível da água na época da cheia.

---

## 💻 Tecnologias Utilizadas

*   **Linguagem:** Java
*   **IDE:** Visual Studio Code
*   **Controle de Versão:** Git / GitHub
*   **Ambiente de Desenvolvimento:** Linux

---

## ⚙️ Como Executar o Projeto

1. Clone este repositório em sua máquina local:
   ```bash
   git clone [https://github.com/ivsonpf/jungle-lodge-system.git](https://github.com/ivsonpf/jungle-lodge-system.git)
