package dio.com.br.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dio.com.br.persistence.config.ConnectionConfig;
import dio.com.br.persistence.entity.BoardColumnEntity;
import dio.com.br.persistence.entity.BoardColumnKindEnum;
import dio.com.br.persistence.entity.BoardEntity;
import dio.com.br.service.BoardService;

public class MainMenu {

    private final Scanner sc = new Scanner(System.in);
    
    public void execute() throws SQLException {
        System.out.println("Bem-vindo ao gerenciador de boards, escolha a opção desejada");
        
        while (true) {
            System.out.println("1 - Criar um novo board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Sair");
            
            int option = sc.nextInt();
            switch (option) {
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, informe uma opção do menu");
            }
        }
    }

    private void createBoard() throws SQLException {
        var entity = new BoardEntity();
        System.out.println("Informe o nome do seu board:");
        entity.setName(sc.next());

        System.out.println("Seu board terá colunas além das 3 padrões? Se sim, informe quantas, senão digite '0'");
        int additionalColumn = sc.nextInt();

        List<BoardColumnEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board:");
        String initialColumnName = sc.next();
        var initialColumn = createColumn(initialColumnName, BoardColumnKindEnum.INITIAL, 0);
        columns.add(initialColumn);

        for (int i = 0; i < additionalColumn; i++) {
            System.out.println("Informe o nome da coluna de tarefa pendente do board:");
            String pendingColumnName = sc.next();
            var pendingColumn = createColumn(pendingColumnName, BoardColumnKindEnum.PENDING, i + 1);
            columns.add(pendingColumn);
        }

        int currentOrder = additionalColumn + 1;

        System.out.println("Informe o nome da coluna final do board:");
        String finalColumnName = sc.next();
        var finalColumn = createColumn(finalColumnName, BoardColumnKindEnum.FINAL, currentOrder++);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do board:");
        String cancelColumnName = sc.next();
        var cancelColumn = createColumn(cancelColumnName, BoardColumnKindEnum.CANCEL, currentOrder);
        columns.add(cancelColumn);

        entity.setBoardColumns(columns);
        try (var connection = ConnectionConfig.getConnection()) {
            var service = new BoardService(connection);
            service.insert(entity);
        }
    }

    private void selectBoard() throws SQLException {
        System.out.println("Informe o ID do board que deseja selecionar:");
        long id = sc.nextLong();
        try (var connection = ConnectionConfig.getConnection()) {
            var queryService = new BoardService(connection);
            var optional = queryService.findById(id);
            optional.ifPresentOrElse(
                b -> new BoardMenu(b).execute(), 
                () -> System.out.printf("Não foi encontrado um board com ID %d\n", id)
            );
        }
    }

    private void deleteBoard() throws SQLException {
        System.out.println("Informe o ID do board que vai ser excluído:");
        long id = sc.nextLong();
        try (var connection = ConnectionConfig.getConnection()) {
            var service = new BoardService(connection);
            if (service.delete(id)) {
                System.out.printf("O board %d foi excluído\n", id);
            } else {
                System.out.printf("Não foi encontrado um board com ID %d\n", id);
            }
        }
    }
    
    private BoardColumnEntity createColumn(final String name, final BoardColumnKindEnum kind, final int order) {
        var boardColumn = new BoardColumnEntity();
        boardColumn.setName(name);
        boardColumn.setKind(kind);
        boardColumn.setOrder(order);
        return boardColumn;
    }
}
