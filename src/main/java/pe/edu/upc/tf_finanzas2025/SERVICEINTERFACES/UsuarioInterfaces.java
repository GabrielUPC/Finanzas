package pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Usuario;

import java.util.List;

public interface UsuarioInterfaces {
    public List<Usuario> list();
    public void add(Usuario usuario);
    public Usuario listId(int id);
    public void modificar(Usuario usuario);
    public void eliminar(int id);
    public int getUserIdFromUsername(String username);
}
