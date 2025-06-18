package pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Usuario;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IUsuarioRepository;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.UsuarioInterfaces;

import java.util.List;
@Service
public class UsuarioImplements implements UsuarioInterfaces {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

    @Override
    public void add(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return usuarioRepository.findById(id).orElse(new Usuario());
    }

    @Override
    public void modificar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public int getUserIdFromUsername(String username) {
        Usuario usuario = usuarioRepository.findOneByUsername(username);
        return usuario != null ? usuario.getId() : -1;
    }

}
