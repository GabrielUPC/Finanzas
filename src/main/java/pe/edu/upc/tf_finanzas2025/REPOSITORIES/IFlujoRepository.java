package pe.edu.upc.tf_finanzas2025.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Flujo;

import java.util.List;

public interface IFlujoRepository extends JpaRepository<Flujo, Integer> {
    List<Flujo> findByBoIdBono(int idBono);

}
