package pe.edu.upc.tf_finanzas2025.ENTITIES;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Bono")
public class Bono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBono;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "monto", nullable = false)
    private double montonominal;

    @Column(name = "moneda", nullable = false, length = 30)
    private String moneda;

    @Column(name = "tasainteres", nullable = false)
    private double tasainteres;

    @Column(name = "frecuenciapago", nullable = false, length = 100)
    private String frecuenciapago;

    @Column(name = "plazomeses", nullable = false)
    private int plazomeses;

    @Column(name = "fechaemision", nullable = false)
    private LocalDate fechaemision;
    @Column(name = "tasaCOK", nullable = false)
    private double tasaCOK;

    @ElementCollection
    private Map<Integer, String> mapaGraciaPorPeriodo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario u;

    @OneToMany(mappedBy = "bo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flujo> flujos;

    @OneToOne(mappedBy = "bo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Resultado resultado;

    // Constructor vacío
    public Bono() {}


    public Bono(int idBono, String nombre, double montonominal, String moneda, double tasainteres, String frecuenciapago, int plazomeses, double tasaCOK, LocalDate fechaemision, Map<Integer, String> mapaGraciaPorPeriodo, Usuario u, List<Flujo> flujos, Resultado resultado) {
        this.idBono = idBono;
        this.nombre = nombre;
        this.montonominal = montonominal;
        this.moneda = moneda;
        this.tasainteres = tasainteres;
        this.frecuenciapago = frecuenciapago;
        this.plazomeses = plazomeses;
        this.tasaCOK = tasaCOK;
        this.fechaemision = fechaemision;
        this.mapaGraciaPorPeriodo = mapaGraciaPorPeriodo;
        this.u = u;
        this.flujos = flujos;
        this.resultado = resultado;
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

    public double getTasaCOK() {
        return tasaCOK;
    }

    public void setTasaCOK(double tasaCOK) {
        this.tasaCOK = tasaCOK;
    }

    public LocalDate getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(LocalDate fechaemision) {
        this.fechaemision = fechaemision;
    }

    public Map<Integer, String> getMapaGraciaPorPeriodo() {
        return mapaGraciaPorPeriodo;
    }

    public void setMapaGraciaPorPeriodo(Map<Integer, String> mapaGraciaPorPeriodo) {
        this.mapaGraciaPorPeriodo = mapaGraciaPorPeriodo;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public List<Flujo> getFlujos() {
        return flujos;
    }

    public void setFlujos(List<Flujo> flujos) {
        this.flujos = flujos;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
