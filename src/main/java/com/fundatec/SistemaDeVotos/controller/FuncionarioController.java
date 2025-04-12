package com.fundatec.SistemaDeVotos.controller;

import com.fundatec.SistemaDeVotos.dto.FuncionarioDTO;
import com.fundatec.SistemaDeVotos.model.Funcionario;
import com.fundatec.SistemaDeVotos.service.SistemaDeVotosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsável por gerenciar operações relacionadas a funcionários.
 */
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private SistemaDeVotosService sistemaDeVotosService;

    /**
     * Carrega o formulário para cadastrar um novo funcionário.
     *
     * @param model o modelo para passar dados ao template
     * @return o nome do template "formulario-funcionario"
     */
    @GetMapping("/cadastrar")
    public String carregarFormulario(Model model) {
        model.addAttribute("funcionarioDTO", new FuncionarioDTO());
        return "formulario-funcionario";
    }

    /**
     * Processa o formulário de cadastro de funcionário.
     *
     * @param funcionarioDTO o DTO contendo os dados do funcionário
     * @param result o resultado da validação
     * @param model o modelo para passar dados ao template
     * @return redirecionamento para a lista de funcionários ou o formulário em caso de erro
     */
    @PostMapping("/cadastrar")
    public String salvarFuncionario(@Valid FuncionarioDTO funcionarioDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formulario-funcionario";
        }
        sistemaDeVotosService.cadastrarFuncionario(funcionarioDTO.getNomeFuncionario());
        return "redirect:/funcionarios";
    }

    /**
     * Lista todos os funcionários cadastrados.
     *
     * @param model o modelo para passar dados ao template
     * @return o nome do template "lista-funcionarios"
     */
    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", sistemaDeVotosService.listarFuncionarios());
        return "lista-funcionarios";
    }
}