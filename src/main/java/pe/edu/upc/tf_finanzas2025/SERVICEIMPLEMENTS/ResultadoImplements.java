package pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Resultado;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IResultadoRepository;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.ResultadoInterfaces;

import java.util.List;

@Service
public class ResultadoImplements implements ResultadoInterfaces {
    @Autowired
    private IResultadoRepository Rr;
    @Override
    public List<Resultado> list() {
        return Rr.findAll();
    }

    @Override
    public void add(Resultado resultado) {
        Rr.save(resultado);
    }

    @Override
    public Resultado listId(int id) {
        return Rr.findById(id).orElse(new Resultado());
    }

    @Override
    public void modificar(Resultado resultado) {
        Rr.save(resultado);
    }

    @Override
    public void eliminar(int id) {
        Rr.deleteById(id);
    }
}
