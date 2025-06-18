package pe.edu.upc.tf_finanzas2025.CONTROLLERS;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tf_finanzas2025.DTOS.BonoDTO;
import pe.edu.upc.tf_finanzas2025.DTOS.BonoRespuestaDTO;
import pe.edu.upc.tf_finanzas2025.DTOS.FlujoDTO;
import pe.edu.upc.tf_finanzas2025.DTOS.ResultadoDTO;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Bono;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Usuario;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IUsuarioRepository;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.BonoInterfaces;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Bonos")
public class BonoController {

    @Autowired
    private BonoInterfaces Bi;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<BonoDTO> listar() {
        return Bi.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            BonoDTO dto = m.map(x, BonoDTO.class);
            if (x.getU() != null) {
                dto.setIdUsuario(x.getU().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void add(@RequestBody BonoDTO dto) {
        ModelMapper m = new ModelMapper();
        Bono bono = m.map(dto, Bono.class);

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);

        Bi.add(bono);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public void modificar(@RequestBody BonoDTO dto) {
        ModelMapper m = new ModelMapper();
        Bono bono = m.map(dto, Bono.class);

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);

        Bi.modificar(bono);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        Bi.eliminar(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registrar")
    public BonoRespuestaDTO registrarConCalculos(@RequestBody BonoDTO dto) {
        ModelMapper m = new ModelMapper();
        Bono bono = m.map(dto, Bono.class);

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);

        Bono bonoRegistrado = Bi.registrarConCalculos(bono);

        BonoRespuestaDTO respuesta = new BonoRespuestaDTO();
        BonoDTO bonoDtoRespuesta = m.map(bonoRegistrado, BonoDTO.class);
        bonoDtoRespuesta.setIdUsuario(bonoRegistrado.getU().getId());
        respuesta.setBono(bonoDtoRespuesta);

        if (bonoRegistrado.getFlujos() != null && !bonoRegistrado.getFlujos().isEmpty()) {
            List<FlujoDTO> flujoDTOs = bonoRegistrado.getFlujos().stream()
                    .map(f -> m.map(f, FlujoDTO.class))
                    .collect(Collectors.toList());
            respuesta.setFlujos(flujoDTOs);
        }

        if (bonoRegistrado.getResultado() != null) {
            ResultadoDTO resultadoDTO = m.map(bonoRegistrado.getResultado(), ResultadoDTO.class);
            respuesta.setResultado(resultadoDTO);
        }

        return respuesta;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/previsualizar")
    public BonoRespuestaDTO previsualizar(@RequestBody BonoDTO dto) {
        ModelMapper m = new ModelMapper();
        Bono bono = m.map(dto, Bono.class);

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);

        Bono bonoCalculado = Bi.calcularTemporal(bono);

        BonoRespuestaDTO respuesta = new BonoRespuestaDTO();
        BonoDTO bonoDtoRespuesta = m.map(bonoCalculado, BonoDTO.class);
        bonoDtoRespuesta.setIdUsuario(bonoCalculado.getU().getId());
        respuesta.setBono(bonoDtoRespuesta);

        if (bonoCalculado.getFlujos() != null && !bonoCalculado.getFlujos().isEmpty()) {
            List<FlujoDTO> flujoDTOs = bonoCalculado.getFlujos().stream()
                    .map(f -> m.map(f, FlujoDTO.class))
                    .collect(Collectors.toList());
            respuesta.setFlujos(flujoDTOs);
        }

        if (bonoCalculado.getResultado() != null) {
            ResultadoDTO resultadoDTO = m.map(bonoCalculado.getResultado(), ResultadoDTO.class);
            respuesta.setResultado(resultadoDTO);
        }

        return respuesta;
    }

}
