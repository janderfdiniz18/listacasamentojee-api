package com.api.casamentoapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvidadoDto {

    private Long id;

//    private Long idResponsavel;

//    private Long idCasamento;

    private String nomeConvidado;

    private Boolean status;

    private Boolean statusConfirmacao;

    private String codigo;

    private LocalDate dataConfirmacao;

}
