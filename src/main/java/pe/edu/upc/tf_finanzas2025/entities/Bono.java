package pe.edu.upc.tf_finanzas2025.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Bono")
public class Bono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBono;
    @Column(name="nombre",nullable=false, length=100)
    private String nombre;
    @Column(name="monto",nullable=false)
    private double montonominal;
    @Column(name="moneda",nullable=false, length=30)
    private String moneda;
    @Column(name="tipo",nullable=false, length=100)
    private String tipo;
    @Column(name="tasainteres",nullable=false)
    private double tasainteres;
    @Column(name="frecuenciapago",nullable=false, length=100)
    private String frecuenciapago;
    @Column(name="plazomeses",nullable=false)
    private int plazomeses;
    @Column(name="pgracia",nullable=false, length=100)
    private String pgracia;
    @Column(name="capitalizacion",nullable=false, length=50)
    private String capitalizacion;
    @Column(name="fechaemision",nullable=false)
    private LocalDate fechaemision;

    public Bono() {
    }

    public Bono(int idBono, String nombre, double montonominal, String moneda, String tipo, double tasainteres, String frecuenciapago, int plazomeses, String pgracia, String capitalizacion, LocalDate fechaemision) {
        this.idBono = idBono;
        this.nombre = nombre;
        this.montonominal = montonominal;
        this.moneda = moneda;
        this.tipo = tipo;
        this.tasainteres = tasainteres;
        this.frecuenciapago = frecuenciapago;
        this.plazomeses = plazomeses;
        this.pgracia = pgracia;
        this.capitalizacion = capitalizacion;
        this.fechaemision = fechaemision;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getPgracia() {
        return pgracia;
    }

    public void setPgracia(String pgracia) {
        this.pgracia = pgracia;
    }

    public String getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(String capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public LocalDate getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(LocalDate fechaemision) {
        this.fechaemision = fechaemision;
    }
}
