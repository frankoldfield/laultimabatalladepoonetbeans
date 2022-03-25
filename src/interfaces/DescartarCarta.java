package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */
public class DescartarCarta implements ActionListener {

    private Jugador owner;//El owner va a ser el jugador a quien vamos a dar la opcion de elegir de qué carta se descarta
    private JDialog jDialogPrincipal; //Este JFrame va a contener todo
    private Carta cartanueva;//Esta Carta va a ser iterada para ir siendo las criaturas que pueden ser descartadas
    private JButton[] botones;//Este va a ser un array de botones, uno para cada carta en la mano
    private int longitud;

    public DescartarCarta(Jugador owner) throws GetCartaException {
        setup(owner);
        jDialogPrincipal.setTitle("Descartar carta - " + owner.getNombre());
    }

    public void setup(Jugador owner) throws GetCartaException {
        this.owner = owner;//Primero le asociamos el propietario a la interfaz grafica
        longitud = owner.getMano().getArrayCartas().getLength();
        botones = new JButton[longitud];//Creamos el array de botones de cada carta de la mano
        jDialogPrincipal = new JDialog();//Iniciamos el JFrame donde todo esto va a aparecer
        jDialogPrincipal.setMinimumSize(new Dimension(400, 400));//Cambiamos el tamaño de la ventana
        jDialogPrincipal.setMaximumSize(new Dimension(450, 450));
        JLabel subtitulo = new JLabel("Elige, qué carta quieres descartar?");
        subtitulo.setForeground(Color.BLACK);
        JPanel panel = new JPanel();//Creamos el panel donde va a estar todo
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(0, 1));//Cambiamos el layout del JFrame a uno de rejilla con una columnas
        panel.add(subtitulo);
        jDialogPrincipal.add(panel, BorderLayout.CENTER);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        for (int i = 0; i <= (longitud - 1); i++) {//Con este bucle vamos a ir generando para cada carta. El menos 1 es porque si no recorreriamos una posicion de mas, ya que el valor de longitud siempre va a ser una unidad mas que el de posicion
            cartanueva = owner.getMano().getArrayCartas().getCarta(i);//guardamos la carta en la variable cartanueva (se va a ir sobreescribiendo cada vez que se produzca una instancia del for)
            botones[i] = new JButton(cartanueva.getNombre() + " -  " + i);//Creamos un boton con el nombre de la carta y la posicion en el nombre
            botones[i].setForeground(Color.BLACK);
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setBorder(compound);
            botones[i].addActionListener(this);//Le añadimos un ActionListener
            botones[i].setActionCommand("Descartar");//Su accion va a ser descartar
            panel.add(botones[i]);//Le añadimos el boton al panel principal

        }
    }

    public JDialog getJFramePrincipal() {
        return jDialogPrincipal;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Descartar")) {//Si pulsamos el botón de una carta
            try {
                int indice = 0;
                for (int i = 0; i <= longitud - 1; i++) {
                    if (botones[i] == evt.getSource()) {//Se guarda el índice del botón pulsado
                        indice = i;
                    }
                }
                owner.getMano().getArrayCartas().getCarta(indice).desaparecer();//Y se hace desaparecer la carta de ese índice
                jDialogPrincipal.dispose();//Se borra este diálogo
                owner.actualizarinterfaces();//Se actualizan las interfaces
                if (owner.getMano().getArrayCartas().getLength() > owner.getCartasMaximas()) {//Si sigue estando por encima del límite será porque el jugador es un humano y ha robado 2 cartas y tiene 2 mas que el límite
                    owner.setDescartar(new DescartarCarta(owner));//Volvemos a hacer la interfaz para descartarnos de una carta
                    owner.getDescartar().getJFramePrincipal().setVisible(true);//Lo ponemos visible
                    owner.getAccionesJugador().setVisible(false);//Le quitamos la visibilidad al menú de acciones del jugador y su enemigo
                    owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(false);
                } else {
                    owner.actualizarinterfaces();//Si eso no pasa actualizamos las interfaces
                }
            } catch (GetCartaException | GetPositionException ex) {
                throw new UnsupportedOperationException("Algo ha ido mal :/");
            } finally {

            }
        }
    }
}
