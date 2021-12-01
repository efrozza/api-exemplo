package br.com.efrozza.apiexemplo.services.impl;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.repository.UserRepository;
import br.com.efrozza.apiexemplo.services.UserService;
import br.com.efrozza.apiexemplo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final String NOME         = "Teste Mock User";
    public static final String EMAIL        = "everton@bol.com";
    public static final String PASSWORD     = "senha123";
    public static final Integer ID          = 1;
    public static final String USUARIO_NAO_ENCONTRADO = "Usuario nao encontrado";

    // precisamos ter uma instancia real da classe que vamos testar
    // as demais vamos mockar
    @InjectMocks
    private UserServiceImpl service;

    // Os acessos ao DB não devem ser feitos em testes unitários
    // então mockamos o repository
    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {

        // Antes de tudo faça o abaixo
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        // quando o metodo findById do respository for chamado com qualquer parametro inteiro
        // retorno o optionalUser mockado
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        // chamada do metodo alvo de teste
        User response = service.findById(ID);

        // Teste
        assertNotNull(response);
        // testa se o metdo estah devolvendo User
        // caso alguem mude o retorno do metodo o nosso teste passa a quebrar
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnobjectNotFoundException(){

        // Mockando a execeção quando chamar o findbyId

        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        } catch (Exception ex){
            // verifica se a exceção de retorno foi do time esperado
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USUARIO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    // inicializa objetos que serão usados
    private void startUser(){
        user = new User(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NOME, EMAIL, PASSWORD));
    }
}