package br.com.efrozza.apiexemplo.config;

import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDb(){
        User user1 = new User(1, "Everton", "everton@bol.com", "123");
        User user2 = new User(1, "Testador", "testador@bol.com", "123");

        List<User> usuariosDoBanco = new ArrayList<>();
        usuariosDoBanco.add(user1);
        usuariosDoBanco.add(user2);

        repository.saveAll(usuariosDoBanco);
    }
}
