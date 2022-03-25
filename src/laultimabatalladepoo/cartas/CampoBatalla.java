package laultimabatalladepoo.cartas;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.Tablero;

/**
 *
 * @author frank
 */

public class CampoBatalla {//La clase del campo de batalla, cada jugador va a tener una instancia de esta clase asociada, y esta instancia va a conocer a ese jugador.

//AQUI EMPIEZAN LOS ATRIBUTOS

    private final Jugador owner; //Este atributo indica de quien es este campo de batalla
    private ListaCarta arraycartas;//Este atributo es el que le asocia la lista de cartas al campo de batalla
    private Tablero tablero;//Va a conocer al tablero donde se encuentra cuando el tablero empiece a conocer a este campo de batala
//AQUI TERMINAN LAS ATRIBUTOS

//AQUI EMPIEZA EL CONSTRUCTOR
    public CampoBatalla(Jugador owner) { //Pedimos la entrada del owner y creamos la lista de cartas desde 0 ya que al principio el campo de batalla va a estar vacio.
        this.owner = owner;
        arraycartas = new ListaCarta();
    }
//AQUI TERMINA EL CONSTRUCTOR
    
//AQUI EMPIEZA EL SETTER GETTER
    public ListaCarta getArrayCartas(){
        return arraycartas;
    }
    
    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public String toString(){
        return String.valueOf(this);
    }
    
    public void equal(CampoBatalla campobatalla){
        if (this==campobatalla){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
//AQUI TERMINA EL SETTER GETTER
}