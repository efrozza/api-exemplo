package br.com.efrozza.apiexemplo.services.impl;

import br.com.efrozza.apiexemplo.domain.Conteudo;
import br.com.efrozza.apiexemplo.domain.Trilha;
import br.com.efrozza.apiexemplo.repository.ConteudoRepository;
import br.com.efrozza.apiexemplo.repository.TrilhaRepository;
import br.com.efrozza.apiexemplo.services.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ConteudoServiceImpl implements ConteudoService {

    @Autowired
    private ConteudoRepository repository;

    @Autowired
    private TrilhaRepository repositoryTrilha;

    @Override
    public Optional<Conteudo> obterConteudo(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Conteudo> obterConteudosDaTrilha(Integer id) {
        Optional<Trilha> trilha = repositoryTrilha.findById(id);
        return trilha.get().getConteudos();
    }
}
