package laultimabatalladepoo.cartas;

import java.util.ArrayList;
import java.util.List;
import laultimabatalladepoo.Jugador;
import static laultimabatalladepoo.cartas.Habilidad.HabilidadEnum.NOTIENE;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

public class Criatura extends Carta {//Esta clase es del tipo de carta Criatura y va a ser hija de la clase Carta
    
//AQUI EMPIEZAN LAS VARIABLES DE LA CLASE
    private final Reino reino; //Cada criatura va a tener un reino asociado.
    private List<String> encantamientoslista;//Esta lista va a contener todos los encantamientos que tiene esta criatura
//AQUI TERMINAN LAS VARIABLES DE LA CLASE

//AQUI EMPIEZA EL CONSTRUCTOR
    public Criatura(double vida, double atk, String nombre, Jugador owner, Reino reino) {//Para el constructor pedimos la vida, el ataque, el nombre, el propietario, y el reino de la criatura (la habilidad va a ser NOTIENE y se pone automáticamente sin pedirla, además una criatura no puede simplemente defender)
        super(atk, nombre, owner, NOTIENE, false);//Llamamos al constructor de la clase padre
        this.reino = reino;//Establecemos el reino
        this.vida = vida;//Le asignamos la vida que hemos introducido como argumento
        encantamientoslista = new ArrayList<>();//La lista de encantamientos la creamos de 0 porque en un principio la criatura no va a tener encantamientos
        candef = false; //Sabemos que las criaturas no pueden defender (pueden atacar y defender pero no solo defender)
    }
//AQUI TERMINA EL CONSTRUCTOR
    
//AQUI EMPIEZA EL SETTER GETTER
    @Override//Como las criaturas si tienen reino sobreescribimos el método y le ponemos como valor de retorno el reino que tiene asociado.
    public Reino getReino(){
        return reino;
    }
    
    @Override
    public String toString(){
        return("Los atributos de esta criatura son; Vida:"+String.valueOf(this.getVida())+" su ataque es "+String.valueOf(this.getAtk())+"");
    };
    
    public Boolean equals(Criatura criaturacomparada){
        Boolean igual = true;
        if(criaturacomparada.getAtk() != atk){
            igual = false;
        }
        if(criaturacomparada.getVida() != vida){
            igual = false;
        }
        return igual;
    }
    
    public Criatura clone(){
    Criatura criaturanueva = new Criatura(vida, atk, nombre, owner, reino);
    return criaturanueva;
    }
    
    public void addEncantamiento(String encantamiento) {
        encantamientoslista.add(encantamiento);
    }
    
    @Override
    public List getEncantamientosLista(){
        return encantamientoslista;
    }
//AQUI TERMINA EL SETTER GETTER


//AQUI EMPIEZAN LOS METODOS DE CLASE
    
    /**
    *Este método hace que la criatura muera, se va al cementerio y desaparece del campo de batalla
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     */
    
    @Override
    public void die() throws GetPositionException {//Este metodo hace que la carta se vaya al cementerio y desaparezca del campo de batalla
        owner.getCementerio().getArrayCartas().addCarta(this);//Primero añadimos esta carta al cementerio
        owner.getCampoBatalla().getArrayCartas().removeCarta(owner.getCampoBatalla().getArrayCartas().getPosition(this));//Despues borramos esta carta del campo de batalla
        encantamientoslista.clear();//Limpiamos sus encantamientos porque ya no va a tener
        if (owner.getCartaDefendiendo()==this){//Si la carta que ha muerto es la que estaba defendiendo el jugador ya no tiene ninguna carta defendiendo
            owner.setCartaDefendiendo(null);//Asi que vaciamos esa posicion
        }
        System.out.println(owner.getNombre()+", ha muerto tu "+nombre+" y ha ido al cementerio");
    }
    
    /**
    *Este método saca a la criatura al campo de batalla desde la mano
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    @Override
    public void use() throws GetPositionException{//Este metodo va a colocar a la carta en el campo de batalla
        owner.getCampoBatalla().getArrayCartas().addCarta(this);//Primero copiamos la carta al campo de batalla
        owner.getMano().getArrayCartas().removeCarta(owner.getMano().getArrayCartas().getPosition(this));//Ahora borramos la carta de la mano del jugador
        System.out.println(owner.getNombre()+" ha sacado un "+nombre);//Informamos de la carta que se ha sacado a través de la consola
    }
    
    /**
    *Una criatura no puede usarse sobre otra carta
     * @param carta
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    @Override
    public void use(Carta carta) throws GetCartaException, GetPositionException {//Como una criatura no puede actuar sobre otra carta
        throw new UnsupportedOperationException("Una criatura no puede actuar directamente sobre otra carta");//Imprimimos este mensaje
    }
    
    /**
    *Con este método comprobamos si la criatura está muerta
     * @throws laultimabatalladepoo.excepciones.GetPositionException
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    @Override
    public void isitdead() throws GetPositionException, GetCartaException{
        if (vida<=0){
            die();
        }
    }
//AQUI TERMINAN LOS METODOS DE CLASE
}

