package src.model.cardapio;

/**
 classe folha que representa um item individual no cardápio, como um prato ou uma bebida-não pode ter filhos
 */
public class ItemCardapio implements ComponenteCardapio {
    private String nome;
    private String descricao;
    private double preco;

    public ItemCardapio(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void exibir() {
        System.out.printf("  - %-25s | %s (R$ %.2f)\n", getNome(), descricao, getPreco());
    }
}