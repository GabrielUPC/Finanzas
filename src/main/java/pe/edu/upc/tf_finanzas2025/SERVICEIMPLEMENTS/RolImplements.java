package pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Rol;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IRolRepository;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.RolInterfaces;

import java.util.List;

@Service
public class RolImplements implements RolInterfaces {
    @Autowired
    private IRolRepository Irol;
    @Override
    public List<Rol> list() {
        return Irol.findAll();
    }

    @Override
    public void add(Rol Rol) {
        Irol.save(Rol);
    }

    @Override
    public Rol listId(int id) {
        return Irol.findById(id).orElse(new Rol());
    }

    @Override
    public void modificar(Rol rol) {
        Irol.save(rol);
    }

    @Override
    public void eliminar(int id) {
        Irol.deleteById(id);
    }
}
