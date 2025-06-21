package org.aguzman.apiservlet.webapp.headers.models;

import org.aguzman.apiservlet.webapp.headers.services.ProductoService;

import java.util.Objects;

public class ItemCarro {
    private int cantidad;
    private Producto producto;

    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        // Optimización: Si los objetos son la misma instancia en memoria, son iguales.
        if(this == o) return  true;
        // Si el objeto pasado es null o no es de la misma clase, no son iguales.
        if(o == null || getClass() != o.getClass()) return false;
        // Realiza un casting seguro al tipo ItemCarro.
        ItemCarro itemCarro = (ItemCarro)o;
        // La igualdad se basa en la identidad del producto:
        // Si el ID del producto es igual Y el nombre del producto es igual,
        // consideramos que los ItemCarro se refieren al mismo producto.
        // Se usa Objects.equals para manejar posibles valores nulos de forma segura.
        return Objects.equals(producto.getId(), itemCarro.producto.getId())
                && Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    @Override
    public int hashCode() {
        // El hashCode debe ser consistente con equals.
        // Si dos objetos son iguales según equals, deben tener el mismo hashCode.
        // Aquí, el hashCode se genera basado en el objeto `producto`.
        // Idealmente, deberíamos usar los campos que definen la igualdad (id y nombre del producto).
        // Una mejor implementación podría ser: Objects.hash(producto.getId(), producto.getNombre());
        return Objects.hashCode(producto); // Mejorar a Objects.hash(producto.getId(), producto.getNombre())
    }

    public int getImporte(){
        return cantidad * producto.getPrecio();
    }
}
