package src.model.cardapio;

// Decorador concreto que adiciona a responsabilidade de um "Ovo Extra".
public class OvoExtraDecorator extends ItemAdicionalDecorator {
    public OvoExtraDecorator(ComponenteCardapio item) {
        super(item);
    }

    @Override
    public String getNome() {
        //adiciona + Ovo Extra na descrição do prato base
        return super.getNome() + " + Ovo Extra";
    }

    @Override
    public double getPreco() {
        //adiciona R$ 2,00 ao preço do prato base
        return super.getPreco() + 2.00;
    }

    @Override
    public void exibir() {
        System.out.printf("  - %-35s | (R$ %.2f)\n", getNome(), getPreco());
    }
}