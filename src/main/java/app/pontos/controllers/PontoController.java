package app.pontos.controllers;


import app.pontos.apis.PontoApi;
import app.pontos.components.RequestPayloadPonto;
import app.pontos.components.ResponsePonto;
import app.pontos.mappers.PontoMapper;
import app.pontos.models.Ponto;
import app.pontos.services.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("ponto")
public class PontoController implements PontoApi {

    @Autowired
    PontoService service;



    //LISTAGEM DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @GetMapping
    public ResponseEntity<ResponsePonto> listaPonto() {
        ResponsePonto response = new ResponsePonto();
        List<Ponto> pontos = service.findAll();
        response.setData(PontoMapper.toResponse(pontos));
        return  ResponseEntity.ok(response);
    }




    //LISTAGEM DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePonto> listaPontoId(@PathVariable("id") Integer id) {
        ResponsePonto response = new ResponsePonto();
        Ponto ponto = service.findById(id.longValue());
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }




    //EXCLUSÃO REAL DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePonto> deletePonto(@PathVariable("id") Integer id) {
        ResponsePonto response = new ResponsePonto();
        Ponto ponto = service.findById(id.longValue());
        ponto = service.delete(ponto.id);
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }




    //CADASTRO DE NOVOS ITENS ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePonto> cadastroPonto(@RequestBody RequestPayloadPonto requestPayloadPonto) {
        ResponsePonto response = new ResponsePonto();
        Ponto ponto = service.fromRequest(requestPayloadPonto);
        ponto = service.create(ponto);
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }




    //ALTERAÇÃO DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponsePonto> alteraPonto(@PathVariable("id") Integer id,@RequestBody RequestPayloadPonto requestPayloadPonto) {
        ResponsePonto response = new ResponsePonto();
        Ponto validador = service.validador(id.longValue());
        Ponto ponto = service.fromRequest(requestPayloadPonto);
        ponto = service.validaPonto(ponto,validador);
        ponto = service.update(ponto, id.longValue());
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }
}
