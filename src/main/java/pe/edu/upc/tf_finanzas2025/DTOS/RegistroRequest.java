package pe.edu.upc.tf_finanzas2025.DTOS;

public class RegistroRequest {
    private String username;
    private String password;
    private String rol; // CLIENTE o EMISOR

    public RegistroRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
