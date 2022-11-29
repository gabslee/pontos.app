package app.pontos.controllers;

import app.pontos.apis.UsuarioApi;
import app.pontos.components.RequestPayloadUsuario;
import app.pontos.components.ResponseUsuario;
import app.pontos.mappers.UsuarioMapper;
import app.pontos.models.Usuario;
import app.pontos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController implements UsuarioApi {

    @Autowired
    UsuarioService service;



    //LISTAGEM DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @GetMapping
    @Override
    public ResponseEntity<ResponseUsuario> listaUsuario() {
        ResponseUsuario response = new ResponseUsuario();
        List<Usuario> usuarios = service.findAll();
        response.setData(UsuarioMapper.toResponse(usuarios));
        return ResponseEntity.ok(response);
    }





    //LISTAGEM DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuario> listaUsuarioId(@PathVariable("id") Integer id) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario usuario = service.findById(id.longValue());
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }



    //EXCLUSÃO LÓGICA DOS ITENS POR ID ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseUsuario> deleteUsuario(@PathVariable("id")Integer id) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario usuario = service.findById(id.longValue());
        usuario = service.delete(usuario.id);
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }




    //CADASTRO DE NOVOS ITENS ATRAVÉS DOS MÉTODOS GERADO PELO CONTRATO
    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUsuario> cadastroUsuario(@RequestBody RequestPayloadUsuario requestPayloadUsuario) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario usuario = service.fromRequest(requestPayloadUsuario);
        usuario = service.create(usuario);
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }




    //ALTERAÇÃO DOS ITENS ATRAVÉS DOS MÉTODOS GERADOS PELO CONTRATO
    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseUsuario> alteraUsuario(@PathVariable("id")Integer id,@RequestBody RequestPayloadUsuario requestPayloadUsuario) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario validador = service.validador(id.longValue());
        Usuario usuario = service.fromRequest(requestPayloadUsuario);
        usuario = service.validaUsuario(usuario,validador);
        usuario = service.update(usuario,id.longValue());
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }




    //ATIVAÇÃO LÓGICA DOS ITENS ATRAVÉS DO MÉTODO "REATIVA" CRIADO NA CLASSE SERVICE
    @Override
    @GetMapping("/ativa/{id}")
    @Transactional
    public ResponseEntity<ResponseUsuario> reativaUsuario(@PathVariable("id") Integer id) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario usuario = service.reativa(id.longValue());
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }

}
