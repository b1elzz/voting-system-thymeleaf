package com.fundatec.SistemaDeVotos.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Representa um funcionário participante do sistema de votação.
 *
 * <p>Cada funcionário possui um identificador único e um nome. Esta entidade
 * é persistida no banco de dados e utilizada na associação de votos.</p>
 */
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_sequence")
    @SequenceGenerator(name = "funcionario_sequence", sequenceName = "funcionario_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nomeFuncionario;

    public Funcionario() {}

    public Funcionario(Integer id, String nomeFuncionario) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id) && Objects.equals(nomeFuncionario, that.nomeFuncionario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeFuncionario);
    }

    @Override
    public String toString() {
        return String.format("Funcionário [ID=%d, Nome='%s']", id, nomeFuncionario);
    }
}