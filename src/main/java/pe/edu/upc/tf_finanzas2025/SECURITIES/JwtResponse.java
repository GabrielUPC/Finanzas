package pe.edu.upc.tf_finanzas2025.SECURITIES;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String token; // ✅ renómbralo de jwttoken → token
    private int userId;

    public JwtResponse(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}

