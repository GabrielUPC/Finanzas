package pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Flujo;

import java.util.List;

public interface FlujoInterfaces {
    public List<Flujo> list();
    public void add(Flujo flujo);
    public Flujo listId(int id);
    public void modificar(Flujo flujo);
    public void eliminar(int id);
}
