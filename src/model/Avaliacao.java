package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * NOVO MODELO: Representa o conjunto de avaliações de um único usuário.
 * Armazena múltiplos comentários e notas para calcular uma média.
 */
public class Avaliacao {
    private String userLogin;
    private List<String> comentarios;
    private List<Integer> notas;

    public Avaliacao(String userLogin) {
        this.userLogin = userLogin;
        this.comentarios = new ArrayList<>();
        this.notas = new ArrayList<>();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    /**
     * Calcula a média de todas as notas recebidas.
     * @return A média das notas ou 0 se não houver notas.
     */
    public double getMediaDeNotas() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        // Usando streams para um cálculo mais moderno
        return notas.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
    }

    /**
     * Adiciona um novo feedback (comentário e/ou nota) a este registro de avaliação.
     * @param comentario O comentário feito pelo usuário.
     * @param nota A nota de 0 a 5.
     */
    public void adicionarAvaliacao(String comentario, int nota) {
        if (comentario != null && !comentario.isEmpty()) {
            // Garante o limite de 100 caracteres
            if (comentario.length() > 100) {
                this.comentarios.add(comentario.substring(0, 100));
            } else {
                this.comentarios.add(comentario);
            }
        }
        if (nota >= 0 && nota <= 5) {
            this.notas.add(nota);
        }
    }
}