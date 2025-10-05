package src.service;

import src.model.cardapio.BatataFritaDecorator;
import src.model.cardapio.ComponenteCardapio;
import src.model.cardapio.ItemCardapio;
import src.model.cardapio.OvoExtraDecorator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {

    //lista de pratos base disponíveis para escolha
    private List<ItemCardapio> pratosBase;

    public PedidoService() {
        this.pratosBase = new ArrayList<>();
        pratosBase.add(new ItemCardapio("PF Universitário", "Arroz, feijão, farofa e uma proteína.", 12.00));
        pratosBase.add(new ItemCardapio("PF Vegano", "Arroz integral, feijão, salada e proteína de soja.", 13.50));
        pratosBase.add(new ItemCardapio("Macarronada", "Molho à bolonhesa ou molho branco.", 15.00));
    }

    /**gerencia a criação de um pedido
     */
    public void gerenciarPedidoPadrao(Scanner scanner) {
        System.out.println("\n--- Fazer Pedido (Item do Cardápio) ---");
        ComponenteCardapio pedido = selecionarItemBase(scanner);

        if (pedido != null) {
            System.out.println("\n--- Pedido Confirmado ---");
            pedido.exibir();
            System.out.println("-------------------------");
            System.out.println("Seu pedido foi enviado para a cozinha!");
        } else {
            System.out.println("Criação do pedido cancelada.");
        }
    }

    /**gerencia a criação de um pedido personalizado de forma interativa.
     */
    public void gerenciarPedidoPersonalizado(Scanner scanner) {
        System.out.println("\n--- Fazer Pedido (Personalizado) ---");
        ComponenteCardapio pedido = selecionarItemBase(scanner);

        if (pedido == null) {
            System.out.println("Criação do pedido cancelada.");
            return;
        }

        //loop para adicionar extras
        while (true) {
            System.out.println("\n--- Montando seu Pedido ---");
            System.out.println("Seu pedido atual:");
            System.out.printf("  Item: %s\n", pedido.getNome());
            System.out.printf("  Preço: R$ %.2f\n", pedido.getPreco());
            System.out.println("---------------------------");

            System.out.println("Adicionar extra:");
            System.out.println("1. Ovo Extra (R$ 2.00)");
            System.out.println("2. Batata Frita Extra (R$ 4.50)");
            System.out.println("0. Finalizar Pedido");
            System.out.print("Escolha uma opção: ");

            String escolha = scanner.nextLine();

            if (escolha.equals("1")) {
                pedido = new OvoExtraDecorator(pedido);
                System.out.println(">> Ovo Extra adicionado!");
            } else if (escolha.equals("2")) {
                pedido = new BatataFritaDecorator(pedido);
                System.out.println(">> Batata Frita Extra adicionada!");
            } else if (escolha.equals("0")) {
                break; // Sai do loop de adição de extras
            } else {
                System.out.println("Opção de extra inválida!");
            }
        }

        System.out.println("\n--- Pedido Finalizado e Confirmado ---");
        pedido.exibir();
        System.out.println("--------------------------------------");
        System.out.println("Seu pedido personalizado foi enviado para a cozinha!");
    }

    /**método auxiliar para exibir pratos base e permitir que o usuário escolha um
     @return o ItemCardapio escolhido ou null se a operação for cancelada
     */
    private ItemCardapio selecionarItemBase(Scanner scanner) {
        System.out.println("Por favor, escolha um prato base para o seu pedido:");
        for (int i = 0; i < pratosBase.size(); i++) {
            ItemCardapio item = pratosBase.get(i);
            System.out.printf("%d. %-25s (R$ %.2f)\n", (i + 1), item.getNome(), item.getPreco());
        }
        System.out.print("Digite o número do prato (ou 0 para cancelar): ");

        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= pratosBase.size()) {
                return pratosBase.get(escolha - 1);
            }
        } catch (NumberFormatException e) {
            //ignora o erro e o método retorna null
        }
        return null; //retorna null se a escolha for inválida ou 0
    }
}