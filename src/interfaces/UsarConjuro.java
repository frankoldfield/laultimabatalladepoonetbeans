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
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

public class UsarConjuro implements ActionListener {

    private Jugador owner;//El owner va a ser el jugador a quien vamos a dar la opcion de elegir a qué conjura
    private JDialog jDialogPrincipal; //Este JFrame va a contener todo
    private JButton botonjugador;//Este será el botón para conjurar al jugador enemigo
    private Carta cartanueva;//Esta Carta va a ser iterada para ir siendo las criaturas a las que puedes conjurar
    private JButton[] botones;//Este va a ser un array de botones, uno para cada criatura del campo de batalla
    private int longitud;
    private Carta conjuro;

    public UsarConjuro(Jugador owner, Carta conjuro) throws GetCartaException {
        setup(owner, conjuro);
        jDialogPrincipal.setTitle("Conjurar carta - " + owner.getNombre());
    }

    public void setup(Jugador owner, Carta conjuro) throws GetCartaException {
        this.conjuro = conjuro;//Construimos el conjuro
        this.owner = owner;//Primero le asociamos el propietario a la interfaz grafica
        longitud = owner.getTablero().getJugador(owner.getEnemigo()).getCampoBatalla().getArrayCartas().getLength();
        botones = new JButton[longitud];//Creamos el array de botones de cada criatura
        jDialogPrincipal = new JDialog();//Iniciamos el JFrame donde todo esto va a aparecer
        jDialogPrincipal.setMinimumSize(new Dimension(400, 400));//Modificamos el tamaño de la ventana
        jDialogPrincipal.setMaximumSize(new Dimension(450, 450));
        JLabel subtitulo = new JLabel("Elige a qué carta quieres conjurar");
        subtitulo.setForeground(Color.BLACK);//Con esto podemos modificar el aspecto del subtitulo
        JPanel panel = new JPanel();//Este es el panel donde va a estar contenido todo
        panel.setBackground(Color.WHITE);//Ponemos el fondo de color blanco
        panel.setLayout(new GridLayout(0, 1));//Cambiamos el layout del JFrame a uno de rejilla con una columnas
        panel.add(subtitulo);//Añadimos el subtítulo al panel que lo contiene todo
        jDialogPrincipal.add(panel, BorderLayout.CENTER);//Le añadimos el panel a la ventana
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);//Cambiamos el tipo de vorde para luego ir poniendoselo a los botones y que sea mas agradable a la vista
        botonjugador = new JButton("Conjurar al jugador enemigo");//Creamos un boton para dar la opcion de conjurar al enemigo directamente
        botonjugador.setForeground(Color.BLACK);//Cambiamos el aspecto de ese boton para que sea mas agradable a la vista
        botonjugador.setBackground(Color.LIGHT_GRAY);
        botonjugador.setBorder(compound);
        botonjugador.addActionListener(this);//Le añadimos un ActionListener
        botonjugador.setActionCommand("Conjurarjugador");
        panel.add(botonjugador);//Le añadimos ese boton al panel principal
        for (int i = 0; i <= (longitud - 1); i++) {//Con este bucle vamos a ir generando para cada criatura a la que podemos conjurar.El menos 1 es porque si no recorreriamos una posicion de mas, ya que el valor de longitud siempre va a ser una unidad mas que el de posicion
            cartanueva = owner.getTablero().getJugador(owner.getEnemigo()).getCampoBatalla().getArrayCartas().getCarta(i);//guardamos la criatura en la variable criatura nueva (se va a ir sobreescribiendo cada vez que se produzca una instancia del for)
            botones[i] = new JButton(cartanueva.getNombre() + " -  " + i);//Creamos un boton con el nombre de la criatura y la posicion en el nombre
            botones[i].setForeground(Color.BLACK);
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setBorder(compound);//Le cambiamos el aspecto para que sea mas agradable
            botones[i].addActionListener(this);//Le añadimos un ActionListener
            botones[i].setActionCommand("Conjurar");//Su accion va a ser opciones, que nos desplega los botones atacar y atacar y defender
            panel.add(botones[i]);//Le añadimos el boton al panel que los contiene a todos
        }
    }

    public JDialog getJFramePrincipal() {
        return jDialogPrincipal;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Conjurar")) {
            try {

                int indice = 0;//Iniciamos el indice
                for (int i = 0; i <= longitud - 1; i++) {//Recorremos todo el campo de batalla
                    if (botones[i] == evt.getSource()) {//Si el boton de la posicion i es el que se ha pulsado
                        indice = i;//Guardamos la posicion i
                    }
                }
                conjuro.use(owner.getTablero().getJugador(owner.getEnemigo()).getCampoBatalla().getArrayCartas().getCarta(indice));//Y hacemos que se conjure a la carta de la posicion i
                owner.actualizarinterfaces();//Actualizamos las interfaces
                owner.getAccionesJugador().setVisible(true);//Ponemos visibles de nuevo las acciones del jugador
                owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(true);
            } catch (GetCartaException | GetPositionException ex) {
                throw new UnsupportedOperationException("Algo ha ido mal :/");
            } finally {
                jDialogPrincipal.dispose();
            }
        }
        if (evt.getActionCommand().equals("Conjurarjugador")) {//Esta accion es para si se va a conjurar al enemigo directamente
            try {
                conjuro.use();//LLamamos al metodo sin ningun argumento (Para esto habiamos hecho sobrecarga de métodos)
                owner.actualizarinterfaces();
            } catch (GetCartaException | GetPositionException ex) {
                System.out.println("Error en el botón conjurarjugador de usarconjuro");
            }
            
            owner.getAccionesJugador().setVisible(true);//Ponemos visibles las interfaces
            owner.getTablero().getJugador(owner.getEnemigo()).getAccionesJugador().setVisible(true);
            jDialogPrincipal.dispose();//Borramos esta ventana
        }
    }
}
