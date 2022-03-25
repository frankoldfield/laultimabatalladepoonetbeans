/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.cartas;

import laultimabatalladepoo.Jugador;

/**
 *
 * @author frank
 */
public class Mano {//Esta es la clase de la mano que va a llevar cada jugador
    private final Jugador owner;//El jugador al que va a ir asociada la mano
    private ListaCarta arraycartas;//La lista de cartas contenidas en la mano
    private final int cartasmaximas;//El limite de cartas de la mano
    
    public Mano(Jugador owner){
        this.owner = owner;
        arraycartas = new ListaCarta();
        cartasmaximas = owner.getCartasMaximas();//La mano va a heredar las cartas maximas permitidas del jugador
    }
    
//EMPIEZA EL SETTER GETTER
    /**
     *Este método sirve para acceder a la lista de cartas de la mano
     * @return 
    */
    
    public ListaCarta getArrayCartas(){ //El metodo que retorna la lista de cartas que contiene la mano
        return arraycartas;
    }
    
    
    
    /**
     *Este método sirve para conocer el propietario de esta mano
     * @return 
    */
    
    public Jugador getOwner(){//Este método sirve por si necesitásemos conocer el dueño de una mano de cartas.
        return owner;
    }
    
    public String toString(){
        return String.valueOf(this);
    }
    
    public void equal(Mano mano){
        if (this==mano){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
//TERMINA EL SETTER GETTER
}
