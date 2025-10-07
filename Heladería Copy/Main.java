import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Heladeria> listaHeladerias = new ArrayList<>();
        listaHeladerias.add(new Heladeria("Calle 10 #5-25"));
        listaHeladerias.add(new Heladeria("Carrera 45 #22-10"));
        listaHeladerias.add(new Heladeria("Avenida 7 #100-50"));

        Helpers.crudHeladeria(listaHeladerias);
    }
}
