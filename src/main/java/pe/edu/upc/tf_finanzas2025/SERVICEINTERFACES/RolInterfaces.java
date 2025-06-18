package pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Rol;

import java.util.List;

public interface RolInterfaces {
    public List<Rol> list();
    public void add(Rol Rol);
    public Rol listId(int id);
    public void modificar(Rol rol);
    public void eliminar(int id);


}
