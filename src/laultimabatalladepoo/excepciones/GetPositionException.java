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
public class GetPositionException extends Exception{ //Este error es para cuando una carta no este en una lista en la que estamos intentando buscarla
    public GetPositionException(){
        super("Esta carta no se encuentra en esta lista");
    }
}