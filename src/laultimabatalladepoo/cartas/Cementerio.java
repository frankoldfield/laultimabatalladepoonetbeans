package laultimabatalladepoo.cartas;

import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.Tablero;

/**
 *
 * @author frank
 */

public class Cementerio {//El cementerio es a donde van a ir las cartas de criatura o edificacion que mueran, este va a estar asociado a un jugador y a un tablero.
    private final Jugador owner;//Este atributo es para poder referirnos en algun momento al propietario del cementerio
    private ListaCarta arrayCartas;//Le asociamos la lista de cartas que va a contener
    private Tablero tablero;//Este se lo vamos a asignar cuando le asignemos el cementerio al tablero
    
    public Cementerio(Jugador owner){//El constructor solo pide el jugador propietario y crea el array de 0 ya que este al principio estara vacio
        this.owner = owner;//Propietario del cementerio
        arrayCartas = new ListaCarta(); //nueva lista de cartas asociada al cementerio
    }
    
//EMPIEZA EL SETTER GETTER
    /**
    *Este método va a darnos la lista de cartas del cementerio
     * @return 
    */
    
    public ListaCarta getArrayCartas(){//Este metodo nos sirve para poder acceder al array de cartas que tiene asociado el cementerio
        return arrayCartas;
    }
    
    /**
    *Este método va a dejarnos cambiar el tablero en el que se encuentra este cementerio
     * @param tablero
    */
    
    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
    
    /**
    *Este método va a dejarnos conocer el tablero en el que se encuentra este cementerio
     * @return 
    */
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public String toString(){
        return String.valueOf(this);
    }
    
    public void equal(Cementerio cementerio){
        if (this==cementerio){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
//TERMINA EL SETTER GETTER
}
