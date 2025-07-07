package pe.edu.upc.tf_finanzas2025.CONTROLLERS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.tf_finanzas2025.DTOS.RegistroRequest;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.UsuarioInterfaces;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioInterfaces usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Void> registrar(@RequestBody RegistroRequest request) {
        usuarioService.registrarUsuarioConRol(
                request.getUsername(),
                request.getPassword(),
                request.getRol()
        );
        return ResponseEntity.status(201).build(); // Retorna 201 Created sin cuerpo
    }
}

