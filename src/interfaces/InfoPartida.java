package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.cartas.Carta;
import laultimabatalladepoo.cartas.Criatura;
import laultimabatalladepoo.cartas.Edificacion;
import laultimabatalladepoo.cartas.Encantamiento;
import laultimabatalladepoo.excepciones.GetCartaException;

/**
 *
 * @author frank
 */

public class InfoPartida extends javax.swing.JFrame {

    private Jugador owner;
    private JLabel[] nombrecarta;
    private JLabel[] vidacarta;
    private JLabel[] ataquecarta;
    private JLabel[] tipocarta;
    private JLabel[] habilidadcarta;
    private JLabel[] reinocarta;
    private JPanel[] panelcarta;
    private JPanel[] panelcarta2;
    private JLabel[] nombrecarta2;
    private JLabel[] vidacarta2;
    private JLabel[] ataquecarta2;
    private JLabel[] tipocarta2;
    private JLabel[] habilidadcarta2;
    private JLabel[] reinocarta2;
    private JPanel[] panelcarta3;
    private JLabel[] nombrecarta3;
    private JLabel[] vidacarta3;
    private JLabel[] ataquecarta3;
    private JLabel[] tipocarta3;
    private JLabel[] habilidadcarta3;
    private JLabel[] reinocarta3;
    Carta cartaadd;
//Estos arrays de JLabel son el 1 para el campo de batalla del jugador, el 2 para el campo de batalla del enemigo, y el 3 para la mano del enemigo, se va a ir cogiendo la información iterando
//Cada carta que hay en esos sitios y copiando la información a la misma posición pero del array de JLabel correspondiente a esa información
//Luego la información correspondiente a la misma carta se junta y se van colocando en paneles individuales para cada carta, estos luego se ponen en un panel algo mas grande.
    public InfoPartida() {
        initComponents();
    }

    public InfoPartida(Jugador owner) throws GetCartaException { //HACEMOS SOBRECARGA DE MÉTODOS PARA PODER PASARLE EL VALOR DE OWNER.
        this.owner = owner;
        initComponents();
        vidaowner.setText("Tu vida: " + owner.getVida());//Lo siguiente son modificaciones del texto para que muestre información del jugador y el enemigo
        vidaenemigo.setText("Vida del enemigo: " + owner.getTablero().getJugador(owner.getEnemigo()).getVida());
        razaowner.setText("Tu raza: " + owner.printRaza());
        razaenemigo.setText("Raza enemigo: " + owner.getTablero().getJugador(owner.getEnemigo()).printRaza());
        try {
            cartadefendiendoowner.setText("Tu carta defendiendo:" + owner.getCartaDefendiendo().getNombre());
        } catch (NullPointerException e) {
        }

        int longitud = owner.getCampoBatalla().getArrayCartas().getLength();
        int longitud2 = owner.getTablero().getJugador(owner.getEnemigo()).getCampoBatalla().getArrayCartas().getLength();
        int longitud3 = owner.getMano().getArrayCartas().getLength();
        nombrecarta = new JLabel[longitud];
        vidacarta = new JLabel[longitud];
        ataquecarta = new JLabel[longitud];
        tipocarta = new JLabel[longitud];
        habilidadcarta = new JLabel[longitud];
        reinocarta = new JLabel[longitud];
        panelcarta = new JPanel[longitud];
        nombrecarta2 = new JLabel[longitud2];
        vidacarta2 = new JLabel[longitud2];
        ataquecarta2 = new JLabel[longitud2];
        tipocarta2 = new JLabel[longitud2];
        habilidadcarta2 = new JLabel[longitud2];
        reinocarta2 = new JLabel[longitud2];
        panelcarta2 = new JPanel[longitud2];
        nombrecarta3 = new JLabel[longitud3];
        vidacarta3 = new JLabel[longitud3];
        ataquecarta3 = new JLabel[longitud3];
        tipocarta3 = new JLabel[longitud3];
        habilidadcarta3 = new JLabel[longitud3];
        reinocarta3 = new JLabel[longitud3];
        panelcarta3 = new JPanel[longitud3];
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);

        for (int i = 0; i <= (longitud - 1); i++) {//Primero creamos la parte del owner
            panelcarta[i] = new JPanel();
            panelcarta[i].setMinimumSize(new Dimension(95, 140));
            panelcarta[i].setMaximumSize(new Dimension(96, 150));
            panelcarta[i].setLayout(new GridLayout(7, 1));
            panelcarta[i].setForeground(Color.WHITE);
            panelcarta[i].setBackground(Color.LIGHT_GRAY);
            panelcarta[i].setBorder(compound);
            cartaadd = owner.getCampoBatalla().getArrayCartas().getCarta(i);
            nombrecarta[i] = new JLabel(cartaadd.getNombre());
            vidacarta[i] = new JLabel("Vida: " + String.valueOf(cartaadd.getVida()));
            ataquecarta[i] = new JLabel("Ataque: " + String.valueOf(cartaadd.getAtk()));
            habilidadcarta[i] = new JLabel("Habilidad: " + String.valueOf(cartaadd.getHabilidad()));
            panelcarta[i].add(nombrecarta[i]);
            reinocarta[i] = new JLabel();
            tipocarta[i] = new JLabel();
            if (cartaadd instanceof Criatura) {
                tipocarta[i].setText("Tipo: criatura");
                reinocarta[i].setText("Reino: " + String.valueOf(cartaadd.getReino()));
                panelcarta[i].add(reinocarta[i]);
            } else {
                tipocarta[i].setText("Tipo: edificacion");
            }
            panelcarta[i].add(tipocarta[i]);
            panelcarta[i].add(habilidadcarta[i]);
            panelcarta[i].add(vidacarta[i]);
            panelcarta[i].add(ataquecarta[i]);
            jPanel5.add(panelcarta[i]);
        }

