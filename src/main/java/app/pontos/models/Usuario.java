package app.pontos.models;

import app.pontos.enums.status_usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity(name = "Usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    public
    Long id;
    String nome;
    String email;
    String senha;
    @Column(name = "cpf")
    String cpf;
    LocalDate nascimento;
    String foto;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    status_usuario status = status_usuario.ATIVO;

}
