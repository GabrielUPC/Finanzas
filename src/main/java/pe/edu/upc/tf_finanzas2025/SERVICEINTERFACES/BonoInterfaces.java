package pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Bono;

import java.util.List;

public interface BonoInterfaces {
    public List<Bono> list();
    public void add(Bono bono);
    public Bono listId(int id);
    public void modificar(Bono bono);
    public void eliminar(int id);
    Bono registrarConCalculos(Bono bono);
    Bono calcularTemporal(Bono bono);

}
