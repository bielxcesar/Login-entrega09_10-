package com.example.LoginGenerico.repositories;

import com.example.LoginGenerico.models.LogSistema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSistemaRepository extends MongoRepository<LogSistema, String> {
    // Pede ao cofre para trazer as últimas 20 anotações, ordenadas da mais recente para a mais antiga
    List<LogSistema> findTop20ByOrderByDataHoraDesc();
}