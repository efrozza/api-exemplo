package br.com.efrozza.apiexemplo.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String nome;
    private String email;

    // Essa anotation não envia o campo para o json de saida, mas considera
    // o campo em operacoes de escrita, como create e update por exemplo

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
