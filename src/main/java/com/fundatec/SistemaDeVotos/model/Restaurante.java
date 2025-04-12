package com.fundatec.SistemaDeVotos.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Representa um restaurante que pode receber votos no sistema.
 *
 * <p>Um restaurante possui um identificador e um nome, sendo persistido
 * no banco de dados para associação com os votos registrados.</p>
 */
@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurante_sequence")
    @SequenceGenerator(name = "restaurante_sequence", sequenceName = "restaurante_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nomeRestaurante;

    public Restaurante() {}

    public Restaurante(Integer id, String nomeRestaurante) {
        this.id = id;
        this.nomeRestaurante = nomeRestaurante;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id) && Objects.equals(nomeRestaurante, that.nomeRestaurante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeRestaurante);
    }

    @Override
    public String toString() {
        return String.format("Restaurante [ID=%d, Nome='%s']", id, nomeRestaurante);
    }
}