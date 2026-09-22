package com.example.gabriel.services;

import com.example.gabriel.models.LogSistema;
import com.example.gabriel.repositories.LogSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogSistemaRepository logRepository;

    public void registrarAcao(String mensagem) {
        LogSistema novoLog = new LogSistema();
        novoLog.setMensagem(mensagem);
        logRepository.save(novoLog);
    }
}