package br.com.efrozza.apiexemplo.services;

import br.com.efrozza.apiexemplo.domain.Conteudo;

import java.util.List;
import java.util.Optional;

public interface ConteudoService {

    Optional<Conteudo> obterConteudo(Integer id);

    List<Conteudo> obterConteudosDaTrilha(Integer id);

}
