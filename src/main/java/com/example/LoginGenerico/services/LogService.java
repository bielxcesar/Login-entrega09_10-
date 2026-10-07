package com.example.LoginGenerico.services;

import com.example.LoginGenerico.models.LogSistema;
import com.example.LoginGenerico.repositories.LogSistemaRepository;
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