package laultimabatalladepoo;

import interfaces.AccionesJugador;
import interfaces.Atacar;
import interfaces.Defender;
import interfaces.DescartarCarta;
import interfaces.InfoPartida;
import interfaces.UsarCarta;
import interfaces.UsarConjuro;
import interfaces.UsarEncantamiento;
import laultimabatalladepoo.razas.*;
import laultimabatalladepoo.cartas.*;
import laultimabatalladepoo.excepciones.*;
import java.util.*;

/**
 *
 * @author frank
 */

public class Jugador { //Esta clase es la del jugador, va a contener los metodos de este y al instanciarlo obtendremos al jugador 1 y al 2.
//EMPIEZAN LAS VARIABLES DE CLASE

    private Cementerio cementerio;//El cementerio es donde van las cartas de criatura y edificacion que mueren.
    private CampoBatalla campobatalla;//El campo de batalla va a ser donde el jugador tenga desplegadas sus cartas de Criatura y de edificacion
    private Mazo mazo; //Este es el mazo del jugador
    private Mano mano; //Esta es la mano del jugador
    private final Raza raza; //La raza del jugador, va a ser una subclase de raza, pero la iniciamos como un objeto de Raza, y luego le haremos casting a una de sus subclases.
    private double vida;//Este determina la vida del jugador
    private int enemigo; // El jugador va a conocer a su enemigo, esto se necesita por ejemplo cuando vamos a atacarle
    private final int cartasmaximas; //Este va a ser el numero de cartas maximas que va a poder tener el jugador
    private Scanner escaner = new Scanner(System.in); //Este escaner lo vamos a utilizar para cuando haya que introducir datos en el juego.
    private Carta cartadefendiendo; //Este atributo va a guardar en cada momento si el jugador tiene una carta defendiendo y si es asi cual de ellas.
    private Carta cartaatacando; //Lo mismo que la cartadefendiendo pero con el ataque.
    private InfoPartida infopartida;//El jugador tiene asociada su interfaz gráfica con todos los datos de la partida y esta se va a ir actualizando, ocultando y mostrando, pero nunca eliminando.
    private UsarCarta usarcarta; //Esta es la interfaz donde se elige qué carta se va a utilizar, cada jugador tiene una asociada
    private AccionesJugador accionesjugador;//Esta es la interfaz donde se elige qué se va a hacer, cada jugador tiene una asociada
    private Atacar atacar;//Esta es la interfaz donde se elige con qué carta atacar, cada jugador tiene una asociada
    private Defender defender;//Esta es la interfaz donde se elige con qué carta defender, cada jugador tiene una asociada
    private Boolean suturno;//Determina si es el turno del jugador
    private Boolean canuse;//Determina si, en el caso de que sea el turno del jugador, puede usar una carta
    private Boolean canattack;//Determina si, en el caso de que sea el turno del jugador, puede atacar
    private String nombre;//El nombre del jugador
    private DescartarCarta descartar;//La interfaz para descartarse de una carta
    private UsarEncantamiento usarencantamiento;//La interfaz para utilizar un encantamiento (nos da las opciones de qué cartas encantar)
    private UsarConjuro usarconjuro;//La interfaz para utilizar un conjuro (nos da las opciones de qué cartas conjurar)
    private Tablero tablero;//El tablero que tiene el jugador asociado
    private final int numjug;//Este es el número del jugador, determinará quien es su enemigo
//TERMINAN LAS VARIABLES DE CLASE
    
//EMPIEZAn LOS CONSTRUCTORES DE LA CLASE
    public Jugador() throws GetCartaException, MazoVacio{
        this(false, "jugadornuevo", 10, new Tablero());
        
    }
    
    public Jugador(Tablero tablero) throws GetCartaException, MazoVacio {
        this(false, "jugadornuevo", 10, tablero);
    }

