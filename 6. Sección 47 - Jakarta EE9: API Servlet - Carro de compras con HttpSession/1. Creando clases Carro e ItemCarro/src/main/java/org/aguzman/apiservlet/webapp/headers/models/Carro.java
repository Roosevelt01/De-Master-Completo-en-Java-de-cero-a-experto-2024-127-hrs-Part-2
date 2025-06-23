package org.aguzman.apiservlet.webapp.headers.models;

import java.util.List;
import java.util.Optional;

public class Carro {

    // Una lista de ItemCarro
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemCarro){
        // 1. Comprobar si el item (producto) ya existe en el carrito
        // Se usa items.contains(itemCarro), que a su vez invoca el método equals() de ItemCarro.
        // Gracias a nuestro equals() personalizado, esto detecta si el producto del itemCarro
        // ya está en alguna línea de la lista `items`.
        if(items.contains(itemCarro)){
            // Si el item ya existe, lo buscamos para actualizar su cantidad.
            // Se utiliza Stream API de Java 8 para encontrar el item existente.
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    // Filtra la lista para encontrar el item que es "igual" (mismo producto).
                    .filter(i -> i.equals(itemCarro))
                    // Encuentra cualquier coincidencia (se asume que solo habrá una por la lógica de contains).
                    .findAny();
            
            // 2. Si el item (producto) se encuentra en la lista (que debería, dada la condición `contains`)
            if(optionalItemCarro.isPresent()){
                // Obtenemos la instancia del ItemCarro existente.
                ItemCarro i = optionalItemCarro.get();
                // Incrementamos la cantidad del item existente en 1.
                // Esta es la lógica clave para manejar duplicados: sumar a la cantidad existente.
                i.setCantidad(i.getCantidad()+1);
            }
        }else{
            // 3. Si el item (producto) no existe en el carrito, lo añadimos como una nueva línea.
            this.items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems(){
        return items;
    }

    public int getTotal(){
        // Calcula el importe total del carrito sumando los importes de cada ItemCarro.
        // 1. `items.stream()`: Crea un flujo de ItemCarro desde la lista `items`.
        // 2. `mapToInt(ItemCarro::getImporte)`: Transforma el flujo de ItemCarro
        //    en un flujo de enteros (`IntStream`). Para cada ItemCarro, invoca su método `getImporte()`.
        //    `ItemCarro::getImporte` es una referencia a método, una forma concisa de escribir `item -> item.getImporte()`.
        // 3. `sum()`: Es un operador terminal que suma todos los enteros en el IntStream,
        //    devolviendo el total acumulado de todos los importes de línea.
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }
}
