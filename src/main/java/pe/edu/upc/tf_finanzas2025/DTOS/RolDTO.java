package pe.edu.upc.tf_finanzas2025.DTOS;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Usuario;

public class RolDTO {
    private int id;
    private String rol;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
