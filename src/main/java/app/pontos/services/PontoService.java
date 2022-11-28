package app.pontos.services;

import app.pontos.enums.tipo_ponto;
import app.pontos.models.Ponto;
import app.pontos.repository.PontoRepository;
import br.com.crud.components.RequestPayloadPonto;
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

    public List<Ponto> findAll(){
        return repository.findAll();}

    public Ponto findById(Long id){
        Optional<Ponto> obj = repository.findById(id);
        return obj.get();
    }

    public Ponto create(Ponto ponto){
        return repository.save(ponto);}

    public Ponto delete(Long id){
        Optional<Ponto> obj = repository.findById(id);
        repository.deleteById(id);
        return obj.get();
    }

    public Ponto update(Ponto ponto, Long id){
        ponto.setId(id);
        return repository.save(ponto);
    }

    public Ponto fromRequest(RequestPayloadPonto requestPayloadPonto){
        Ponto ponto = new Ponto();
        ponto.setTipo(tipo_ponto.toTipo(Integer.parseInt(requestPayloadPonto.getData().getTipo())));
        ponto.setDia(LocalDate.parse(requestPayloadPonto.getData().getDia()));
        ponto.setHorario(LocalTime.parse(requestPayloadPonto.getData().getHorario()));
        ponto.setObservacao(requestPayloadPonto.getData().getObservacao());
        return ponto;
    }
}
