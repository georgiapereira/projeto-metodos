package src.service;

import src.model.cardapio.ItemCardapio;
import src.model.cardapio.Menu;

//lógica de negócio relacionada ao cardápio
public class CardapioService {

    public void gerenciarVisualizacaoCardapio() {
        //cria a estrutura do cardápio usando o padrão Composite
        Menu cardapioCompleto = new Menu("CARDÁPIO RU ALTO - O SABOR UNIVERSITÁRIO");

        //cria as categorias-Compositions
        Menu pratosPrincipais = new Menu("PRATOS PRINCIPAIS");
        Menu bebidas = new Menu("BEBIDAS");
        Menu sobremesas = new Menu("SOBREMESAS");

        //adiciona as categorias ao cardápio principal
        cardapioCompleto.adicionar(pratosPrincipais);
        cardapioCompleto.adicionar(bebidas);
        cardapioCompleto.adicionar(sobremesas);

        //adiciona itens Leafs à categoria de pratos principais
        pratosPrincipais.adicionar(new ItemCardapio("PF Universitário", "Arroz, feijão, farofa e uma proteína.", 12.00));
        pratosPrincipais.adicionar(new ItemCardapio("PF Vegano", "Arroz integral, feijão, salada e proteína de soja.", 13.50));
        pratosPrincipais.adicionar(new ItemCardapio("Macarronada", "Molho à bolonhesa ou molho branco.", 15.00));

        //adiciona itens à categoria de Bebidas
        bebidas.adicionar(new ItemCardapio("Refrigerante Lata", "Coca-Cola, Guaraná ou Fanta.", 4.50));
        bebidas.adicionar(new ItemCardapio("Suco Natural 300ml", "Laranja, Abacaxi ou Morango.", 6.00));
        bebidas.adicionar(new ItemCardapio("Água Mineral 500ml", "Com ou sem gás.", 3.00));

        //adiciona itens à categoria de Sobremesas
        sobremesas.adicionar(new ItemCardapio("Pudim de Leite", "Fatia generosa.", 5.00));
        sobremesas.adicionar(new ItemCardapio("Mousse de Maracujá", "Copo de 150ml.", 5.50));

        //exibe o cardápio completo com uma única chamada
        cardapioCompleto.exibir();
    }
}