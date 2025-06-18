package pe.edu.upc.tf_finanzas2025.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Resultado;

public interface IResultadoRepository extends JpaRepository<Resultado, Integer> {
    Resultado findByBoIdBono(int idBono);
}
