public class Main {
    public static void main(String[] args) {
        System.out.println("Montando a estrutura hierárquica do cadastro do colaborador...");

        //cria a raiz do cadastro
        SecaoComposta cadastroColaborador = new SecaoComposta("Cadastro do Colaborador");

        //cria as seções principais
        SecaoComposta dadosPessoais = new SecaoComposta("Dados Pessoais");
        SecaoComposta dadosAdmissional = new SecaoComposta("Dados Admissional");
        SecaoComposta endereco = new SecaoComposta("Endereço");
        SecaoComposta documentos = new SecaoComposta("Documentos");

        //adiciona as seções principais ao cadastro
        cadastroColaborador.adicionar(dadosPessoais);
        cadastroColaborador.adicionar(dadosAdmissional);
        cadastroColaborador.adicionar(endereco);
        cadastroColaborador.adicionar(documentos);

        //populando a seção Dados Pessoais com suas subseções
        SecaoComposta dependentes = new SecaoComposta("Dependentes");
        dependentes.adicionar(new ItemCadastro("Filho(a): João da Silva"));
        dependentes.adicionar(new ItemCadastro("Cônjuge: Maria da Silva"));
        
        SecaoComposta contatos = new SecaoComposta("Contatos");
        contatos.adicionar(new ItemCadastro("Email: colaborador@email.com"));
        contatos.adicionar(new ItemCadastro("Telefone: (83) 99999-9999"));
        
        dadosPessoais.adicionar(dependentes);
        dadosPessoais.adicionar(contatos);

        //populando as outras seções com itens simples
        dadosAdmissional.adicionar(new ItemCadastro("Cargo: Desenvolvedor de Software"));
        dadosAdmissional.adicionar(new ItemCadastro("Data de Admissão: 01/10/2025"));
        dadosAdmissional.adicionar(new ItemCadastro("Salário: R$ 5000,00"));

        endereco.adicionar(new ItemCadastro("Residencial: Rua das Flores, 123"));

        documentos.adicionar(new ItemCadastro("RG: 1.234.567"));
        documentos.adicionar(new ItemCadastro("CPF: 123.456.789-00")); 
        documentos.adicionar(new ItemCadastro("CTPS: 9876543"));

        //exibe a estrutura completa
        //achamada de um único método monta toda a estrutura recursivamente.
        System.out.println("\n--- Estrutura do Cadastro ---");
        cadastroColaborador.exibir("");
    }
}