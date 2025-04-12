package com.fundatec.SistemaDeVotos.controller;

import com.fundatec.SistemaDeVotos.dto.VotoDTO;
import com.fundatec.SistemaDeVotos.exception.FuncionarioJaVotouHojeException;
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
 * Controller responsável por lidar com as requisições relacionadas à votação de restaurantes.
 */
@Controller
@RequestMapping
public class SistemaDeVotosController {

    @Autowired
    private SistemaDeVotosService sistemaDeVotosService;

    /**
     * Mapeia a raiz da aplicação para redirecionar para a página de apuração.
     *
     * @return redirecionamento para "/sistema-votos"
     */
    @GetMapping("/")
    public String redirectToApuracao() {
        return "redirect:/sistema-votos";
    }

    /**
     * Mapeia a página de apuração de votos.
     *
     * @param model o modelo para passar dados ao template
     * @return o nome do template "apuracao-votos"
     */
    @GetMapping("/sistema-votos")
    public String apuracao(Model model) {
        model.addAttribute("votos", sistemaDeVotosService.apuracaoVotos());
        return "apuracao-votos";
    }

    /**
     * Carrega o formulário para cadastrar um novo voto.
     *
     * @param model o modelo para passar dados ao template
     * @return o nome do template "formulario-voto"
     */
    @GetMapping("/votos/cadastrar")
    public String carregarFormularioVoto(Model model) {
        model.addAttribute("votoDTO", new VotoDTO());
        model.addAttribute("funcionarios", sistemaDeVotosService.listarFuncionarios());
        model.addAttribute("restaurantes", sistemaDeVotosService.listarRestaurantes());
        return "formulario-voto";
    }

    /**
     * Processa o formulário de cadastro de voto.
     *
     * @param votoDTO o DTO contendo os dados do voto
     * @param result o resultado da validação
     * @param model o modelo para passar dados ao template
     * @return redirecionamento para a página de apuração ou o formulário em caso de erro
     */
    @PostMapping("/votos/cadastrar")
    public String salvarVoto(@Valid VotoDTO votoDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("funcionarios", sistemaDeVotosService.listarFuncionarios());
            model.addAttribute("restaurantes", sistemaDeVotosService.listarRestaurantes());
            return "formulario-voto";
        }
        try {
            sistemaDeVotosService.inserirVoto(votoDTO.getFuncionarioId(), votoDTO.getRestauranteId());
        } catch (FuncionarioJaVotouHojeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("funcionarios", sistemaDeVotosService.listarFuncionarios());
            model.addAttribute("restaurantes", sistemaDeVotosService.listarRestaurantes());
            return "formulario-voto";
        }
        return "redirect:/sistema-votos";
    }
}