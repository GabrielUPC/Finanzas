package pe.edu.upc.tf_finanzas2025.SERVICEIMPLEMENTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Bono;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Flujo;
import pe.edu.upc.tf_finanzas2025.ENTITIES.Resultado;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IBonoRepository;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IFlujoRepository;
import pe.edu.upc.tf_finanzas2025.REPOSITORIES.IResultadoRepository;
import pe.edu.upc.tf_finanzas2025.SERVICEINTERFACES.BonoInterfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BonoImplements implements BonoInterfaces {
    @Autowired
    private IBonoRepository bonoRepository;
    @Autowired
    private IFlujoRepository flujoRepository;

    @Autowired
    private IResultadoRepository resultadoRepository;

    @Override
    public List<Bono> list() {
        return bonoRepository.findAll();
    }

    @Override
    public void add(Bono bono) {
        bonoRepository.save(bono);
    }

    @Override
    public Bono listId(int id) {
        return bonoRepository.findById(id).orElse(new Bono());
    }

    @Override
    public void modificar(Bono bono) {
        bonoRepository.save(bono);
    }

    @Override
    public void eliminar(int id) {
        bonoRepository.deleteById(id);
    }

    @Override
    public Bono registrarConCalculos(Bono bono) {
        double montoNominal = bono.getMontonominal();
        double tasa = bono.getTasainteres() / 100.0;
        int plazoMeses = bono.getPlazomeses();
        String frecuencia = bono.getFrecuenciapago();
        String tipoTasa = bono.getTipo();
        String capitalizacion = bono.getCapitalizacion();
        String gracia = bono.getPgracia();
        LocalDate fecha = bono.getFechaemision();

        Map<String, Integer> frecuenciaMap = Map.of(
                "Mensual", 12,
                "Bimestral", 6,
                "Trimestral", 4,
                "Semestral", 2,
                "Anual", 1
        );
        int pagosPorAño = frecuenciaMap.getOrDefault(frecuencia, 12);
        int totalPeriodos = (plazoMeses * pagosPorAño) / 12;

        // Convertir tasa nominal a efectiva si aplica
        if (tipoTasa.equalsIgnoreCase("Nominal")) {
            int n = frecuenciaMap.getOrDefault(capitalizacion, pagosPorAño);
            tasa = Math.pow(1 + (tasa / n), n * 1.0 / pagosPorAño) - 1;
        } else {
            tasa = Math.pow(1 + tasa, 1.0 / pagosPorAño) - 1;
        }

        bonoRepository.save(bono);

        List<Flujo> flujos = new ArrayList<>();
        double saldo = montoNominal;
        double interesPago = montoNominal * tasa;
        int graciaPeriodos = 1;

        double vpn = 0.0;
        double duracion = 0.0;
        double convexidad = 0.0;
        double tasaDescuento = tasa;

        for (int i = 1; i <= totalPeriodos; i++) {
            double interes = 0.0;
            double amort = 0.0;

            if (gracia.equalsIgnoreCase("Total") && i <= graciaPeriodos) {
                interes = 0;
                amort = 0;
            } else if (gracia.equalsIgnoreCase("Parcial") && i <= graciaPeriodos) {
                interes = interesPago;
                amort = 0;
            } else {
                interes = interesPago;
                amort = (i == totalPeriodos) ? montoNominal : 0;
            }

            double cuota = interes + amort;
            saldo = (i == totalPeriodos) ? 0 : montoNominal;

            LocalDate fechaPago = fecha.plusMonths((long) (12.0 / pagosPorAño) * i);
            Flujo flujo = new Flujo(0, i, fechaPago, interes, amort, cuota, saldo, bono);
            flujos.add(flujo);
            flujoRepository.save(flujo);

            double flujoDesc = cuota / Math.pow(1 + tasaDescuento, i);
            vpn += flujoDesc;
            duracion += i * flujoDesc;
            convexidad += i * (i + 1) * flujoDesc;
        }

        duracion = duracion / vpn;
        double duracionMod = duracion / (1 + tasaDescuento);
        convexidad = convexidad / (vpn * Math.pow(1 + tasaDescuento, 2));

        Resultado resultado = new Resultado();
        resultado.setTcea(tasaDescuento * pagosPorAño);
        resultado.setTrea(tasaDescuento * pagosPorAño);
        resultado.setDuracion(duracion);
        resultado.setDuracion_modificada(duracionMod);
        resultado.setConvexidad(convexidad);
        resultado.setPrecio_maximo(vpn);
        resultado.setBo(bono);
        resultadoRepository.save(resultado);

        Bono bonoFinal = bonoRepository.findById(bono.getIdBono()).orElse(bono);
        bonoFinal.setFlujos(flujoRepository.findByBoIdBono(bono.getIdBono()));
        bonoFinal.setResultado(resultadoRepository.findByBoIdBono(bono.getIdBono()));
        return bonoFinal;


    }
    @Override
    public Bono calcularTemporal(Bono bono) {
        // Copia exacta del método registrarConCalculos, pero sin guardar en base de datos
        double montoNominal = bono.getMontonominal();
        double tasa = bono.getTasainteres() / 100.0;
        int plazoMeses = bono.getPlazomeses();
        String frecuencia = bono.getFrecuenciapago();
        String tipoTasa = bono.getTipo();
        String capitalizacion = bono.getCapitalizacion();
        String gracia = bono.getPgracia();
        LocalDate fecha = bono.getFechaemision();

        Map<String, Integer> frecuenciaMap = Map.of(
                "Mensual", 12,
                "Bimestral", 6,
                "Trimestral", 4,
                "Semestral", 2,
                "Anual", 1
        );
        int pagosPorAño = frecuenciaMap.getOrDefault(frecuencia, 12);
        int totalPeriodos = (plazoMeses * pagosPorAño) / 12;

        if (tipoTasa.equalsIgnoreCase("Nominal")) {
            int n = frecuenciaMap.getOrDefault(capitalizacion, pagosPorAño);
            tasa = Math.pow(1 + (tasa / n), n * 1.0 / pagosPorAño) - 1;
        } else {
            tasa = Math.pow(1 + tasa, 1.0 / pagosPorAño) - 1;
        }

        List<Flujo> flujos = new ArrayList<>();
        double saldo = montoNominal;
        double interesPago = montoNominal * tasa;
        int graciaPeriodos = 1;

        double vpn = 0.0;
        double duracion = 0.0;
        double convexidad = 0.0;
        double tasaDescuento = tasa;

        for (int i = 1; i <= totalPeriodos; i++) {
            double interes = 0.0;
            double amort = 0.0;

            if (gracia.equalsIgnoreCase("Total") && i <= graciaPeriodos) {
                interes = 0;
                amort = 0;
            } else if (gracia.equalsIgnoreCase("Parcial") && i <= graciaPeriodos) {
                interes = interesPago;
                amort = 0;
            } else {
                interes = interesPago;
                amort = (i == totalPeriodos) ? montoNominal : 0;
            }

            double cuota = interes + amort;
            saldo = (i == totalPeriodos) ? 0 : montoNominal;

            LocalDate fechaPago = fecha.plusMonths((long) (12.0 / pagosPorAño) * i);
            Flujo flujo = new Flujo(0, i, fechaPago, interes, amort, cuota, saldo, bono);
            flujos.add(flujo);

            double flujoDesc = cuota / Math.pow(1 + tasaDescuento, i);
            vpn += flujoDesc;
            duracion += i * flujoDesc;
            convexidad += i * (i + 1) * flujoDesc;
        }

        duracion = duracion / vpn;
        double duracionMod = duracion / (1 + tasaDescuento);
        convexidad = convexidad / (vpn * Math.pow(1 + tasaDescuento, 2));

        Resultado resultado = new Resultado();
        resultado.setTcea(tasaDescuento * pagosPorAño);
        resultado.setTrea(tasaDescuento * pagosPorAño);
        resultado.setDuracion(duracion);
        resultado.setDuracion_modificada(duracionMod);
        resultado.setConvexidad(convexidad);
        resultado.setPrecio_maximo(vpn);
        resultado.setBo(bono);

        bono.setFlujos(flujos);
        bono.setResultado(resultado);
        return bono;
    }

}
