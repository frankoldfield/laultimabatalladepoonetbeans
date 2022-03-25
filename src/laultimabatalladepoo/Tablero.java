package laultimabatalladepoo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import laultimabatalladepoo.cartas.CampoBatalla;
import laultimabatalladepoo.cartas.Cementerio;
import laultimabatalladepoo.excepciones.GetCartaException;

/**
 *
 * @author frank
 */

public class Tablero {//HACER QUE EL TABLERO CONOZCA A LOS JUGADORES A LOS CAMPOS DE BATALLA Y A LOS CEMENTERIOS
//EMPIEZAN LOS ATRIBUTOS DE LA CLASE
    private Jugador[] jugador;//Este va a ser el array de jugadores que va a conocer el tablero
    private Cementerio[] cementerios;//El array de cementerios que a su vez van a conocer los jugadores
    private CampoBatalla[] camposbatalla;//El array de campos de batalla de cada jugador dentro del tablero
    private int contador;//Este contador va a servir para saber en qué turno estamos y para determinar cuándo se empata
//TERMINAN LOS ATRIBUTOS DE LA CLASE
    
//EMPIEZA EL CONSTRUCTOR DE LA CLASE
    public Tablero(Jugador[] jugador) {//Para crear un tablero podemos introducirle un array de jugadores
        this.jugador = jugador;//Lo copiamos
        cementerios = new Cementerio[jugador.length];//Creamos un array de cementerios
        camposbatalla = new CampoBatalla[jugador.length];//Creamos un array de campos de batalla
        contador = 0;
    }

    public Tablero() {//Si no le damos ningun dato se crea un array con dos jugadores vacío
        jugador = new Jugador[2];
        cementerios = new Cementerio[jugador.length];
        camposbatalla = new CampoBatalla[jugador.length];
        contador = 0;

    }
//TERMINA EL CONSTRUCTOR DE LA CLASE
    
//EMPIEZA EL SETTER GETTER
    public Jugador getJugador(int num) {
        if (num <= jugador.length - 1) {
            return jugador[num];
        } else {
            System.out.println("No hay un jugador con ese número de jugador");
            return null;
        }
    }

    public void setJugador(Jugador jugmod, int num) { //Este método sirve para cambiar el jugador de la posición i del array de jugadores
        if (num <= jugador.length + 1) {
            jugador[num - 1] = jugmod;
        } else {
            System.out.println("No hay un jugador con ese número de jugador, no se ha podido modificar");
        }
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {//Utilizaremos este método para cambiar el contador a 0 cuando ambos jugadores se queden sin cartas y contar hasta 5
        this.contador = contador;
    }
    
    public void setCampoBatalla(int i){//Este método va a servir para que la posición i del array de campos de batalla coja el campo de batalla del jugador i. Se tiene que inicializar después del tablero
        camposbatalla[i] = jugador[i].getCampoBatalla();
    }
    
    public CampoBatalla getCampoBatalla(int i){
        return camposbatalla[i];
    }

    public void setCementerio(int i) { //Este método va a servir para que la posición i del array de cementerios coja el cementerio del jugador i. Se tiene que inicializar después del tablero
        cementerios[i] = jugador[i].getCementerio();
    }

    public Cementerio getCementerio(int i) {
        return cementerios[i];
    }
    
    public String toString(){
        return String.valueOf(this);
    }
    
    public void equal(Tablero tablero){
        if (this==tablero){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
    
//TERMINA EL SETTER GETTER

//AQUI EMPIEZAN LOS METODOS DE LA CLASE
    /**
     * Este método sirve para terminar el juego, pero no necesariamente cerrarlo
     * (al final de el invocamos al que si que lo cierra)
     */
    public void terminarjuego() throws IOException {//Este método es para cuando terminemos el juego

        String ruta = "C:/Users/frank/Desktop/NetBeansProjects/laultimabatalladepoo";
        File fichero = new File(ruta, "FicheroFinJuego.txt");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(fichero));
        bw.write("Datos fin de partida");
        bw.newLine();
        System.out.println(" ----------------------------------------------------");
        bw.write(" ----------------------------------------------------");
        bw.newLine();
        System.out.println("|La partida ha terminado, estos son los datos finales|");//Se imprime un pequeño indicador
        bw.write("|La partida ha terminado, estos son los datos finales|");
        bw.newLine();
        System.out.println(" ----------------------------------------------------");
        bw.write(" ----------------------------------------------------");
        bw.newLine();
        for (int j = 0; j < jugador.length; j++) {//Se recorren todos los jugadores
            System.out.println("Vida de " + jugador[j].getNombre() + ": " + String.valueOf(jugador[j].getVida()));//Imprimiendo la vida de cada uno de ellos
            bw.write("Vida de " + jugador[j].getNombre() + ": " + String.valueOf(jugador[j].getVida()));
            bw.newLine();
            System.out.println("Mano de " + jugador[j].getNombre() + ":");
            bw.write("Mano de " + jugador[j].getNombre() + ":");
            bw.newLine();
            try {
                for (int i = 0; i < jugador[j].getMano().getArrayCartas().getLength(); i++) {//Se recorre la mano de cada jugador
                    System.out.println("-" + jugador[j].getMano().getArrayCartas().getCarta(i).getNombre());//Imprimiendo cada carta que tenía el jugador
                    bw.write("-" + jugador[j].getMano().getArrayCartas().getCarta(i).getNombre());
                    bw.newLine();
                }
            } catch (GetCartaException ex) {//Si nos topamos con una excepción de este tipo es porque no tenía ninguna carta en la mano
                System.out.println(jugador[j].getNombre() + " no tenía cartas en la mano");
                bw.write(jugador[j].getNombre() + " no tenía cartas en la mano");
                bw.newLine();
            }
            System.out.println(" ");
            bw.newLine();
            System.out.println("Cementerio de " + jugador[j].getNombre() + ":");
            bw.write("Cementerio de " + jugador[j].getNombre() + ":");
            bw.newLine();
            if (jugador[j].getCementerio().getArrayCartas().getLength() == 0) { //Si el cementerio está vacío entonces solo imprimimos que estaba vacío 
                System.out.println(" No tiene cartas en el cementerio");
                bw.write(" No tiene cartas en el cementerio");
                bw.newLine();
            }
            try {
                for (int i = 0; i < jugador[j].getCementerio().getArrayCartas().getLength(); i++) {//Recorremos todo el array de cartas del cementerio del jugador
                    System.out.println("-" + jugador[j].getCementerio().getArrayCartas().getCarta(i).getNombre());//Y vamos imprimiendo el nombre de estas cartas
                    bw.write("-" + jugador[j].getCementerio().getArrayCartas().getCarta(i).getNombre());
                    bw.newLine();
                }
            } catch (GetCartaException ex) {
                System.out.println(jugador[j].getNombre() + " no tenía cartas en el cementerio");//Aun asi si tuviésemos el caso en el que nos da una excepción de este tipo imprimimos que no tenía cartas en el cementerio
                bw.write(jugador[j].getNombre() + " no tenía cartas en el cementerio");
                bw.newLine();
            }
        }
        bw.close();
        cerrarjuego();
    }

    /**
     * Este método va a servir para cerrar el juego
     */
    public void cerrarjuego() {//Al cerrar el juego ponemos todo en null y salimos de la ejecución del programa
        this.jugador = null;
        this.cementerios = null;
        this.camposbatalla = null;
        System.exit(0);
    }
//AQUI TERMINAN LOS METODOS DE LA CLASE
}
