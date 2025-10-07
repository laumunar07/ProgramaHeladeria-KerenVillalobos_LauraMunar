import java.util.ArrayList;

public class Heladeria {
    private String direccion;
    private ArrayList<Pedido> pedidos;

    public Heladeria(String direccion) {
        this.direccion = direccion;
        this.pedidos = new ArrayList<>();
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}
