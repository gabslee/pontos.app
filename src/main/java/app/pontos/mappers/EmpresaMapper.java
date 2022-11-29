package app.pontos.mappers;

import app.pontos.components.ResponseEmpresaData;
import app.pontos.enums.status_empresa;
import app.pontos.models.Empresa;


import java.util.ArrayList;
import java.util.List;

public class EmpresaMapper {

    //método que converte dado Empresa em um dado ResponseEmpresa
    public static ResponseEmpresaData toResponse(Empresa empresa){
        ResponseEmpresaData responseEmpresa = new ResponseEmpresaData();
        responseEmpresa.setIdEmpresa(empresa.getId().intValue());
        responseEmpresa.setEmail(empresa.getEmail());
        responseEmpresa.setNome(empresa.getNome());
        responseEmpresa.setCnpj(String.valueOf(empresa.getCnpj()));
        responseEmpresa.setTelefone(empresa.getTelefone());
        responseEmpresa.setStatus(String.valueOf(status_empresa.toStatus(empresa.getStatus().getStatus())));
        responseEmpresa.setIdUsuario(empresa.getId_usuario());
        return responseEmpresa;
    }

    
    
    //método que utiliza uma lista para conversão de dados de Empresa em ResponseEmpresa
    public static List<ResponseEmpresaData> toResponse(List<Empresa> empresas){
        List<ResponseEmpresaData> responseEmpresaData = new ArrayList<>();
        empresas.forEach(empresa -> {
            responseEmpresaData.add(toResponse(empresa));
        });
        return responseEmpresaData;
    }



    //método que utiliza uma lista para conversão de dados ResponseEmpresa em Empresa
    static Empresa toModel(ResponseEmpresaData responseEmpresaData){
        Empresa empresa = new Empresa();
        empresa.setId(responseEmpresaData.getIdEmpresa().longValue());
        empresa.setCnpj(Long.valueOf(responseEmpresaData.getCnpj()));
        empresa.setNome(responseEmpresaData.getNome());
        empresa.setEmail(responseEmpresaData.getEmail());
        empresa.setTelefone(responseEmpresaData.getTelefone());
        empresa.setId_usuario(responseEmpresaData.getIdUsuario());
        return empresa;
    }

    
    //método que utiliza uma lista para conversão de dados ResponseEmpresa em Empresa
    static List<Empresa> toModel(List<ResponseEmpresaData> responseEmpresaDatas){
        List<Empresa> empresas = new ArrayList<>();
        responseEmpresaDatas.forEach(responseEmpresaData -> {
            empresas.add(toModel(responseEmpresaData));
        });
        return empresas;
    }

}
