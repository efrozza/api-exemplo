package br.com.efrozza.apiexemplo.resources;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){

        User user = service.findById(id).orElse(null);
        return ResponseEntity.ok().body(user);

    }

}
