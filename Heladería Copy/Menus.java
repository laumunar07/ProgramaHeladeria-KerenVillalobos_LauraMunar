import java.util.*;

public class Menus {
    private int size;
    private char borderChar;
    private char optionChar;

    public Menus(int size, char borderChar, char optionChar) {
        this.size = size;
        this.borderChar = borderChar;
        this.optionChar = optionChar;
    }

    public int createMenu(ArrayList<String> options, boolean clearScreen) {
        Scanner input = new Scanner(System.in);
        boolean DRAW_MENU = true;
        int option = -1;

        while (DRAW_MENU) {
            System.out.println(String.valueOf(borderChar).repeat(size));
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.println(String.valueOf(borderChar).repeat(size));

            System.out.print("Seleccione una opción: ");
            option = input.nextInt();

            if (option >= 1 && option <= options.size()) {
                DRAW_MENU = false;
            } else {
                System.out.println("Opción inválida, intente de nuevo.");
            }
        }

        return option;
    }
}
