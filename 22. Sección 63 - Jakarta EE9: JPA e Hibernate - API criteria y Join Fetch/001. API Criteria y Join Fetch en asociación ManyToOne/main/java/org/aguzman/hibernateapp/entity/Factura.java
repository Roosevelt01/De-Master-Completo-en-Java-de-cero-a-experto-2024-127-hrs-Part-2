package org.aguzman.hibernateapp.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="facturar")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String descripcion;
    private  Long total;

    //Paso 1: Definición de la relación @ManyToOne LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public Factura() {
    }

    public Factura(String descripcion, Long total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                " | descripción='" + descripcion + '\'' +
                " | total=" + total;
    }

    //Nueva modificación
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Factura factura = (Factura) object;
        return Objects.equals(id, factura.id) && Objects.equals(descripcion, factura.descripcion) && Objects.equals(total, factura.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, total);
    }
}
