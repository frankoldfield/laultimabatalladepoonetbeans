package laultimabatalladepoo.cartas;

import java.util.List;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.cartas.Habilidad.HabilidadEnum;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

public abstract class Carta {//Esta clase abstracta Carta va a ser padre de todos los demas tipos de carta
    
//AQUI EMPIEZAN LOS ATRIBUTOS
    public Jugador owner;//Cada carta va a conocer a su propietario
    public double vida;//Cada carta va a tener una vida determinada (Aunque los hechizos la van a utilizar de diferente manera)
    public double atk;//Cada carta va a tener un daño determinado
    public final String nombre;//Este sera el nombre de cada carta
    public boolean candef;//Este booleano nos servira para poder identificar que cartas pueden defender
    public HabilidadEnum habilidad;//Algunas cartas van a tener una habilidad (todas menos las criaturas), vamos a cogerlas de un enumerado con los metodos asociados
    public static int numerodecartas;
//AQUI TERMINAN LOS ATRIBUTOS
    
//AQUI EMPIEZA EL CONSTRUCTOR
    public Carta(double atk, String nombre, Jugador owner, HabilidadEnum habilidad, Boolean candef){//El constructor va a ser algo mas escaso porque dependiendo del tipo de carta algunos atributos se van a utilizar de una manera u otra
        this.atk = atk;
        this.nombre = nombre;
        this.owner = owner;
        this.habilidad = habilidad;
        this.candef = candef;
        numerodecartas = numerodecartas + 1;
    }
//AQUI TERMINA EL CONSTRUCTOR
    
//AQUI EMPIEZA EL SETTER GETTER
    public double getVida() {
        return vida;
    } //El metodo getVida va a ser igual para todas las cartas asi que lo podemos declarar ya

    public void setVida(double vida) {//Este metodo va a ser diferente para cada tipo de carta porque en algunos no va a existir, simplemente imprimiremos que a esa carta no se le puede cambiar la vida si es asi.
        this.vida = vida;
    }
    public void setAtk(double atk){//Por la misma razon este va a ser abstracto
        this.atk = atk;
    }
    
    public double getAtk() {
        return atk;
    }

    public Jugador getOwner(){
        return owner;
    }
    public void setOwner(Jugador newowner){
        owner = newowner;
    }
    
    public boolean getCanDef(){//Metodo que devuelve el booleano que nos dice si esta carta puede defender o no
        return candef;
    }
    public String getNombre(){//Para poder utilizar el nombre de una carta necesitamos esto
        return this.nombre;
    }
    public HabilidadEnum getHabilidad(){//Este metodo sirve para luego determinar que habilidad realiza cada carta
        return habilidad;
    }
    
    public abstract List getEncantamientosLista();//Este metodo se implementara unicamente en las criaturas, y nos sirve para ver que encantamientos tiene

    public Reino getReino() {
        throw new UnsupportedOperationException("Este tipo de carta no tiene reino"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString(){
        return nombre;
    }
    
    public void equal(Carta carta){
        if (this==carta){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
    
//AQUI TERMINA EL SETTER GETTER
    
//AQUI EMPIEZAN LOS METODOS DE LA CLASE
    
    /**
    *Este método va a comprobar si la carta está muerta
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void isitdead() throws GetPositionException, GetCartaException{
        if (vida<=0){
            die();
        }
    }
    
    /**
    *Este método va a hacer que la carta desaparezca complétamente del juego
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    public void desaparecer() throws GetCartaException, GetPositionException{//Este metodo hara desaparecer una carta, para cuando esta no vaya al cementerio
    owner.getMano().getArrayCartas().removeCarta(owner.getMano().getArrayCartas().getPosition(this));//Hacemos que la carta utilizada sea eliminada de la mano del propietario
        owner=null;//Borramos la referencia que tiene la carta del propietario, esta ya no tiene propietario
    }
    
    public abstract void die() throws GetPositionException, GetCartaException;

    public abstract void use() throws GetCartaException, GetPositionException;//Este metodo es para las cartas que se colocaran en el campo de batalla
    
    public abstract void use(Carta carta) throws GetCartaException, GetPositionException; //Este metodo es para usar las cartas que actuan sobre otras, lo que se pide es el objetivo sobre el que se utiliza la carta.
//AQUI TERMINAN LOS METODOS DE LA CLASE
}