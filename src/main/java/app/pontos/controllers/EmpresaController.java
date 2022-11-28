package app.pontos.controllers;

import app.pontos.mappers.EmpresaMapper;
import app.pontos.models.Empresa;
import app.pontos.services.EmpresaService;
import br.com.crud.apis.EmpresaApi;
import br.com.crud.components.RequestPayloadEmpresa;
import br.com.crud.components.ResponseEmpresa;
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


    @Override
    @GetMapping
    public ResponseEntity<ResponseEmpresa> listaEmpresa() {
        ResponseEmpresa response = new ResponseEmpresa();
        List<Empresa> empresas = service.findAll();
        response.setData(EmpresaMapper.toResponse(empresas));
        return ResponseEntity.ok(response);
        }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmpresa> listaEmpresaId(@PathVariable("id") Integer id) {
        ResponseEmpresa response = new ResponseEmpresa();
        Empresa empresa = service.findById(id.longValue());
        response.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(response);
    }

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

    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseEmpresa> alteraEmpresa(@PathVariable("id")Integer id,@RequestBody RequestPayloadEmpresa requestPayloadEmpresa) {
        ResponseEmpresa response = new ResponseEmpresa();
        Empresa empresa = service.fromRequest(requestPayloadEmpresa);
        empresa = service.update(empresa, id.longValue());
        response.setData(Collections.singletonList(EmpresaMapper.toResponse(empresa)));
        return ResponseEntity.ok(response);
    }

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
