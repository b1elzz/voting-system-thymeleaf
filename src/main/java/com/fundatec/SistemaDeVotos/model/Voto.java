package com.fundatec.SistemaDeVotos.model;

import jakarta.persistence.*;
import java.util.Calendar;
import java.util.Objects;

/**
 * Representa um voto registrado por um funcionário em um restaurante.
 *
 * <p>Cada voto é associado a uma data, um funcionário e um restaurante.
 * A data é armazenada sem hora (apenas o dia), e a entidade é persistida no banco.</p>
 */
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_sequence")
    @SequenceGenerator(name = "voto_sequence", sequenceName = "voto_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Calendar data;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    public Voto() {}

    public Voto(Calendar data, Funcionario funcionario, Restaurante restaurante) {
        this.data = data;
        this.funcionario = funcionario;
        this.restaurante = restaurante;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Calendar getData() {
        return data;
    }


    public void setData(Calendar data) {
        this.data = data;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }


    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


    public Restaurante getRestaurante() {
        return restaurante;
    }


    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }


    @Override
    public int hashCode() { return Objects.hash(id, data, funcionario, restaurante); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Voto)) return false;
        Voto other = (Voto) obj;
        return Objects.equals(id, other.id) && Objects.equals(data, other.data)
                && Objects.equals(funcionario, other.funcionario) && Objects.equals(restaurante, other.restaurante);
    }

    @Override
    public String toString() {
        return String.format("Voto [ID=%d, Data=%tF, Funcionário=%s, Restaurante=%s]",
                id, data, funcionario.getNomeFuncionario(), restaurante.getNomeRestaurante());
    }
}