package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.domain.UserDTO;
import br.com.efrozza.apiexemplo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = ID)
    // Devolver uma entity eh um erro de arquitetura e atéh uma falha de segurança
    // por isso o padrao DTO é usado
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
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
