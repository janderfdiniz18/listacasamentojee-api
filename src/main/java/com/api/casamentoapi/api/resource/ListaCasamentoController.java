package com.api.casamentoapi.api.resource;

import com.api.casamentoapi.api.dto.ConvidadosDto;
import com.api.casamentoapi.model.entity.Convidados;
import com.api.casamentoapi.service.ConvidadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lista-casamento")
@RequiredArgsConstructor
@Api("Lista Casamento API")
@Slf4j
public class ListaCasamentoController {

    private final ModelMapper modelMapper;
    private final ConvidadoService service;

    @PostMapping
    @CrossOrigin( origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a List")
    public List<ConvidadosDto> create(@RequestBody @Valid List<ConvidadosDto> dtos ){
//        log.info(" creating a list: {} ", dto.getCodigo());
        List<ConvidadosDto> convidadosDtoList = new ArrayList<>();
        dtos.forEach(convidadosDto -> {
            Convidados entity = modelMapper.map( convidadosDto, Convidados.class );
            entity = service.save(entity);
            convidadosDtoList.add(modelMapper.map(entity, ConvidadosDto.class)) ;
        });
        return convidadosDtoList;
    }
}
