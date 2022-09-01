package com.api.casamentoapi.api.resource;

import com.api.casamentoapi.api.dto.ConvidadosDto;
import com.api.casamentoapi.model.entity.Convidados;
import com.api.casamentoapi.service.ConvidadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lista-casamento")
@RequiredArgsConstructor
@Api("Lista Casamento API")
@Slf4j
public class ListaCasamentoController {

    private ModelMapper modelMapper;

    @Autowired
    private ConvidadoService service;

    @PostMapping
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

    @GetMapping("{codigo}")
    @ApiOperation("Get convidados")
    public List<ConvidadosDto> get( @PathVariable(value="codigo") String codigo){
        List<Convidados> convidados = service.getByCodigo(codigo);
        List<ConvidadosDto> convidadosDtoList = convidados.stream().map(entity -> {
            ConvidadosDto convidadoDto = modelMapper.map(entity, ConvidadosDto.class);
            return convidadoDto;
                }
                ).collect(Collectors.toList());
        return convidadosDtoList;
    }

    @GetMapping
    public List<Convidados> getAllConvidados(){
        return service.findAll();
    }
}
