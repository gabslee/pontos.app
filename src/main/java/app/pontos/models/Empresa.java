package app.pontos.models;

import app.pontos.enums.status_empresa;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;

@Data
@Entity(name = "Empresa")
@Table(name = "empresas")
@EqualsAndHashCode(of = "id")
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


}
