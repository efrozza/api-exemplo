package br.com.efrozza.apiexemplo.services;

import br.com.efrozza.apiexemplo.domain.User;

import java.util.Optional;

public interface UserService {

    User findById (Integer id);

}
