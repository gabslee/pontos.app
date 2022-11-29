package app.pontos.controllers;

import app.pontos.apis.EmpresaApi;
import app.pontos.components.RequestPayloadEmpresa;
import app.pontos.components.ResponseEmpresa;
import app.pontos.mappers.EmpresaMapper;
import app.pontos.models.Empresa;
import app.pontos.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("empresa")
public class EmpresaController implements EmpresaApi {

    @Autowired
    EmpresaService service;


   //LISTAGEM DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @GetMapping
    public ResponseEntity<ResponseEmpresa> listaEmpresa() {
        ResponseEmpresa response = new ResponseEmpresa();
        List<Empresa> empresas = service.findAll();
        response.setData(EmpresaMapper.toResponse(empresas));
        return ResponseEntity.ok(response);
        }


        //LISTAGEM DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmpresa> listaEmpresaId(@PathVariable("id") Integer id) {
        ResponseEmpresa response = new ResponseEmpresa();
        Empresa empresa = service.findById(id.longValue());
        response.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(response);
    }



    //EXCLUSÃO LÓGICA DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseEmpresa> deleteEmpresa(@PathVariable("id")Integer id) {
        ResponseEmpresa responseEmpresa = new ResponseEmpresa();
        Empresa empresa = service.findById(id.longValue());
        empresa = service.delete(empresa.id);
        responseEmpresa.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(responseEmpresa);
    }



    //CADASTRO DE NOVOS ITENS ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEmpresa> cadastroEmpresa(@RequestBody RequestPayloadEmpresa requestPayloadEmpresa) {
        ResponseEmpresa response = new ResponseEmpresa();
        Empresa empresa = service.fromRequest(requestPayloadEmpresa);
        empresa = service.create(empresa);
        response.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(response);
    }



    //ALTERAÇÃO DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseEmpresa> alteraEmpresa(@PathVariable("id")Integer id,@RequestBody RequestPayloadEmpresa requestPayloadEmpresa) {
        ResponseEmpresa response = new ResponseEmpresa();
        Empresa validador = service.validador(id.longValue());
        Empresa empresa = service.fromRequest(requestPayloadEmpresa);
        empresa = service.validaEmpresa(empresa,validador);
        empresa = service.update(empresa, id.longValue());
        response.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(response);
    }



    //ATIVAÇÃO LÓGICA DOS ITENS ATRAVÉS DO MÉTODO "REATIVA" CRIADO NA CLASSE SERVICE
    @Override
    @GetMapping("/ativa/{id}")
    @Transactional
    public ResponseEntity<ResponseEmpresa> reativaEmpresa(@PathVariable("id") Integer id) {
        ResponseEmpresa responseEmpresa = new ResponseEmpresa();
        Empresa empresa = service.reativa(id.longValue());
        responseEmpresa.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(responseEmpresa);
    }


}
