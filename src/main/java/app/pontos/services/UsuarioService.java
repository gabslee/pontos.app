/* CLASSE SERVICE QUE UTILIZA A REPOSITORY PARA CRIAÇÃO DA LÓGICA DA API,
E SE UTILIZA DOS MÉTODOS HERDADOS DA JPA */


package app.pontos.services;


import app.pontos.components.RequestPayloadUsuario;
import app.pontos.enums.status_usuario;
import app.pontos.models.Usuario;
import app.pontos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    //MÉTODO PARA LISTAR TODOS OS ITENS DA TABELA UTILIZANDO JPA
    public List<Usuario> findAll(){
        return repository.findAll();
    }




    //MÉTODO PARA LISTAR ITEM DA TABELA ATRAVÉS DO ID UTILIZANDO JPA
    public Usuario findById(Long id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.get();
    }




    //MÉTODO PARA CADASTRAR NOVO ITEM NA TABELA UTILIZANDO JPA
    public Usuario create(Usuario usuario) {
        return repository.save(usuario);
    }





    /*MÉTODO PARA FAZER A EXCLUSÃO LÓGICA DO ITEM ATRAVÉS DO ID,
    EXCLUSÃO LÓGICA É FEITA PELA MUDANÇA DO ENUM STATUS, DE ATIVO PARA INATIVO*/
    public Usuario delete(Long id){
        Optional<Usuario> obj = repository.findById(id);
        Usuario usuario = repository.getReferenceById(id);
        usuario.setStatus(status_usuario.INATIVO);
        return obj.get();
    }




    //MÉTODO PARA ALTERAR ITEM NA TABELA UTILIZANDO JPA
    public Usuario update(Usuario usuario, Long id){
        usuario.setId(id);
        return repository.save(usuario);
    }




    /*MÉTODO PARA FAZER A REATIVAÇÃO LÓGICA DO ITEM ATRAVÉS DO ID,
    EXCLUSÃO LÓGICA É FEITA PELA MUDANÇA DO ENUM STATUS, DE INATIVO PARA ATIVO*/
    public Usuario reativa(Long id){
        Optional<Usuario> obj = repository.findById(id);
        Usuario usuario = repository.getReferenceById(id);
        usuario.setStatus(status_usuario.ATIVO);
        return obj.get();
    }





    /*MÉTODO QUE RECEBE AS INFORMAÇÕES DO CORPO DA REQUISIÇÃO E AS CONVERTE
     * NO MESMO TIPO DE OBJETO DA ENTIDADE.
     * IF's UTILIZADOS PARA NÃO QUEBRAR A REQUISIÇÃO CASO ALGUM DADO VENHA NULO,
     * NO CASO DE ALGUMA MUDAÇA SER FEITA EM SOMENTE UM CAMPO*/
    public Usuario fromRequest(RequestPayloadUsuario requestPayloadUsuario){
        Usuario usuario = new Usuario();

        if(requestPayloadUsuario.getData().getNome() != null){
        usuario.setNome(requestPayloadUsuario.getData().getNome());}
        if(requestPayloadUsuario.getData().getEmail() != null){
        usuario.setEmail(requestPayloadUsuario.getData().getEmail());}
        if(requestPayloadUsuario.getData().getSenha() != null){
        usuario.setSenha(requestPayloadUsuario.getData().getSenha());}
        if(requestPayloadUsuario.getData().getCpf() != null){
        usuario.setCpf(requestPayloadUsuario.getData().getCpf());}
        if(requestPayloadUsuario.getData().getNascimento() != null){
        usuario.setNascimento(LocalDate.parse(requestPayloadUsuario.getData().getNascimento()));}
        if(requestPayloadUsuario.getData().getFoto() != null){
        usuario.setFoto(requestPayloadUsuario.getData().getFoto());}
        return usuario;
    }





    /*MÉTODO QUE CRIA UM CLONE TEMPORÁRIO DA ENTIDADE/OBJETO ATUAL*/
    public Usuario validador(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        Usuario validador = new Usuario();
        validador.setNome(usuario.get().getNome());
        validador.setEmail(usuario.get().getEmail());
        validador.setSenha(usuario.get().getSenha());
        validador.setCpf(usuario.get().getCpf());
        validador.setCpf(usuario.get().getCpf());
        validador.setNascimento(usuario.get().getNascimento());
        validador.setFoto(usuario.get().getFoto());
        return validador;
    }





    /*MÉTODO PARA UTILIZA O CLONE PARA TRANSFERIR OS DADOS ANTIGOS DA ENTIDADE QUE VIERAM
     * NULOS DA REQUISIÇÃO, POR MOTIVO DE ALTERAÇÃO EM SOMENTE UM CAMPO */
    public Usuario validaUsuario(Usuario usuario, Usuario usuarioValidado){
        if(usuario.getNome() == null) usuario.setNome(usuarioValidado.getNome());
        if(usuario.getEmail() == null) usuario.setEmail(usuarioValidado.getEmail());
        if(usuario.getSenha() == null) usuario.setSenha(usuarioValidado.getSenha());
        if(usuario.getCpf() == null) usuario.setCpf(usuarioValidado.getCpf());
        if(usuario.getNascimento() == null) usuario.setNascimento(usuarioValidado.getNascimento());
        if(usuario.getFoto() == null) usuario.setFoto(usuarioValidado.getFoto());
        return usuario;
    }




}
