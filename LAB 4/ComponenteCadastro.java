//define a interface comum para todos os componentes (seções ou itens) do cadastro
//isso garante que tanto seções compostas quanto itens individuais possam ser tratados de forma uniforme
public interface ComponenteCadastro {
    void exibir(String indentacao);
}