package br.com.efrozza.apiexemplo.repository;

import br.com.efrozza.apiexemplo.domain.Trilha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrilhaRepository extends JpaRepository<Trilha, Integer> {
    List<Trilha> findAllByOrderByNomeAsc();
}
