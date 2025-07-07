package pe.edu.upc.tf_finanzas2025.CONTROLLERS;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tf_finanzas2025.DTOS.ResultadoDTO;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Resultado;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.ResultadoInterfaces;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Resultados")
public class ResultadoController {
    @Autowired
    private ResultadoInterfaces Ri;
    @PreAuthorize("hasAuthority('CLIENTE') or hasAuthority('ADMIN')")
    @GetMapping
    public List<ResultadoDTO> listar(){
        return Ri.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x, ResultadoDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('CLIENTE') or hasAuthority('ADMIN')")
    @PostMapping
    public void add(@RequestBody ResultadoDTO dto){
        ModelMapper m=new ModelMapper();
        Resultado r=m.map(dto, Resultado.class);
        Ri.add(r);
    }
    @PreAuthorize("hasAuthority('CLIENTE') or hasAuthority('ADMIN')")
    @PutMapping
    public void modificar(@RequestBody ResultadoDTO dto){
        ModelMapper m=new ModelMapper();
        Resultado r=m.map(dto, Resultado.class);
        Ri.modificar(r);
    }
    @PreAuthorize("hasAuthority('CLIENTE') or hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        Ri.eliminar(id);
    }
}
