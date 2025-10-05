package src.model.cardapio;

//decorador concreto que adiciona a responsabilidade de batata frita extra
public class BatataFritaDecorator extends ItemAdicionalDecorator {
    public BatataFritaDecorator(ComponenteCardapio item) {
        super(item);
    }

    @Override
    public String getNome() {
        return super.getNome() + " + Batata Frita Extra";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 4.50;
    }

    @Override
    public void exibir() {
        System.out.printf("  - %-35s | (R$ %.2f)\n", getNome(), getPreco());
    }
}