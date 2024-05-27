/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import ConnectionMySQL.ConnectionDB;
import java.awt.Color;
import java.sql.SQLException;

/**
 *
 * @author acer_
 */
public class Login extends javax.swing.JFrame {

    Connection koneksi;
    public Login() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        this.setLocationRelativeTo(this);
        this.getContentPane().setBackground(new Color(102,204,255));
        inputPassword.setEchoChar((char)0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        inputPassword = new javax.swing.JPasswordField();
        tombolLogin = new javax.swing.JButton();
        checkboxShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Login");
        setBackground(new java.awt.Color(102, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel1.setText("LOGIN");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText(":");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Username");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText(":");

        inputUsername.setBackground(new java.awt.Color(102, 204, 255));
        inputUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputUsername.setText("Masukkan Username");
        inputUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        inputUsername.setMargin(new java.awt.Insets(10, 6, 2, 6));
        inputUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputUsernameFocusLost(evt);
            }
        });

        inputPassword.setBackground(new java.awt.Color(102, 204, 255));
        inputPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputPassword.setText("Masukkan Password");
        inputPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        inputPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPasswordFocusLost(evt);
            }
        });

        tombolLogin.setBackground(new java.awt.Color(204, 204, 0));
        tombolLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tombolLogin.setText("Login");
        tombolLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolLoginMouseClicked(evt);
            }
        });

        checkboxShowPassword.setText("Show Pass");
        checkboxShowPassword.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxShowPasswordItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tombolLogin)
                                .addGap(215, 260, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkboxShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(431, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxShowPassword))
                .addGap(18, 18, 18)
                .addComponent(tombolLogin)
                .addGap(154, 154, 154))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(163, 163, 163)
                    .addComponent(jLabel4)
                    .addContainerGap(255, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tombolLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolLoginMouseClicked
        // TODO add your handling code here:
        try{
            Statement st = this.koneksi.createStatement();
            String query1 = String.format("select version() as result;");
            ResultSet rs = st.executeQuery(query1);
            while(rs.next()){
                System.out.println(rs.getString("result"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_tombolLoginMouseClicked

    private void checkboxShowPasswordItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkboxShowPasswordItemStateChanged
        // TODO add your handling code here:
        
        if(inputPassword.getText().equals("Masukkan Password") && checkboxShowPassword.isSelected()){
            this.inputPassword.setEchoChar((char)0);
        }else if(!inputPassword.getText().equals("Masukkan Password") && checkboxShowPassword.isSelected()){
            this.inputPassword.setEchoChar((char)0);
        }else if(!inputPassword.getText().equals("Masukkan Password") && !checkboxShowPassword.isSelected()){
            this.inputPassword.setEchoChar('*');
        }
        
        inputUsername.requestFocusInWindow();
    }//GEN-LAST:event_checkboxShowPasswordItemStateChanged

    private void inputPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPasswordFocusGained
        // TODO add your handling code here:
        if(inputPassword.getText().equals("Masukkan Password")){
            inputPassword.setText("");
            if(inputPassword.getText().isBlank() && !this.checkboxShowPassword.isSelected()){
                this.inputPassword.setEchoChar('*');
            }
        }
    }//GEN-LAST:event_inputPasswordFocusGained

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkboxShowPassword;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton tombolLogin;
    // End of variables declaration//GEN-END:variables
}
