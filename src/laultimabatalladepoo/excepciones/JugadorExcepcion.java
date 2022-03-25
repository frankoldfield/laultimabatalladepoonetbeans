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
public class JugadorExcepcion extends Exception {//Esta excepcion sera para cuando haya algun error relacionado con los jugadores
    public JugadorExcepcion(){
        super("Los jugadores se han generado mal");
    }
}
