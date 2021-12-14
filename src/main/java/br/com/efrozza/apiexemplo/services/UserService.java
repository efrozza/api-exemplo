package br.com.efrozza.apiexemplo.services;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO userDTO);

    void findByEmail(UserDTO userDTO);

    User update(UserDTO userDTO);

    void delete(Integer id);
}
