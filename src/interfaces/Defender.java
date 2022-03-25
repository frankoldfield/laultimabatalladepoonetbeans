package interfaces;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.cartas.Carta;
import laultimabatalladepoo.cartas.Edificacion;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;
import laultimabatalladepoo.excepciones.MazoVacio;
/**
 *
 * @author frank
 */

public class Defender implements ActionListener {

//AQUI EMPIEZAN LOS ATRIBUTOS DE LA CLASE
    private Jugador owner;//El owner va a ser el jugador a quien vamos a dar la opcion de elegir con que carta ataca
    private JFrame jframeprincipal; //Este JFrame va a contener todo
    private Carta defensanueva;//Esta Carta va a ser iterada para ir siendo las edificaciones que pueden defender
    private JPopupMenu[] menu;//Este va a ser un array de menus popup, para ir creando el menu asociado al boton de cada criatura
    private JButton[] botones;//Este va a ser un array de botones, uno para cada carta que pueda defender del campo de batalla
    private JButton[] botonesdefensa;//Este va a ser un array de botones de defensa para cada menu de cada criatura
    private boolean tienedefensa;//Este booleano nos indicará si el jugador puede seleccionar alguna carta con la que defenderse
    private int longitud;//Esta es la longitud del campo de batalla del jugador
//AQUI TERMINAN LOS ATRIBUTOS DE LA CLASE

//AQUI EMPIEZAN LOS CONSTRUCTORES DE LA CLASE
    public Defender(Jugador owner) throws GetCartaException {
        setup(owner);
    }

    public void setup(Jugador owner) throws GetCartaException {
        tienedefensa = false;
        longitud = owner.getCampoBatalla().getArrayCartas().getLength();
        this.owner = owner;//Primero le asociamos el propietario a la interfaz grafica
        menu = new JPopupMenu[longitud];//Creamos el array de menus
        botones = new JButton[longitud];//Creamos el array de botones de cada criatura
        botonesdefensa = new JButton[longitud]; //Creamos el array de botones de defensa (mas tarde iremos asociando cada uno de estos con un elemento del array de menus)
        jframeprincipal = new JFrame();//Iniciamos el JFrame donde todo esto va a aparecer
        jframeprincipal.setTitle("Defender - " + owner.getNombre());
        jframeprincipal.setLayout(new GridLayout(10, 1, 10, 5));//Cambiamos el layout del JFrame a uno de rejilla con una columnas
        jframeprincipal.setMinimumSize(new Dimension(400, 400));
        jframeprincipal.setMaximumSize(new Dimension(450, 450));
        try {
            for (int i = 0; i <= owner.getCampoBatalla().getArrayCartas().getLength() - 1; i++) {//Con este bucle vamos a ir generando para cada carta su respectivo menu y botones
                if (owner.getCampoBatalla().getArrayCartas().getCarta(i) instanceof Edificacion && owner.getCampoBatalla().getArrayCartas().getCarta(i).getCanDef()) {//Si la carta en esta posicion del campodebatalla es una edificacion y puede defender se crea lo siguiente
                    tienedefensa = true;//Decimos que el jugador si puede defenderse
                    defensanueva = owner.getCampoBatalla().getArrayCartas().getCarta(i);//guardamos la edificación en la variable defensanueva (se va a ir sobreescribiendo cada vez que se produzca una instancia del for)
                    botones[i] = new JButton(defensanueva.getNombre() + " -  " + i);//Creamos un boton con el nombre de la edificación y la posicion en el nombre
                    botones[i].addActionListener(this);//Le añadimos un ActionListener
                    botones[i].setActionCommand("Opciones");//Su accion va a ser opciones, que nos desplega los botones atacar y atacar y defender
                    menu[i] = new JPopupMenu("Opciones");//Creamos el menu que contiene esos dos botones
                    botones[i].add(menu[i]);//Añadimos el menu al boton
                    botonesdefensa[i] = new JButton("Defender");//Creamos el boton de atacar
                    botonesdefensa[i].addActionListener(this);//Le damos un ActionListener
                    botonesdefensa[i].setActionCommand("Defender");//Le damos su accion
                    menu[i].add(botonesdefensa[i]);//Añadimos el boton al menu popup
                    jframeprincipal.add(botones[i]);//Le añadimos el boton que contiene el menu y lel botón de defender
                } else {//Si la carta de esa posicion no pudiese defender
                    botones[i] = null;//Esa posicion sera null
                    botonesdefensa[i] = null;//Esta tambien
                    menu[i] = null;//Esta tambien
                }
            }
        } catch (NullPointerException e) { //SI TENEMOS UN NULLPOINTEREXCEPTION QUERRA DECIR QUE NO TENEMOS CARTAS EN EL CAMPO DE BATALLA, O SEA QUE NO PODEMOS DEFENDER
            System.out.print("No tienes cartas nuevas para defender :(");
        }
    }
//AQUI TERMINAN LOS CONSTRUCTORES DE LA CLASE
    
//AQUI EMPIEZAN LOS METODOS DE LA CLASE
    public boolean getTieneDefensa() {
        return tienedefensa;
    }

    public JFrame getJFramePrincipal() {
        return jframeprincipal;
    }
//AQUI TERMINAN LOS METODOS DE LA CLASE
    
//AQUI EMPIEZAN LAS ACCIONES REALIZADAS POR LOS BOTONES
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Defender")) {//Si le damos a defender con una criatura
            try {
                int indice = 0;
                for (int i = 0; i <= longitud - 1; i++) {
                    if (botonesdefensa[i] == evt.getSource()) {//Recorremos el array de botones viendo cual es el que hemos pulsado
                        indice = i;//Guardamos la posicion del boton
                    }
                }
                owner.defender(owner.getCampoBatalla().getArrayCartas().getCarta(indice));//Defendemos con la carta de esa posición
                owner.actualizarinterfaces();
            } catch (GetCartaException | GetPositionException ex) {
                throw new UnsupportedOperationException("Algo ha ido mal :/");
            } catch (MazoVacio ex) {
                Logger.getLogger(Defender.class.getName()).log(Level.SEVERE, null, ex);
            }
            owner.getAccionesJugador().setVisible(true);
            owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(true);
            jframeprincipal.dispose();//Borramos esta ventana
        }

        if (evt.getActionCommand().equals("Opciones")) {//Si le damos al botón de una edificacion que puede defender
            int indice = 0;
            for (int i = 0; i <= longitud - 1; i++) {
                if (botones[i] == evt.getSource()) {
                    indice = i;//Vemos qué posición era de una forma igual a la acción anterior
                }
            }
            menu[indice].show(botones[indice], 0, 0);//Aqui lo que hacemos es fijar el popupmenu a la posicion del boton pulsado

        }
    }
//AQUI TERMINAN LAS ACCIONES REALIZADAS POR LOS BOTONES
}
