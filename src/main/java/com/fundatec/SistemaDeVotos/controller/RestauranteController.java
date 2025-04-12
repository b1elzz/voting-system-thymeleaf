package com.fundatec.SistemaDeVotos.controller;

import com.fundatec.SistemaDeVotos.dto.RestauranteDTO;
import com.fundatec.SistemaDeVotos.model.Restaurante;
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
 * Controller responsável por gerenciar operações relacionadas a restaurantes.
 */
@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private SistemaDeVotosService sistemaDeVotosService;

    /**
     * Carrega o formulário para cadastrar um novo restaurante.
     *
     * @param model o modelo para passar dados ao template
     * @return o nome do template "formulario-restaurante"
     */
    @GetMapping("/cadastrar")
    public String carregarFormulario(Model model) {
        model.addAttribute("restauranteDTO", new RestauranteDTO());
        return "formulario-restaurante";
    }

    /**
     * Processa o formulário de cadastro de restaurante.
     *
     * @param restauranteDTO o DTO contendo os dados do restaurante
     * @param result o resultado da validação
     * @param model o modelo para passar dados ao template
     * @return redirecionamento para a lista de restaurantes ou o formulário em caso de erro
     */
    @PostMapping("/cadastrar")
    public String salvarRestaurante(@Valid RestauranteDTO restauranteDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formulario-restaurante";
        }
        sistemaDeVotosService.cadastrarRestaurante(restauranteDTO.getNomeRestaurante());
        return "redirect:/restaurantes";
    }

    /**
     * Lista todos os restaurantes cadastrados.
     *
     * @param model o modelo para passar dados ao template
     * @return o nome do template "lista-restaurantes"
     */
    @GetMapping
    public String listarRestaurantes(Model model) {
        model.addAttribute("restaurantes", sistemaDeVotosService.listarRestaurantes());
        return "lista-restaurantes";
    }
}