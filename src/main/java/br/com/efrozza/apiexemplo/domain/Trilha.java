package br.com.efrozza.apiexemplo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Trilha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "trilha_conteudo",
            joinColumns = @JoinColumn(name = "trilha_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "conteudo_id", referencedColumnName = "id"))
    private List<Conteudo> conteudos = new ArrayList<>();

}
