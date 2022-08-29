package com.api.casamentoapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Convidados {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//      @Column
//      private Long idResponsavel;

//    @Column
//    private Long idCasamento;

    @Column
    private String codigo;

    @Column
    private String nomeConvidado;

    @Column
    private Boolean status;

    @Column
    private LocalDate dataConfirmacao;
}
