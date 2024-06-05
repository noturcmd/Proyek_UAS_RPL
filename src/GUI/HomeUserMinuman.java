/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import javax.swing.JScrollBar;

/**
 *
 * @author ACER
 */
public class HomeUserMinuman extends javax.swing.JFrame {

    /**
     * Creates new form HomeUser
     */
    public HomeUserMinuman() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        JScrollBar verScrol = this.jScrollPane1.getVerticalScrollBar();
        verScrol.setUnitIncrement(20);
        verScrol.setBlockIncrement(50);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        inputUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png"))); // NOI18N
        jToggleButton1.setAlignmentY(0.0F);
        jToggleButton1.setAutoscrolls(true);
        jToggleButton1.setBorder(null);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum w.png"))); // NOI18N
        jToggleButton2.setAlignmentY(0.0F);
        jToggleButton2.setAutoscrolls(true);
        jToggleButton2.setBorder(null);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 140, -1, -1));

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png"))); // NOI18N
        jToggleButton3.setAlignmentY(0.0F);
        jToggleButton3.setAutoscrolls(true);
        jToggleButton3.setBorder(null);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        inputUsername.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        inputUsername.setForeground(new java.awt.Color(125, 0, 124));
        inputUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputUsername.setText("Search");
        inputUsername.setBorder(null);
        inputUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputUsernameFocusLost(evt);
            }
        });
        inputUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputUsernameActionPerformed(evt);
            }
        });
        jPanel2.add(inputUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nasi +.png"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputUsernameFocusGained
        // TODO add your handling code here:
        if(inputUsername.getText().equals("Masukkan Username")){
            inputUsername.setText(""); // set password field to empty string

        }
    }//GEN-LAST:event_inputUsernameFocusGained

    private void inputUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputUsernameFocusLost
        // TODO add your handling code here:
        if(inputUsername.getText().equals("")){
            inputUsername.setText("Masukkan Username");
        }
    }//GEN-LAST:event_inputUsernameFocusLost

    private void inputUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputUsernameActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(HomeUserMinuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUserMinuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUserMinuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUserMinuman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUserMinuman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables
}
