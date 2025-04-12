package com.fundatec.SistemaDeVotos.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para validação de dados de entrada ao cadastrar um funcionário.
 */
public class FuncionarioDTO {

    @NotBlank(message = "O nome do funcionário é obrigatório")
    private String nomeFuncionario;

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
}