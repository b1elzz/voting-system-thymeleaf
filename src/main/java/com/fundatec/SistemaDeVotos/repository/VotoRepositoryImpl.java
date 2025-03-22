package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.TotalVotosRestaurante;
import com.fundatec.SistemaDeVotos.model.Voto;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

/**
 * Implementação customizada de repositório para consultas específicas envolvendo a entidade {@link Voto}.
 *
 * <p>Utiliza {@link EntityManager} e JPQL para executar queries que não são facilmente
 * expressas através da interface {@link JpaRepository}, como contagem de votos por restaurante e
 * verificação de votos por data.</p>
 */
@Repository
public class VotoRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca o total de votos por restaurante no dia atual.
     *
     * @return lista de objetos {@link TotalVotosRestaurante} contendo o nome do restaurante e a contagem de votos
     */
    public List<TotalVotosRestaurante> buscar() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT new com.fundatec.SistemaDeVotos.model.TotalVotosRestaurante(e.nomeRestaurante, COUNT(a.id)) ");
        sb.append("FROM Voto a ");
        sb.append("INNER JOIN a.restaurante e ");
        sb.append("WHERE a.data = CURRENT_DATE ");
        sb.append("GROUP BY e.nomeRestaurante ");
        sb.append("ORDER BY COUNT(a.id) DESC");

        TypedQuery<TotalVotosRestaurante> query = entityManager.createQuery(sb.toString(), TotalVotosRestaurante.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Verifica se um funcionário já votou em uma determinada data.
     *
     * @param funcionarioId o ID do funcionário
     * @param data          a data da votação
     * @return {@code true} se já houver um voto do funcionário nessa data, {@code false} caso contrário
     */
    public boolean votoPorData(Integer funcionarioId, Calendar data) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(v) FROM Voto v WHERE v.funcionario.id = :id AND v.data = :data", Long.class);
        query.setParameter("id", funcionarioId);
        query.setParameter("data", data, TemporalType.DATE);
        return query.getSingleResult() > 0;
    }
}
