package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.cartas.Carta;
import laultimabatalladepoo.cartas.Conjuro;
import laultimabatalladepoo.cartas.Criatura;
import laultimabatalladepoo.cartas.Encantamiento;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;
import laultimabatalladepoo.excepciones.MazoVacio;
import laultimabatalladepoo.razas.Elfo;
import laultimabatalladepoo.razas.Enano;
import laultimabatalladepoo.razas.Humano;

/**
 *
 * @author frank
 */

public class UsarCarta implements ActionListener {

    private Jugador owner;//El owner va a ser el jugador a quien vamos a dar la opcion de elegir qué carta utiliza
    private JDialog jDialogPrincipal; //Este JDialog va a contener todo
    private Carta cartanueva;//Esta Carta va a ser iterada para ir siendo las cartas que se pueden utilizar
    private JButton[] botones;//Este va a ser un array de botones, uno para cada carta en la mano del jugador
    private int longitud;
    private int longitudcampo;

    public UsarCarta(Jugador owner) throws GetCartaException {
        setup(owner);
        jDialogPrincipal.setTitle("Usar carta - " + owner.getNombre());
    }

    public void setup(Jugador owner) throws GetCartaException {
        this.owner = owner;//Primero le asociamos el propietario a la interfaz grafica
        longitud = owner.getMano().getArrayCartas().getLength();//Cogemos la longitud de la mano
        longitudcampo = owner.getCampoBatalla().getArrayCartas().getLength();
        botones = new JButton[longitud];//Creamos el array de botones de cada carta de la mano
        jDialogPrincipal = new JDialog();//Iniciamos el JFrame donde todo esto va a aparecer
        jDialogPrincipal.setMinimumSize(new Dimension(400, 400));
        jDialogPrincipal.setMaximumSize(new Dimension(450, 450));
        JLabel subtitulo = new JLabel("Elige qué carta quieres utilizar?");
        subtitulo.setForeground(Color.BLACK);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(0, 1));//Cambiamos el layout del JFrame a uno de rejilla con una columna
        panel.add(subtitulo);
        jDialogPrincipal.add(panel, BorderLayout.CENTER);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        for (int i = 0; i <= (longitud - 1); i++) {//Con este bucle vamos a ir generando para cada carta su boton. El menos 1 es porque si no recorreriamos una posicion de mas, ya que el valor de longitud siempre va a ser una unidad mas que el de posicion
            cartanueva = owner.getMano().getArrayCartas().getCarta(i);//guardamos la carta en la variable carta nueva (se va a ir sobreescribiendo cada vez que se produzca una instancia del for)
            botones[i] = new JButton(cartanueva.getNombre() + " -  " + i);//Creamos un boton con el nombre de la carta y la posicion en el nombre
            botones[i].setForeground(Color.BLACK);//Le cambiamos el estilo a los botones para que sean mas agradables
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setBorder(compound);
            botones[i].addActionListener(this);//Le añadimos un ActionListener
            botones[i].setActionCommand("Utilizar");//Su accion va a ser utilizar, que dependiendo del tipo de carta hará una cosa u otra
            panel.add(botones[i]);//Le añadimos el boton al panel principal

        }
    }

    public JDialog getJFramePrincipal() {
        return jDialogPrincipal;
    }

    public void terminardeusar() throws MazoVacio, GetCartaException {
        if (owner.getCanAttack() == false) {//Si el jugador puede usar pero ya no puede atacar es porque es un elfo que ha usado una vez, por lo que ponemos que no es su turno y restablecemos el atributo canuse a true para que cuando sea su turno pueda usar y lo mismo con canattack
            owner.findeturno();
        }
        if (owner.getRaza() instanceof Elfo && owner.getSuTurno() == true) {//Si el jugador es un elfo le quitamos la oportunidad para atacar pero puede usar una carta mas
            owner.setCanAttack(false);//Ya no podrá atacar pero si podrá utilizar otra carta
            owner.actualizarinterfaces();//Actualizamos los datos de las interfaces
        }
        if (owner.getRaza() instanceof Enano) {//Si el jugador es un enano no puede usar mas cartas pero puede atacar
            owner.setCanUse(false);
            owner.actualizarinterfaces();
        }
        if (owner.getRaza() instanceof Humano) {//Si el jugador es humano simplemente decimos que no es su turno y no podra hacer nada mas
            owner.findeturno();
        }
        owner.getAccionesJugador().setVisible(true);//Volvemos a darle visibilidad a los menús de acciones
        owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Utilizar")) {//Si hemos utilizado un botón
            if (owner.getSuTurno() && owner.getCanUse()) {
                try {

                    int indice = 0;
                    for (int i = 0; i <= longitud - 1; i++) {
                        if (botones[i] == evt.getSource()) {//Recuperamos su índice recorriendo todo el array
                            indice = i;
                        }
                    }
                    if (owner.getMano().getArrayCartas().getCarta(indice) instanceof Encantamiento) {//Si es un encantamiento
                        try {
                            boolean haycriaturas = false;//Iniciamos la variable haycriaturas
                            for (int i = 0; i <= (longitudcampo); i++) {
                                if (owner.getCampoBatalla().getArrayCartas().getCarta(i) instanceof Criatura) {//Si en el campo de batalla del jugador se encuentra alguna criatura
                                    haycriaturas = true;//Entonces decimos que hay criaturas
                                }
                            }
                            if (haycriaturas) {//Si hay criaturas
                                owner.setUsarEncantamiento(new UsarEncantamiento(owner, owner.getMano().getArrayCartas().getCarta(indice)));//Cambiamos el menú de usar encantamiento del jugador al asociado a este
                                owner.getUsarEncantamiento().getJFramePrincipal().setVisible(true);//Lo hacemos visible
                                terminardeusar();//Terminamos de usar la carta
                                owner.getAccionesJugador().setVisible(false);//Quitamos visibilidad a las opciones
                                owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(false);
                            } else {//Si no hay criaturas a las que encantar simplemente lo decimos y el jugador puede elegir otra carta que utilizar
                                System.out.println("No hay criaturas a las que encantar!");
                            }
                        } catch (GetCartaException ex) {
                            System.out.println("No hay cartas a las que encantar!");
                        }
                    } else {
                        if (owner.getMano().getArrayCartas().getCarta(indice) instanceof Conjuro) {//Si la carta es un conjuro diréctamente actualizamos e invocamos la interfaz de conjuro
                            owner.setUsarConjuro(new UsarConjuro(owner, owner.getMano().getArrayCartas().getCarta(indice)));//Ya que siempre va a tener alguna opción para conjurar
                            owner.getUsarConjuro().getJFramePrincipal().setVisible(true);
                            terminardeusar();
                            owner.getAccionesJugador().setVisible(false);
                            owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(false);
                        } else {//Si tampoco fuese un conjuro entonces sería una criatura o una edificación, simplemente la usamos (la colocamos en el campo de batalla)
                            owner.getMano().getArrayCartas().getCarta(indice).use();
                            terminardeusar();//Y finalizamos
                            owner.actualizarinterfaces();
                            owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(true);
                            owner.getAccionesJugador().setVisible(true);
                        }
                    }
                } catch (GetCartaException | GetPositionException ex) {
                    throw new UnsupportedOperationException("Algo ha ido mal :/");
                } catch (MazoVacio ex) {
                    Logger.getLogger(UsarCarta.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    jDialogPrincipal.dispose();
                }
            } else {//Si no fuese el turno del jugador o no pudiese utilizar mas cartas entonces imprimiríamos esto
                System.out.println(owner.getNombre() + ", no es tu turno!");
            }

        }
    }
}
