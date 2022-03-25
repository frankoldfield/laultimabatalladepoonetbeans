/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.cartas;
import laultimabatalladepoo.Jugador;
import static laultimabatalladepoo.cartas.Habilidad.HabilidadEnum.NOTIENE;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

public abstract class Hechizo extends Carta{ //Esta clase abstracta va a ser superclase de las clases conjuro y encantamiento
    
//AQUI EMPIEZA EL CONSTRUCTOR DE LA CLASE ABSTRACTA HECHIZO
    public Hechizo(double atk, String nombre, Jugador owner){
        super(atk, nombre, owner, NOTIENE, false); //Llamamos al constructor de la superclase carta, pero ponemos fijo el que no tenga habilidad
    }
//AQUI TERMINA EL CONSTRUCTOR DE LA CLASE ABSTRACTA HECHIZO

//AQUI EMPIEZA EL SETTER GETTER DE LA CLASE ABSTRACTA HECHIZO
    /**
     *Al intentar cambiar la vida del hechizo no nos dejará
     * @param vida
    */
    @Override //Sobreescribimos este método imprimiendo que no se puede cambiar la vida de un hechizo, asi no tendremos que hacerlo en conjuro y encantamiento
    public void setVida(double vida) {
        System.out.println("No se puede cambiar la vida de un hechizo");
    }

    
    /**
     *Al intentar cambiar el daño del hechizo no nos dejará
     * @param atk
    */
    @Override//Sobreescribimos este método imprimiendo que no se puede cambiar el ataque de un hechizo, asi no tendremos que hacerlo en conjuro y encantamiento
    public void setAtk(double atk) {
        System.out.println("No se puede cambiar el ataque de un hechizo");
    } 
    
    
    
//AQUI TERMINA EL SETTER GETTER DE LA CLASE ABSTRACTA HECHIZO

//AQUI EMPIEZAN LOS METODOS DE LA CLASE ABSTRACTA HECHIZO
   /**
     *Este método va a ser sobreescrito mas tarde por las subclases de la clase hechizos, y van a funcionar de manera distinta dependiendo del tipo
     * @param objetivo
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    @Override
    public abstract void use(Carta objetivo) throws GetCartaException, GetPositionException; //Los conjuros van a hace efecto en el enemigo, y los encantamientos en las criaturas aliadas, este método se sobreescribirá en cada clase

    
    
//AQUI TERMINAN LOS METODOS DE LA CLASE ABSTRACTA HECHIZO
    
}
