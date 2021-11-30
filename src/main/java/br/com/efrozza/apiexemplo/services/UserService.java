package br.com.efrozza.apiexemplo.services;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById (Integer id);
    List<User> findAll();
    User create(UserDTO userDTO);
    User findByEmail (UserDTO userDTO);
    User update(UserDTO userDTO);
    void delete(Integer id);
}
