package com.example.LoginGenerico.services;

import com.example.LoginGenerico.dto.UsuarioDTO;
import com.example.LoginGenerico.models.Usuario;
import com.example.LoginGenerico.repositories.UsuarioRepository;
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

    @Autowired
    private LogService logService;

    public Usuario registarNovoUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um registo com este e-mail.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setTipoUsuario(dto.getTipo());

        String senhaSegura = combinadorDeSenhas.encode(dto.getSenha());
        novoUsuario.setSenha(senhaSegura);
        novoUsuario.setCriadoEm(LocalDateTime.now());
        novoUsuario.setAtualizadoEm(LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        logService.registrarAcao("Novo usuário cadastrado: " + usuarioSalvo.getEmail() + " (" + usuarioSalvo.getTipoUsuario() + ")");

        return usuarioSalvo;
    }
}