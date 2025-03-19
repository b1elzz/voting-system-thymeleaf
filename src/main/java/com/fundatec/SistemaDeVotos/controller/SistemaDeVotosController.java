package com.fundatec.SistemaDeVotos.controller;

import com.fundatec.SistemaDeVotos.service.SistemaDeVotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voto-restaurate")
public class SistemaDeVotosController {
    @Autowired
    SistemaDeVotosService sistemaDeVotosService;

    public SistemaDeVotosController(){

    }


    @GetMapping
    public String obterList(Model model){
        model.addAttribute("votos",sistemaDeVotosService.apuracaoVotos());
        return "apuracao-votos";
    }

}
