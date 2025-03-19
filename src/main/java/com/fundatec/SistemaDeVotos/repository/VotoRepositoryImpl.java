package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.TotalVotosRestaurante;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class VotoRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TotalVotosRestaurante> buscar() {
        StringBuffer sb = new StringBuffer(100);
        sb.append("SELECT new main.java.com.fundatec.SistemaDeVotos.model.TotalVotosRestaurante(e.nome, COUNT(a.id)) ");
        sb.append("FROM Voto a ");
        sb.append("INNER JOIN a.restaurante e ");
        sb.append("WHERE a.data = CURRENT_DATE ");
        sb.append("GROUP BY e.nome ");
        sb.append("ORDER BY COUNT(a.id) DESC");

        TypedQuery<TotalVotosRestaurante> query = entityManager.createQuery(sb.toString(), TotalVotosRestaurante.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean votoPorData(Integer funcionarioId, Calendar data) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(v) FROM Voto v WHERE v.funcionario.id = :id AND v.data = :data", Long.class);
        query.setParameter("id", funcionarioId);
        query.setParameter("data", data, TemporalType.DATE);
        return query.getSingleResult() > 0;
    }



}
