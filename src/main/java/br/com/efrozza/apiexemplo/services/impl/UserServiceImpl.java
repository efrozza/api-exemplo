package br.com.efrozza.apiexemplo.services.impl;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.repository.UserRepository;
import br.com.efrozza.apiexemplo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user;
    }
}
