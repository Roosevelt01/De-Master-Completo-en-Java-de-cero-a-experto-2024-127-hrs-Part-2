package org.aguzman.apiservlet.webapp.headers.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.aguzman.apiservlet.webapp.headers.configs.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@CarroCompra//Paso 3
public class Carro implements Serializable {
    private List<ItemCarro> items;

    // Paso 4: primarez que veo transient, necesito que profundices eso y su usabilidad
    @Inject
    private transient Logger log;

    public Carro() {
    }

    //Paso 1
    @PostConstruct
    public void inicializar() {
        this.items = new ArrayList<>();
        //System.out.println("¡Inicializando el carro de comprar!");
        //Paso 5:
        log.info("Inicializando el carro de compra");
    }

    //Paso 2
    @PreDestroy
    public void destruir(){
        //System.out.println("¡Destruyendo el carro de compra!");
        //Paso 6:
        log.info("Inicializando el carro de compra");
    }


    public void addItemCarro(ItemCarro itemCarro) {
        if (items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()) {
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        } else {
            this.items.add(itemCarro);
        }
    }
    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
