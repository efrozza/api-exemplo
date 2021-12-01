package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    public static final String NOME         = "Teste Mock User";
    public static final String EMAIL        = "everton@bol.com";
    public static final String PASSWORD     = "senha123";
    public static final Integer ID          = 1;
    public static final String USUARIO_NAO_ENCONTRADO = "Usuario nao encontrado";
    public static final int INDEX_ZERO = 0;
    public static final String EMAIL_JA_EXISTENTE = "Email ja existente";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void consultarUsuarios() {
    }

    @Test
    void criarUsuario() {
    }

    @Test
    void atualizarUsuario() {
    }

    @Test
    void deletarUsuario() {
    }

    // inicializa objetos que serão usados
    private void startUser(){
        user = new User(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
    }
}