package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável por operações de persistência da entidade {@link Restaurante}.
 *
 * <p>Herda os métodos do {@link JpaRepository}, como salvar, excluir, buscar por ID, e adiciona
 * uma busca específica por nome.</p>
 */
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    /**
     * Busca um restaurante pelo nome exato.
     *
     * @param nomeRestaurante o nome do restaurante
     * @return o restaurante encontrado ou {@code null} se não existir
     */
    Restaurante findByNomeRestaurante(String nomeRestaurante);
}
