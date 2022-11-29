package app.pontos.models;

import app.pontos.enums.tipo_ponto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Entity(name = "Ponto")
@Table(name = "pontos")
@EqualsAndHashCode(of = "id")
//MAPEAMENTO DE ENTIDADE ATRAVÉS DA JPA
public class Ponto {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto")
    public
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    tipo_ponto tipo = tipo_ponto.ENTRADA;
    LocalDate dia;
    LocalTime horario;
    String observacao;

    @Column(name = "id_funcionario")
    Integer id_funcionario;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", insertable = false, updatable = false)
    Funcionario funcionario;

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
}
