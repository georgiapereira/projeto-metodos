package src.model.cardapio;

/**
 interface componente - define a operação comum para itens individuais-folhas
 e para categorias-composições*/
public interface ComponenteCardapio {
    void exibir();
    String getNome();
    double getPreco();
}