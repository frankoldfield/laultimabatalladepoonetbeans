package interfaces;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.MazoVacio;
/**
 *
 * @author frank
 */

public class AccionesJugador extends javax.swing.JFrame {
//AQUI EMPIEZAN LOS ATRIBUTOS
    private Jugador owner; //Este es el jugador al que la ventana va a estar asociada, esta clase va a dar la opción a este jugador de realizar distintas acciones
//AQUI TERMINAN LOS ATRIBUTOS

//AQUI EMPIEZAN LOS CONSTRUCTORES
    public AccionesJugador() { //Este constructor simplemente hace una ventana que no tiene dueño, sirve para ver de qué manera se ve esta interfaz
        initComponents();
    }

    public AccionesJugador(Jugador owner) {//Lo primero que hacemos en este constructor es asignar el dueño, y luego iniciar los demás componentes, asi estos podrán coger los datos del dueño
        this.owner = owner;
        initComponents();
    }
//AQUI TERMINAN LOS CONSTRUCTORES
    
    public JLabel getTitulo() { //Este getter sirve para coger la etiqueta del titulo de la interfaz
        return titulo;
    }

    public void setTitulo(String titulo){//Este setter sirve para cambiar la etiqueta del titulo de la interfaz (Para cuando le asignemos un nombre al jugador, poder cambiar el título de la ventana)
        this.titulo.setText(titulo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(owner.getNombre());
        setBackground(java.awt.Color.white);

        titulo.setText("No se sabe si es tu turno");
        if(owner.getSuTurno()){
            titulo.setText("Es tu turno");
        }
        else{
            titulo.setText("No es tu turno");
        }

        jButton1.setText("ATTACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("USE CARD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("INFO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("SKIP TURN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Quit game");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("NombreJugador");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(titulo)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(29, 29, 29)
                .addComponent(jButton5)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//AQUI EMPIEZAN LAS ACCIONES QUE REALIZAN LOS BOTONES
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            owner.setUsarCarta(new UsarCarta(owner)); //Aqui actualizamos la interfaz de usarcarta del jugador, por si ha habido un cambio desde la última vez que se actualizó.
            owner.getUsarCarta().getJFramePrincipal().setVisible(true);//Ahora la ponemos visible
        } catch (GetCartaException ex) {//En un principio no debería de haber ningún error, pero si lo hubiese captamos la excepción y decimos dónde se ha originado.
            System.out.println("Alguna carta no se ha podido recuperar en boton 2 de acciones jugador");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        owner.getInfoPartida().dispose();//Este método será el que invoque a la interfaz que le muestra al jugador la información de la partida
        try {
            owner.setInfoPartida(new InfoPartida(owner));//Primero actualizamos la interfaz por si hubiese habido cambios en  el curso de la partida.
            owner.getInfoPartida().setVisible(true);//Ahora hacemos que esta interfaz sea visible
        } catch (GetCartaException ex) {//Captamos la excepción en el caso de que no se pudiese recuperar una carta para imprimir su información en la interfaz
            System.out.println("Alguna carta no se ha podido recuperar en boton 3 de acciones jugador");//Si se da el caso indicamos dónde se ha ocasionado el error
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Esta interfaz no hace falta actualizarla porque se actualiza siempre que el jugador saca una carta al campo de batalla.
        owner.getAtacar().getDialogoPrincipal().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (owner.getSuTurno()) {//Este botón nos da la opción de saltar el turno, pero si no es nuestro turno no podemos saltarlol
            try {
                owner.findeturno();//Invocamos al método findeturno del owner, que hará todo lo necesario para que se pase el turno al siguiente jugador
            } catch (MazoVacio | GetCartaException ex) {
               System.out.println("Alguna carta no se ha podido recuperar en boton 4 de acciones jugador, o tu mazo está vacío");
            }
        } else {
            System.out.println("No es tu turno por lo que no puedes saltar turno");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            owner.getTablero().terminarjuego();//Este botón hace que se salga del juego, se cancela la partida
        } catch (IOException ex) {
            Logger.getLogger(AccionesJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        System.out.println(owner.getNombre());// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
//AQUI TERMINAN LAS ACCIONES QUE REALIZAN LOS BOTONES
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
            java.util.logging.Logger.getLogger(AccionesJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccionesJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccionesJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccionesJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccionesJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
