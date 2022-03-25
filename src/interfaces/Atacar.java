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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.cartas.Carta;
import laultimabatalladepoo.cartas.Criatura;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;
import laultimabatalladepoo.excepciones.MazoVacio;
/**
 *
 * @author frank
 */

public class Atacar implements ActionListener {
//AQUI EMPIEZAN LOS ATRIBUTOS
    private Jugador owner;//El owner va a ser el jugador a quien vamos a dar la opcion de elegir con que carta ataca
    private JDialog dialogoprincipal; //Este JFrame va a contener todo
    private Carta criaturanueva;//Esta Carta va a ser iterada para ir siendo las criaturas que pueden atacar, se va a ir copiando su informacion a los nuevos botones
    private JPopupMenu[] menu;//Este va a ser un array de menus popup, para ir creando el menu asociado al boton de cada criatura
    private JButton[] botones;//Este va a ser un array de botones, uno para cada criatura del campo de batalla
    private JButton[] botonesataque;//Este va a ser un array de botones de ataque para cada menu de cada criatura
    private JButton[] botonesataqueydefensa;//Este va a ser un array de botones de ataqueydefensa para cada menu de cada criatura
    private int longitud;//Esta longitud nos va a decir cuantas cartas tenemos en el campo de batalla
//AQUI TERMINAN LOS ATRIBUTOS
    
//AQUI EMPIEZAN LOS CONSTRUCTORES
    public Atacar(Jugador owner) throws GetCartaException {
        setup(owner);//Llamamos al método setup
        
    }

