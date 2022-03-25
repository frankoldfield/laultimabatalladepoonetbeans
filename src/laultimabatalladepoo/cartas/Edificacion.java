package laultimabatalladepoo.cartas;

import java.util.List;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.cartas.Habilidad.HabilidadEnum;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

public class Edificacion extends Carta { //Las edificaciones son una subclase de la clase abstracta carta
    
//AQUI EMPIEZA EL CONSTRUCTOR
    public Edificacion(double vida, double atk, String nombre, HabilidadEnum habilidad, Jugador owner, boolean candef) { //El constructor de la clase edificacion
        super(atk, nombre, owner, habilidad, candef);//Se pedirá si se puede defender, porque no todas las edificaciones pueden defender
        this.vida = vida;//Como las edificaciones tienen vida, también tenemos que definirla, ya que en el constructor de la clase carta no estaba ese atributo
    }
//AQUI TERMINA EL CONSTRUCTOR
    
//AQUI EMPIEZA EL SETTER GETTER
@Override
    public List getEncantamientosLista() {//Como las edificaciones no tienen encantamientos no podemos devolver una lista con sus encantamientos
        throw new UnsupportedOperationException("Las edificaciones no tienen encantamientos");
    }
//AQUI TERMINA EL SETTER GETTER
    
//AQUI EMPIEZAN LOS METODOS DE LA CLASE
    /**
     * Metodo que hace que la edificacion muera
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     */
    @Override
    public void die() throws GetPositionException , GetCartaException{//Este metodo hace que la carta se vaya al cementerio y desaparezca del campo de batalla
        owner.getCementerio().getArrayCartas().addCarta(this); //Añadimos esta carta al array de cartas del cementerio
        owner.getCampoBatalla().getArrayCartas().removeCarta(owner.getCampoBatalla().getArrayCartas().getPosition(this));//Quitamos esta carta del array de cartas del campo de batalla
        pararHabilidad();//Paramos la habilidad de la edificacion
        owner=null;
    }
    
    
        /**
     * Metodo que hace que la edificacion se mueva de la mano al campo de batalla
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     */
    @Override
    public void use() throws GetCartaException, GetPositionException{//Este método será para sacar la carta de la mano al campo de batalla
        habilidadEdificacion(); //Ponemos en marcha su habilidad
        owner.getCampoBatalla().getArrayCartas().addCarta(this);//Añadimos la carta al array de cartas del campo de batalla del dueño de la carta
        owner.getMano().getArrayCartas().removeCarta(owner.getCampoBatalla().getArrayCartas().getPosition(this));//Quitamos la carta de la mano del dueño
        System.out.println(owner.getNombre()+" ha sacado un/una "+nombre);//Imprimimos que el jugador ha sacado esta carta
    }
    

    /**
     * Metodo que hace que la edificacion utilice su habilidad
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     */
    public void habilidadEdificacion() throws GetCartaException { //Este método pone en marcha las habilidades de las edificaciones cuando las sacamos
        try {
            if (habilidad == HabilidadEnum.CURAR) {//Si la habilidad es CURAR cura 2 de vida al jugador
                Habilidad.curarJugador(owner);
            }
            if (habilidad == HabilidadEnum.BOOSTDAMAGECAMPO) {//Esta habilidad potencia el daño de todas las criaturas del campo de batalla
                Habilidad.boostDamageCampo(owner);
            }
            if (habilidad == HabilidadEnum.BOOSTHEALTHCAMPO) { //Esta habilidad sube la vida a todas las criaturas del campo de batalla
                Habilidad.boostHealthCampo(owner);
            }
        }catch(NullPointerException e){System.out.println("Como no tienes criaturas en el campo de batalla la habilidad de la edificacion no ha hecho efecto.");} //Si recibimos un nullpointerexception será porque no hay criaturas en el campo de batalla, simplemente imprimimos eso
    }
    
    
    /**
     * Metodo que hace que la edificacion pare su habilidad, es decir, la quita de la serie de encantamientos que afectan a las criaturas del campo de batalla
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     */
    public void pararHabilidad() throws GetCartaException{//Este método se utilizará cuando una edificación muera, para quitarle la etiqueta de la habilidad a las criaturas, he decidido que no se les quite el daño o la vida que se les otorga porque entonces el juego duraría demasiado
        if (habilidad == HabilidadEnum.BOOSTDAMAGECAMPO) {//Si la habilidad de la edificación es boostdamagecampo
            for (int i = 0; i <= owner.getCampoBatalla().getArrayCartas().getLength(); i++) {//Recorremos todo el campo de batalla
                if (owner.getCampoBatalla().getArrayCartas().getCarta(i) instanceof Criatura) {
                    if (owner.getCampoBatalla().getArrayCartas().getCarta(i).getEncantamientosLista().contains("boostDamageCampo")) {//Si la carta tiene esta habilidad puesta
                        owner.getCampoBatalla().getArrayCartas().getCarta(i).getEncantamientosLista().remove("boostDamageCampo");//Se lo quitamos
                        break;//Tenemos que salir del bucle en cuanto se cumpla una vez ya que si no podríamos quitar mas de una habilidad en el caso de que hubiésemos sacado varias edificaciones con la misma habilidad
                    }//Si quisiésemos podríamos hacer que cuando detecte que una carta la tiene se le baja el ataque que se le había subido}

                }
            }
            if (habilidad == HabilidadEnum.BOOSTHEALTHCAMPO) {//Si la habilidad es BOOSTHEALTHCAMPO
                for (int i = 0; i <= owner.getCampoBatalla().getArrayCartas().getLength(); i++) {//Recorremos el campo de batalla buscando cartas que tengan esta habilidad aplicada en ellas
                    if (owner.getCampoBatalla().getArrayCartas().getCarta(i).getEncantamientosLista().contains("boostHealthCampo")) {
                        owner.getCampoBatalla().getArrayCartas().getCarta(i).getEncantamientosLista().remove("boostHealthCampo");
                        break;
                    }//Podríamos hacer que al morir la edificación las criaturas perdiesen la vida que habían ganado por la habilidad poniendo owner.getCampoBatalla().getArratCartas().getCarta(i).setVida("vidaanterior"-2);
                }
            }
        }
    }
    
    
    /**
     * Metodo que comprueba si una edificacion esta muerta
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     */
    @Override
    public void isitdead() throws GetPositionException, GetCartaException{
        if (vida<=0){
            die();
        }
    }
    
    
    @Override
    public void use(Carta carta) throws GetCartaException, GetPositionException {//Las edificaciones solo se pueden usar para sacarlas al campo de batalla, por lo que no tendría sentido pedir una carta como argumento
        throw new UnsupportedOperationException("Una edificación no puede actuar directamente sobre otra carta");
    }
//AQUI TERMINAN LOS METODOS DE CLASE

    

    
}
