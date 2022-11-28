package app.pontos.services;


import app.pontos.enums.status_usuario;
import app.pontos.models.Usuario;
import app.pontos.repository.UsuarioRepository;
import br.com.crud.components.RequestPayloadUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(Long id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.get();
    }

    public Usuario create(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario delete(Long id){
        Optional<Usuario> obj = repository.findById(id);
        Usuario usuario = repository.getReferenceById(id);
        usuario.setStatus(status_usuario.INATIVO);
        return obj.get();
    }

    public Usuario reativa(Long id){
        Optional<Usuario> obj = repository.findById(id);
        Usuario usuario = repository.getReferenceById(id);
        usuario.setStatus(status_usuario.ATIVO);
        return obj.get();
    }


    public Usuario update(Usuario usuario, Long id){
        usuario.setId(id);
        return repository.save(usuario);
    }

    public Usuario fromRequest(RequestPayloadUsuario requestPayloadUsuario){
        Usuario usuario = new Usuario();
        usuario.setNome(requestPayloadUsuario.getData().getNome());
        usuario.setEmail(requestPayloadUsuario.getData().getEmail());
        usuario.setSenha(requestPayloadUsuario.getData().getSenha());
        usuario.setCpf(requestPayloadUsuario.getData().getCpf());
        usuario.setNascimento(LocalDate.parse(requestPayloadUsuario.getData().getNascimento()));
        usuario.setFoto(requestPayloadUsuario.getData().getFoto());
        return usuario;
    }


}
