package com.api.casamentoapi.service;


import com.api.casamentoapi.model.entity.Convidado;
import com.api.casamentoapi.model.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Convidado> getById(Long id) {
        return repository.findById(id);
    }

    public Convidado update(Convidado convidado) {
        if (convidado == null || convidado.getId() == null){
            throw new IllegalArgumentException("Convidado id cant be null.");
        }
        return repository.save(convidado);
    }

    public void delete(Convidado convidado) {
        if(convidado == null)
            throw new IllegalArgumentException("Convidado nao existe");
        this.repository.delete(convidado);
    }

    public void deleteAll(){
        this.repository.deleteAll();
    }

    public List<Convidado> findAllConvidadosStatusConfirmacao(String codigo, Boolean status) {
        if(codigo.equals("JeENoivosTe4m0")) {
            return status ? repository.findByStatusConfirmacaoIsTrue() : repository.findByStatusConfirmacaoIsFalse();
        }
        return null;
    }
}
