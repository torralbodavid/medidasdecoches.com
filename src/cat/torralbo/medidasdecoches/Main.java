package cat.torralbo.medidasdecoches;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        if(menu.inicialitza()) {
            menu.subMenus(menu.principal());
        } else {
            System.out.println("Hi ha hagut un error i el programa no pot continuar.");
            System.exit(0);
        }

    }
}
