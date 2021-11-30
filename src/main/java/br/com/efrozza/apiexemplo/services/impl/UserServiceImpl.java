package br.com.efrozza.apiexemplo.services.impl;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.repository.UserRepository;
import br.com.efrozza.apiexemplo.services.UserService;
import br.com.efrozza.apiexemplo.services.exceptions.DataIntegretyVaiolationException;
import br.com.efrozza.apiexemplo.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado"));
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User findByEmail(UserDTO userDTO) {
        Optional<User> user = repository.findByEmail(userDTO.getEmail());
        if (user.isPresent()){
            throw new DataIntegretyVaiolationException("Email ja existente");
        }
        return null;
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()){
            throw new ObjectNotFoundException("Usuario nao encontrado");
        }
        repository.delete(user.get());
    }
}
