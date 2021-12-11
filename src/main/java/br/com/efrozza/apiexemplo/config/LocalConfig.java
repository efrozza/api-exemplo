package br.com.efrozza.apiexemplo.config;

import br.com.efrozza.apiexemplo.domain.Conteudo;
import br.com.efrozza.apiexemplo.domain.Trilha;
import br.com.efrozza.apiexemplo.domain.User;
import br.com.efrozza.apiexemplo.repository.ConteudoRepository;
import br.com.efrozza.apiexemplo.repository.TrilhaRepository;
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
    private UserRepository userRepository;

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private TrilhaRepository trilhaRepository;


    @Bean
    public void startDb(){

        Trilha t1 = new Trilha(1, "Trilha 1", new ArrayList<>());
        Trilha t2 = new Trilha(2, "Trilha 2", new ArrayList<>());

        List<Trilha> trilhas = new ArrayList<>();
        trilhas.add(t1);

        User user1 = new User(1, "Everton", "everton@bol.com", "123");
        User user2 = new User(2, "Testador", "testador@bol.com", "123");

        List<User> usuariosDoBanco = new ArrayList<>();
        usuariosDoBanco.add(user1);
        usuariosDoBanco.add(user2);

        Conteudo c1 = new Conteudo(1, "Conteudo1", "Valor do conteudo", "www.conteudo1.com", true);
        Conteudo c2 = new Conteudo(2, "Conteudo2", "Valor do conteudo 2", "www.conteudo1.com", true);

        userRepository.saveAll(usuariosDoBanco);
        trilhaRepository.save(t1);
        trilhaRepository.save(t2);
        conteudoRepository.save(c1);
        conteudoRepository.save(c2);
    }
}
