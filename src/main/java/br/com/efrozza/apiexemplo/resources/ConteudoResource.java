package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.Conteudo;
import br.com.efrozza.apiexemplo.services.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping(value = "/conteudo")
public class ConteudoResource {

    @Autowired
    ConteudoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conteudo> obterConteudo (@PathVariable Integer id){
        return ResponseEntity.ok().body(service.obterConteudo(id).get());
    }

    @GetMapping(value = "/trilha/{id}")
    public ResponseEntity<List<Conteudo>> obterConteudosDaTrilha (@PathVariable Integer id){
        return ResponseEntity.ok().body(service.obterConteudosDaTrilha(id));
    }

}
