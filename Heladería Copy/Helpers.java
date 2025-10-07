import java.util.ArrayList;
import java.util.Scanner;

public class Helpers {

    public static void crudHeladeria(ArrayList<Heladeria> listaHeladerias) {
        Scanner input = new Scanner(System.in);
        Menus menu = new Menus(40, '-', '1');
        int opcion;

        ArrayList<String> opcionesHeladerias = new ArrayList<>();
        for (Heladeria h : listaHeladerias) {
            opcionesHeladerias.add(h.getDireccion());
        }
        opcionesHeladerias.add("Salir");

        do {
            System.out.println("\n=== BIENVENIDO A LAS HELADERÍAS ===");
            opcion = menu.createMenu(opcionesHeladerias, false);

            if (opcion >= 1 && opcion <= listaHeladerias.size()) {
                Heladeria seleccionada = listaHeladerias.get(opcion - 1);
                menuHeladeria(seleccionada);
            }

        } while (opcion != opcionesHeladerias.size());
    }

    private static void menuHeladeria(Heladeria heladeria) {
        Scanner input = new Scanner(System.in);
        Menus menu = new Menus(40, '-', '1');
        int opcion;

        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Crear Pedido");
        opciones.add("Ver Pedidos");
        opciones.add("Editar Pedido");
        opciones.add("Eliminar Pedido");
        opciones.add("Volver");

        do {
            System.out.println("\n=== Heladería: " + heladeria.getDireccion() + " ===");
            opcion = menu.createMenu(opciones, false);

            switch (opcion) {
                case 1 -> crearPedido(heladeria);
                case 2 -> verPedidos(heladeria);
                case 3 -> editarPedido(heladeria);
                case 4 -> eliminarPedido(heladeria);
            }
        } while (opcion != 5);
    }

    private static void crearPedido(Heladeria heladeria) {
        int id = heladeria.getPedidos().size() + 1;
        Pedido nuevo = new Pedido(id);
        System.out.println("Pedido #" + id + " creado correctamente.");
        editarPedidoInterno(nuevo);
        heladeria.getPedidos().add(nuevo);
    }

