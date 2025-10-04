package src.service.report;

import src.model.DeletedUser;
import java.util.List;

public class DeletedUsersReportGenerator extends ReportGenerator {

    @Override
    protected String getHeader() {
        return "========== RELATÓRIO DE USUÁRIOS EXCLUÍDOS ==========\n\n";
    }

    @Override
    protected String getContent() {
        List<DeletedUser> deletedUsers = controllerFacade.getDeletedUsers();
        if (deletedUsers.isEmpty()) {
            return "Nenhum usuário excluído encontrado no sistema.\n";
        }

        StringBuilder content = new StringBuilder();
        content.append(String.format("Total de usuários excluídos: %d%n%n", deletedUsers.size()));
        for (int i = 0; i < deletedUsers.size(); i++) {
            DeletedUser deletedUser = deletedUsers.get(i);
            content.append(String.format("%d. Login: %s%n", i + 1, deletedUser.getOriginalUser().getLogin()));
            content.append(String.format("   Justificativa: %s%n", deletedUser.getDeletionJustification().isEmpty() ? "Não fornecida" : deletedUser.getDeletionJustification()));
            content.append(String.format("   Avaliação na exclusão: %s%n", deletedUser.getDeletionRating() == -1 ? "Não fornecida" : deletedUser.getDeletionRating() + "/5"));
            content.append("\n");
        }
        return content.toString();
    }
}