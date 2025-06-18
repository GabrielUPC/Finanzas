package pe.edu.upc.tf_finanzas2025.ENTITIES;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Flujo")
public class Flujo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFlujo;
    @Column(name="periodo",nullable=false)
    private int periodo;
    @Column(name="fecha_pago",nullable=false)
    private LocalDate fecha_pago;
    @Column(name="interes",nullable=false)
    private double interes;
    @Column(name="amortizacion",nullable=false)
    private double amortizacion;
    @Column(name="cuotatotal",nullable=false)
    private double cuotatotal;
    @Column(name="saldo",nullable=false)
    private double saldo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBono")
    private Bono bo;

    public Flujo() {
    }

    public Flujo(int idFlujo, int periodo, LocalDate fecha_pago, double interes, double amortizacion, double cuotatotal, double saldo, Bono bo) {
        this.idFlujo = idFlujo;
        this.periodo = periodo;
        this.fecha_pago = fecha_pago;
        this.interes = interes;
        this.amortizacion = amortizacion;
        this.cuotatotal = cuotatotal;
        this.saldo = saldo;
        this.bo = bo;
    }

    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(double amortizacion) {
        this.amortizacion = amortizacion;
    }

    public double getCuotatotal() {
        return cuotatotal;
    }

    public void setCuotatotal(double cuotatotal) {
        this.cuotatotal = cuotatotal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Bono getBo() {
        return bo;
    }

    public void setBo(Bono bo) {
        this.bo = bo;
    }
}
