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

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<BonoDTO> listar() {
        return Bi.list().stream().map(x -> {
            BonoDTO dto = modelMapper.map(x, BonoDTO.class);
            if (x.getU() != null) {
                dto.setIdUsuario(x.getU().getId());
            }
            dto.setMapaGraciaPorPeriodo(x.getMapaGraciaPorPeriodo());
            return dto;
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void add(@RequestBody BonoDTO dto) {
        Bono bono = modelMapper.map(dto, Bono.class);
        bono.setMapaGraciaPorPeriodo(dto.getMapaGraciaPorPeriodo());
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);
        Bi.add(bono);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public void modificar(@RequestBody BonoDTO dto) {
        Bono bono = modelMapper.map(dto, Bono.class);
        bono.setMapaGraciaPorPeriodo(dto.getMapaGraciaPorPeriodo());
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
        Bono bono = modelMapper.map(dto, Bono.class);
        bono.setMapaGraciaPorPeriodo(dto.getMapaGraciaPorPeriodo());
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);

        Bono bonoRegistrado = Bi.registrarConCalculos(bono);

        return construirRespuesta(bonoRegistrado);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/previsualizar")
    public BonoRespuestaDTO previsualizar(@RequestBody BonoDTO dto) {
        Bono bono = modelMapper.map(dto, Bono.class);
        bono.setMapaGraciaPorPeriodo(dto.getMapaGraciaPorPeriodo());
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        bono.setU(usuario);

        Bono bonoCalculado = Bi.calcularTemporal(bono);

        return construirRespuesta(bonoCalculado);
    }

    private BonoRespuestaDTO construirRespuesta(Bono bono) {
        BonoRespuestaDTO respuesta = new BonoRespuestaDTO();

        BonoDTO bonoDtoRespuesta = modelMapper.map(bono, BonoDTO.class);
        bonoDtoRespuesta.setIdUsuario(bono.getU().getId());
        bonoDtoRespuesta.setMapaGraciaPorPeriodo(bono.getMapaGraciaPorPeriodo());
        respuesta.setBono(bonoDtoRespuesta);

        if (bono.getFlujos() != null && !bono.getFlujos().isEmpty()) {
            List<FlujoDTO> flujoDTOs = bono.getFlujos().stream()
                    .map(f -> modelMapper.map(f, FlujoDTO.class))
                    .collect(Collectors.toList());
            respuesta.setFlujos(flujoDTOs);
        } else {
            System.out.println("⚠️ No se encontraron flujos en el bono con ID: " + bono.getIdBono());
        }

        if (bono.getResultado() != null) {
            ResultadoDTO resultadoDTO = modelMapper.map(bono.getResultado(), ResultadoDTO.class);
            respuesta.setResultado(resultadoDTO);
        } else {
            System.out.println("⚠️ No se encontró resultado para el bono con ID: " + bono.getIdBono());
        }

        return respuesta;
    }
}
