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
public interface Raza {//La interfaz raza va a tener de subinterfaces las interfaces elfo, enano y humano.
    public abstract double getvidaInicial();//Este metodo sera para recuperar la vida inicial asociada a cada raza
    
    public abstract Reino getReino();//Este sera para recuperar el reino de procedencia de una raza
    
    public int getCartasMaximas();//Y este para las cartas maximas
}
