/* CLASSE SERVICE QUE UTILIZA A REPOSITORY PARA CRIAÇÃO DA LÓGICA DA API,
E SE UTILIZA DOS MÉTODOS HERDADOS DA JPA */


package app.pontos.services;

import app.pontos.components.RequestPayloadEmpresa;
import app.pontos.enums.status_empresa;
import app.pontos.models.Empresa;
import app.pontos.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    //MÉTODO PARA LISTAR TODOS OS ITENS DA TABELA UTILIZANDO JPA
    public List<Empresa> findAll(){
        return repository.findAll();
    }




    //MÉTODO PARA LISTAR ITEM DA TABELA ATRAVÉS DO ID UTILIZANDO JPA
    public Empresa findById(Long id){
        Optional<Empresa> obj = repository.findById(id);
        return obj.get();
    }




    //MÉTODO PARA CADASTRAR NOVO ITEM NA TABELA UTILIZANDO JPA
    public Empresa create(Empresa empresa){
        return repository.save(empresa);
    }





    /*MÉTODO PARA FAZER A EXCLUSÃO LÓGICA DO ITEM ATRAVÉS DO ID,
    EXCLUSÃO LÓGICA É FEITA PELA MUDANÇA DO ENUM STATUS, DE ATIVO PARA INATIVO*/
    public Empresa delete(Long id){
        Optional<Empresa> obj = repository.findById(id);
        Empresa empresa = repository.getReferenceById(id);
        empresa.setStatus(status_empresa.INATIVO);
        return obj.get();
    }



    //MÉTODO PARA ALTERAR ITEM NA TABELA UTILIZANDO JPA
    public Empresa update(Empresa empresa,Long id){
        empresa.setId(id);
        return repository.save(empresa);
    }




    /*MÉTODO PARA FAZER A REATIVAÇÃO LÓGICA DO ITEM ATRAVÉS DO ID,
    EXCLUSÃO LÓGICA É FEITA PELA MUDANÇA DO ENUM STATUS, DE INATIVO PARA ATIVO*/
    public Empresa reativa(Long id) {
        Optional<Empresa> obj = repository.findById(id);
        Empresa empresa = repository.getReferenceById(id);
        empresa.setStatus(status_empresa.ATIVO);
        return obj.get();
    }



    /*MÉTODO QUE RECEBE AS INFORMAÇÕES DO CORPO DA REQUISIÇÃO E AS CONVERTE
    * NO MESMO TIPO DE OBJETO DA ENTIDADE.
    * IF's UTILIZADOS PARA NÃO QUEBRAR A REQUISIÇÃO CASO ALGUM DADO VENHA NULO,
    * NO CASO DE ALGUMA MUDAÇA SER FEITA EM SOMENTE UM CAMPO*/
    public Empresa fromRequest(RequestPayloadEmpresa requestPayloadEmpresa){
        Empresa empresa = new Empresa();
        if(requestPayloadEmpresa.getData().getCnpj() != null){
            empresa.setCnpj(Long.valueOf(requestPayloadEmpresa.getData().getCnpj()));}
        if(requestPayloadEmpresa.getData().getNome() != null){
            empresa.setNome(requestPayloadEmpresa.getData().getNome());}
        if(requestPayloadEmpresa.getData().getEmail() != null){
            empresa.setEmail(requestPayloadEmpresa.getData().getEmail());}
        if(requestPayloadEmpresa.getData().getTelefone() != null){
            empresa.setTelefone(requestPayloadEmpresa.getData().getTelefone());}
        if(requestPayloadEmpresa.getData().getIdUsuario() != null){
            empresa.setId_usuario(requestPayloadEmpresa.getData().getIdUsuario());
        }
        return empresa;
    }




    /*MÉTODO QUE CRIA UM CLONE TEMPORÁRIO DA ENTIDADE/OBJETO ATUAL*/
    public Empresa validador(Long id){
        Optional<Empresa> empresa = repository.findById(id);
        Empresa validador = new Empresa();
        validador.setCnpj(empresa.get().getCnpj());
        validador.setNome(empresa.get().getNome());
        validador.setTelefone(empresa.get().getTelefone());
        validador.setEmail(empresa.get().getEmail());
        validador.setId_usuario(empresa.get().getId_usuario());
        return validador;
    }





    /*MÉTODO PARA UTILIZA O CLONE PARA TRANSFERIR OS DADOS ANTIGOS DA ENTIDADE QUE VIERAM
    * NULOS DA REQUISIÇÃO, POR MOTIVO DE ALTERAÇÃO EM SOMENTE UM CAMPO */
    public Empresa validaEmpresa(Empresa empresa, Empresa empresaValidada){
        if(empresa.getCnpj() == null) empresa.setCnpj(empresaValidada.getCnpj());
        if(empresa.getNome() == null) empresa.setNome(empresaValidada.getNome());
        if(empresa.getTelefone() == null) empresa.setTelefone(empresaValidada.getTelefone());
        if(empresa.getEmail() == null) empresa.setEmail(empresaValidada.getEmail());
        if(empresa.getId_usuario() == null) empresa.setId_usuario(empresaValidada.getId_usuario());
        return empresa;
    }



}
