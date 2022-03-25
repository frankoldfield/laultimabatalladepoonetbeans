package laultimabatalladepoo;

import java.util.*;

/**
 *
 * @author frank
 */

public class Utilidad {//Esta es la clase de utilidad, va a contener algunos metodos utiles

    /**
    *Este método nos va a dar un número aleatorio entre el 1 y el número que le hayamos introducido
     * @param l
     * @return 
    */
    
    public static int random(int l) { //Este metodo escoge un aleatorio del 0 al numero que introduzcamos
        Random i = new Random();//Creamos un objeto de la clase random
        return i.nextInt(l) + 1;//Devolvemos un entero aleatorio en ese rango, he puesto +1 porque en realidad nextInt coge l como la cantidad de casos, por lo que puede salir del 0 hasta el l-1, si le sumamos 1 corregimos el intervalo
    }

    /**
    *Este método va a devolver un booleano que será verdadero con probabilidad 0.25 y falso con probabilidad 0.75
     * @return 
    */
    
    public static boolean probabilidaduncuarto() {//Este metodo sirve para hacer una muestra con probabilidad 1/4, si sale esta devuelve que es true, o sea, que vamos a robar de la mano enemiga.
        int rango = Utilidad.random(4);//Sacamos un número aleatorio del 0 al 3
        if (rango == 1) {//Si ese número es 1
            return true;//Damos true
        } else {//Si es otro
            return false;//Damos false
        }
    }//Como son 4 casos igualmente probables, la probabilidad de uno de ellos es 1/4
}