    private static void verPedidos(Heladeria heladeria) {
        if (heladeria.getPedidos().isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\nPedidos de la heladería " + heladeria.getDireccion() + ":");
        for (Pedido p : heladeria.getPedidos()) {
            System.out.println("ID: " + p.getId() + " | Total: $" + p.getTotal());
        }
    }

    private static void editarPedido(Heladeria heladeria) {
        Scanner input = new Scanner(System.in);
        if (heladeria.getPedidos().isEmpty()) {
            System.out.println("No hay pedidos para editar.");
            return;
        }

        verPedidos(heladeria);
        System.out.print("Ingrese el ID del pedido a editar: ");
        int id = input.nextInt();

        for (Pedido p : heladeria.getPedidos()) {
            if (p.getId() == id) {
                editarPedidoInterno(p);
                return;
            }
        }
        System.out.println("Pedido no encontrado.");
    }

    private static void eliminarPedido(Heladeria heladeria) {
        Scanner input = new Scanner(System.in);
        if (heladeria.getPedidos().isEmpty()) {
            System.out.println("No hay pedidos para eliminar.");
            return;
        }

        verPedidos(heladeria);
        System.out.print("Ingrese el ID del pedido a eliminar: ");
        int id = input.nextInt();

        heladeria.getPedidos().removeIf(p -> p.getId() == id);
        System.out.println("Pedido eliminado correctamente.");
    }

    // ======= SUBMENÚ DE EDICIÓN DE PEDIDO =======
    private static void editarPedidoInterno(Pedido pedido) {
        Scanner input = new Scanner(System.in);
        Menus menu = new Menus(40, '-', '1');
        Sabores sabores = new Sabores();
        Toppings toppings = new Toppings();

        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Agregar Helado");
        opciones.add("Agregar Malteada");
        opciones.add("Agregar Topping");
        opciones.add("Reemplazar Producto");
        opciones.add("Ver Detalle");
        opciones.add("Finalizar Pedido");

        int opcion;
        do {
            System.out.println("\n=== Editar Pedido #" + pedido.getId() + " ===");
            opcion = menu.createMenu(opciones, false);

            switch (opcion) {
                case 1 -> agregarProducto(pedido, "Helado", sabores, 8000);
                case 2 -> agregarProducto(pedido, "Malteada", sabores, 10000);
                case 3 -> agregarTopping(pedido, toppings);
                case 4 -> reemplazarProducto(pedido, sabores, toppings);
                case 5 -> pedido.mostrarDetalle();
            }

        } while (opcion != 6);
    }

    private static void agregarProducto(Pedido pedido, String tipo, Sabores sabores, double precio) {
        Menus menu = new Menus(40, '-', '1');
        ArrayList<String> listaSabores = sabores.getNombres();

        System.out.println("\n--- Sabores de " + tipo + " ---");
        int opcion = menu.createMenu(listaSabores, false);

        if (opcion >= 1 && opcion <= listaSabores.size()) {
            String sabor = listaSabores.get(opcion - 1);
            Item nuevo = new Item(tipo + " de " + sabor, precio);
            pedido.agregarItem(nuevo);
            System.out.println(tipo + " \"" + sabor + "\" agregado al pedido.");
        }
    }

    private static void agregarTopping(Pedido pedido, Toppings toppings) {
        Menus menu = new Menus(40, '-', '1');
        ArrayList<String> opciones = new ArrayList<>();
        for (int i = 0; i < toppings.getNombres().size(); i++) {
            opciones.add(toppings.getNombres().get(i) + " - $" + toppings.getPrecios().get(i));
        }

        System.out.println("\n--- Toppings Disponibles ---");
        int opcion = menu.createMenu(opciones, false);

        if (opcion >= 1 && opcion <= toppings.getNombres().size()) {
            String nombre = toppings.getNombres().get(opcion - 1);
            double precio = toppings.getPrecios().get(opcion - 1);
            Item nuevo = new Item("Topping: " + nombre, precio);
            pedido.agregarItem(nuevo);
            System.out.println("Topping \"" + nombre + "\" agregado al pedido.");
        }
    }

    private static void reemplazarProducto(Pedido pedido, Sabores sabores, Toppings toppings) {
        Scanner input = new Scanner(System.in);

        if (pedido.getItems().isEmpty()) {
            System.out.println("El pedido está vacío, no hay productos que reemplazar.");
            return;
        }

        // Mostrar los productos actuales
        pedido.mostrarDetalle();
        System.out.print("Seleccione el número del producto que desea reemplazar: ");
        int index = input.nextInt() - 1;

        if (index < 0 || index >= pedido.getItems().size()) {
            System.out.println("Opción inválida.");
            return;
        }

        // Mostrar menú una sola vez
        System.out.println("\n¿Qué tipo de producto desea poner en su lugar?");
        System.out.println("1. Helado");
        System.out.println("2. Malteada");
        System.out.println("3. Topping");
        System.out.print("Seleccione: ");
        int tipo = input.nextInt();

        Menus menu = new Menus(40, '-', '1');

        switch (tipo) {
            case 1 -> {
                System.out.println("\n--- Sabores de Helado ---");
                int opcion = menu.createMenu(sabores.getNombres(), false);
                if (opcion >= 1 && opcion <= sabores.getNombres().size()) {
                    String nuevoSabor = sabores.getNombres().get(opcion - 1);
                    pedido.reemplazarItem(index, new Item("Helado de " + nuevoSabor, 8000));
                }
            }
            case 2 -> {
                System.out.println("\n--- Sabores de Malteada ---");
                int opcion = menu.createMenu(sabores.getNombres(), false);
                if (opcion >= 1 && opcion <= sabores.getNombres().size()) {
                    String nuevoSabor = sabores.getNombres().get(opcion - 1);
                    pedido.reemplazarItem(index, new Item("Malteada de " + nuevoSabor, 10000));
                }
            }
            case 3 -> {
                ArrayList<String> opcionesTopping = new ArrayList<>();
                for (int i = 0; i < toppings.getNombres().size(); i++) {
                    opcionesTopping.add(toppings.getNombres().get(i) + " - $" + toppings.getPrecios().get(i));
                }
                System.out.println("\n--- Toppings Disponibles ---");
                int opcion = menu.createMenu(opcionesTopping, false);
                if (opcion >= 1 && opcion <= toppings.getNombres().size()) {
                    String nuevoTopping = toppings.getNombres().get(opcion - 1);
                    double precio = toppings.getPrecios().get(opcion - 1);
                    pedido.reemplazarItem(index, new Item("Topping: " + nuevoTopping, precio));
                }
            }
            default -> System.out.println("Tipo inválido.");
        }

        System.out.println("Elemento reemplazado correctamente.");
    }

}
