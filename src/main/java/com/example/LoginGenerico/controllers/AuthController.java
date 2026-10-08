package com.example.LoginGenerico.controllers;

import com.example.LoginGenerico.dto.UsuarioDTO;
import com.example.LoginGenerico.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String exibirTelaLogin() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String exibirTelaCadastro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(
            @Valid @ModelAttribute("usuario") UsuarioDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "cadastro";
        }

        try {
            // Tenta salvar o usuário
            usuarioService.registarNovoUsuario(dto);
            return "redirect:/login?sucesso";

        } catch (RuntimeException erro) {
            model.addAttribute("erroEmail", erro.getMessage());
            return "cadastro";
        }
    }
}