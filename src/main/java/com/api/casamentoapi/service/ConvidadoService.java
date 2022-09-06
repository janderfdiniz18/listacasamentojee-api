package com.api.casamentoapi.service;


import com.api.casamentoapi.api.dto.ConvidadoDto;
import com.api.casamentoapi.model.entity.Convidado;
import com.api.casamentoapi.model.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ConvidadoService {

    @Autowired
    ConvidadoRepository repository;

    public Convidado save(Convidado convidado) {
        return repository.save(convidado);
    }

    public Convidado saveSwagger(Convidado convidado) {

        convidado.setStatus(false);
        convidado.setStatusConfirmacao(true);
        return repository.save(convidado);
    }

    public String stringRandom(){
        String az = "janderEellenCasamento29102022";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 10){
            int index = (int) (random.nextFloat() * az.length());
            salt.append(az.charAt(index));
        }
        String condigo = salt.toString();
        return condigo;
    }

    public List<Convidado> getByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public List<Convidado> findAllConvidados(String codigo){
        if(codigo.equals("JeENoivosTe4m0")) {
            return repository.findAll();
        }
        return null;
    }
}
