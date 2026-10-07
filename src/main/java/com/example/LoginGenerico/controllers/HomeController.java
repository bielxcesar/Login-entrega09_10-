package com.example.LoginGenerico.controllers;

import com.example.LoginGenerico.repositories.LogSistemaRepository;
import com.example.LoginGenerico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Traz a gaveta de logs
    @Autowired
    private LogSistemaRepository logSistemaRepository;

    @GetMapping("/home")
    public String exibirTelaHome(Model model) {
        model.addAttribute("listaDeUsuarios", usuarioRepository.findAll());

        // Entrega as anotações para a tela
        model.addAttribute("listaDeLogs", logSistemaRepository.findTop20ByOrderByDataHoraDesc());

        return "home";
    }
}