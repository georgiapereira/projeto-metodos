![alt text](diagrama_classes.png)
# Projeto de Gerenciamento de Usuários e Feedback

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Andamento-orange?style=for-the-badge)
![Arquitetura](https://img.shields.io/badge/Arquitetura-MVC%2BService-blue?style=for-the-badge)

## 📝 Descrição

Este é um projeto acadêmico desenvolvido para a disciplina de **Métodos e Projetos de Software**. A aplicação, construída em Java, é um sistema de console para gerenciamento de usuários que evoluiu de um simples CRUD para uma arquitetura de camadas robusta, incorporando um sistema completo de feedback e avaliações.

O objetivo foi aplicar na prática conceitos de boas práticas de programação, como o **Princípio da Responsabilidade Única (SRP)**, tratamento de exceções customizadas e a separação de responsabilidades em camadas (Model-View-Controller-Service).

## ✨ Funcionalidades Principais

* **👤 Gerenciamento de Usuários (CRUD-like):**
    * **Cadastro:** Criação de novos usuários com validações robustas (duplicidade, formato, espaços em branco).
    * **Listagem:** Visualização separada de usuários ativos e usuários já excluídos.
    * **Exclusão:** Remoção de usuários da lista de ativos, com um fluxo de confirmação e a possibilidade opcional de deixar uma justificativa e nota de saída.

* **⭐ Sistema de Avaliação Contínuo:**
    * Qualquer usuário, seja **ativo ou excluído**, pode se autenticar e deixar avaliações sobre o serviço.
    * A primeira avaliação de um usuário requer obrigatoriamente um comentário e uma nota.
    * O sistema permite que o mesmo usuário deixe múltiplas avaliações, sendo as subsequentes opcionais.
    * Os comentários são armazenados em uma lista e as notas são usadas para calcular uma média contínua.

* **📊 Painel de Feedback:**
    * Uma tela dedicada para visualizar todas as avaliações, exibindo por usuário:
        * A lista completa de comentários.
        * A **média** de todas as notas recebidas.

* **⚙️ Experiência do Usuário:**
    * Interface de Linha de Comando (CLI) totalmente interativa e guiada por um menu de opções.
    * Tratamento de exceções para guiar o usuário em caso de entradas inválidas ou erros de operação.

## 🏛️ Arquitetura do Projeto

* **Apresentação (`main`, `UserView`):** Responsável por iniciar o sistema, exibir o menu e renderizar os dados para o usuário. Delega todas as ações para a camada de serviço.
* **Serviço (`UserService`):** Orquestra os fluxos de negócio. É o "cérebro" da aplicação, sabendo quais controllers chamar para completar uma tarefa.
* **Controle (`UserController`):** Gerencia o acesso e a manipulação direta das listas de dados (adicionar, remover, buscar).
* **Modelo (`Model`):** Representa os dados da aplicação e contém as regras de validação inerentes a esses dados.

## 🚀 Como Executar

Para compilar e executar o projeto, você precisará ter o **JDK (Java Development Kit)** versão 11 ou superior instalado e configurado no seu sistema.

1.  **Clone o repositório** (ou baixe os arquivos) para uma pasta de sua escolha.

2.  **Abra o terminal** na pasta raiz do projeto (a pasta que contém o diretório `src`).

3.  **Compile todos os arquivos .java:**
    Este comando compila todos os fontes e organiza os arquivos `.class` em um novo diretório chamado `bin`.
    ```bash
    javac -d bin src/**/*.java src/model/exception/*.java src/service/*.java
    ```

4.  **Execute a aplicação:**
    Este comando executa a classe principal, informando ao Java para procurar os arquivos compilados no diretório `bin`.
    ```bash
    java -cp bin src.main
    ```

## 🛠️ Tecnologias Utilizadas

* **Java (JDK 11+)**
* **Programação Orientada a Objetos**
* **Padrões de Arquitetura MVC e Service Layer**
