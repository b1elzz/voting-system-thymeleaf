package com.fundatec.SistemaDeVotos.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO para validação de dados de entrada ao registrar um voto.
 */
public class VotoDTO {

    @NotNull(message = "O ID do funcionário é obrigatório")
    private Integer funcionarioId;

    @NotNull(message = "O ID do restaurante é obrigatório")
    private Integer restauranteId;


    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }


    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }
}