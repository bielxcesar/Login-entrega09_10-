package com.example.LoginGenerico.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document(collection = "logs")
public class LogSistema {

    @Id
    private String id;
    private String mensagem;
    private LocalDateTime dataHora = LocalDateTime.now(); // Grava a hora exata sozinho

    // Prepara a data para ficar bonita na tela (ex: 20/09/2026 14:30:00)
    public String getDataFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHora.format(formatador);
    }
}