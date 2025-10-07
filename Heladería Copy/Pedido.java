import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {
    private int id;
    private ArrayList<Item> items;
    private double total;

    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.total = 0;
    }

    // Agregar un nuevo producto (helado, malteada o topping)
    public void agregarItem(Item item) {
        items.add(item);
        recalcularTotal();
    }

    // Eliminar producto por posición
    public void eliminarItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            recalcularTotal();
        } else {
            System.out.println("Índice inválido. No se eliminó ningún elemento.");
        }
    }

    // Mostrar todo el pedido
    public void mostrarDetalle() {
        System.out.println("\n=== Detalle del Pedido #" + id + " ===");
        if (items.isEmpty()) {
            System.out.println("El pedido está vacío.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                Item it = items.get(i);
                System.out.println((i + 1) + ". " + it.getNombre() + " - $" + it.getPrecio());
            }
        }
        System.out.println("Total: $" + total);
        System.out.println();
    }

    // Recalcular el total del pedido
    public void recalcularTotal() {
        total = 0;
        for (Item i : items) {
            total += i.getPrecio();
        }
    }

    // Reemplazar un ítem existente
    public void reemplazarItem(int index, Item nuevoItem) {
    if (index >= 0 && index < items.size()) {
        // Obtener el ítem antiguo
        Item anterior = items.get(index);
        
        // Restar el precio anterior del total
        this.total -= anterior.getPrecio();
        
        // Reemplazar con el nuevo ítem
        items.set(index, nuevoItem);
        
        // Sumar el nuevo precio
        this.total += nuevoItem.getPrecio();
    } else {
        System.out.println("Índice fuera de rango. No se pudo reemplazar el producto.");
    }
}


    // Getters
    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
