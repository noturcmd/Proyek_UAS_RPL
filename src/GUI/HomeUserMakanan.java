/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import ConnectionMySQL.ConnectionDB;
import java.awt.Color;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ACER
 */
public class HomeUserMakanan extends javax.swing.JFrame {
    private String kamar = null;
    Connection koneksi;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel tabelMenu = null;
    ArrayList<ImageIcon> imageList = new ArrayList<>();
    HomeUserMinuman hmus = null;
    HomeUserCamilan hmus2 = null;

    /**
     * Creates new form HomeUserMakanan
     */
    public HomeUserMakanan() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        getData();
        this.gbr1.setIcon(this.imageList.get(0));
        this.hrg1.setText(tabelTabel.getValueAt(0, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(0, 0).toString());
        this.tabelTabel.setBackground(new Color(255, 255, 255));
        this.setVisible(false);
    }
    
    String getNomorKamar(){
        return this.kamar;
    }
    
    void setNomorKamar(String kamar){
        this.kamar = kamar;
        this.nomorKamar.setText(this.kamar);
    }
    

    
    





    
    
    
    void getData(){
        this.imageList.clear();
        tabelMenu = (DefaultTableModel) this.tabelTabel.getModel();
        try {
            this.st = this.koneksi.createStatement();
            String query = String.format("select * from menu where jenis = \"Makanan\"");
            System.out.println("query : " + query);
            this.rs = st.executeQuery(query);
            while(rs.next()){
                tabelMenu.addRow(new Object[]{rs.getString("nama"),rs.getString("harga"),rs.getString("status"),rs.getString("deskripsi")});
                byte[] imageData = rs.getBytes("gambar");
                ImageIcon imageIcon = new ImageIcon(scaleImage(imageData, 420, 320));
                this.imageList.add(imageIcon);
            }
            
            
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTabel = new javax.swing.JTable();
        gbr1 = new javax.swing.JLabel();
        hrg1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        nmMKn1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        menuCamilan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        menuMinuman = new javax.swing.JButton();
        nomorKamar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 710, 540, 290));

        tabelTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Harga", "Status", "Deskripsi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelTabel.setGridColor(new java.awt.Color(255, 255, 255));
        tabelTabel.getTableHeader().setReorderingAllowed(false);
        tabelTabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTabel);
        if (tabelTabel.getColumnModel().getColumnCount() > 0) {
            tabelTabel.getColumnModel().getColumn(0).setResizable(false);
            tabelTabel.getColumnModel().getColumn(1).setResizable(false);
            tabelTabel.getColumnModel().getColumn(2).setResizable(false);
            tabelTabel.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 210, 670, 850));

        gbr1.setText("jLabel5");
        getContentPane().add(gbr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 306, 180, 200));

        hrg1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        hrg1.setForeground(new java.awt.Color(255, 255, 255));
        hrg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hrg1.setText("HARGA");
        hrg1.setAlignmentY(0.0F);
        getContentPane().add(hrg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 400, 210, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 470, -1, -1));

        nmMKn1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        nmMKn1.setForeground(new java.awt.Color(255, 255, 255));
        nmMKn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmMKn1.setText("MAKANAN");
        nmMKn1.setAlignmentY(0.0F);
        getContentPane().add(nmMKn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 290, 290, 90));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 470, -1, -1));

        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png"))); // NOI18N
        menuCamilan.setToolTipText("");
        menuCamilan.setAlignmentY(0.0F);
        menuCamilan.setContentAreaFilled(false);
        menuCamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCamilanActionPerformed(evt);
            }
        });
        getContentPane().add(menuCamilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 680, -1, -1));

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

        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png"))); // NOI18N
        menuMinuman.setToolTipText("");
        menuMinuman.setAlignmentY(0.0F);
        menuMinuman.setContentAreaFilled(false);
        menuMinuman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMinumanActionPerformed(evt);
            }
        });
        getContentPane().add(menuMinuman, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, -1, -1));

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

    private void menuCamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCamilanActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.hmus2 = new HomeUserCamilan();
        this.hmus2.setVisible(true);
        this.hmus2.setNomorKamar(kamar);
    }//GEN-LAST:event_menuCamilanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void menuMinumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMinumanActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.hmus = new HomeUserMinuman();
        this.hmus.setVisible(true);
        this.hmus.setNomorKamar(kamar);
    }//GEN-LAST:event_menuMinumanActionPerformed

    private void tabelTabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTabelMouseClicked
        // TODO add your handling code here:
        int row = tabelTabel.getSelectedRow();
        this.gbr1.setIcon(this.imageList.get(row));
        this.hrg1.setText(tabelTabel.getValueAt(row, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tabelTabelMouseClicked

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
    private javax.swing.JLabel hrg1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton menuCamilan;
    private javax.swing.JButton menuMinuman;
    private javax.swing.JLabel nmMKn1;
    private javax.swing.JLabel nomorKamar;
    private javax.swing.JTable tabelTabel;
    // End of variables declaration//GEN-END:variables
}
