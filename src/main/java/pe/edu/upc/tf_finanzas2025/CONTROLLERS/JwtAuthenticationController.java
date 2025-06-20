package pe.edu.upc.tf_finanzas2025.CONTROLLERS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.tf_finanzas2025.SECURITIES.JwtRequest;
import pe.edu.upc.tf_finanzas2025.SECURITIES.JwtResponse;
import pe.edu.upc.tf_finanzas2025.SECURITIES.JwtTokenUtil;
import pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception {
        authenticate(req.getUsername(), req.getPassword());

        // Cargamos los datos del usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        // ✅ Obtenemos el userId una sola vez
        int userId = userDetailsService.getUserIdByUsername(req.getUsername());

        // Generamos el token con el ID incluido como claim
        final String token = jwtTokenUtil.generateToken(userDetails, userId);

        return ResponseEntity.ok(new JwtResponse(token, userId));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
