package com.api.casamentoapi.api.resource;

import com.api.casamentoapi.api.dto.ConvidadoDto;
import com.api.casamentoapi.model.entity.Convidado;
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
@CrossOrigin("*")
public class ListaCasamentoController {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ConvidadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a List")
    public List<ConvidadoDto> create(@RequestBody @Valid List<ConvidadoDto> dtos ){
//        log.info(" creating a list: {} ", dto.getCodigo());
        List<ConvidadoDto> convidadosDtoList = new ArrayList<>();
        dtos.forEach(convidadosDto -> {
            Convidado entity = modelMapper.map( convidadosDto, Convidado.class );
            entity = service.save(entity);
            convidadosDtoList.add(modelMapper.map(entity, ConvidadoDto.class)) ;
        });
        return convidadosDtoList;
    }

    @PutMapping("/convidados")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Update")
    public List<ConvidadoDto> updateConvidadosConfirm(@RequestBody @Valid List<ConvidadoDto> dtos ){
//        log.info(" creating a list: {} ", dto.getCodigo());
        List<ConvidadoDto> convidadosDtoList = new ArrayList<>();
        dtos.forEach(convidadoDto -> {
                Convidado convidado = modelMapper.map(convidadoDto, Convidado.class);
                convidado.setStatus(false);
                convidado = service.update(convidado);
                convidadosDtoList.add(modelMapper.map(convidado, ConvidadoDto.class));
        });


//        dtos.forEach(convidadosDto -> {
//            Convidado entity = modelMapper.map( convidadosDto, Convidado.class );
//            entity.setStatus(false);
//            entity = service.save(entity);
//            convidadosDtoList.add(modelMapper.map(entity, ConvidadoDto.class)) ;
//        });
        return convidadosDtoList;
    }

    @PutMapping("/noivos")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Update")
    public List<ConvidadoDto> updateConfirm(@RequestBody @Valid List<ConvidadoDto> dtos ){
//        log.info(" creating a list: {} ", dto.getCodigo());
        List<ConvidadoDto> convidadosDtoList = new ArrayList<>();
        dtos.forEach(convidadoDto -> {
            Convidado convidado = modelMapper.map(convidadoDto, Convidado.class);
            convidado = service.update(convidado);
            convidadosDtoList.add(modelMapper.map(convidado, ConvidadoDto.class));
        });

        return convidadosDtoList;
    }

    @PostMapping("/swagger")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a List")
    public List<ConvidadoDto> createSwagger(@RequestBody @Valid List<ConvidadoDto> dtos ){
//        log.info(" creating a list: {} ", dto.getCodigo());
        List<ConvidadoDto> convidadosDtoList = new ArrayList<>();
        String condigo = service.stringRandom();
        dtos.forEach(convidadosDto -> {
            Convidado entity = modelMapper.map( convidadosDto, Convidado.class );
            entity.setCodigo(condigo);
            entity = service.saveSwagger(entity);
            convidadosDtoList.add(modelMapper.map(entity, ConvidadoDto.class)) ;
        });
        return convidadosDtoList;
    }

    @GetMapping("/convidados/{codigo}")
    @ApiOperation("Get convidados")
    public List<ConvidadoDto> getConvidados( @PathVariable(value="codigo") String codigo){
        List<Convidado> convidados = service.getByCodigo(codigo);
        List<ConvidadoDto> convidadosDtoList = convidados.stream().map(entity -> {
            ConvidadoDto convidadoDto = modelMapper.map(entity, ConvidadoDto.class);
            return convidadoDto;
                }
                ).collect(Collectors.toList());
        return convidadosDtoList;
    }

    @GetMapping("/noivos/{codigo}")
    @ApiOperation("Get convidados")
    public List<Convidado> getConvidadosNoivos( @PathVariable(value="codigo") String codigo){
        return service.findAllConvidados(codigo);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deletes a book by id")
    public void delete(@PathVariable Long id){
        log.info(" deleting book of id: {} ", id);
        Convidado convidado = service.getById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
        service.delete(convidado);
    }
    //@GetMapping
    //public List<Convidados> getAllConvidados(){
     //   return service.findAll();
   // }
}
