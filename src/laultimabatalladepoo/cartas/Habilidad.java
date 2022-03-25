/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.cartas;

import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.excepciones.GetCartaException;

/**
 *
 * @author frank
 */
public class Habilidad { //Esta clase va a contener un enumerado, con las distintas habilidades, y a su vez cada una de estas tendrá una habilidad asociada.
    public enum HabilidadEnum{CURAR, BOOSTDAMAGECAMPO, BOOSTHEALTHCAMPO, NOTIENE};
//Curar va a curar al jugador, BOOSTDAMAGECAMPO va a subir el daño de las criaturas en el campo de batalla, BOOSTHEALTHCAMPO lo mismo para la vida, y NOTIENE es para cuando una carta no tiene habilidad
    /**
     * Este método cura al jugador una cantidad de vida determinada por la variable health
     * @param health
     * @param jugador
     */
    public static void curarJugador(double health, Jugador jugador){
        jugador.setVida(jugador.getVida() + health);//Le curamos al jugador la vida que determinemos
    }

    
    /**
     * Este método cura al jugador 2 de vida
     * @param jugador
     */
    public static void curarJugador(Jugador jugador){//HACEMOS SOBRECARGA PARA QUE SI NO LE METEMOS NINGUN ENTERO SUME 2, ESTE SERIA EL CASO DE COLOCAR LA FUENTE DE VIDA
        jugador.setVida(jugador.getVida() + 2);//Le curamos al jugador 2 de vida
    }
    
    
    /**
     * Metodo que hace que todas las criaturas del campo de batalla tengan mas daño
     * @param jugador
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     */
    public static void boostDamageCampo(Jugador jugador) throws GetCartaException{//Este método va a subir el ataque de las criaturas aliadas en el campo de batalla
        Carta carta;//Iniciamos la variable que va a ir cambiando y haciendo la comprobación
        for(int i=0;i<=(jugador.getCampoBatalla().getArrayCartas().getLength()-1);i++){//Hacemos un bucle que recorra el campo de batalla aliado
            carta = jugador.getCampoBatalla().getArrayCartas().getCarta(i);//Vamos cambiando la carta que estamos comprobando
            if (carta instanceof Criatura){//Si esa carta es una criatura se ejecuta el siguiente código
                jugador.getCampoBatalla().getArrayCartas().getCarta(i).setAtk(jugador.getCampoBatalla().getArrayCartas().getCarta(i).getAtk() + 1);//Se le suma 1 de ataque a la carta en la posición que acabamos de comprobar
                jugador.getCampoBatalla().getArrayCartas().getCarta(i).getEncantamientosLista().add("boostDamageCampo");//AÑADIR EL EFECTO A LA LISTA DE ENCANTAMIENTOS
            }
            }
    }
    
    
    /**
     * Metodo que hace que todas las criaturas del campo de batalla tengan mas daño
     * @param jugador
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     */
    public static void boostHealthCampo(Jugador jugador) throws GetCartaException{
        Carta carta;//Iniciamos la variable que va a ir cambiando y haciendo la comprobación
        for(int i=0;i<=(jugador.getCampoBatalla().getArrayCartas().getLength()-1);i++){//Hacemos un bucle for que recorre todas las posiciones del campo de batalla del jugador
            carta = jugador.getCampoBatalla().getArrayCartas().getCarta(i);//Cogemos la carta en la posición i
            if (carta instanceof Criatura){//Si cumple la condición de que la carta sea una criatura se ejecuta el siguiente código
                jugador.getCampoBatalla().getArrayCartas().getCarta(i).setVida(jugador.getCampoBatalla().getArrayCartas().getCarta(i).getVida() + 1);//Le sumamos la vida a cada criatura
                jugador.getCampoBatalla().getArrayCartas().getCarta(i).getEncantamientosLista().add("boostHealthCampo");//AÑADIR EL EFECTO A LA LISTA DE ENCANTAMIENTOS
            }
        }
    }
    
    
    
}
