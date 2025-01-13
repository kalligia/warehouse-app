package gr.giatzi.warehouseapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WarehouseApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		System.setProperty("MYSQL_HOST", dotenv.get("MYSQL_HOST"));
		System.setProperty("MYSQL_PORT", dotenv.get("MYSQL_PORT"));
		System.setProperty("MYSQL_DB", dotenv.get("MYSQL_DB"));
		System.setProperty("MYSQL_USER", dotenv.get("MYSQL_USER"));
		System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));

		SpringApplication.run(WarehouseApplication.class, args);
	}

}
