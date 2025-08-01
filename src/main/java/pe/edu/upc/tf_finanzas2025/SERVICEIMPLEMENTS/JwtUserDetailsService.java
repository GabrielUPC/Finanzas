package pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Usuario;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository qhUR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = qhUR.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getRol()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true, true, true,
                roles
        );
    }

    // ✅ Método adicional para obtener el ID del usuario autenticado
    public int getUserIdByUsername(String username) {
        Usuario user = qhUR.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", username));
        }
        return user.getId();
    }
}
