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
        return calcularConLogica(bono, true);
    }

    @Override
    public Bono calcularTemporal(Bono bono) {
        return calcularConLogica(bono, false);
    }

    private Bono calcularConLogica(Bono bono, boolean persistir) {
        double montoNominal = bono.getMontonominal();
        double tasa = bono.getTasainteres() / 100.0; // TEA bono
        double cok = bono.getTasaCOK() / 100.0;       // TEA COK (mercado)
        int plazoMeses = bono.getPlazomeses();
        String frecuencia = bono.getFrecuenciapago();
        LocalDate fecha = bono.getFechaemision();
        Map<Integer, String> mapaGracia = bono.getMapaGraciaPorPeriodo();

        Map<String, Integer> frecuenciaMap = Map.of("Semestral", 2, "Anual", 1);
        int pagosPorAño = frecuenciaMap.getOrDefault(frecuencia, 2);
        int totalPeriodos = (plazoMeses * pagosPorAño) / 12;

        // Convertir tasas TEA → TES
        double tasaBono = Math.pow(1 + tasa, 1.0 / pagosPorAño) - 1;
        double tasaDescuento = Math.pow(1 + cok, 1.0 / pagosPorAño) - 1;

        if (persistir) bonoRepository.save(bono);

        List<Flujo> flujos = new ArrayList<>();
        double saldo = montoNominal;

        double precioMercado = 0.0;
        double precioMaximo = 0.0;
        double duracion = 0.0;
        double convexidad = 0.0;

        List<Double> flujoEmisor = new ArrayList<>();
        List<Double> flujoInversionista = new ArrayList<>();

        // --- COSTOS EMISOR ---
        double porcentajeEstructuracion = 0.008;
        double porcentajeColocacion = 0.0015;
        double porcentajeCavali = 0.001;
        double costosTotalesEmisor = montoNominal * (porcentajeEstructuracion + porcentajeColocacion + porcentajeCavali);
        double montoRecibido = montoNominal - costosTotalesEmisor;

        // --- COSTOS INVERSIONISTA ---
        double porcentajeSAB = 0.0025;
        double porcentajeCustodia = 0.0005;
        double costosTotalesInversionista = montoNominal * (porcentajeSAB + porcentajeCustodia);
        double montoPagado = montoNominal + costosTotalesInversionista;

        flujoEmisor.add(montoRecibido);
        flujoInversionista.add(-montoPagado);

        for (int i = 1; i <= totalPeriodos; i++) {
            double interes = 0.0;
            double amort = 0.0;
            String tipoGracia = mapaGracia != null ? mapaGracia.getOrDefault(i, "Ninguna") : "Ninguna";

            switch (tipoGracia.toLowerCase()) {
                case "total":
                    interes = 0;
                    amort = 0;
                    saldo += saldo * tasaBono;
                    break;
                default:
                    interes = saldo * tasaBono;
                    amort = (i == totalPeriodos) ? saldo : 0;
                    break;
            }

            double cuota = interes + amort;
            saldo = (i == totalPeriodos) ? 0 : saldo;

            LocalDate fechaPago = fecha.plusMonths((long) (12.0 / pagosPorAño) * i);
            Flujo flujo = new Flujo(0, i, fechaPago, interes, amort, cuota, saldo, bono);
            flujos.add(flujo);
            if (persistir) flujoRepository.save(flujo);

            // Descuento con COK → precio de mercado
            double flujoDesc = cuota / Math.pow(1 + tasaDescuento, i);
            precioMercado += flujoDesc;
            duracion += i * flujoDesc;
            convexidad += i * (i + 1) * flujoDesc;

            // Suma sin descuento → precio máximo teórico
            precioMaximo += cuota;

            flujoEmisor.add(-cuota);
            flujoInversionista.add(cuota);
        }

        duracion = duracion / precioMercado;
        double duracionMod = duracion / (1 + tasaDescuento);
        convexidad = convexidad / (precioMercado * Math.pow(1 + tasaDescuento, 2));

        double tirEmisor = calcularTIR(flujoEmisor);
        double tirInversionista = calcularTIR(flujoInversionista);

        double tcea = Math.pow(1 + tirEmisor, pagosPorAño) - 1;
        double trea = Math.pow(1 + tirInversionista, pagosPorAño) - 1;

        Resultado resultado = new Resultado();
        resultado.setTcea(tcea);
        resultado.setTrea(trea);
        resultado.setDuracion(duracion);
        resultado.setDuracion_modificada(duracionMod);
        resultado.setConvexidad(convexidad);
        resultado.setPrecio_maximo(precioMaximo);    // COK = 0%
        resultado.setPrecio_mercado(precioMercado);  // COK real
        resultado.setBo(bono);
        if (persistir) resultadoRepository.save(resultado);

        if (persistir) {
            Bono bonoFinal = bonoRepository.findById(bono.getIdBono()).orElse(bono);
            bonoFinal.setFlujos(flujoRepository.findByBoIdBono(bono.getIdBono()));
            bonoFinal.setResultado(resultadoRepository.findByBoIdBono(bono.getIdBono()));
            return bonoFinal;
        } else {
            bono.setFlujos(flujos);
            bono.setResultado(resultado);
            return bono;
        }
    }
    private double calcularTIR(List<Double> flujos) {
        double tir = 0.1;
        double epsilon = 1e-7;
        int maxIter = 1000;

        for (int iter = 0; iter < maxIter; iter++) {
            double f = 0;
            double df = 0;
            for (int i = 0; i < flujos.size(); i++) {
                f += flujos.get(i) / Math.pow(1 + tir, i);
                if (i > 0)
                    df += -i * flujos.get(i) / Math.pow(1 + tir, i + 1);
            }
            double newTir = tir - f / df;
            if (Math.abs(newTir - tir) < epsilon) return newTir;
            tir = newTir;
        }
        return tir;
    }

}
