package com.example.gabriel.controllers;

import com.example.gabriel.models.Usuario;
import com.example.gabriel.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostra a tela de login
    @GetMapping("/login")
    public String exibirTelaLogin() {
        return "login";
    }

    // Mostra a tela de cadastro (entregando a ficha vazia)
    @GetMapping("/cadastro")
    public String exibirTelaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    // Processa os dados preenchidos no cadastro
    @PostMapping("/cadastro")
    public String processarCadastro(Usuario usuario) {
        try {
            // Tenta fazer o cadastro normalmente
            usuarioService.registarNovoUsuario(usuario);
            return "redirect:/login?sucesso";

        } catch (RuntimeException erro) {
            // Se o e-mail já existir, devolve para a tela de cadastro com aviso
            return "redirect:/cadastro?erro";
        }
    }
}