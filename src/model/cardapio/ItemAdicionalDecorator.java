package src.model.cardapio;

//classe decorator abstrata-segue a mesma interface que os objetos que ela decora
public abstract class ItemAdicionalDecorator implements ComponenteCardapio {
    protected ComponenteCardapio item; //referência para o objeto que está sendo decorado

    public ItemAdicionalDecorator(ComponenteCardapio item) {
        this.item = item;
    }

    //a implementação padrão é delegar a chamada para o objeto envelopado
    @Override
    public String getNome() {
        return item.getNome();
    }

    @Override
    public double getPreco() {
        return item.getPreco();
    }
}