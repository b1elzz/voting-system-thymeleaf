package com.fundatec.SistemaDeVotos.service;


import com.fundatec.SistemaDeVotos.model.Funcionario;
import com.fundatec.SistemaDeVotos.model.Restaurante;
import com.fundatec.SistemaDeVotos.model.TotalVotosRestaurante;
import com.fundatec.SistemaDeVotos.model.Voto;
import com.fundatec.SistemaDeVotos.repository.FuncionarioRepository;
import com.fundatec.SistemaDeVotos.repository.RestauranteRepository;
import com.fundatec.SistemaDeVotos.repository.VotoRepository;
import com.fundatec.SistemaDeVotos.repository.VotoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SistemaDeVotosService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private VotoRepositoryImpl votoRepositoryImpl;

    public void inserirFuncionario(Funcionario dados){
        funcionarioRepository.save(dados);
    }

    public void inserirRestaurante(Restaurante dados){
        restauranteRepository.save(dados);
    }

    public void inserirVoto(Voto dados){
        votoRepository.save(dados);
    }

    public List<Funcionario> buscarTodosFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public List<Restaurante> buscarTodosRestaurante(){
        return restauranteRepository.findAll();
    }

    public List<TotalVotosRestaurante> apuracaoVotos(){
        return votoRepositoryImpl.buscar();
    }

    public boolean votoPorData(Integer funcId, Calendar data){
        return votoRepositoryImpl.votoPorData(funcId, data);
    }




}
