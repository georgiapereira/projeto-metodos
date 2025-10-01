//representa os objetos "folha" da árvore, ou seja, seções que não contêm outras subseções-salário, RG, CPF
public class ItemCadastro implements ComponenteCadastro {
    private String nome;

    public ItemCadastro(String nome) {
        this.nome = nome;
    }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "- " + nome);
    }
}