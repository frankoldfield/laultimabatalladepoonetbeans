package laultimabatalladepoo.cartas;

import java.util.List;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

//En esta clase hay sobrecarga del metodo use()
public class Conjuro extends Hechizo {//Esta es la clase conjuro, que es hija de carta y de hechizo, los conjuros hacen daño a una carta enemiga o al enemigo directamente

    public Conjuro(double atk, String nombre, Jugador owner) {//Constructor de la clase conjuro, nos pide el ataque, el nombre, y su propietario, como no tiene habilidad le pondremos obligatoriamente NOTIENE
        super(atk, nombre, owner);//Invocamos al constructor de la clase padre, donde se rellenan los datos que son automáticos, que no puede defender, que no tiene habilidad, etc.
    }//Como no tiene vida no tenemos que asignársela, ya que esta carta no está hecha para curar ni puede vivir, simplemente se usa para hacer una cantidad de daño a una criatura enemiga o al jugador enemigo diréctamente

    
    
//AQUI EMPIEZAN LOS METODOS DE CLASE
    /**
    *Este método va a usar el conjuro sobre una carta de criatura enemiga que se halle en el campo de batalla
     * @param atacada
    */
    
    @Override
    public void use(Carta atacada) throws GetCartaException, GetPositionException {//Este metodo nos pide la carta que sera atacada
        atacada.setVida(atacada.getVida() - atk);//Le restamos el daño que tiene la carta a la carta que ha sido atacada
        atacada.isitdead();
        System.out.println(owner.getNombre() + " ha atacado al " + atacada.getNombre() + " enemigo con un " + nombre);
        this.desaparecer();//Invocamos al metodo que hace desaparecer a la carta
    }

    /**
    *Este método va a usar el conjuro sobre el jugador enemigo diréctamente
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    @Override
    public void use() throws GetCartaException, GetPositionException {//Este metodo sera para cuando decidamos atacar al enemigo directamente, simplemente no le pasamos la carta objetivo
        owner.getTablero().getJugador(owner.getEnemigo()).setVida(owner.getTablero().getJugador(owner.getEnemigo()).getVida() - atk);//Hacemos el computo de daño pero esta vez directamente con la vida del jugador enemigo
        System.out.println(owner.getNombre() + " ha atacado al jugador enemigo con un " + nombre);
        this.desaparecer();//Una vez mas llamamos al metodo que hace que esta carta desaparezca

    }

    /**
    *Este método va a decirnos que un conjuro no puede tener encantamientos afectándole
     * @return 
    */
    
    @Override
    public List getEncantamientosLista() {//Tenemos que implementar este metodo porque lo heredamos, pero lo sobreescribimos y
        throw new UnsupportedOperationException("Los conjuros no tienen encantamientos asociados"); //decimos que los conjuros no tienen encantamientos asociados.
    }

    /**
    *Este método va a decirnos que esta carta no puede morir porque es un conjuro
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    @Override
    public void die() throws GetPositionException {//Tenemos que tener este metodo porque lo heredamos de la clase padre
        throw new UnsupportedOperationException("Un conjuro no puede morir, solo desaparecer");//Pero como un conjuro no puede morir imprimimos este mensaje en vez de hacer cualquier otra cosa
    }
}
//AQUI TERMINAN LOS METODOS DE CLASE

