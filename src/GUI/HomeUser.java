/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import ConnectionMySQL.ConnectionDB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;

/**
 *
 * @author ACER
 */
public class HomeUser extends javax.swing.JFrame {
    private DetailMakanan dmkn = new DetailMakanan();
    private Connection koneksi;
    private String namaMenu = null;
    private String kamar = null;

    /**
     * Creates new form HomeUser
     */
    public HomeUser() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        JScrollBar verScrol = this.jScrollPane1.getVerticalScrollBar();
        verScrol.setUnitIncrement(20);
        verScrol.setBlockIncrement(50);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }
    
    public void setKamar(String kamar){
        this.kamar = kamar;
        this.nomorKamar.setText(this.kamar);
    }
    
    public String getKamar(){
        return this.kamar;
    }
    
    private void fetchDataFromDatabase() {
        try {
            Statement statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM menu");
            
            while(resultSet.next()) {
                byte[] imageData = resultSet.getBytes("gambar");
                ImageIcon imageIcon = new ImageIcon(scaleImage(imageData, 400, 300));
                JLabel imageLabel = new JLabel(imageIcon);
                JButton detailButton = new JButton("Detail");

                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
                itemPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
                itemPanel.add(imageLabel);
                itemPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical spacing
                itemPanel.add(detailButton);

                this.jPanel1.add(itemPanel);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Image scaleImage(byte[] imageData, int width, int height) {
        try {
            Image image = ImageIO.read(new ByteArrayInputStream(imageData));
            return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
        nomorKamar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        detailMenu = new javax.swing.JButton();
        tambahPesanan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        gambar = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        searchMkn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel2.setMinimumSize(new java.awt.Dimension(540, 960));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomorKamar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nomorKamar.setText("jLabel17");
        jPanel2.add(nomorKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        detailMenu.setAlignmentY(0.0F);
        detailMenu.setBorder(null);
        detailMenu.setContentAreaFilled(false);
        jPanel1.add(detailMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        tambahPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        tambahPesanan.setAlignmentY(0.0F);
        tambahPesanan.setBorder(null);
        tambahPesanan.setContentAreaFilled(false);
        jPanel1.add(tambahPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rp. 10000");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 40));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(125, 0, 124));
        jLabel7.setText("Sayap Geprek");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 40));

        gambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Geprek.png"))); // NOI18N
        gambar.setText("Nasi Goreng");
        gambar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gambarMouseClicked(evt);
            }
        });
        jPanel1.add(gambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 320));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 420, 320));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton12.setAlignmentY(0.0F);
        jButton12.setBorder(null);
        jButton12.setContentAreaFilled(false);
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton13.setAlignmentY(0.0F);
        jButton13.setBorder(null);
        jButton13.setContentAreaFilled(false);
        jPanel5.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(125, 0, 124));
        jLabel16.setText("Indomie");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 40));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Rp. 8000");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Telur.png"))); // NOI18N
        jLabel4.setText("Nasi Goreng");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1950, 420, 320));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton6.setAlignmentY(0.0F);
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jPanel6.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Rp. 8000");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 40));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(125, 0, 124));
        jLabel10.setText("Nasi Telur");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Telur.png"))); // NOI18N
        jLabel5.setText("Nasi Goreng");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 320));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 780, 420, 320));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton10.setAlignmentY(0.0F);
        jButton10.setBorder(null);
        jButton10.setContentAreaFilled(false);
        jPanel7.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton11.setAlignmentY(0.0F);
        jButton11.setBorder(null);
        jButton11.setContentAreaFilled(false);
        jPanel7.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Rp. 10000");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 40));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(125, 0, 124));
        jLabel14.setText("Indomie Telur");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Indome Telur.png"))); // NOI18N
        jLabel6.setText("Nasi Goreng");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1560, 420, 320));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton9.setAlignmentY(0.0F);
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton8.setAlignmentY(0.0F);
        jButton8.setBorder(null);
        jButton8.setContentAreaFilled(false);
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Rp. 10000");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 40));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(125, 0, 124));
        jLabel12.setText("Nasi Goreng");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nasi Goreng.png"))); // NOI18N
        jLabel2.setText("Nasi Goreng");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 330));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1170, 420, 320));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png"))); // NOI18N
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png"))); // NOI18N
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png"))); // NOI18N
        jButton4.setAlignmentY(0.0F);
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, -1, -1));

        searchMkn.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        searchMkn.setForeground(new java.awt.Color(125, 0, 124));
        searchMkn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        searchMkn.setText("Search");
        searchMkn.setAlignmentX(0.0F);
        searchMkn.setAlignmentY(0.0F);
        searchMkn.setAutoscrolls(false);
        searchMkn.setBorder(null);
        searchMkn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchMknFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchMknFocusLost(evt);
            }
        });
        searchMkn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMknActionPerformed(evt);
            }
        });
        searchMkn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchMknKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchMknKeyTyped(evt);
            }
        });
        jPanel2.add(searchMkn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 203, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BG Home.png"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 2400));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 960));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchMknFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchMknFocusGained
        // TODO add your handling code here:
        if(searchMkn.getText().equals("Search")){
            searchMkn.setText(""); // set password field to empty string

        }
    }//GEN-LAST:event_searchMknFocusGained

    private void searchMknFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchMknFocusLost
        // TODO add your handling code here:
        if(searchMkn.getText().equals("")){
            searchMkn.setText("Search");
        }
    }//GEN-LAST:event_searchMknFocusLost

    private void searchMknActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMknActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchMknActionPerformed

    private void searchMknKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMknKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_searchMknKeyPressed

    private void searchMknKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMknKeyTyped
        // TODO add your handling code here:
        System.out.println(this.searchMkn.getText());
    }//GEN-LAST:event_searchMknKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        try {
                System.out.println(jLabel2.getText());
        } catch (Exception e) {
            
        } 
    }//GEN-LAST:event_jLabel2MouseClicked

    private void gambarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gambarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_gambarMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton detailMenu;
    private javax.swing.JLabel gambar;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomorKamar;
    private javax.swing.JTextField searchMkn;
    private javax.swing.JButton tambahPesanan;
    // End of variables declaration//GEN-END:variables
}
