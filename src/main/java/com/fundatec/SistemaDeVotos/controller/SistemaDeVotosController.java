package com.fundatec.SistemaDeVotos.controller;

import com.fundatec.SistemaDeVotos.exception.FuncionarioJaVotouHojeException;
import com.fundatec.SistemaDeVotos.service.SistemaDeVotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável por lidar com as requisições relacionadas à votação de restaurantes.
 *
 * <p>Gerencia a exibição do formulário de votação, o processamento do voto e a exibição
 * dos resultados da apuração.</p>
 */
@Controller
@RequestMapping("/voto-restaurante")
public class SistemaDeVotosController {

    @Autowired
    private SistemaDeVotosService sistemaDeVotosService;

    /**
     * Exibe o formulário de votação para o usuário.
     *
     * @return o nome da view do formulário
     */
    @GetMapping("/formulario")
    public String carregarFormulario() {
        return "formulario";
    }

    /**
     * Exibe a página com a apuração dos votos registrados no dia atual.
     *
     * @param model objeto que carrega atributos para a view
     * @return o nome da view de apuração
     */
    @GetMapping
    public String obterList(Model model) {
        model.addAttribute("votos", sistemaDeVotosService.apuracaoVotos());
        return "apuracao-votos";
    }

    /**
     * Processa o formulário de votação.
     *
     * <p>Valida se o funcionário já votou no dia e, se não tiver votado, registra o voto.
     * Caso contrário, exibe uma mensagem de erro na tela.</p>
     *
     * @param nomeFuncionario nome do funcionário que está votando
     * @param nomeRestaurante nome do restaurante votado
     * @param model objeto que carrega atributos para a view em caso de erro
     * @return redirecionamento para a lista de apuração ou para o formulário com erro
     */
    @PostMapping
    public String salvarVoto(@RequestParam String nomeFuncionario, @RequestParam String nomeRestaurante,Model model) {
        try {
            sistemaDeVotosService.inserirVoto(nomeFuncionario, nomeRestaurante);
        } catch (FuncionarioJaVotouHojeException e) {
            model.addAttribute("erro", e.getMessage());
            return "formulario";
        }

        return "redirect:/voto-restaurante";
    }
}
