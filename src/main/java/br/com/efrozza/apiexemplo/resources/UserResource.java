package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
@Api(value = "User", description = "API for users operations", tags = "User V1")
@Slf4j
public class UserResource {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    // Anotacoes para documentao swagger
    @ApiOperation(value = "List users", notes = "List users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping(value = ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        log.info("Get Users List");
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> consultarUsuarios(){

        List<UserDTO> listaUsuariosDTO  = service.findAll()
                                        .stream()
                                        .map(usuario -> mapper.map(usuario, UserDTO.class))

                                        .collect(Collectors.toList());

        return ResponseEntity.ok().body(listaUsuariosDTO);

    }

    @PostMapping
    public ResponseEntity<UserDTO> criarUsuario(@RequestBody UserDTO userDTO) {
        User novoUsuario = service.create(userDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path(ID).buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> atualizarUsuario(@PathVariable Integer id,
                                                    @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        User usuarioAtualizado = service.update(userDTO);
        return ResponseEntity.ok().body(mapper.map(usuarioAtualizado, UserDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> deletarUsuario (@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
