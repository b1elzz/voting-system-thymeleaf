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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "label_sequence")
    @SequenceGenerator(name = "label_sequence", sequenceName = "label_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    /**
     * Nome do restaurante.
     */
    @Column(name = "nome")
    private String nomeRestaurante;

    public Restaurante() {}

    /**
     * Construtor com todos os campos.
     *
     * @param id               o identificador do restaurante
     * @param nomeRestaurante o nome do restaurante
     */
    public Restaurante(Integer id, String nomeRestaurante) {
        this.id = id;
        this.nomeRestaurante = nomeRestaurante;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNomeRestaurante() { return nomeRestaurante; }

    public void setNomeRestaurante(String nomeRestaurante) { this.nomeRestaurante = nomeRestaurante; }

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
