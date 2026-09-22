package com.example.gabriel.services;

import com.example.gabriel.models.Usuario;
import com.example.gabriel.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {


    // Aplicando Bcrypt e segurança
    private BCryptPasswordEncoder combinadorDeSenhas = new BCryptPasswordEncoder();
    @Autowired
    private UsuarioRepository usuarioRepository;

    // ADICIONE ESTA LINHA: Chama o escrivão
    @Autowired
    private LogService logService;

    // ... (o resto do código continua igual)

    public Usuario registarNovoUsuario(Usuario novoUsuario) {
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um registo com este e-mail.");
        }

        String senhaSegura = combinadorDeSenhas.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaSegura);
        novoUsuario.setCriadoEm(LocalDateTime.now());
        novoUsuario.setAtualizadoEm(LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        // ADICIONE ESTA LINHA: O escrivão anota o que acabou de acontecer!
        logService.registrarAcao("Novo usuário cadastrado: " + usuarioSalvo.getEmail() + " (" + usuarioSalvo.getTipoUsuario() + ")");

        return usuarioSalvo;
    }

}