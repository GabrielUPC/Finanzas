package pe.edu.upc.tf_finanzas2025.CONTROLLERS;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tf_finanzas2025.DTOS.RolDTO;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Rol;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.RolInterfaces;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('CLIENTE') or hasAuthority('ADMIN')")
public class RolController {
    @Autowired
    private RolInterfaces ri;

    @PostMapping
    public void registrar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        ri.add(r);
    }

    @PutMapping
    public void modificar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        ri.add(r);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        ri.eliminar(id);
    }

    @GetMapping("/{id}")
    public RolDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(ri.listId(id), RolDTO.class);
        return dto;
    }

    @GetMapping
    public List<RolDTO> listar() {
        return ri.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RolDTO.class);
        }).collect(Collectors.toList());
    }
}
