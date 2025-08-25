package org.aguzman.hibernateapp.entity;

import javax.persistence.*;

@Entity
@Table(name="facturar")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String descripción;
    private  Long total;

    @ManyToOne
    private Cliente cliente;

    public Factura() {
    }

    public Factura(String descripción, Long total) {
        this.descripción = descripción;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
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
}
