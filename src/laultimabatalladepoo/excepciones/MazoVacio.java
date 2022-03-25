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
public class MazoVacio extends Exception{//Esta excepcion sera para cuando el mazo de un jugador no tenga mas cartas
        public MazoVacio(){
        super("No te quedan cartas en el mazo");
}
}
