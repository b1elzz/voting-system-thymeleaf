package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório padrão para operações de persistência da entidade {@link Voto}.
 *
 * <p>Utiliza a interface {@link JpaRepository} para fornecer operações básicas
 * como salvar, listar e deletar votos registrados no sistema.</p>
 */
@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

}