    public void setup(Jugador owner) throws GetCartaException {
        this.owner = owner;//Primero le asociamos el propietario a la interfaz grafica
        longitud = owner.getCampoBatalla().getArrayCartas().getLength(); //Ahora cogemos cuantas cartas tiene el propietario en el campo de batalla
        menu = new JPopupMenu[longitud];//Creamos el array de menus
        botones = new JButton[longitud];//Creamos el array de botones de cada criatura
        botonesataque = new JButton[longitud]; //Creamos el array de botones de ataque (mas tarde iremos asociando cada uno de estos con un elemento del array de menus)
        botonesataqueydefensa = new JButton[longitud];//Igual que con los botones de ataque
        dialogoprincipal = new JDialog();//Iniciamos el JFrame donde todo esto va a aparecer
        dialogoprincipal.setMinimumSize(new Dimension(400, 400)); //cambiamos el tamaño mínimo y máximo del diálogo
        dialogoprincipal.setMaximumSize(new Dimension(450, 450));
        dialogoprincipal.setTitle("Atacar - " + owner.getNombre());//Cambiamos el nombre del diálogo para que contenga el del jugador que realiza la acción
        JLabel subtitulo = new JLabel("Elige con qué carta quieres atacar!"); //Un pequeño subtítulo que indica qué debes hacer en esta ventana
        subtitulo.setForeground(Color.BLACK);//cambiamos el color para que sea algo mas agradable a la vista
        JPanel panel = new JPanel();//Iniciamos el panel que va a contener las distintas cartas con las que se puede atacar
        panel.setBackground(Color.WHITE);//Con este ajuste cambiamos el color de fondo del panel
        panel.setLayout(new GridLayout(0, 1));//Cambiamos el layout del JFrame a uno de rejilla con dos columnas
        panel.add(subtitulo);//Añadimos el subtítulo al panel
        dialogoprincipal.add(panel, BorderLayout.CENTER);//Añadimos el panel a la ventana entera
        Border line = new LineBorder(Color.BLACK);//Creamos una nueva linea de borde que luego añadiremos a los botones
        Border margin = new EmptyBorder(5, 15, 5, 15);//Aqui el margen del borde
        Border compound = new CompoundBorder(line, margin);//Creamos un borde con las dos componentes que acabo de mencionar
        for (int i = 0; i <= (owner.getCampoBatalla().getArrayCartas().getLength() - 1); i++) {//Con este bucle vamos a ir generando para cada criatura su respectivo menu y botones //El menos 1 es porque si no recorreriamos una posicion de mas, ya que el valor de longitud siempre va a ser una unidad mas que el de posicion
            if (owner.getCampoBatalla().getArrayCartas().getCarta(i) instanceof Criatura) {//Si la carta en esta posicion del campodebatalla es una criatura se crea lo siguiente
                criaturanueva = owner.getCampoBatalla().getArrayCartas().getCarta(i);//guardamos la criatura en la variable criatura nueva (se va a ir sobreescribiendo cada vez que se produzca una instancia del for)
                botones[i] = new JButton(criaturanueva.getNombre() + " -  " + i);//Creamos un boton con el nombre de la criatura y la posicion en el nombre
                botones[i].setForeground(Color.BLACK);//Hacemos que el texto de los botones sea negro
                botones[i].setBackground(Color.LIGHT_GRAY);//Hacemos que el color del fondo de los botones sea gris, mas agradable a la vista
                botones[i].setBorder(compound);//Le ponemos el borde que hemos creado antes a cada boton
                botones[i].addActionListener(this);//Le añadimos un ActionListener
                botones[i].setActionCommand("Opciones");//Su accion va a ser opciones, que nos desplega los botones atacar y atacar y defender
                menu[i] = new JPopupMenu("Opciones");//Creamos el menu que contiene esos dos botones
                botones[i].add(menu[i]);//Añadimos el menu al boton
                botonesataque[i] = new JButton("Atacar");//Creamos el boton de atacar
                botonesataque[i].addActionListener(this);//Le damos un ActionListener
                botonesataque[i].setActionCommand("Atacar");//Le damos su accion
                menu[i].add(botonesataque[i]);//Añadimos el boton al menu popup
                botonesataqueydefensa[i] = new JButton("Atacar y  Defender");//Hacemos lo mismo para el boton de atacar y defender
                botonesataqueydefensa[i].addActionListener(this);
                botonesataqueydefensa[i].setActionCommand("AtacarYDefender");
                menu[i].add(botonesataqueydefensa[i]);
                panel.add(botones[i]);//Le añadimos el boton que contiene el menu y los otros dos botones al JFrame principal
            } else {//Si la carta de esa posicion no fuese una criatura
                botones[i] = null;//Esa posicion sera null
                botonesataque[i] = null;//Esta tambien
                botonesataqueydefensa[i] = null;//Esta tambien
                menu[i] = null;//Esta tambien
            }
        }
    }
//AQUI TERMINAN LOS CONSTRUCTORES
    
//AQUI EMPIEZAN LOS METODOS DE LA CLASE
    public JDialog getDialogoPrincipal() { //Este método lo utilizaremos mas que nada para mostrar u ocultar la ventana de esta clase.
        return dialogoprincipal;
    }
//AQUI TERMINAN LOS METODOS DE CLASE
    
//AQUI EMPIEZAN LAS ACCIONES REALIZADAS POR LOS BOTONES
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Atacar")) {//Si le damos al botón de atacar
            if (owner.getSuTurno() && owner.getCanAttack()) {//Si es nuestro turno y podemos atacar
                try {
                    int indice = 0;
                    for (int i = 0; i <= (longitud - 1); i++) {
                        if (botonesataque[i] == evt.getSource()) {//Encontramos el botón de la criatura que hemos pulsado
                            indice = i;//Cogemos esa posición
                        }
                    }
                    owner.atacar(owner.getCampoBatalla().getArrayCartas().getCarta(indice));//Atacamos con la criatura en esa posición
                } catch (GetCartaException ex) {//Cubrimos las posibles excepciones
                    System.out.println("Error al recuperar una carta en clase atacarcarta en accion atacar");
                } catch (GetPositionException ex) {
                    System.out.println("Error al recuperar una posicion en clase atacarcarta en accion atacar");
                } catch (MazoVacio ex) {
                    Logger.getLogger(Atacar.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialogoprincipal.dispose();//Borramos la ventana
            } else {//Si no fuese asi hacemos que salte un pequeño diálogo que nos dice que no podemos atacar
                JLabel label = new JLabel("No puedes atacar!");
                JFrame frame = new JFrame();
                frame.add(label);
                JDialog cantattack = new JDialog(frame);
                frame.setVisible(true);
                label.setVisible(true);
                frame.setMinimumSize(new Dimension(300, 150));
                cantattack.setMinimumSize(new Dimension(300, 150));
            }

        }
        if (evt.getActionCommand().equals("AtacarYDefender")) {//Este proceso es igual que el de atacar pero con el método atacar y defender
            if (owner.getSuTurno() && owner.getCanAttack()) {
                try {
                    int indice = 0;
                    for (int i = 0; i <= (longitud - 1); i++) {
                        if (botonesataqueydefensa[i] == evt.getSource()) {
                            indice = i;
                        }
                    }
                    owner.atacarYDefender(owner.getCampoBatalla().getArrayCartas().getCarta(indice));
                    owner.findeturno();
                } catch (GetCartaException ex) {
                    throw new UnsupportedOperationException("Algo ha ido mal :/");
                } catch (GetPositionException | MazoVacio ex) {
                    Logger.getLogger(Atacar.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialogoprincipal.dispose();
            } else {
                JLabel
                        label = new JLabel("No puedes atacar!");
                JFrame frame = new JFrame();
                frame.add(label);
                JDialog cantattack = new JDialog(frame);
                frame.setVisible(true);
                label.setVisible(true);
                frame.setMinimumSize(new Dimension(300, 150));
                cantattack.setMinimumSize(new Dimension(300, 150));
            }
        }
        if (evt.getActionCommand().equals("Opciones")) {//Esto es para que cuando pulsamos sobre una criatura nos salgan los dos botones, de atacar o atacar y defender
            int indice = 0;
            for (int i = 0; i <= (longitud - 1); i++) {
                if (botones[i] == evt.getSource()) {
                    indice = i;
                }
            }
            menu[indice].show(botones[indice], 0, 0);//Aqui lo que hacemos es fijar el popupmenu a la posicion del boton pulsado
        }

    }
//AQUI TERMINAN LAS ACCIONES REALIZADAS POR LOS BOTONES
}
