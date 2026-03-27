package com.unisociesc.SistemaFolhaPagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Payroll System application.
 *
 * <p>This class bootstraps the Spring Boot application context,
 * triggering component scanning, auto-configuration, and all
 * bean initialization defined across the project.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 */
@SpringBootApplication
public class SistemaFolhaPagamentoApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 *
	 * @param args command-line arguments passed at startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(SistemaFolhaPagamentoApplication.class, args);
	}

}