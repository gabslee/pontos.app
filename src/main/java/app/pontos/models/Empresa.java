package app.pontos.models;

import app.pontos.enums.status_empresa;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;

@Data
@Entity(name = "Empresa")
@Table(name = "empresas")
@EqualsAndHashCode(of = "id")
//MAPEAMENTO DE ENTIDADE ATRAVÉS DA JPA
public class Empresa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    public
    Long id;
    String nome;
    Long cnpj;
    String telefone;
    String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    status_empresa status = status_empresa.ATIVO;

    @Column(name = "id_usuario")
    Integer id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    Usuario usuario;

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
