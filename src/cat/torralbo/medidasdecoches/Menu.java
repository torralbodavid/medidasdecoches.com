package cat.torralbo.medidasdecoches;

import java.util.Scanner;

/**
 * Creat per davidtorralbo el 25/5/17.
 */
public class Menu {
    public Scanner sc = new Scanner(System.in);
    public Cotxes cotxes = new Cotxes();

    public int principal(){
        cotxes.crear();
        int seleccio = 0;

        System.out.println("BENVINGUT A MEDIDASDECOCHES.COM!\n");
        System.out.print("MENÚ\n" +
                "————————————————————————————————————————————————\n" +
                "1 Cotxe amb la mida més gran\n" +
                "2 Cotxe amb la mida més petita\n" +
                "3 Cotxe amb la capacitat de maleter més gran\n" +
                "4 Cotxe amb la capacitat de maleter més petita\n" +
                "5 Nombre de models de cada marca al fitxer \n" +
                "————————————————————————————————————————————————\n\n" +
                "Selecció: ");
        try {
            seleccio = Integer.parseInt(sc.nextLine());
        }catch (Exception e){
            System.out.println("Hi ha hagut un problema, seleccioni un nombre.\n");
            principal();
        }

        return seleccio;

    }

    public void comprovantFinalitzar(){
        char seleccio;

        try{
            System.out.print("Escrigui S per a sortir, o qualsevol tecla per a seguir: ");
            seleccio = sc.nextLine().toUpperCase().charAt(0);
            System.out.println("");

            switch (seleccio){
                case 'S':
                    System.exit(0);
                    break;
                default:
                    subMenus(principal());
                    break;
            }
        }catch (Exception e){
            System.out.println("Hi ha hagut un error\n");
            comprovantFinalitzar();
        }

    }

    public void subMenus(int seleccio){

        switch(seleccio){
            case 1:
                System.out.println("\nEl cotxe amb la mida més gran\n----------------------\n");
                cotxes.consultarPerMides(true);
                comprovantFinalitzar();
                break;
            case 2:
                System.out.println("\nEl cotxe amb la mida més petita\n----------------------\n");
                cotxes.consultarPerMides(false);
                comprovantFinalitzar();
                break;
            case 3:
                System.out.println("\nEl cotxe amb la mida de maleter més gran\n----------------------\n");
                cotxes.consultarPerMaleter(true);
                comprovantFinalitzar();
                break;
            case 4:
                System.out.println("\nEl cotxe amb la mida de maleter més petita\n----------------------\n");
                cotxes.consultarPerMaleter(false);
                comprovantFinalitzar();
                break;
            case 5:
                cotxes.retornaMarques();
                comprovantFinalitzar();
                break;
            default:
                System.out.println("Error! Ha de seleccionar un nombre del menú.\n");
                subMenus(principal());
                break;
        }
    }
}
