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
public class Enano implements Raza {//Esta es la clase Humano que es subinterfaz de Raza
    private final double vidainicial = 25;//La vida inicial del enano va a ser 25
    private final Reino reino = Reino.CAVERNAS;//El reino de origen del emamp van a ser las cavernas
    private final int cartasmaximas = 9;//El enano va a poder tener 9 cartas en la mano
    
    @Override
    public double getvidaInicial(){return vidainicial;}//El metodo para recuperar la vida inicial asociada a una raza
    
    @Override
    public Reino getReino(){return reino;} //El metodo para recuperar el reino asociado a un jugador, esto se utilizara mas tarde para comparar reinos entre criaturas y su propietario
    
    @Override
    public int getCartasMaximas(){ //Este metodo retorna el numero de cartas maximas que podra el jugador cuya raza sea esta tener en la mano
        return cartasmaximas;
    }
}
