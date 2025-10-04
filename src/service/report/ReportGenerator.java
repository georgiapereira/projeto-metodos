package src.service.report;

import src.controller.ControllerFacadeSingleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata que define o Template Method para a geração de relatórios.
 * O método `generateReport` é o método principal que define o esqueleto do algoritmo.
 */
public abstract class ReportGenerator {

    protected final ControllerFacadeSingleton controllerFacade;

    public ReportGenerator() {
        this.controllerFacade = ControllerFacadeSingleton.getInstance();
    }

    /**
     * O Template Method. Ele define a ordem da execução dos passos para gerar o relatório.
     * Este método é final para que as subclasses não possam sobrescrevê-lo.
     * @return O relatório completo como uma String.
     */
    public final String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append(getHeader());
        report.append(getContent());
        report.append(getFooter());
        return report.toString();
    }

    /**
     * Passo abstrato para obter o cabeçalho do relatório.
     * Deve ser implementado pelas subclasses.
     * @return O cabeçalho do relatório.
     */
    protected abstract String getHeader();

    /**
     * Passo abstrato para obter o conteúdo principal do relatório.
     * Deve ser implementado pelas subclasses.
     * @return O conteúdo principal do relatório.
     */
    protected abstract String getContent();

    /**
     * Passo concreto (com implementação padrão) para obter o rodapé do relatório.
     * Pode ser sobrescrito pelas subclasses se um rodapé diferente for necessário.
     * @return O rodapé do relatório.
     */
    protected String getFooter() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return String.format("%n----------------------------------------%nRelatório gerado em: %s%n", dtf.format(now));
    }
}