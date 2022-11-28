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
public class Ponto {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    tipo_ponto tipo;
    LocalDate dia;
    LocalTime horario;
    String observacao;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    Funcionario id_funcionario;
}
