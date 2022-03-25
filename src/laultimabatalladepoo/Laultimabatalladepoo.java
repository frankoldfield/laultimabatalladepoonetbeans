package laultimabatalladepoo;

import interfaces.InicioPartida;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.System.Logger;
import static java.lang.System.console;
import static java.lang.System.getLogger;
import laultimabatalladepoo.excepciones.*;

/**
 *
 * @author frank
 */

public class Laultimabatalladepoo {//La clase principal donde vamos a desarrollar el curso del juego

    public static void main(String[] args) throws GetCartaException, MazoVacio, InterruptedException, IOException {
//        PrintStream out = new PrintStream(new FileOutputStream("log.txt")); SI DESCOMENTAMOS ESTAS DOS LINEAS Y LA DE ABAJO NUMERO 76 LA CONSOLA PASA A ESCRIBIRSE EN log.txt
//            System.setOut(out); Iba a hacerlo creando un logger y añadiéndole un filehandler nuevo con un consolehandler nuevo, pero no me deja
        InicioPartida inicio = new InicioPartida(2);//Creamos un inicio de partida en este caso con dos jugadores
        inicio.setVisible(true);//Lo ponemos visible para que los jugadores introduzcan sus nombres
        while (inicio.getTablero().getJugador(0) == null || inicio.getTablero().getJugador(1) == null) {//Mientras que los dos jugadores no se hayan creado
            Thread.sleep(1);//Pausamos el hilo
        }
        System.out.println("------------------------------------------------------");
        System.out.println("Turno 0");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");//Un pequeño indicador en la consola de que ha empezado el turno 0
        Tablero tablero = inicio.getTablero(); //Hacemos que la clase main conozca el tablero
        boolean partidaenmarcha = true;//Ponemos la partida en marcha
        while (tablero.getJugador(0) == null || tablero.getJugador(1) == null) {//Esperamos a que el tablero coja a los dos jugadores, porque si no se podria ejecutar el código a continuación antes de que los conociese y nos daría un error
            Thread.sleep(1);//Pausamos el hilo hasta que los jugadores se hayan asignado al tablero
        }
        try {//En caso de que nos de algún error imprimiremos que ha habido un error en el bucle while del main
            while (partidaenmarcha) {//Mientras que la partida esté en marcha
                if (tablero.getJugador(0).getVida() < 1) {//Si la vida del jugador 1 baja de 1
                    System.out.println("Ha ganado " + tablero.getJugador(1).getNombre());//Gana el jugador 1
                    tablero.getJugador(0).getMazo().ficheromazo();
                    tablero.getJugador(0).getMazo().ordenarcriaturas();
                    tablero.getJugador(1).getMazo().ficheromazo();
                    tablero.getJugador(1).getMazo().ordenarcriaturas();
                    tablero.getJugador(0).getMazo().cualserepitemas();
                    tablero.getJugador(1).getMazo().cualserepitemas();
                    tablero.terminarjuego();//Y se termina el juego
                    partidaenmarcha = false;//Se sale del bucle
                } else {//Si eso no se cumple se hacen las siguientes comprobaciones
                    if (tablero.getJugador(1).getVida() < 1) {//Lo mismo pero para el jugador 1
                        System.out.println("Ha ganado " + tablero.getJugador(0).getNombre());
                        tablero.getJugador(0).getMazo().ficheromazo();
                        tablero.getJugador(0).getMazo().ordenarcriaturas();
                        tablero.getJugador(1).getMazo().ficheromazo();
                        tablero.getJugador(1).getMazo().ordenarcriaturas();
                        tablero.getJugador(0).getMazo().cualserepitemas();
                        tablero.getJugador(1).getMazo().cualserepitemas();
                        tablero.terminarjuego();
                        partidaenmarcha = false;
                    } else {//Si esto tampoco se cumple se comprueba si pueden seguir jugando
                        if (tablero.getJugador(0).getMazo().getArrayCartas().getLength() < 1 || tablero.getJugador(1).getMazo().getArrayCartas().getLength() < 1) {
                            tablero.setContador(0);//En el caso de que uno de ellos se haya quedado sin cartas en el mazo se reinicia el contador y se les da 5 turnos
                            while (partidaenmarcha) {//mientras que la partida siga en marcha
                                if (tablero.getContador() == 4) {//Si el contador de turnos llega a 4 (porque empezamos desde el 0)
                                    System.out.println("Habeis empatado");//Empatan
                                    tablero.getJugador(0).getMazo().ficheromazo();
                                    tablero.getJugador(0).getMazo().ordenarcriaturas();
                                    tablero.getJugador(1).getMazo().ficheromazo();
                                    tablero.getJugador(1).getMazo().ordenarcriaturas();
                                    tablero.getJugador(0).getMazo().cualserepitemas();
                                    tablero.getJugador(1).getMazo().cualserepitemas();
                                    tablero.terminarjuego();//Y se termina el juego
                                    partidaenmarcha = false;
                                }
                            }
                        } else {
                            Thread.sleep(1);//Si no pasa nada de lo anterior no se hace nada
                        }
                    }
                }

            }
            tablero = null;
//            out.close(); SI DESCOMENTAMOS ESTO Y LO DE ARRIBA EN VEZ DE IR A LA CONSOLA LAS SALIDAS VAN AL FICHERO LOG
        } catch (NullPointerException ex) {
            System.out.println("Ha habido un error en el while del main");
        }

    }

}
