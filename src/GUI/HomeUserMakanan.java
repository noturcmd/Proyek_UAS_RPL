/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import ConnectionMySQL.ConnectionDB;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author ACER
 */
public class HomeUserMakanan extends javax.swing.JFrame {
    private String kamar = null;
    Connection koneksi;
    Statement st = null;
    ResultSet rs = null;
    
    int curIndex = 0;
    int maxIndex;
    
    String[] listNamaMakanan = new String[]{};
    String[] listHargaMakanan = new String[]{};
    ImageIcon[] listGambarMakanan = new ImageIcon[]{};

    /**
     * Creates new form HomeUserMakanan
     */
    public HomeUserMakanan() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        showMenu();
        showData();
    }
    
    String getNomorKamar(){
        return this.kamar;
    }
    
    void setNomorKamar(String kamar){
        this.kamar = kamar;
        this.nomorKamar.setText(this.kamar);
    }
    
    private void prev() {
        this.curIndex -= 4;
        if (this.curIndex < 0) {
            this.curIndex = 0;
        }
        showMenu();
        showData();
    }

    private void next() {
        this.curIndex += 4;
        showData();
        if (this.listGambarMakanan.length < 4) {
            this.curIndex = 0;
        }
        showMenu();
        showData();
    }
    
    void showData(){
        if(this.maxIndex == 1){
            System.out.println("1");
            this.gbr1.setIcon(this.listGambarMakanan[0]);
            this.gbr2.setVisible(false);
            this.gbr3.setVisible(false);
            this.gbr4.setVisible(false);
            
            this.nmMKn1.setText(this.listNamaMakanan[0]);
            this.nmMKn2.setVisible(false);
            this.nmMKn3.setVisible(false);
            this.nmMKn4.setVisible(false);
        }else if(this.maxIndex == 2){
            System.out.println("2");
            this.gbr1.setIcon(this.listGambarMakanan[0]);
            this.gbr2.setIcon(this.listGambarMakanan[1]);
            this.gbr3.setVisible(false);
            this.gbr4.setVisible(false);
            
            this.nmMKn1.setText(this.listNamaMakanan[0]);
            this.nmMKn2.setText(this.listNamaMakanan[1]);
            this.nmMKn3.setVisible(false);
            this.nmMKn4.setVisible(false);
        }else if(this.maxIndex == 3){
            System.out.println("3");
            this.gbr1.setIcon(this.listGambarMakanan[0]);
            this.gbr2.setIcon(this.listGambarMakanan[1]);
            this.gbr3.setIcon(this.listGambarMakanan[2]);
            this.gbr4.setVisible(false);
            
            this.nmMKn1.setText(this.listNamaMakanan[0]);
            this.nmMKn2.setText(this.listNamaMakanan[1]);
            this.nmMKn3.setText(this.listNamaMakanan[2]);
            this.nmMKn4.setVisible(false);
        }else if(this.maxIndex == 4){
            System.out.println("4");
            this.gbr1.setIcon(this.listGambarMakanan[0]);
            this.gbr2.setIcon(this.listGambarMakanan[1]);
            this.gbr3.setIcon(this.listGambarMakanan[2]);
            this.gbr4.setIcon(this.listGambarMakanan[3]);
            
            this.nmMKn1.setText(this.listNamaMakanan[0]);
            this.nmMKn2.setText(this.listNamaMakanan[1]);
            this.nmMKn3.setText(this.listNamaMakanan[2]);
            this.nmMKn4.setText(this.listNamaMakanan[3]);
        }
        
    }
    
    
    
    
    void showMenu(){
        try {
            this.st = this.koneksi.createStatement();
            String query = String.format("select * from menu limit 0,4;");
            this.rs = st.executeQuery(query);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            
            this.maxIndex = rowCount;
            
            this.listGambarMakanan = new ImageIcon[rowCount];
            this.listNamaMakanan = new String[rowCount];
            this.listHargaMakanan = new String[rowCount];
            
            Statement st2 = this.koneksi.createStatement();
            String query2 = String.format("select * from menu limit %s,4;", this.curIndex);
            System.out.println(query2);
            ResultSet rs2 = st2.executeQuery(query2);
            int i = 0;
            while(rs2.next()){
                byte[] imageData = rs2.getBytes("gambar");
                ImageIcon imageIcon = new ImageIcon(scaleImage(imageData, 420, 320));
                this.listGambarMakanan[i] = imageIcon;
                this.listNamaMakanan[i] = rs2.getString("nama");
                i++;
            }
            
            System.out.println("Size : " + this.listGambarMakanan.length);
//this.gbr1.setIcon(this.listGambarMakanan[0]);
//            this.gbr2.setIcon(this.listGambarMakanan[1]);
//            this.gbr3.setIcon(this.listGambarMakanan[2]);
//            this.gbr4.setIcon(this.listGambarMakanan[3]);
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

        next = new javax.swing.JButton();
        pre = new javax.swing.JButton();
        gbr1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        nmMKn2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        gbr2 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        nmMKn1 = new javax.swing.JLabel();
        gbr4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nmMKn4 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        nmMKn3 = new javax.swing.JLabel();
        gbr3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        nomorKamar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        next.setText(">");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        getContentPane().add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1850, 600, 50, 60));

        pre.setText("<");
        pre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preActionPerformed(evt);
            }
        });
        getContentPane().add(pre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 590, 50, 60));

        gbr1.setText("jLabel5");
        getContentPane().add(gbr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 306, 180, 200));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("HARGA");
        jLabel3.setAlignmentY(0.0F);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 400, 210, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 470, -1, -1));

        nmMKn2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        nmMKn2.setForeground(new java.awt.Color(255, 255, 255));
        nmMKn2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmMKn2.setText("MAKANAN");
        nmMKn2.setAlignmentY(0.0F);
        getContentPane().add(nmMKn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 290, 290, 100));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("HARGA");
        jLabel13.setAlignmentY(0.0F);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 400, 210, -1));

        gbr2.setText("jLabel5");
        getContentPane().add(gbr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 300, 180, 200));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1720, 470, -1, -1));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton13.setContentAreaFilled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 470, -1, -1));

        nmMKn1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        nmMKn1.setForeground(new java.awt.Color(255, 255, 255));
        nmMKn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmMKn1.setText("MAKANAN");
        nmMKn1.setAlignmentY(0.0F);
        getContentPane().add(nmMKn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 290, 290, 90));

        gbr4.setText("jLabel5");
        getContentPane().add(gbr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 750, 180, 200));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("HARGA");
        jLabel10.setAlignmentY(0.0F);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 850, 210, -1));

        nmMKn4.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        nmMKn4.setForeground(new java.awt.Color(255, 255, 255));
        nmMKn4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmMKn4.setText("MAKANAN");
        nmMKn4.setAlignmentY(0.0F);
        getContentPane().add(nmMKn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 740, 290, 100));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 920, -1, -1));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1720, 920, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 920, -1, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 920, -1, -1));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("HARGA");
        jLabel6.setAlignmentY(0.0F);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 850, 210, -1));

        nmMKn3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        nmMKn3.setForeground(new java.awt.Color(255, 255, 255));
        nmMKn3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmMKn3.setText("MAKANAN");
        nmMKn3.setAlignmentY(0.0F);
        getContentPane().add(nmMKn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 740, 290, 110));

        gbr3.setText("jLabel5");
        getContentPane().add(gbr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 750, 180, 200));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 470, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setAlignmentY(0.0F);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 680, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setAlignmentY(0.0F);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png"))); // NOI18N
        jButton3.setToolTipText("");
        jButton3.setAlignmentY(0.0F);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 880, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png"))); // NOI18N
        jButton4.setToolTipText("");
        jButton4.setAlignmentY(0.0F);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 780, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png"))); // NOI18N
        jButton5.setToolTipText("");
        jButton5.setAlignmentY(0.0F);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, -1, -1));

        nomorKamar.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        nomorKamar.setForeground(new java.awt.Color(255, 255, 255));
        nomorKamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomorKamar.setText("KAMAR");
        nomorKamar.setAlignmentY(0.0F);
        getContentPane().add(nomorKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 320, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg home user makanan.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void preActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preActionPerformed
        // TODO add your handling code here:
        this.prev();
    }//GEN-LAST:event_preActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        this.next();
    }//GEN-LAST:event_nextActionPerformed

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
            java.util.logging.Logger.getLogger(HomeUserMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUserMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUserMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUserMakanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUserMakanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gbr1;
    private javax.swing.JLabel gbr2;
    private javax.swing.JLabel gbr3;
    private javax.swing.JLabel gbr4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton next;
    private javax.swing.JLabel nmMKn1;
    private javax.swing.JLabel nmMKn2;
    private javax.swing.JLabel nmMKn3;
    private javax.swing.JLabel nmMKn4;
    private javax.swing.JLabel nomorKamar;
    private javax.swing.JButton pre;
    // End of variables declaration//GEN-END:variables
}
