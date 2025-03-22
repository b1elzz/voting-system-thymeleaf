package com.fundatec.SistemaDeVotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal do sistema de votação.
 *
 * <p>Esta classe inicializa a aplicação Spring Boot e é o ponto de entrada do sistema.</p>
 *
 */
@SpringBootApplication
public class SistemaDeVotosApplication {

    /**
     *
     * Metodo principal que inicia a aplicação Spring Boot.
     *
     * @param args argumentos da linha de comando (opcional)
     */
    public static void main(String[] args) {
        SpringApplication.run(SistemaDeVotosApplication.class, args);
    }
}