        for (int i = 0; i <= (longitud2 - 1); i++) {//Ahora creamos la informacion del enemigo
            panelcarta2[i] = new JPanel();
            panelcarta2[i].setMinimumSize(new Dimension(95, 130));
            panelcarta2[i].setMaximumSize(new Dimension(96, 131));
            panelcarta2[i].setLayout(new GridLayout(6, 1));
            panelcarta2[i].setForeground(Color.WHITE);
            panelcarta2[i].setBackground(Color.LIGHT_GRAY);
            panelcarta2[i].setBorder(compound);
            cartaadd = owner.getTablero().getJugador(owner.getEnemigo()).getCampoBatalla().getArrayCartas().getCarta(i);
            nombrecarta2[i] = new JLabel(cartaadd.getNombre());
            vidacarta2[i] = new JLabel("Vida: " + String.valueOf(cartaadd.getVida()));
            ataquecarta2[i] = new JLabel("Ataque: " + String.valueOf(cartaadd.getAtk()));
            habilidadcarta2[i] = new JLabel("Habilidad: " + String.valueOf(cartaadd.getHabilidad()));
            panelcarta2[i].add(nombrecarta2[i]);
            reinocarta2[i] = new JLabel();
            tipocarta2[i] = new JLabel();
            if (cartaadd instanceof Criatura) {
                tipocarta2[i].setText("Tipo: Criatura");
                reinocarta2[i].setText("Reino: " + String.valueOf(cartaadd.getReino()));
                panelcarta2[i].add(reinocarta2[i]);
            } else {
                tipocarta2[i].setText("Tipo: Edificacion");
            }
            panelcarta2[i].add(tipocarta2[i]);
            panelcarta2[i].add(habilidadcarta2[i]);
            panelcarta2[i].add(vidacarta2[i]);
            panelcarta2[i].add(ataquecarta2[i]);
            jPanel6.add(panelcarta2[i]);
        }
        for (int i = 0; i <= (longitud3 - 1); i++) {//Primero creamos la parte del owner
            panelcarta3[i] = new JPanel();
            panelcarta3[i].setMinimumSize(new Dimension(95, 130));
            panelcarta3[i].setMaximumSize(new Dimension(96, 131));
            panelcarta3[i].setLayout(new GridLayout(6, 1));
            panelcarta3[i].setForeground(Color.WHITE);
            panelcarta3[i].setBackground(Color.LIGHT_GRAY);
            panelcarta3[i].setBorder(compound);
            cartaadd = owner.getMano().getArrayCartas().getCarta(i);
            nombrecarta3[i] = new JLabel(cartaadd.getNombre());
            vidacarta3[i] = new JLabel("Vida: " + String.valueOf(cartaadd.getVida()));
            ataquecarta3[i] = new JLabel("Ataque: " + String.valueOf(cartaadd.getAtk()));
            habilidadcarta3[i] = new JLabel("Habilidad: " + String.valueOf(cartaadd.getHabilidad()));
            panelcarta3[i].add(nombrecarta3[i]);
            reinocarta3[i] = new JLabel();
            tipocarta3[i] = new JLabel();
            if (cartaadd instanceof Criatura) {
                tipocarta3[i].setText("Tipo: Criatura");
                reinocarta3[i].setText("Reino: " + String.valueOf(cartaadd.getReino()));
                panelcarta3[i].add(reinocarta3[i]);
            } else {
                if (cartaadd instanceof Edificacion) {
                    tipocarta3[i].setText("Tipo: Edificacion");
                } else {
                    if (cartaadd instanceof Encantamiento) {
                        tipocarta3[i].setText("Tipo: Encantamiento");
                    } else {
                        tipocarta3[i].setText("Tipo: Conjuro");
                    }
                }
            }
            panelcarta3[i].add(tipocarta3[i]);
            panelcarta3[i].add(habilidadcarta3[i]);
            panelcarta3[i].add(vidacarta3[i]);
            panelcarta3[i].add(ataquecarta3[i]);
            jPanel7.add(panelcarta3[i]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        vidaowner = new javax.swing.JLabel();
        vidaenemigo = new javax.swing.JLabel();
        razaowner = new javax.swing.JLabel();
        razaenemigo = new javax.swing.JLabel();
        cartadefendiendoowner = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion de la partida - "+owner.getNombre());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(650, 500));

        jLabel1.setText("INFO ABOUT YOU");

        vidaowner.setText("Tu vida: ");

        vidaenemigo.setText("Vida enemigo: ");

        razaowner.setText("Tu raza:");

        razaenemigo.setText("Raza enemigo:");

        cartadefendiendoowner.setText("Carta que tienes defendiendo: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartadefendiendoowner, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razaowner, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vidaenemigo, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vidaowner, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razaenemigo, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vidaowner)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaenemigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(razaowner)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(razaenemigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartadefendiendoowner)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Players", jPanel1);

        jLabel4.setText("Tus cartas");
        jLabel4.setMaximumSize(new java.awt.Dimension(59, 18));
        jLabel4.setMinimumSize(new java.awt.Dimension(59, 16));
        jLabel4.setPreferredSize(new java.awt.Dimension(59, 17));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jLabel5.setText("Cartas del enemigo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        jTabbedPane1.addTab("Campo de Batalla", jScrollPane1);

        jLabel2.setText("Cartas de tu mano:");

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(468, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        jTabbedPane1.addTab("Tu mano", jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InfoPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InfoPartida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cartadefendiendoowner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel razaenemigo;
    private javax.swing.JLabel razaowner;
    private javax.swing.JLabel vidaenemigo;
    private javax.swing.JLabel vidaowner;
    // End of variables declaration//GEN-END:variables
}
