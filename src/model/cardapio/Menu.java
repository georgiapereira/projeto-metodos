package src.model.cardapio;

import java.util.ArrayList;
import java.util.List;

/**
 classe composição que representa uma categoria ou um menu que contém outros
 componentes-outras categorias ou itens individuais*/
public class Menu implements ComponenteCardapio {
    private String nome;
    private List<ComponenteCardapio> componentes = new ArrayList<>();

    public Menu(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteCardapio componente) {
        componentes.add(componente);
    }

    public void remover(ComponenteCardapio componente) {
        componentes.remove(componente);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        //uma categoria não tem preço próprio, mas poderia calcular o total dos itens se necessário
        //por enquanto, retorna 0
        return 0;
    }

    @Override
    public void exibir() {
        System.out.println("\n+ " + getNome());
        System.out.println("-----------------------------------------");
        for (ComponenteCardapio componente : componentes) {
            componente.exibir();
        }
    }
}