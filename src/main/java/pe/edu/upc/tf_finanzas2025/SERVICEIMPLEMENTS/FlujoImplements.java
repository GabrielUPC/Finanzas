package pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Flujo;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IFlujoRepository;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.FlujoInterfaces;

import java.util.List;

@Service
public class FlujoImplements implements FlujoInterfaces {
    @Autowired
    private IFlujoRepository flujoR;

    @Override
    public List<Flujo> list() {
        return flujoR.findAll();
    }

    @Override
    public void add(Flujo flujo) {
        flujoR.save(flujo);
    }

    @Override
    public Flujo listId(int id) {
        return flujoR.findById(id).orElse(new Flujo());
    }

    @Override
    public void modificar(Flujo flujo) {
        flujoR.save(flujo);
    }

    @Override
    public void eliminar(int id) {
        flujoR.deleteById(id);
    }
}
