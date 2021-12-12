package br.com.efrozza.apiexemplo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Conteudo.findConteudosByTrilhaId",
        query = "SELECT * FROM CONTEUDO C INNER JOIN trilha_conteudo tc " +
                "ON c.id = tc.conteudo_id WHERE tc.trilha_id = :id",
        resultSetMapping = "ConteudosDaTrilha")
@SqlResultSetMapping(name = "ConteudosDaTrilha",
        classes = @ConstructorResult(targetClass = Conteudo.class,
                columns = {@ColumnResult(name = "id"),
                        @ColumnResult(name = "nome"),
                        @ColumnResult(name = "descricao"),
                        @ColumnResult(name = "link"),
                        @ColumnResult(name = "conteudo_Aprovado")}))
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private String link;
    private boolean conteudoAprovado;
}
