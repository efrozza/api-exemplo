package br.com.efrozza.apiexemplo.services.impl;

import br.com.efrozza.apiexemplo.domain.Trilha;
import br.com.efrozza.apiexemplo.repository.TrilhaRepository;
import br.com.efrozza.apiexemplo.services.TrilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilhaServiceImpl implements TrilhaService {
    @Autowired
    private TrilhaRepository repository;

    @Override
    public List<Trilha> findAll() {
        return repository.findAllByOrderByNomeAsc();
    }
}
