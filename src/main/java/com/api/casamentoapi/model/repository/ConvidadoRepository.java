package com.api.casamentoapi.model.repository;

import com.api.casamentoapi.api.dto.ConvidadoDto;
import com.api.casamentoapi.model.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

    List<Convidado> findByCodigo(String codigo);

}
