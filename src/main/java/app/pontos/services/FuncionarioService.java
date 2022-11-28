package app.pontos.services;

import app.pontos.enums.status_funcionario;
import app.pontos.enums.tipo_funcionario;
import app.pontos.models.Funcionario;
import app.pontos.repository.FuncionarioRepository;
import br.com.crud.components.RequestPayloadFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository repository;


    public List<Funcionario> findAll(){
        return repository.findAll();
    }

    public Funcionario findById(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        return obj.get();
    }

    public Funcionario create(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public Funcionario delete(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        Funcionario funcionario = repository.getReferenceById(id);
        funcionario.setStatus(status_funcionario.INATIVO);
        return obj.get();
    }

    public Funcionario update(Funcionario funcionario, Long id){
        funcionario.setId(id);
        return repository.save(funcionario);
    }
    public Funcionario reativa(Long id){
        Optional<Funcionario> obj = repository.findById(id);
        Funcionario funcionario = repository.getReferenceById(id);
        funcionario.setStatus(status_funcionario.ATIVO);
        return obj.get();
    }


    public Funcionario fromRequest(RequestPayloadFuncionario requestPayloadFuncionario){
        Funcionario funcionario = new Funcionario();
        funcionario.setTipo(tipo_funcionario.valueOf(requestPayloadFuncionario.getData().getTipo()));
        funcionario.setCargo(requestPayloadFuncionario.getData().getCargo());
        funcionario.setData_admissao(LocalDate.parse(requestPayloadFuncionario.getData().getDataAdmissao()));
        funcionario.setData_demissao(LocalDate.parse(requestPayloadFuncionario.getData().getDataDemissao()));
        funcionario.setId_usuario(requestPayloadFuncionario.getData().getIdUsuario());
        funcionario.setId_empresa(requestPayloadFuncionario.getData().getIdEmpresa());
        return funcionario;
    }



}
