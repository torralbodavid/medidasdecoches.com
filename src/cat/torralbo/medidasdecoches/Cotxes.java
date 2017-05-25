package cat.torralbo.medidasdecoches;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Creat per davidtorralbo el 24/5/17.
 */
public class Cotxes {

    public List<Cotxes> use = new ArrayList<Cotxes>();
    public File f = new File("src/cotxes.txt");

    private String marca, model, versio;
    private int any;
    private double llarg, ample, alt, maleter;
    private boolean seleccio;

    public Cotxes() {
        this.marca = marca;
        this.model = model;
        this.versio = versio;
        this.any = any;
        this.llarg = llarg;
        this.ample = ample;
        this.alt = alt;
        this.maleter = maleter;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersio() {
        return versio;
    }

    public void setVersio(String versio) {
        this.versio = versio;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public double getLlarg() {
        return llarg;
    }

    public void setLlarg(double llarg) {
        this.llarg = llarg;
    }

    public double getAmple() {
        return ample;
    }

    public void setAmple(double ample) {
        this.ample = ample;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public double getMaleter() {
        return maleter;
    }

    public void setMaleter(double maleter) {
        this.maleter = maleter;
    }

    public void crear() {

        try {

            List<String> lines = FileUtils.readLines(f, "UTF-8");

            int i = 0;

            Cotxes creaCotxes = new Cotxes();


            for (String line : lines) {
                if(!line.equals("END.")) {
                    i++;
                    //ingressem valors a l'objecte
                    ingressarValors(i, creaCotxes, line);

                    //comprovem que l'iterador sigui igual a 8
                    if(i == 8){
                        //afegim l'objecte
                        use.add(creaCotxes);
                        //reiniciem la posició a 0
                        i = 0;
                        //creem un nou objecte per el següent cotxe.
                        creaCotxes = new Cotxes();
                    }
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /*
    Aquest mètode ingressarà els valors de l'objecte en els setters adients a partir de la línia del fitxer adient.
     */
    public void ingressarValors(int i, Cotxes creaCotxes, String line){
        switch(i){
            case 1:
                try {
                    creaCotxes.setMarca(line);
                } catch (Exception e) {
                    creaCotxes.setMarca(null);
                };
            case 2:
                try {
                    creaCotxes.setModel(line);
                } catch (Exception e) {
                    creaCotxes.setModel(null);
                };
            case 3:
                try {
                    creaCotxes.setVersio(line);
                } catch (Exception e) {
                    creaCotxes.setVersio(null);
                };
            case 4:
                try {
                    creaCotxes.setAny(Integer.parseInt(line));
                } catch (Exception e) {
                    creaCotxes.setAny(0);
                };
            case 5:
                try {
                    creaCotxes.setLlarg(Double.parseDouble(line));
                } catch (Exception e) {
                    creaCotxes.setLlarg(0);
                };
            case 6:
                try {
                    creaCotxes.setAmple(Double.parseDouble(line));
                } catch (Exception e) {
                    creaCotxes.setAmple(0);
                };
            case 7:
                try {
                    creaCotxes.setAlt(Double.parseDouble(line));
                } catch (Exception e) {
                    creaCotxes.setAlt(0);
                };
            case 8:
                try {
                    creaCotxes.setMaleter(Double.parseDouble(line));
                } catch (Exception e) {
                    creaCotxes.setMaleter(0);
                };
        }


    }

    /*
    Ordenarà per mida els cotxes d'una arraylist.
     */
    class OrdenaPerMida implements Comparator<Cotxes> {
        @Override
        public int compare(Cotxes o1, Cotxes o2) {

            if(seleccio){
                if (o1.calculaMida(o1.getLlarg(),o1.getAmple(),o1.getAlt()) > o2.calculaMida(o2.getLlarg(),o2.getAmple(),o2.getAlt())) {
                    return -1;
                } else if (o1.calculaMida(o1.getLlarg(),o1.getAmple(),o1.getAlt()) < o2.calculaMida(o2.getLlarg(),o2.getAmple(),o2.getAlt())) {
                    return 1;
                }
                return 0;
            } else {
                if (o1.calculaMida(o1.getLlarg(),o1.getAmple(),o1.getAlt()) > o2.calculaMida(o2.getLlarg(),o2.getAmple(),o2.getAlt())) {
                    return 1;
                } else if (o1.calculaMida(o1.getLlarg(),o1.getAmple(),o1.getAlt()) < o2.calculaMida(o2.getLlarg(),o2.getAmple(),o2.getAlt())) {
                    return -1;
                }
                return 0;
            }


        }
    }

    /*
    Aquest mètode consulta els cotxes ordenant-los de més gran a més petit per mides... o a la inversa.
     */
    public void consultarPerMides(boolean seleccio){
        //passem el paràmetre boleà a la variable de la classe per a ordenar-ho de la forma desitjada.
        this.seleccio = seleccio;

        //ho ordenem a partir de la classe OrdenaPerMida.
        Collections.sort(use, new OrdenaPerMida());

        for (Cotxes objecte : use) {
            System.out.println("La marca: "+ objecte.getMarca());
            System.out.println("El model: "+ objecte.getModel());
            System.out.println("La versió: "+ objecte.getVersio());
            System.out.println("L'any: "+ objecte.getAny());
            System.out.println("El llarg: "+ objecte.getLlarg());
            System.out.println("L'Ample: "+ objecte.getAmple());
            System.out.println("L'alt: "+ objecte.getAlt());
            System.out.println("La capacitat del maleter: "+ objecte.getMaleter());
            System.out.println("Mida (llarg * ample * alt) en metres: "+calculaMida(objecte.getLlarg(),objecte.getAmple(),objecte.getAlt())+"m");
            System.out.println("----------------------\n");
            //Posem el break perque només ens interessa el primer...
            break;
        }
    }

        /*
    Ordenarà per maleter els cotxes d'una arraylist.
     */

    class OrdenaPerMaleter implements Comparator<Cotxes> {
        @Override
        public int compare(Cotxes o1, Cotxes o2) {

            if(seleccio){
                if (o1.getMaleter() > o2.getMaleter()) {
                    return -1;
                } else if (o1.getMaleter() < o2.getMaleter()) {
                    return 1;
                }
                return 0;
            } else {
                if (o1.getMaleter() > o2.getMaleter()) {
                    return 1;
                } else if (o1.getMaleter() < o2.getMaleter()) {
                    return -1;
                }
                return 0;
            }


        }
    }

    /*
    Aquest mètode consulta els cotxes ordenant-los de més gran a més petit per maleter... o a la inversa.
     */
    public void consultarPerMaleter(boolean seleccio){
        //passem el paràmetre boleà a la variable de la classe per a ordenar-ho de la forma desitjada.
        this.seleccio = seleccio;

        //ho ordenem a partir de la classe OrdenaPerMida.
        Collections.sort(use, new OrdenaPerMaleter());

        for (Cotxes objecte : use) {
            System.out.println("La marca: "+ objecte.getMarca());
            System.out.println("El model: "+ objecte.getModel());
            System.out.println("La versió: "+ objecte.getVersio());
            System.out.println("L'any: "+ objecte.getAny());
            System.out.println("El llarg: "+ objecte.getLlarg());
            System.out.println("L'Ample: "+ objecte.getAmple());
            System.out.println("L'alt: "+ objecte.getAlt());
            System.out.println("La capacitat del maleter: "+ objecte.getMaleter());
            System.out.println("Mida (llarg * ample * alt) en metres: "+calculaMida(objecte.getLlarg(),objecte.getAmple(),objecte.getAlt())+"m");
            System.out.println("----------------------\n");
            //Posem el break perque només ens interessa el primer...
            break;
        }
    }


    /*
        Calcula la mida del cotxe i ho formata a metres. També formata el nombre de decimals que sortiran.
     */
    public double calculaMida(double llarg, double ample, double alt){

        DecimalFormat df = new DecimalFormat(".##", new DecimalFormatSymbols(Locale.US));

        double mida = (llarg/1000)*(ample/1000)*(alt/1000);

        return Double.parseDouble(df.format(mida));

    }

    public void retornaMarques(){
        String[] arrayMarques = new String[use.size()+1];
        List llistaMarques = Arrays.asList(arrayMarques);

        int i = 0;

        for (Cotxes objecte : use) {
            if(!llistaMarques.contains(objecte.getMarca())) {
                arrayMarques[i] = objecte.getMarca();
                i++;
            }
        }

        for (String marques : arrayMarques){
            if(marques != null) {
                System.out.println(marques);
            }
        }

    }


}