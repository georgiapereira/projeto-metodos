import java.util.ArrayList;
import java.util.List;

//representa um componente que pode ter filhos, como Dados Pessoais ou Documentos
public class SecaoComposta implements ComponenteCadastro {
    private String nome;
    private List<ComponenteCadastro> componentes = new ArrayList<>();

    public SecaoComposta(String nome) {
        this.nome = nome;
    }

    //permite adicionar seções e subseções dinamicamente
    public void adicionar(ComponenteCadastro componente) {
        componentes.add(componente);
    }

    //permite remover seções e subseções dinamicamente
    public void remover(ComponenteCadastro componente) {
        componentes.remove(componente);
    }

    //implementa a operação de forma recursiva, chamando o mesmo método para cada um de seus filhos
    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "+ " + nome);
        for (ComponenteCadastro componente : componentes) {
            componente.exibir(indentacao + "  ");
        }
    }
}