package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, response.getBody().getId());
    }

    @Test
    void whenConsultarUsuariosThenReturnListOfUser() {

        when(service.findAll()).thenReturn(listaUsuariosMock());
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.consultarUsuarios();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX_ZERO).getClass());
        assertEquals(ID, response.getBody().get(INDEX_ZERO).getId());
    }

    @Test
    void whenCriarUsuarioThenReturnCreated() {

        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.criarUsuario(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    void whenAtualizarUsuario() {

        when(service.update(userDTO)).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.atualizarUsuario(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

    }

    @Test
    void deletarUsuario() {
    }

    // inicializa objetos que serão usados
    private void startUser(){
        user = new User(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
    }

    private List<User> listaUsuariosMock (){
        User user1 = new User(1, "Everton", "everton@bol.com", "123");
        User user2 = new User(2, "Testador", "testador@bol.com", "123");

        List<User> usuariosMock = new ArrayList<>();
        usuariosMock.add(user1);
        usuariosMock.add(user2);

        return usuariosMock;
    }


}