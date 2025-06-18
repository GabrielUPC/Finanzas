package pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Resultado;

import java.util.List;

public interface ResultadoInterfaces {
    public List<Resultado> list();
    public void add(Resultado resultado);
    public Resultado listId(int id);
    public void modificar(Resultado resultado);
    public void eliminar(int id);
}
