package src.service.report;

import src.model.User;
import java.util.List;

public class ActiveUsersReportGenerator extends ReportGenerator {

    @Override
    protected String getHeader() {
        return "========== RELATÓRIO DE USUÁRIOS ATIVOS ==========\n\n";
    }

    @Override
    protected String getContent() {
        List<User> activeUsers = controllerFacade.getActiveUsers();
        if (activeUsers.isEmpty()) {
            return "Nenhum usuário ativo encontrado no sistema.\n";
        }

        StringBuilder content = new StringBuilder();
        content.append(String.format("Total de usuários ativos: %d%n%n", activeUsers.size()));
        for (int i = 0; i < activeUsers.size(); i++) {
            User user = activeUsers.get(i);
            content.append(String.format("%d. Login: %s%n", i + 1, user.getLogin()));
        }
        return content.toString();
    }
}