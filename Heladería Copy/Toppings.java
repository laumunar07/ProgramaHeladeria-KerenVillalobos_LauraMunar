import java.util.ArrayList;

public class Toppings {
    private ArrayList<String> nombres;
    private ArrayList<Double> precios;

    public Toppings() {
        nombres = new ArrayList<>();
        precios = new ArrayList<>();

        nombres.add("Galleta Waffer");
        precios.add(500.0);

        nombres.add("Chispas de Chocolate");
        precios.add(700.0);

        nombres.add("Sirope de Caramelo");
        precios.add(600.0);

        nombres.add("Mermelada de Fresa");
        precios.add(650.0);

        nombres.add("Arequipe");
        precios.add(750.0);
    }

    public void mostrarToppings() {
        Menus menuToppings = new Menus(40, '-', '1');
        ArrayList<String> opciones = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) {
            opciones.add(nombres.get(i) + " - $" + precios.get(i));
        }
        menuToppings.createMenu(opciones, false);
    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public ArrayList<Double> getPrecios() {
        return precios;
    }
}
