package com.fundatec.SistemaDeVotos.exception;

/**
 * Exceção lançada quando um funcionário tenta votar mais de uma vez no mesmo dia.
 *
 * <p>Essa exceção é verificada (checked) e deve ser tratada na camada de controle
 * ou serviço, exibindo uma mensagem clara ao usuário final.</p>
 *
 * <p>Ela reforça uma das regras de negócio do sistema de votação, garantindo que
 * cada funcionário possa registrar apenas um voto por dia.</p>
 */
public class FuncionarioJaVotouHojeException extends Exception {

    /**
     * Cria uma nova exceção com a mensagem informada.
     *
     * @param message a mensagem de erro descritiva
     */
    public FuncionarioJaVotouHojeException(String message) {
        super(message);
    }
}
