/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.razas;
import laultimabatalladepoo.cartas.Reino;

/**
 *
 * @author frank
 */
public class Humano implements Raza {//Esta es la clase Humano que es subinterfaz de Raza
    private final double vidainicial = 30;//La vida inicial del humano va a ser 30
    private final Reino reino = Reino.CIUDAD; //El reino de origen del humano va a ser la ciudad
    private final int cartasmaximas = 11;//El humano va a poder tener 11 cartas en la mano
    
    @Override
    public double getvidaInicial(){return vidainicial;}//El metodo para recuperar la vida inicial asociada a una raza
    
    @Override
    public Reino getReino(){return reino;} //El metodo para recuperar el reino asociado a un jugador, esto se utilizara mas tarde para comparar reinos entre criaturas y su propietario
    
    @Override
    public int getCartasMaximas(){ //Este metodo retorna el numero de cartas maximas que podra el jugador cuya raza sea esta tener en la mano
        return cartasmaximas;
    }
}