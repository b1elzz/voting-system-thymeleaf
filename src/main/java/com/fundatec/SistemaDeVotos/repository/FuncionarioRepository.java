package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável por operações de persistência da entidade {@link Funcionario}.
 *
 * <p>Extende {@link JpaRepository}, fornecendo acesso a métodos padrão como salvar, buscar e remover.
 * Também permite realizar buscas específicas com base no nome do funcionário.</p>
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    /**
     * Busca um funcionário pelo nome exato.
     *
     * @param nomeFuncionario o nome do funcionário a ser buscado
     * @return o funcionário encontrado ou {@code null} se não existir
     */
    Funcionario findByNomeFuncionario(String nomeFuncionario);
}
