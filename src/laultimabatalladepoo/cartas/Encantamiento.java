/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.cartas;

import java.util.List;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */
public class Encantamiento extends Hechizo{ //Debe de ser abstracta ya que hay metodos de la superclase padre que no utiliza
    //Los encantamientos son las cartas que potencian a nuestras criaturas del campo de batalla
//EMPIEZA EL CONSTRUCTOR
    public Encantamiento(double vida, double atk, String nombre, Jugador owner){//Como los encantamientos si que tienen una vida asociada, tenemos que asignársela a parte, porque los hechizos de por si no tenían
        super(atk, nombre, owner);
        this.vida = vida;
    }
//TERMINA EL CONSTRUCTOR
    
//EMPIEZAN LOS MÉTODOS DE CLASE
    /**
     * Metodo que hace que se utilice este encantamiento sobre una criatura aliada
     * @param encantada
     */
    @Override //Sobreescribimos el método use con la forma en la que se utilizan los encantamientos
    public void use(Carta encantada) throws GetCartaException, GetPositionException {
        if (encantada instanceof Criatura){//Si la carta seleccionada es una criatura entonces se puede encantar
            encantada.setVida(encantada.getVida()+vida);//Se le suma la vida que sube esta carta
            encantada.setAtk(encantada.getAtk()+atk);//Lo mismo con el ataque
            encantada.getEncantamientosLista().add(this.toString());//Y se le añade el string de este encantamiento a la lista de encantamientos de la criatura
            System.out.println(owner.getNombre()+" ha usado un "+nombre+" sobre la carta "+encantada.getNombre());//Imprimimos la carta que se ha encantado, quien la ha encantado, y con qué
        }
        else{//Si por algún casual seleccionáramos una carta que no fuese una criatura, simplemente se imprimiría que no se puede encantar esa carta
            System.out.println("No se puede encantar esta carta!");//CUANDO HAGA LA INTERFAZ GRAFICA PARA UTILIZAR LA CARTA TENGO QUE HACER UN BUCLE HASTA QUE EL JUGADOR ELIJA UNA CRIATURA A LA QUE ENCANTAR
        }
        
    }
    /**
     * Este método nos dice que no hemos introducido la posicion de la carta que queremos encantar, porque no se puede encantar al vacío
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     */
    @Override
    public void use() throws GetCartaException, GetPositionException {//Un encantamiento no se puede utilizar en algo que no sea una carta concreta
        throw new UnsupportedOperationException("No has introducido la posicion de la carta a la que quieres encantar");//To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Al intentar usar este método nos dice que los encantamientos no se pueden encantar
     * @return 
     */
    @Override
    public List getEncantamientosLista() {//Las cartas de encantamiento no tienen una lista de encantamientos asociada
        throw new UnsupportedOperationException("Los encantamientos no tienen encantamientos"); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Metodo que nos dice que un hechizo no puede morir, solo desaparecer
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     */
    @Override
    public void die() throws GetPositionException {//Un encantamiento no puede ir al cementerio, simplemente imprimiremos lo siguiente
        throw new UnsupportedOperationException("Un hechizo no puede morir, solo desaparecer.");
    }
    
    
    
//TERMINAN LOS MÉTODOS DE CLASE
}
