/*INTERFACE QUE ESTENDE A JPA PARA FUTURA IMPLEMENTAÇÃO DOS MÉTODOS JA HERDADOS */


package app.pontos.repository;

import app.pontos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String username);

}
