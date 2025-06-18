package pe.edu.upc.tf_finanzas2025.CONTROLLERS;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tf_finanzas2025.DTOS.FlujoDTO;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Flujo;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.FlujoInterfaces;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Flujos")
public class FlujoController {
    @Autowired
    private FlujoInterfaces Fi;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<FlujoDTO> listar(){
        return Fi.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x, FlujoDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void add(@RequestBody FlujoDTO dto){
        ModelMapper m=new ModelMapper();
        Flujo r=m.map(dto, Flujo.class);
        Fi.add(r);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public void modificar(@RequestBody FlujoDTO dto){
        ModelMapper m=new ModelMapper();
        Flujo f=m.map(dto, Flujo.class);
        Fi.modificar(f);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        Fi.eliminar(id);
    }
}
