package com.api.casamentoapi.service;


import com.api.casamentoapi.api.dto.ConvidadosDto;
import com.api.casamentoapi.model.entity.Convidados;
import com.api.casamentoapi.model.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvidadoService {

    @Autowired
    ConvidadoRepository repository;

    public Convidados save(Convidados convidados) {
        return repository.save(convidados);
    }

    public List<Convidados> getByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public List<Convidados> findAll(){
        return repository.findAll();
    }
}
