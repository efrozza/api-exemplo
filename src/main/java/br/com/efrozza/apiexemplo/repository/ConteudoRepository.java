package br.com.efrozza.apiexemplo.repository;

import br.com.efrozza.apiexemplo.domain.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Integer> {
    Optional<Conteudo> findById (Integer id);
}
