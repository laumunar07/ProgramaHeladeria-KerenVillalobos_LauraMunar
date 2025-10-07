import java.util.ArrayList;

public class Sabores {
    private ArrayList<String> nombres;

    public Sabores() {
        nombres = new ArrayList<>();
        nombres.add("Explosión Bombastic Frutos Rojos");
        nombres.add("Mix Fantastic Citric");
        nombres.add("Aquamarine");
        nombres.add("Cocossette");
        nombres.add("Vino Sin Pasas");
    }

    public void mostrarSabores() {
        Menus menuSabores = new Menus(40, '-', '1');
        menuSabores.createMenu(nombres, false);
    }

    public ArrayList<String> getNombres() {
        return nombres;
    }
}
