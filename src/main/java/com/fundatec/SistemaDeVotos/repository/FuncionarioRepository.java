package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}
