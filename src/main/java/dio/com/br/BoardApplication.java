package dio.com.br;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dio.com.br.persistence.config.ConnectionConfig;
import dio.com.br.persistence.migration.MigrationStrategy;
import dio.com.br.ui.MainMenu;

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BoardApplication.class, args);
		
		try (var connection = ConnectionConfig.getConnection()) {
			new MigrationStrategy(connection).executeMigration();
		}
		
		new MainMenu().execute();
	}

}
