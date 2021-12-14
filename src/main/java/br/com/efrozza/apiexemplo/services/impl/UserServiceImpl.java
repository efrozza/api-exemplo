package br.com.efrozza.apiexemplo.services.impl;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.repository.UserRepository;
import br.com.efrozza.apiexemplo.services.UserService;
import br.com.efrozza.apiexemplo.services.exceptions.DataIntegrityViolationException;
import br.com.efrozza.apiexemplo.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuario nao encontrado";
    public static final String EMAIL_JA_EXISTENTE = "Email ja existente";

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    public void findByEmail(UserDTO userDTO) {
        Optional<User> user = repository.findByEmail(userDTO.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
            throw new DataIntegrityViolationException(EMAIL_JA_EXISTENTE);
        }
    }

    @Override
    public User update(UserDTO userDTO) {
        Optional<User> user = repository.findById(userDTO.getId());
        if (user.isPresent()) {
            findByEmail(userDTO);
        } else {
            throw new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO);
        }
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO);
        }
        repository.deleteById(id);
    }
}
