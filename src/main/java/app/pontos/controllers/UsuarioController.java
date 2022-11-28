package app.pontos.controllers;

import app.pontos.mappers.UsuarioMapper;
import app.pontos.models.Usuario;
import app.pontos.services.UsuarioService;
import br.com.crud.apis.UsuarioApi;
import br.com.crud.components.RequestPayloadUsuario;
import br.com.crud.components.ResponseUsuario;
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

    @GetMapping
    @Override
    public ResponseEntity<ResponseUsuario> listaUsuario() {
        ResponseUsuario response = new ResponseUsuario();
        List<Usuario> usuarios = service.findAll();
        response.setData(UsuarioMapper.toResponse(usuarios));
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuario> listaUsuarioId(@PathVariable("id") Integer id) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario usuario = service.findById(id.longValue());
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }

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

    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseUsuario> alteraUsuario(@PathVariable("id")Integer id,@RequestBody RequestPayloadUsuario requestPayloadUsuario) {
        ResponseUsuario responseUsuario = new ResponseUsuario();
        Usuario usuario = service.fromRequest(requestPayloadUsuario);
        usuario = service.update(usuario,id.longValue());
        responseUsuario.setData(Collections.singletonList(UsuarioMapper.toResponse(usuario)));
        return ResponseEntity.ok(responseUsuario);
    }

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
