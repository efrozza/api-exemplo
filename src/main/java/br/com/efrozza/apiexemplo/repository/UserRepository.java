package br.com.efrozza.apiexemplo.repository;

import br.com.efrozza.apiexemplo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



}