    public Jugador(Boolean suturno, String nombre, int numjug, Tablero tablero) throws MazoVacio, GetCartaException {//Constructor de la clase
        this.nombre = nombre;
        this.suturno = suturno;
        this.numjug = numjug;
        this.tablero = tablero;
        //constructor de la raza
        int r = (int) (Utilidad.random(3)); //Generamos un numero entero aleatorio entre 1 y 3 para determinar la raza del jugador.
        if (r == 1) { //Dado el entero aleatorio hacemos que la variable raza pase a ser de tipo humano, elfo o enano. Como estas clases las vamos a poner con variables fijas el jugador va a importar los datos correspondientes en el constructor.
            raza = new Humano();
        } else {
            if (r == 2) {
                raza = new Elfo();
            } else {
                raza = new Enano();
            }
        }
        vida = raza.getvidaInicial();//constructor de la vida
        cementerio = new Cementerio(this);//constructor del cementerio
        campobatalla = new CampoBatalla(this);//constructor del campo de batalla
        mano = new Mano(this);//constructor de la mano
        mazo = new Mazo(this);//constructor del mazo
        robarinicial();
        if (numjug == 0) {
            enemigo = 1;
        } else {
            enemigo = 0;
        }
        cartasmaximas = raza.getCartasMaximas();//cogemos el valor de las cartas maximas que podemos tener en la mano de nuestra raza.
        cartadefendiendo = null;//al principio no tendremos ninguna carta defendiendo
        cartaatacando = null;//al principio no tendremos ninguna carta ataque
        this.usarcarta = new UsarCarta(this);
        this.atacar = new Atacar(this);
        this.defender = new Defender(this);
        this.infopartida = null;
        this.accionesjugador = new AccionesJugador(this);
        this.descartar = new DescartarCarta(this);
        this.usarconjuro = null;
        this.usarencantamiento = null;
        this.canuse = true;
        this.canattack = true;
    }
//TERMINA EL CONSTRUCTOR DE LA CLASE
    
//EMPIEZAN LOS SETTER GETTER
    public Object getNumJug() {
        return numjug;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getSuTurno() {
        return suturno;
    }

    public void setSuTurno(Boolean suTurno) {
        this.suturno = suTurno;
    }

    public void setCementerio(Cementerio cementerio) {
        this.cementerio = cementerio;
    }

    public Cementerio getCementerio() {
        return cementerio;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getVida() {
        return vida;
    }

    public void setCampoBatalla(CampoBatalla campobatalla) {
        this.campobatalla = campobatalla;
    }

    public CampoBatalla getCampoBatalla() {
        return campobatalla;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public int getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(int enemigo) {
        this.enemigo = enemigo;
    }

    public Raza getRaza() {
        return this.raza;
    }

    public String printRaza() {
        return raza.getClass().getSimpleName();
    }

    public void setCartaDefendiendo(Carta cartadefendiendo) {
        this.cartadefendiendo = cartadefendiendo;
    }

    public Carta getCartaDefendiendo() {
        return cartadefendiendo;
    }

    public int getCartasMaximas() {
        return cartasmaximas;
    }

    public Carta getCartaAtacando() {
        return cartaatacando;
    }

    public void setCartaAtacando(Carta cartaatacando) {
        this.cartaatacando = cartaatacando;
    }

    public InfoPartida getInfoPartida() {
        return infopartida;
    }

    public void setInfoPartida(InfoPartida infopartida) {
        this.infopartida = infopartida;
    }

    public UsarCarta getUsarCarta() {
        return usarcarta;
    }

    public void setUsarCarta(UsarCarta usarcarta) {
        this.usarcarta = usarcarta;
    }

    public Atacar getAtacar() {
        return this.atacar;
    }

    public void setAtacar(Atacar atacar) {
        this.atacar = atacar;
    }

    public Defender getDefender() {
        return this.defender;
    }

    public void setDefender(Defender defender) {
        this.defender = defender;
    }

    public AccionesJugador getAccionesJugador() {
        return this.accionesjugador;
    }

    public void setAccionesJugador(AccionesJugador accionesjugador) {
        this.accionesjugador = accionesjugador;
    }

    public void setDescartar(DescartarCarta descartar) {
        this.descartar = descartar;
    }

    public DescartarCarta getDescartar() {
        return descartar;
    }

    public void setUsarEncantamiento(UsarEncantamiento usarencantamiento) {
        this.usarencantamiento = usarencantamiento;
    }

    public UsarEncantamiento getUsarEncantamiento() {
        return usarencantamiento;
    }

    public void setUsarConjuro(UsarConjuro usarenconjuro) {
        this.usarconjuro = usarenconjuro;
    }

    public UsarConjuro getUsarConjuro() {
        return usarconjuro;
    }

    public Boolean getCanUse() {
        return this.canuse;
    }

    public void setCanUse(Boolean booleano) {
        this.canuse = booleano;
    }

    public Boolean getCanAttack() {
        return this.canattack;
    }

    public void setCanAttack(Boolean booleano) {
        this.canattack = booleano;
    }

    public String toString(){
        return String.valueOf(this);
    }
    
    public void equal(Jugador jugador){
        if (this==jugador){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
    
//TERMINAN LOS SETTER GETTER


//AQUI EMPIEZAN LOS METODOS DE CLASE
    
    /**
    *Este método se ejecuta al final de cada turno por el jugador cuyo turno acaba de finalizar, y sirve para robar al final del turno
     * @throws laultimabatalladepoo.excepciones.MazoVacio
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void robar() throws MazoVacio, GetCartaException {//El metodo para robar una carta
        if (tablero.getJugador(enemigo).getMano().getArrayCartas().getLength() == 0) { //Primero cubrimos el caso en el que al jugador enemigo no le quedan cartas en la mano, solo podremos robar de nuestro mazo
            robardemazo(); //invocamos al metodo de la clase mazo para robar una carta de el
        } else { //Ahora cubrimos el caso en el que el enemigo tiene cartas
            boolean roboEnemigo = Utilidad.probabilidaduncuarto(); //Una funcion de probabilidad con 1/4 de robar al enemigo y 3/4 de robar de nuestro mazo
            if (roboEnemigo) { //si se cumple el booleano vamos a robar de la mano del enemigo
                robarManoEnemigo(); //Invocamos al metodo para robar la carta de la mano enemiga
            } else {
                try {
                    robardemazo();//invocamos al metodo de la clase mazo para robar una carta de el
                } catch (MazoVacio mv) {

                }

            }
        }
    }

    /**
    *Con este método el jugador roba una carta de su mazo
     * @throws laultimabatalladepoo.excepciones.MazoVacio
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void robardemazo() throws MazoVacio, GetCartaException {//Este metodo es por el cual robamos de nuestro propio mazo
        if (mazo.getArrayCartas().getLength() >= 1) { //Primero comprobamos que nuestro mazo no este vacio
            int i = Utilidad.random(mazo.getArrayCartas().getLength()) - 1;//Cogemos un numero aleatorio hasta la longitud del mazo
            Carta robada = mazo.getArrayCartas().getCarta(i);//La carta robada es la que esta en la posicion i aleatoria
            mano.getArrayCartas().addCarta(robada); //Añadimos la carta robada a nuestra mano
            mazo.getArrayCartas().removeCarta(i); //Quitamos la carta robada de nuestro mazo
            System.out.println(nombre + ", has robado la carta " + robada.getNombre() + " de tu mazo");
        } else {
            throw new MazoVacio();//Si esta vacio lanzamos la excepcion que hemos creado: Mazo vacio
        }
    }

    /**
    *Este método sirve para robar las cartas iniciales, las 7 con las que se empieza
     * @throws laultimabatalladepoo.excepciones.MazoVacio
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void robarinicial() throws MazoVacio, GetCartaException {//Este metodo es por el cual robamos de nuestro propio mazo
        for(int j=0;j<7;j++){
        if (mazo.getArrayCartas().getLength() >= 1) { //Primero comprobamos que nuestro mazo no este vacio
            int i = Utilidad.random(mazo.getArrayCartas().getLength()) - 1;//Cogemos un numero aleatorio hasta la longitud del mazo
            Carta robada = mazo.getArrayCartas().getCarta(i);//La carta robada es la que esta en la posicion i aleatoria
            mano.getArrayCartas().addCarta(robada); //Añadimos la carta robada a nuestra mano
            mazo.getArrayCartas().removeCarta(i); //Quitamos la carta robada de nuestro mazo
        } else {
            throw new MazoVacio();//Si esta vacio lanzamos la excepcion que hemos creado: Mazo vacio
        }
        }
        
    }
    
    /**
    *Este método hace que el jugador robe una carta de la mano enemiga
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void robarManoEnemigo() throws GetCartaException {//El metodo para robar una carta de la mano enemiga
        int i = (Utilidad.random(tablero.getJugador(enemigo).getMano().getArrayCartas().getLength()) - 1);//Primero cogemos el indice aleatorio
        Carta robada = tablero.getJugador(enemigo).getMano().getArrayCartas().getCarta(i);//Recuperamos la carta de esa posicion de la mano enemiga
        tablero.getJugador(enemigo).getMano().getArrayCartas().removeCarta(i); //AQUI ESTAMOS QUITANDO LA REFERENCIA A LA CARTA DE LA MANO DEL JUGADOR AL QUE SE ROBA
        mano.getArrayCartas().addCarta(robada); //AQUI ESTAMOS AÑADIENDO LA CARTA ROBADA A LA MANO DEL LADRON (EL JUGADOR QUE ROBA)
        robada.owner = this;//AQUI QUITAMOS LA REFERENCIA DE LA CARTA AL JUGADOR QUE LA PIERDE Y LE REFERENCIAMOS EL NUEVO PROPIETARIO
        System.out.println(nombre + ", has robado la carta " + robada.getNombre() + " de la mano enemiga");
    }

    /**
    *Este método va a hacer que el jugador ataque con la criatura seleccionada
     * @param cartaatacando
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    public void atacar(Carta cartaatacando) throws GetCartaException, GetPositionException, MazoVacio { //Este es el metodo con el que atacamos
        this.cartaatacando = cartaatacando; //Primero fijamos la carta que hemos seleccionado como la carta atacando
        tablero.getJugador(enemigo).setDefender(new Defender(tablero.getJugador(enemigo)));
        if (cartaatacando.getReino() == this.getRaza().getReino()) {//Si el reino de la carta es el mismo que el del propietario
            cartaatacando.setAtk(cartaatacando.getAtk() * (1.2));//multiplicamos por 1,2 el ataque y la vida a la hora de atacar
            cartaatacando.setVida(cartaatacando.getVida() * (1.2));
        }
        if (tablero.getJugador(enemigo).getCartaDefendiendo() == null) {//Ahora comprobamos si el enemigo tiene ya una carta defendiendo o no//En caso de que no tenga una carta defendiendo creamos un objeto de la clase Defender del enemigo
            if (tablero.getJugador(enemigo).getDefender().getTieneDefensa() == false) {//Si se da el caso de que no tiene cartas con las que defender
                tablero.getJugador(enemigo).setVida(tablero.getJugador(enemigo).getVida() - cartaatacando.getAtk());//Hacemos el computo de daño con el enemigo
                System.out.println(nombre + ", has atacado al jugador enemigo y este ha perdido " + String.valueOf(cartaatacando.getAtk()) + " de vida");
                if (cartaatacando.getReino() == cartaatacando.getOwner().getRaza().getReino()) {
                    cartaatacando.setAtk(cartaatacando.getAtk() / (1.2));//Reseteamos los valores del ataque y la vida cuando ha terminado el ataque
                    cartaatacando.setVida(cartaatacando.getVida() / 1.2);
                }
                findeturno();//Terminamos nuestro turno
                this.cartaatacando = null; //Ahora como ya ha terminado el ataque hacemos que el jugador ya no tenga cartas atacando.
            } else {//En el caso de que si tenga cartas con las que pueda defender
                tablero.getJugador(enemigo).setDefender(new Defender(tablero.getJugador(enemigo)));
                tablero.getJugador(enemigo).getDefender().getJFramePrincipal().setVisible(true);//Ponemos la interfaz grafica visible para que el enemigo elija con que carta defender
                accionesjugador.setVisible(false);
                tablero.getJugador(enemigo).getAccionesJugador().setVisible(false);
            
            }//El computo de daño se va a hacer en el metodo defender
        } else {
            cartaatacando.setVida(cartaatacando.getVida() - tablero.getJugador(enemigo).getCartaDefendiendo().getAtk());//Primero le quitamos a la carta atacando la vida
            tablero.getJugador(enemigo).getCartaDefendiendo().setVida(tablero.getJugador(enemigo).getCartaDefendiendo().getVida() - cartaatacando.getAtk());//Ahora le quitamos a la carta defendiendo la vida
            System.out.println(nombre + ", has atacado al jugador enemigo y su defensa ha perdido " + String.valueOf(cartaatacando.getAtk()) + " de vida");
            if (cartaatacando.getReino() == cartaatacando.getOwner().getRaza().getReino()) {
                cartaatacando.setAtk(cartaatacando.getAtk() / (1.2));//Reseteamos los valores del ataque y la vida cuando ha terminado el ataque
                cartaatacando.setVida(cartaatacando.getVida() / 1.2);
            }
            System.out.println(nombre + ", la carta con la que has atacado ahora tiene " + String.valueOf(cartaatacando.getVida()));
            if (cartaatacando.getVida() < 0) {
                cartaatacando.die();
            }
            
            this.cartaatacando = null; //Ahora como ya ha terminado el ataque hacemos que el jugador ya no tenga cartas atacando.
            if (tablero.getJugador(enemigo).getCartaDefendiendo().getVida() < 0) {
                tablero.getJugador(enemigo).getCartaDefendiendo().die();
            }
            tablero.getJugador(enemigo).setCartaDefendiendo(null); //Ahora como ya ha terminado el ataque hacemos que el jugador ya no tenga cartas atacando.
            findeturno();//Terminamos nuestro turno
        }

    }

    /**
    *Este método hace que el jugador ataque con una criatura y luego esta se ponga a defender para el próximo turno
     * @param cartaataca
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    public void atacarYDefender(Carta cartaataca) throws GetCartaException, GetPositionException, MazoVacio { //Este es el metodo por el cual una criatura puede atacar y defender
        atacar(cartaataca); //Primero invocamos al metodo atacar con la carta dada
        if (cartadefendiendo == null) { //Comprobamos si no hay cartadefendiendo para ver si esta criatura puede pasar a defender
            if (cartaataca.vida > 0) { //Si no hay otra carta defendiendo y nuestra critatura sigue viva
                cartadefendiendo = cartaataca;//Esta pasa a ser la carta que esta defendiendo para el proximo turno
                System.out.println("Has atacado y ahora esta criatura estará defendiendo el próximo turno");
            } else {
                System.out.println(nombre + ", como tu criatura ha morido en el intento no podrá defender en el siguiente turno");//Este es el caso en el que ataca y muere
            }
        } else {
            System.out.println(nombre + ", ya tenias una carta defendiendo, aun asi esta carta ha atacado");//Este es el caso en el que ataca pero ya habia una carta defendiendo
        }
    }

    /**
    *Este método es para cuando el jugador se defiende de un ataque con una edificación que tenía en el campo de batalla
     * @param cartadefendiendo
     * @throws laultimabatalladepoo.excepciones.GetCartaException
     * @throws laultimabatalladepoo.excepciones.GetPositionException
    */
    
    public void defender(Carta cartadefendiendo) throws GetCartaException, GetPositionException, MazoVacio { //Cogemos como entrada la carta que nos habra dado el jugador que se esta defendiendo para defenderse
        System.out.println(nombre + " se ha defendido con un/una " + cartadefendiendo.getNombre());
        tablero.getJugador(enemigo).getCartaAtacando().setVida(tablero.getJugador(enemigo).getCartaAtacando().getVida() - cartadefendiendo.getAtk());//Primero le quitamos a la carta atacando la vida
        cartadefendiendo.setVida(cartadefendiendo.getVida() - tablero.getJugador(enemigo).getCartaAtacando().getAtk());//Ahora le quitamos a la carta defendiendo la vida
        if (tablero.getJugador(enemigo).getCartaAtacando().getReino() == tablero.getJugador(enemigo).getRaza().getReino()) {//En el caso de que antes estuviese aumentado el daño y la vida de la carta ahora tenemos que resetearlos una vez hecho el computo de daño
            tablero.getJugador(enemigo).getCartaAtacando().setAtk(tablero.getJugador(enemigo).getCartaAtacando().getAtk() / 1.2);//Dividimos entre el valor que antes hemos multiplicado
            tablero.getJugador(enemigo).getCartaAtacando().setVida(tablero.getJugador(enemigo).getCartaAtacando().getVida() / 1.2);
        }
        System.out.println(tablero.getJugador(enemigo).getNombre() + ", has atacado al jugador "+nombre+ " y su defensa ha perdido " + String.valueOf(tablero.getJugador(enemigo).getCartaAtacando().getAtk()) + " de vida");
        System.out.println(tablero.getJugador(enemigo).getNombre() + ", la carta con la que has atacado ahora tiene " + String.valueOf(tablero.getJugador(enemigo).getCartaAtacando().getVida()));
        if (tablero.getJugador(enemigo).getCartaAtacando().getVida() <= 0) {//Comprobamos si la carta atacando ha bajado de 0 de vida y si es asi esta muere
            tablero.getJugador(enemigo).getCartaAtacando().die();
        }

        if (cartadefendiendo.getVida() <= 0) {//Comprobamos si la carta defendiendo ha bajado de 0 de vida y si es asi esta muere
            cartadefendiendo.die();
        }
        tablero.getJugador(enemigo).findeturno();//Terminamos nuestro turno
        tablero.getJugador(enemigo).setCartaAtacando(null);//Ahora hacemos que el jugador que ha atacado ya no tenga cartas atacando ya que el ataque ha terminado.
    }

    /**
    *Este método sirve para actualizar todas las interfaces de un jugador
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void actualizarinterfaces() throws GetCartaException {
        accionesjugador.dispose();//Primero nos deshacemos de todas las interfaces del jugador
        infopartida.dispose();
        atacar.getDialogoPrincipal().dispose();
        usarcarta.getJFramePrincipal().dispose();
        accionesjugador = new AccionesJugador(this);//Ahora las volvemos a crear, para que sus contenidos se actualicen
        infopartida = new InfoPartida(this);
        atacar = new Atacar(this);
        descartar = new DescartarCarta(this);
        usarcarta = new UsarCarta(this);
        tablero.getJugador(enemigo).getAccionesJugador().dispose();//Hacemos lo mismo para el enemigo
        tablero.getJugador(enemigo).getInfoPartida().dispose();
        tablero.getJugador(enemigo).getAtacar().getDialogoPrincipal().dispose();
        tablero.getJugador(enemigo).getUsarCarta().getJFramePrincipal().dispose();
        tablero.getJugador(enemigo).setAccionesJugador(new AccionesJugador(tablero.getJugador(enemigo)));
        tablero.getJugador(enemigo).setInfoPartida(new InfoPartida(tablero.getJugador(enemigo)));
        tablero.getJugador(enemigo).setAtacar(new Atacar(tablero.getJugador(enemigo)));
        tablero.getJugador(enemigo).setUsarCarta(new UsarCarta(tablero.getJugador(enemigo)));
        accionesjugador.setVisible(true);//Hacemos que su menú de opciones vuelva a ser visible
        tablero.getJugador(enemigo).getAccionesJugador().setVisible(true);//Hacemos que el menú de opciones del enemigo vuelva a ser visible

    }

    /**
    *Este método se ejecuta cada vez que se termina un turno, hace que el jugador robe, y se hacen comprobaciones necesarias, y actualizamos interfaces y el contador del tablero
     * @throws laultimabatalladepoo.excepciones.MazoVacio
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void findeturno() throws MazoVacio, GetCartaException {//Este método se ejecuta al final de turno
        tablero.setContador(tablero.getContador() + 1);//Se le suma uno al contador de turnos
        robar();//Se roba una carta
        if (raza instanceof Humano) {//Si eres un humano
            Boolean robo2 = Utilidad.probabilidaduncuarto();//Con probabilidad un cuarto
            if (robo2) {//Puedes robar una carta mas o no
                robar();
            }
        }
        suturno = false;//Deja de ser tu turno
        canattack = true;//Se resetean los atributos que determinan si en tu siguiente turno podrás atacar o usar
        canuse = true;
        tablero.getJugador(enemigo).setSuTurno(true);//Se inicia el turno del enemigo
        tengoquedescartar();//Se comprueba si tienes que descartarte de alguna carta
        System.out.println("------------------------------------------------------");
        System.out.println("Turno " + tablero.getContador());//Se imprime un indicador de el inicio del siguiente turno
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
    *Este método servirá para ver si el jugador tiene alguna carta que descartar, es decir, si tiene mas cartas de las que debería, y si ese es el caso, se le obligará a descartarse de cartas hasta estar dentro del límite permitido
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void tengoquedescartar() throws GetCartaException { //Este método comprueba si tenemos que descartar cartas y si es asi nos obliga
        if (mano.getArrayCartas().getLength() > cartasmaximas) {//Si tenemos mas cartas de las que debemos
            descartar = new DescartarCarta(this);//Creamos y aparece la interfaz para elegir qué carta descartar
            descartar.getJFramePrincipal().setVisible(true);//Hacemos que sea visible el menu para descartar una carta
            accionesjugador.setVisible(false);//Ocultamos los menus de opciones para obligar al jugador a que elija qué carta quiere descartar
            tablero.getJugador(enemigo).getAccionesJugador().setVisible(false);
        } else {//Si no nos pasamos del límite

            actualizarinterfaces();//Actualizamos las interfaces
            accionesjugador.setVisible(true);//Hacemos que el menú de acciones sea visible
            tablero.getJugador(enemigo).getAccionesJugador().setVisible(true);//Y el del enemigo también
        }
    }

//AQUI TERMINAN LOS METODOS DE CLASE
}
