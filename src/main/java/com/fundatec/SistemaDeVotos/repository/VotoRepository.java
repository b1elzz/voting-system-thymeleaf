package com.fundatec.SistemaDeVotos.repository;

import com.fundatec.SistemaDeVotos.model.Voto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

}
