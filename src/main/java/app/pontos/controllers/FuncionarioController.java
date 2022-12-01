package app.pontos.controllers;

import app.pontos.apis.FuncionarioApi;
import app.pontos.apis.FuncionarioApiDelegate;
import app.pontos.components.RequestPayloadFuncionario;
import app.pontos.components.ResponseFuncionario;
import app.pontos.mappers.FuncionarioMapper;
import app.pontos.models.Funcionario;
import app.pontos.services.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RestController
public class FuncionarioController implements FuncionarioApiDelegate {

    @Autowired
    FuncionarioService service;

    //LISTAGEM DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    public ResponseEntity<ResponseFuncionario> listaFuncionario() {
        ResponseFuncionario response = new ResponseFuncionario();
        List<Funcionario> funcionarios = service.findAll();
        response.setData(FuncionarioMapper.toResponse(funcionarios));
        return ResponseEntity.ok(response);
    }




    //LISTAGEM DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    public ResponseEntity<ResponseFuncionario> listaFuncionarioId(@PathVariable("id") Integer id) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.findById(id.longValue());
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }




    //EXCLUSÃO LÓGICA DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    public ResponseEntity<ResponseFuncionario> deleteFuncionario(@PathVariable("id") Integer id) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.findById(id.longValue());
        funcionario = service.delete(funcionario.id);
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }



    //CADASTRO DE NOVOS ITENS ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    public ResponseEntity<ResponseFuncionario> cadastroFuncionario(@RequestBody RequestPayloadFuncionario requestPayloadFuncionario) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.fromRequest(requestPayloadFuncionario);
        funcionario = service.create(funcionario);
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }




    //ALTERAÇÃO DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    public ResponseEntity<ResponseFuncionario> alteraFuncionario(@PathVariable("id") Integer id, @RequestBody RequestPayloadFuncionario requestPayloadFuncionario) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario validador = service.validador(id.longValue());
        Funcionario funcionario = service.fromRequest(requestPayloadFuncionario);
        funcionario = service.validaFuncionario(funcionario,validador);
        funcionario = service.update(funcionario, id.longValue());
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }





    //ATIVAÇÃO LÓGICA DOS ITENS ATRAVÉS DO MÉTODO "REATIVA" CRIADO NA CLASSE SERVICE
    @Override
    public ResponseEntity<ResponseFuncionario> reativaFuncionario(@PathVariable("id") Integer id) {
        ResponseFuncionario responseFuncionario = new ResponseFuncionario();
        Funcionario funcionario = service.reativa(id.longValue());
        responseFuncionario.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(responseFuncionario);
    }
}
