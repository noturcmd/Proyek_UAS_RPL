/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import ConnectionMySQL.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class SignIn extends javax.swing.JFrame {
    Connection koneksi;
    HomeUser hmus = new HomeUser();
    HomeAdmin hmad = new HomeAdmin();
    Statement st = null;
    ResultSet rs = null;

    /**
     * Creates new form SignIn
     */
    public SignIn() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        this.signInBtn.setBorderPainted(false);
        inputPassword.setEchoChar((char)0);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        inputUsername = new javax.swing.JTextField();
        inputPassword = new javax.swing.JPasswordField();
        checkboxShowPassword = new javax.swing.JCheckBox();
        signInBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputUsername.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        inputUsername.setForeground(new java.awt.Color(125, 0, 124));
        inputUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputUsername.setText("Masukkan Username");
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
        getContentPane().add(inputUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 530, 290, -1));

        inputPassword.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        inputPassword.setForeground(new java.awt.Color(125, 0, 124));
        inputPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputPassword.setText("Masukkan Password");
        inputPassword.setBorder(null);
        inputPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPasswordFocusLost(evt);
            }
        });
        inputPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(inputPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 625, 260, -1));

        checkboxShowPassword.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        checkboxShowPassword.setForeground(new java.awt.Color(125, 0, 124));
        checkboxShowPassword.setText("Show Password");
        checkboxShowPassword.setBorder(null);
        checkboxShowPassword.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxShowPasswordItemStateChanged(evt);
            }
        });
        checkboxShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxShowPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(checkboxShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 680, -1, -1));

        signInBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        signInBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sign button.png"))); // NOI18N
        signInBtn.setAlignmentY(0.0F);
        signInBtn.setBorder(null);
        signInBtn.setBorderPainted(false);
        signInBtn.setContentAreaFilled(false);
        signInBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signInBtnMouseClicked(evt);
            }
        });
        signInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInBtnActionPerformed(evt);
            }
        });
        getContentPane().add(signInBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 770, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SignIn.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPasswordActionPerformed

    private void inputUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputUsernameActionPerformed

    private void checkboxShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxShowPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxShowPasswordActionPerformed

    private void signInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInBtnActionPerformed
        // TODO add your handling code here:


        try{
            if(this.inputPassword.getText().equals("Masukkan Password") && this.inputUsername.getText().equals("Masukkan Username")){
                JOptionPane.showMessageDialog(this, "Mohon tidak mengosongkan username dan password!");
            }else if (!this.inputUsername.getText().equals("Masukkan Username") && this.inputPassword.getText().equals("Masukkan Password")) {
                JOptionPane.showMessageDialog(this, "Mohon tidak mengosongkan password!");
            }else if (this.inputUsername.getText().equals("Masukkan Username") && !this.inputPassword.getText().equals("Masukkan Password")) {
                JOptionPane.showMessageDialog(this, "Mohon tidak mengosongkan username!");
            }else{
                st = koneksi.createStatement();
                String query = String.format("select * from daftar_akun where username=\"%s\";", this.inputUsername.getText());
                ResultSet rs = st.executeQuery(query);
                if(rs.next() == true){
                    st = koneksi.createStatement();
                    String query10 = String.format("select * from daftar_akun where username=\"%s\";", this.inputUsername.getText());
                    ResultSet rs10 = st.executeQuery(query10);
                    if(rs10.next() == true && this.inputPassword.getText().equals(rs10.getString("password"))){
                        if(rs.getString("status").equals("admin")){
                            this.hmad.setVisible(true);
                        }else if(rs.getString("status").equals("kamar")){
                            this.hmus.setVisible(true);
                            hmus.setKamar(rs.getString("username").toUpperCase());
                        }
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(this, "Password salah!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Akun tidak tersedia!");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_signInBtnActionPerformed

    private void inputUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputUsernameFocusGained
        // TODO add your handling code here:
        if(inputUsername.getText().equals("Masukkan Username")){
            inputUsername.setText(""); // set password field to empty string

        }
    }//GEN-LAST:event_inputUsernameFocusGained

    private void inputPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPasswordFocusGained
        // TODO add your handling code here:
        if(inputPassword.getText().equals("Masukkan Password")){
            inputPassword.setText("");
            if(inputPassword.getText().isBlank() && !this.checkboxShowPassword.isSelected()){
                this.inputPassword.setEchoChar('*');
            }
        }
    }//GEN-LAST:event_inputPasswordFocusGained

    private void inputUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputUsernameFocusLost
        // TODO add your handling code here:
        if(inputUsername.getText().equals("")){
            inputUsername.setText("Masukkan Username");
        }
    }//GEN-LAST:event_inputUsernameFocusLost

    private void inputPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPasswordFocusLost
        // TODO add your handling code here:
        if(inputPassword.getText().equals("")){
            inputPassword.setText("Masukkan Password");
            if(inputPassword.getText().isBlank() && !this.checkboxShowPassword.isSelected()){
                this.inputPassword.setEchoChar('*');
            }
            if(inputPassword.getText().equals("Masukkan Password")){
                this.inputPassword.setEchoChar((char)0);
            }
        }
    }//GEN-LAST:event_inputPasswordFocusLost

    private void checkboxShowPasswordItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkboxShowPasswordItemStateChanged
        // TODO add your handling code here:
        if(inputPassword.getText().equals("Masukkan Password") && checkboxShowPassword.isSelected()){
            this.inputPassword.setEchoChar((char)0);
        }else if(!inputPassword.getText().equals("Masukkan Password") && checkboxShowPassword.isSelected()){
            this.inputPassword.setEchoChar((char)0);
        }else if(!inputPassword.getText().equals("Masukkan Password") && !checkboxShowPassword.isSelected()){
            this.inputPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_checkboxShowPasswordItemStateChanged

    private void signInBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signInBtnMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_signInBtnMouseClicked

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
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkboxShowPassword;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JButton signInBtn;
    // End of variables declaration//GEN-END:variables
}
