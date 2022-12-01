package app.pontos.controllers;


import app.pontos.apis.PontoApi;
import app.pontos.apis.PontoApiDelegate;
import app.pontos.components.RequestPayloadPonto;
import app.pontos.components.ResponsePonto;
import app.pontos.mappers.PontoMapper;
import app.pontos.models.Ponto;
import app.pontos.services.PontoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RestController
public class PontoController implements PontoApiDelegate {

    @Autowired
    PontoService service;



    //LISTAGEM DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    public ResponseEntity<ResponsePonto> listaPonto() {
        ResponsePonto response = new ResponsePonto();
        List<Ponto> pontos = service.findAll();
        response.setData(PontoMapper.toResponse(pontos));
        return  ResponseEntity.ok(response);
    }




    //LISTAGEM DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    public ResponseEntity<ResponsePonto> listaPontoId(@PathVariable("id") Integer id) {
        ResponsePonto response = new ResponsePonto();
        Ponto ponto = service.findById(id.longValue());
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }




    //EXCLUSÃO REAL DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    public ResponseEntity<ResponsePonto> deletePonto(@PathVariable("id") Integer id) {
        ResponsePonto response = new ResponsePonto();
        Ponto ponto = service.findById(id.longValue());
        ponto = service.delete(ponto.id);
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }




    //CADASTRO DE NOVOS ITENS ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    public ResponseEntity<ResponsePonto> cadastroPonto(@RequestBody RequestPayloadPonto requestPayloadPonto) {
        ResponsePonto response = new ResponsePonto();
        Ponto ponto = service.fromRequest(requestPayloadPonto);
        ponto = service.create(ponto);
        response.setData(Collections.singletonList(PontoMapper.toResponse(ponto)));
        return ResponseEntity.ok(response);
    }




    //ALTERAÇÃO DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
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
