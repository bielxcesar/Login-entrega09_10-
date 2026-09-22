package com.example.gabriel.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String email;

    private String senha;

    private TipoUsuario tipoUsuario = TipoUsuario.ESTUDANTE;

    private boolean isRootAdmin = false;

    private String universidade;
    private String especialidadeJuridica;

    // Controle de segurança
    private boolean consentimentoLgpd = false;
    private LocalDateTime dataConsentimento;
    private String versaoTermos = "1.0";

    private LocalDateTime criadoEm = LocalDateTime.now();
    private LocalDateTime atualizadoEm = LocalDateTime.now();

}
