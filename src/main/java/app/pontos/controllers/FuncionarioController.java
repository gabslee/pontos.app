package app.pontos.controllers;

import app.pontos.mappers.FuncionarioMapper;
import app.pontos.models.Funcionario;
import app.pontos.services.FuncionarioService;
import br.com.crud.apis.FuncionarioApi;
import br.com.crud.components.RequestPayloadFuncionario;
import br.com.crud.components.ResponseFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController implements FuncionarioApi {

    @Autowired
    FuncionarioService service;

    @GetMapping
    @Override
    public ResponseEntity<ResponseFuncionario> listaFuncionario() {
        ResponseFuncionario response = new ResponseFuncionario();
        List<Funcionario> funcionarios = service.findAll();
        response.setData(FuncionarioMapper.toResponse(funcionarios));
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseFuncionario> listaFuncionarioId(@PathVariable("id") Integer id) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.findById(id.longValue());
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseFuncionario> deleteFuncionario(@PathVariable("id") Integer id) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.findById(id.longValue());
        funcionario = service.delete(funcionario.id);
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ResponseFuncionario> cadastroFuncionario(@RequestBody RequestPayloadFuncionario requestPayloadFuncionario) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.fromRequest(requestPayloadFuncionario);
        funcionario = service.create(funcionario);
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseFuncionario> alteraFuncionario(@PathVariable("id") Integer id, @RequestBody RequestPayloadFuncionario requestPayloadFuncionario) {
        ResponseFuncionario response = new ResponseFuncionario();
        Funcionario funcionario = service.fromRequest(requestPayloadFuncionario);
        funcionario = service.update(funcionario, funcionario.id);
        response.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(response);
    }
    @Override
    @GetMapping("/ativa/{id}")
    @Transactional
    public ResponseEntity<ResponseFuncionario> reativaFuncionario(@PathVariable("id") Integer id) {
        ResponseFuncionario responseFuncionario = new ResponseFuncionario();
        Funcionario funcionario = service.reativa(id.longValue());
        responseFuncionario.setData(Collections.singletonList(FuncionarioMapper.toResponse(funcionario)));
        return ResponseEntity.ok(responseFuncionario);
    }
}
