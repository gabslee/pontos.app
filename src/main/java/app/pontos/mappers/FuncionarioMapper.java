package app.pontos.mappers;


import app.pontos.enums.status_funcionario;
import app.pontos.enums.tipo_funcionario;
import app.pontos.models.Funcionario;
import br.com.crud.components.ResponseFuncionarioData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioMapper {

    //método que converte dado Funcionario em um dado ResponseFuncionario


    public static ResponseFuncionarioData toResponse(Funcionario funcionario) {
        ResponseFuncionarioData responseFuncionario = new ResponseFuncionarioData();
        responseFuncionario.setIdFuncionario(funcionario.getId().intValue());
        responseFuncionario.setStatus(String.valueOf(status_funcionario.toStatus(funcionario.getStatus().getStatus())));
        responseFuncionario.setTipo(String.valueOf(tipo_funcionario.toTipo(funcionario.getTipo().getTipo())));
        responseFuncionario.setCargo(funcionario.getCargo());
        responseFuncionario.setDataAdmissao(String.valueOf(funcionario.getData_admissao()));
        responseFuncionario.setDataDemissao(String.valueOf(funcionario.getData_demissao()));
        responseFuncionario.setIdUsuario(Math.toIntExact(funcionario.getId_usuario().getId()));
        responseFuncionario.setIdEmpresa(Math.toIntExact(funcionario.getId_empresa().getId()));
        return responseFuncionario;
    }

    //método que utiliza uma lista para conversão de dados de Funcionario em ResponseFuncionario
    public static List<ResponseFuncionarioData> toResponse(List<Funcionario> funcionarios) {
        List<ResponseFuncionarioData> responseFuncionarioData = new ArrayList<>();
        funcionarios.forEach(funcionario -> responseFuncionarioData.add(toResponse(funcionario)));
        return responseFuncionarioData;
    }

    //método que converte dado responseFuncionarioData em um dado Funcionario no modelo pré-definido no contrato
    static Funcionario toModel(ResponseFuncionarioData responseFuncionarioData) {
        Funcionario funcionario = new Funcionario();
        funcionario.setStatus(status_funcionario.valueOf(responseFuncionarioData.getStatus()));
        funcionario.setTipo(tipo_funcionario.valueOf(responseFuncionarioData.getTipo()));
        funcionario.setCargo(responseFuncionarioData.getCargo());
        funcionario.setData_admissao(LocalDate.parse(responseFuncionarioData.getDataAdmissao()));
        funcionario.setData_demissao(LocalDate.parse(responseFuncionarioData.getDataDemissao()));
        funcionario.setId_usuario(responseFuncionarioData.getIdUsuario());
        funcionario.setId_empresa(responseFuncionarioData.getIdEmpresa());
        return funcionario;
    }

    //método que utiliza uma lista para conversão de dados ResponseAutor em Funcionario
    static List<Funcionario> toModel(List<ResponseFuncionarioData> responseFuncionarioDatas) {
        List<Funcionario> funcionarios = new ArrayList<>();
        responseFuncionarioDatas.forEach(responseFuncionarioData -> {
            funcionarios.add(toModel(responseFuncionarioData));
        });
        return funcionarios;


    }
}
