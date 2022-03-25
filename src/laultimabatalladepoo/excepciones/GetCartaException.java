/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.excepciones;

/**
 *
 * @author frank
 */
public class GetCartaException extends Exception{//Este error sera para cuando pidamos una carta en una posicion que no existe en la lista donde la estamos pidiendo
    public GetCartaException(){
        super("En esta posicion no hay una carta");
    }
}
