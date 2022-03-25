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

public class UsarEncantamiento implements ActionListener {

    private Jugador owner;//El owner va a ser el jugador a quien vamos a dar la opcion de elegir a qué carta encanta
    private JDialog jDialogPrincipal; //Este JFrame va a contener todo
    private Carta cartanueva;//Esta Carta va a ser iterada para ir siendo las criaturas que pueden ser encantadas
    private JButton[] botones;//Este va a ser un array de botones, uno para cada criatura del campo de batalla
    private int longitud;//Esta va a ser la longitud  del campo de batalla del jugador
    private Carta encantamiento;//Esta es la carta que va a encantar

    public UsarEncantamiento(Jugador owner, Carta encantamiento) throws GetCartaException {
        setup(owner, encantamiento);//Hacemos el setup con el propietario y con el encantamiento que hemos seleccionado
        jDialogPrincipal.setTitle("Encantar carta - " + owner.getNombre());//El título indica que este es el diálogo para encantar una carta
    }

    public void setup(Jugador owner, Carta encantamiento) throws GetCartaException {
        this.encantamiento = encantamiento;//Construimos el encantamiento
        this.owner = owner;//Primero le asociamos el propietario a la interfaz grafica
        longitud = owner.getCampoBatalla().getArrayCartas().getLength();//Cogemos la longitud del campo de batalla del propietario
        botones = new JButton[longitud];//Creamos el array de botones de cada criatura
        jDialogPrincipal = new JDialog();//Iniciamos el JFrame donde todo esto va a aparecer
        jDialogPrincipal.setMinimumSize(new Dimension(400, 400));//Modificamos el tamaño de la ventana
        jDialogPrincipal.setMaximumSize(new Dimension(450, 450));
        JLabel subtitulo = new JLabel("Elige qué carta quieres encantar");
        subtitulo.setForeground(Color.BLACK);//Con esto podemos modificar el aspecto del subtitulo
        JPanel panel = new JPanel();//Este es el panel donde va a estar contenido todo
        panel.setBackground(Color.WHITE);//Ponemos el fondo de color blanco
        panel.setLayout(new GridLayout(0, 1));//Cambiamos el layout del JFrame a uno de rejilla con una columna 
        panel.add(subtitulo);//Añadimos el subtítulo al panel que lo contiene todo
        jDialogPrincipal.add(panel, BorderLayout.CENTER);//Le añadimos el panel a la ventana
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);//Cambiamos el tipo de vorde para luego ir poniendoselo a los botones y que sea mas agradable a la vista
        for (int i = 0; i <= (longitud - 1); i++) {//Con este bucle vamos a ir generando para cada boton para cada criatura.El menos 1 es porque si no recorreriamos una posicion de mas, ya que el valor de longitud siempre va a ser una unidad mas que el de posicion
            cartanueva = owner.getCampoBatalla().getArrayCartas().getCarta(i);//guardamos la criatura en la variable criatura nueva (se va a ir sobreescribiendo cada vez que se produzca una instancia del for)
            botones[i] = new JButton(cartanueva.getNombre() + " -  " + i);//Creamos un boton con el nombre de la criatura y la posicion en el nombre
            botones[i].setForeground(Color.BLACK);
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setBorder(compound);//Cambiamos el estilo del botón
            botones[i].addActionListener(this);//Le añadimos un ActionListener
            botones[i].setActionCommand("Encantar");//Su accion va a ser que se encante esa criatura
            panel.add(botones[i]);//Le añadimos el boton al panel que los contiene a todos
        }
    }

    public JDialog getJFramePrincipal() {//Esto nos servirá para ir cambiando la visibilidad de la ventana
        return jDialogPrincipal;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Encantar")) {
            try {
                int indice = 0;//Iniciamos el índice
                for (int i = 0; i <= longitud - 1; i++) {//Recorremos todo el campo de batalla
                    if (botones[i] == evt.getSource()) {//Si el boton de la posicion i es el que se ha pulsado
                        indice = i;//Guardamos la posicion i
                    }
                }
                encantamiento.use(owner.getCampoBatalla().getArrayCartas().getCarta(indice));//Y hacemos que se encante a la carta de la posicion i
                owner.actualizarinterfaces();//Actualizamos las interfaces
                owner.getAccionesJugador().setVisible(true);//Ponemos visibles de nuevo las acciones del jugador
                owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(true);
            } catch (GetCartaException | GetPositionException ex) {
                throw new UnsupportedOperationException("Error en UsarEncantamiento al pulsar un botón de criatura a la que encantar");
            } finally {
                jDialogPrincipal.dispose();
            }
        }
    }
}
