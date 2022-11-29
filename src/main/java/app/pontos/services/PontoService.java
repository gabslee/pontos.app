/* CLASSE SERVICE QUE UTILIZA A REPOSITORY PARA CRIAÇÃO DA LÓGICA DA API,
E SE UTILIZA DOS MÉTODOS HERDADOS DA JPA
 */


package app.pontos.services;

import app.pontos.components.RequestPayloadPonto;
import app.pontos.enums.tipo_ponto;
import app.pontos.models.Ponto;
import app.pontos.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PontoService {

    @Autowired
    PontoRepository repository;

    //MÉTODO PARA LISTAR TODOS OS ITENS DA TABELA UTILIZANDO JPA
    public List<Ponto> findAll(){
        return repository.findAll();}




    //MÉTODO PARA LISTAR ITEM DA TABELA ATRAVÉS DO ID UTILIZANDO JPA
    public Ponto findById(Long id){
        Optional<Ponto> obj = repository.findById(id);
        return obj.get();
    }



    //MÉTODO PARA CADASTRAR NOVO ITEM NA TABELA UTILIZANDO JPA
    public Ponto create(Ponto ponto){
        return repository.save(ponto);}





    //MÉTODO PARA FAZER EXCLUSÃO REAL ITEM UTILIZANDO JPA
    public Ponto delete(Long id){
        Optional<Ponto> obj = repository.findById(id);
        repository.deleteById(id);
        return obj.get();
    }



    //MÉTODO PARA ALTERAR ITEM NA TABELA UTILIZANDO JPA
    public Ponto update(Ponto ponto, Long id){
        ponto.setId(id);
        return repository.save(ponto);
    }




    /*MÉTODO QUE RECEBE AS INFORMAÇÕES DO CORPO DA REQUISIÇÃO E AS CONVERTE
     * NO MESMO TIPO DE OBJETO DA ENTIDADE.
     * IF's UTILIZADOS PARA NÃO QUEBRAR A REQUISIÇÃO CASO ALGUM DADO VENHA NULO,
     * NO CASO DE ALGUMA MUDAÇA SER FEITA EM SOMENTE UM CAMPO*/
    public Ponto fromRequest(RequestPayloadPonto requestPayloadPonto){
        Ponto ponto = new Ponto();
        if(requestPayloadPonto.getData().getTipo() != null){
        ponto.setTipo(tipo_ponto.valueOf(requestPayloadPonto.getData().getTipo()));}
        if(requestPayloadPonto.getData().getDia() != null){
        ponto.setDia(LocalDate.parse(requestPayloadPonto.getData().getDia()));}
        if(requestPayloadPonto.getData().getHorario() != null){
        ponto.setHorario(LocalTime.parse(requestPayloadPonto.getData().getHorario()));}
        if(requestPayloadPonto.getData().getObservacao() != null){
        ponto.setObservacao(requestPayloadPonto.getData().getObservacao());}
        if(requestPayloadPonto.getData().getIdFuncionario() != null){
        ponto.setId_funcionario(requestPayloadPonto.getData().getIdFuncionario());}
        return ponto;
    }




    /*MÉTODO QUE CRIA UM CLONE TEMPORÁRIO DA ENTIDADE/OBJETO ATUAL*/
    public Ponto validador( Long id){
        Optional<Ponto> ponto = repository.findById(id);
        Ponto validador = new Ponto();
        validador.setTipo(tipo_ponto.toTipo(ponto.get().getTipo().getTipo()));
        validador.setDia(ponto.get().getDia());
        validador.setHorario(ponto.get().getHorario());
        validador.setObservacao(ponto.get().getObservacao());
        validador.setId_funcionario(ponto.get().getId_funcionario());
        return validador;
    }





    /*MÉTODO PARA UTILIZA O CLONE PARA TRANSFERIR OS DADOS ANTIGOS DA ENTIDADE QUE VIERAM
     * NULOS DA REQUISIÇÃO, POR MOTIVO DE ALTERAÇÃO EM SOMENTE UM CAMPO */
    public Ponto validaPonto(Ponto ponto, Ponto pontoValidado){
        if (ponto.getTipo() == null) ponto.setTipo(tipo_ponto.valueOf(String.valueOf(pontoValidado.getTipo())));
        if (ponto.getDia() == null) ponto.setDia(pontoValidado.getDia());
        if(ponto.getHorario() == null) ponto.setHorario(pontoValidado.getHorario());
        if(ponto.getObservacao() == null) ponto.setObservacao(pontoValidado.getObservacao());
        if(ponto.getId_funcionario() == null) ponto.setId_funcionario(pontoValidado.getId_funcionario());
        return ponto;
    }




}
