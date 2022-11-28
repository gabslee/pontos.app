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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario id_usuario;
    @OneToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    Empresa id_empresa;

    public void setId_usuario(Integer idUsuario) {
        id_usuario = getId_usuario();
    }

    public void setId_empresa(Integer idEmpresa) {
        id_empresa = getId_empresa();
    }
}
