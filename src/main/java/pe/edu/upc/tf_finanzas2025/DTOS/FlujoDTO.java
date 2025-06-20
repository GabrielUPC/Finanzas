package pe.edu.upc.tf_finanzas2025.DTOS;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Bono;

import java.time.LocalDate;

public class FlujoDTO {
    private int idFlujo;
    private int periodo;
    private LocalDate fecha_pago;
    private double interes;
    private double amortizacion;
    private double cuotatotal;
    private double saldo;

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


}
