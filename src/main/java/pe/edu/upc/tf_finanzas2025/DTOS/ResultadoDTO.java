package pe.edu.upc.tf_finanzas2025.DTOS;

import pe.edu.upc.tf_finanzas2025.ENTITIES.Bono;

public class ResultadoDTO {
    private int idResultado;
    private double tcea;
    private double trea;
    private double duracion;
    private double duracion_modificada;
    private double convexidad;
    private double precio_maximo;


    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public double getTcea() {
        return tcea;
    }

    public void setTcea(double tcea) {
        this.tcea = tcea;
    }

    public double getTrea() {
        return trea;
    }

    public void setTrea(double trea) {
        this.trea = trea;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getDuracion_modificada() {
        return duracion_modificada;
    }

    public void setDuracion_modificada(double duracion_modificada) {
        this.duracion_modificada = duracion_modificada;
    }

    public double getConvexidad() {
        return convexidad;
    }

    public void setConvexidad(double convexidad) {
        this.convexidad = convexidad;
    }

    public double getPrecio_maximo() {
        return precio_maximo;
    }

    public void setPrecio_maximo(double precio_maximo) {
        this.precio_maximo = precio_maximo;
    }
}
