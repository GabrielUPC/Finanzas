package pe.edu.upc.tf_finanzas2025.ENTITIES;

import jakarta.persistence.*;

@Entity
@Table(name="Resultado")
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResultado;
    @Column(name="tcea",nullable=false)
    private double tcea;
    @Column(name="trea",nullable=false)
    private double trea;
    @Column(name="duracion",nullable=false)
    private double duracion;
    @Column(name="duracion_modificada",nullable=false)
    private double duracion_modificada;
    @Column(name="convexidad",nullable=false)
    private double convexidad;
    @Column(name="precio_maximo",nullable=false)
    private double precio_maximo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBono")
    private Bono bo;

    public Resultado() {
    }

    public Resultado(int idResultado, double tcea, double trea, double duracion, double duracion_modificada, double convexidad, double precio_maximo, Bono bo) {
        this.idResultado = idResultado;
        this.tcea = tcea;
        this.trea = trea;
        this.duracion = duracion;
        this.duracion_modificada = duracion_modificada;
        this.convexidad = convexidad;
        this.precio_maximo = precio_maximo;
        this.bo = bo;
    }

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

    public Bono getBo() {
        return bo;
    }

    public void setBo(Bono bo) {
        this.bo = bo;
    }
}
