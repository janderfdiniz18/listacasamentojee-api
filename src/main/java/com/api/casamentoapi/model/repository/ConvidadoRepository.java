package com.api.casamentoapi.model.repository;

import com.api.casamentoapi.api.dto.ConvidadosDto;
import com.api.casamentoapi.model.entity.Convidados;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidados, Long> {

    List<Convidados> findByCodigo(String codigo);

}
