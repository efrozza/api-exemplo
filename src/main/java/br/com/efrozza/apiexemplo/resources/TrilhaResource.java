package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.TrilhaDTO;
import br.com.efrozza.apiexemplo.services.TrilhaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/trilha")
public class TrilhaResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TrilhaService service;

    @GetMapping
    ResponseEntity<List<TrilhaDTO>> listarTrilhas() {

        List<TrilhaDTO> listaTrilha = service.findAll()
                .stream()
                .map(trilha -> mapper.map(trilha, TrilhaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listaTrilha);
    }

}
