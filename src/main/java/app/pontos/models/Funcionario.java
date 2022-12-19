package app.pontos.models;

import app.pontos.enums.status_funcionario;
import app.pontos.enums.tipo_funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "Funcionario")
@Table(name = "funcionarios")
@EqualsAndHashCode(of = "id")
//MAPEAMENTO DE ENTIDADE ATRAVÉS DA JPA
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    public
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    status_funcionario status = status_funcionario.ATIVO;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    tipo_funcionario tipo;
    String cargo;
    LocalDate data_admissao;
    LocalDate data_demissao;
    @Column(name = "id_usuario")
    Integer id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario",insertable = false, updatable = false)
    Usuario usuario;

    @Column(name = "id_empresa")
    Integer id_empresa;
    @OneToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    Empresa empresa;

    public void setId_usuario(Integer idUsuario) {
        id_usuario = idUsuario;
    }

    public void setId_empresa(Integer idEmpresa) {
        id_empresa = idEmpresa;
    }
}