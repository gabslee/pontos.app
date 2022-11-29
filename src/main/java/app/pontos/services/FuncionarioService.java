/* CLASSE SERVICE QUE UTILIZA A REPOSITORY PARA CRIAÇÃO DA LÓGICA DA API,
E SE UTILIZA DOS MÉTODOS HERDADOS DA JPA
 */


package app.pontos.services;

import app.pontos.components.RequestPayloadFuncionario;
import app.pontos.enums.status_funcionario;
import app.pontos.enums.tipo_funcionario;
import app.pontos.models.Funcionario;
import app.pontos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository repository;

    //MÉTODO PARA LISTAR TODOS OS ITENS DA TABELA UTILIZANDO JPA
    public List<Funcionario> findAll(){
        return repository.findAll();
    }




    //MÉTODO PARA LISTAR ITEM DA TABELA ATRAVÉS DO ID UTILIZANDO JPA
    public Funcionario findById(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        return obj.get();
    }



    //MÉTODO PARA CADASTRAR NOVO ITEM NA TABELA UTILIZANDO JPA
    public Funcionario create(Funcionario funcionario){
        return repository.save(funcionario);
    }




    /*MÉTODO PARA FAZER A EXCLUSÃO LÓGICA DO ITEM ATRAVÉS DO ID,
    EXCLUSÃO LÓGICA É FEITA PELA MUDANÇA DO ENUM STATUS, DE ATIVO PARA INATIVO*/
    public Funcionario delete(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        Funcionario funcionario = repository.getReferenceById(id);
        funcionario.setStatus(status_funcionario.INATIVO);
        return obj.get();
    }



    //MÉTODO PARA ALTERAR ITEM NA TABELA UTILIZANDO JPA
    public Funcionario update(Funcionario funcionario, Long id){
        funcionario.setId(id);
        return repository.save(funcionario);
    }



    /*MÉTODO PARA FAZER A REATIVAÇÃO LÓGICA DO ITEM ATRAVÉS DO ID,
    EXCLUSÃO LÓGICA É FEITA PELA MUDANÇA DO ENUM STATUS, DE INATIVO PARA ATIVO*/
    public Funcionario reativa(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        Funcionario funcionario = repository.getReferenceById(id);
        funcionario.setStatus(status_funcionario.ATIVO);
        return obj.get();
    }




    /*MÉTODO QUE RECEBE AS INFORMAÇÕES DO CORPO DA REQUISIÇÃO E AS CONVERTE
     * NO MESMO TIPO DE OBJETO DA ENTIDADE.
     * IF's UTILIZADOS PARA NÃO QUEBRAR A REQUISIÇÃO CASO ALGUM DADO VENHA NULO,
     * NO CASO DE ALGUMA MUDAÇA SER FEITA EM SOMENTE UM CAMPO*/
    public Funcionario fromRequest(RequestPayloadFuncionario requestPayloadFuncionario){
        Funcionario funcionario = new Funcionario();
        if(requestPayloadFuncionario.getData().getTipo() != null){
        funcionario.setTipo(tipo_funcionario.valueOf(requestPayloadFuncionario.getData().getTipo()));}
        if(requestPayloadFuncionario.getData().getCargo() != null){
        funcionario.setCargo(requestPayloadFuncionario.getData().getCargo());}
        if(requestPayloadFuncionario.getData().getDataAdmissao() != null){
        funcionario.setData_admissao(LocalDate.parse(requestPayloadFuncionario.getData().getDataAdmissao()));}
        if(requestPayloadFuncionario.getData().getDataDemissao() != null){
        funcionario.setData_demissao(LocalDate.parse(requestPayloadFuncionario.getData().getDataDemissao()));}
        if(requestPayloadFuncionario.getData().getIdUsuario() != null){
        funcionario.setId_usuario(requestPayloadFuncionario.getData().getIdUsuario());}
        if(requestPayloadFuncionario.getData().getIdEmpresa() != null){
        funcionario.setId_empresa(requestPayloadFuncionario.getData().getIdEmpresa());}
        return funcionario;
    }




    /*MÉTODO QUE CRIA UM CLONE TEMPORÁRIO DA ENTIDADE/OBJETO ATUAL*/
    public Funcionario validador(Long id){
        Optional<Funcionario> funcionario = repository.findById(id);
        Funcionario validador = new Funcionario();
        validador.setTipo(tipo_funcionario.toTipo(funcionario.get().getTipo().getTipo()));
        validador.setCargo(funcionario.get().getCargo());
        validador.setData_admissao(funcionario.get().getData_admissao());
        validador.setData_demissao(funcionario.get().getData_demissao());
        validador.setId_empresa(funcionario.get().getId_empresa());
        validador.setId_usuario(funcionario.get().getId_empresa());
        return validador;
    }




    /*MÉTODO PARA UTILIZA O CLONE PARA TRANSFERIR OS DADOS ANTIGOS DA ENTIDADE QUE VIERAM
     * NULOS DA REQUISIÇÃO, POR MOTIVO DE ALTERAÇÃO EM SOMENTE UM CAMPO */
    public Funcionario validaFuncionario(Funcionario funcionario, Funcionario funcionarioValidado){
        if(funcionario.getTipo() == null)funcionario.setTipo(tipo_funcionario.valueOf(String.valueOf(funcionarioValidado.getTipo())));
        if(funcionario.getCargo() == null)funcionario.setCargo(funcionarioValidado.getCargo());
        if (funcionario.getData_admissao() == null)funcionario.setData_admissao(funcionarioValidado.getData_admissao());
        if(funcionario.getData_demissao() == null)funcionario.setData_demissao(funcionarioValidado.getData_demissao());
        if (funcionario.getId_usuario() == null)funcionario.setId_usuario(funcionarioValidado.getId_usuario());
        if(funcionario.getId_empresa() == null)funcionario.setId_empresa(funcionarioValidado.getId_empresa());
        return funcionario;
    }

}
