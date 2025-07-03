package pe.edu.upc.tf_finanzas2025.DTOS;

import java.time.LocalDate;
import java.util.Map;

public class BonoDTO {
    private int idBono;
    private String nombre;
    private double montonominal;
    private String moneda;
    private double tasainteres;
    private String frecuenciapago;
    private int plazomeses;

    private LocalDate fechaemision;
    private int idUsuario;
    private Map<Integer, String> mapaGraciaPorPeriodo;

    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMontonominal() {
        return montonominal;
    }

    public void setMontonominal(double montonominal) {
        this.montonominal = montonominal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTasainteres() {
        return tasainteres;
    }

    public void setTasainteres(double tasainteres) {
        this.tasainteres = tasainteres;
    }

    public String getFrecuenciapago() {
        return frecuenciapago;
    }

    public void setFrecuenciapago(String frecuenciapago) {
        this.frecuenciapago = frecuenciapago;
    }

    public int getPlazomeses() {
        return plazomeses;
    }

    public void setPlazomeses(int plazomeses) {
        this.plazomeses = plazomeses;
    }



    public LocalDate getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(LocalDate fechaemision) {
        this.fechaemision = fechaemision;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Map<Integer, String> getMapaGraciaPorPeriodo() {
        return mapaGraciaPorPeriodo;
    }

    public void setMapaGraciaPorPeriodo(Map<Integer, String> mapaGraciaPorPeriodo) {
        this.mapaGraciaPorPeriodo = mapaGraciaPorPeriodo;
    }
}
