/*INTERFACE QUE ESTENDE A JPA PARA FUTURA IMPLEMENTAÇÃO DOS MÉTODOS JA HERDADOS */


package app.pontos.repository;

import app.pontos.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
}
