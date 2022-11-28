package app.pontos.services;

import app.pontos.enums.status_empresa;
import app.pontos.models.Empresa;
import app.pontos.repository.EmpresaRepository;
import br.com.crud.components.RequestPayloadEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public List<Empresa> findAll(){
        return repository.findAll();
    }

    public Empresa findById(Long id){
        Optional<Empresa> obj = repository.findById(id);
        return obj.get();
    }

    public Empresa create(Empresa empresa){
        return repository.save(empresa);
    }

    public Empresa delete(Long id){
        Optional<Empresa> obj = repository.findById(id);
        Empresa empresa = repository.getReferenceById(id);
        empresa.setStatus(status_empresa.INATIVO);
        return obj.get();
    }

    public Empresa update(Empresa empresa,Long id){
        empresa.setId(id);
        return repository.save(empresa);
    }

    public Empresa reativa(Long id) {
        Optional<Empresa> obj = repository.findById(id);
        Empresa empresa = repository.getReferenceById(id);
        empresa.setStatus(status_empresa.ATIVO);
        return obj.get();
    }

    public Empresa fromRequest(RequestPayloadEmpresa requestPayloadEmpresa){
        Empresa empresa = new Empresa();
        empresa.setCnpj(Long.valueOf(requestPayloadEmpresa.getData().getCnpj()));
        empresa.setNome(requestPayloadEmpresa.getData().getNome());
        empresa.setEmail(requestPayloadEmpresa.getData().getEmail());
        empresa.setTelefone(requestPayloadEmpresa.getData().getTelefone());
        return empresa;
    }

}
