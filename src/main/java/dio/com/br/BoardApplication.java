package dio.com.br;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BoardApplication.class, args);
	}

}
