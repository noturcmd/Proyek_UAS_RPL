/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import ConnectionMySQL.ConnectionDB;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ACER
 */
public class HomeAdmin extends javax.swing.JFrame {

    Connection koneksi;
    Statement st = null;
    ResultSet rs = null;
    DefaultTableModel tabelMenu = null;
    ArrayList<ImageIcon> imageList = new ArrayList<>();
    ArrayList<String> daftarKeranjang = new ArrayList<>();
    String panelAktif = "Makanan";
    ImageIcon imageIcon = null;
    File f = null;
    File f2 = null;
    
    
    public HomeAdmin() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        getData("Makanan");
    }
    
    void getData(String param){
        this.imageList.clear();
        tabelMenu = (DefaultTableModel) this.tabelMenuAdmin.getModel();
        tabelMenu.setRowCount(0);
        try {
            this.st = this.koneksi.createStatement();
            String query = String.format("select * from menu where jenis = \"%s\"", param);
            System.out.println("query : " + query);
            this.rs = st.executeQuery(query);
            int count = 0;
            while(rs.next()){
                byte[] imageData = null;
                
                if(rs.getString("status").equals("Tidak Tersedia")){
                    imageData = rs.getBytes("gambar_dis");
                }else{
                    imageData = rs.getBytes("gambar");
                }
                
                if(imageData != null){ 
                    imageIcon = new ImageIcon(scaleImage(imageData, 420, 320));
                    this.imageList.add(imageIcon);
                    tabelMenu.addRow(new Object[]{rs.getString("nama"),rs.getString("harga"),rs.getString("status"),rs.getString("deskripsi")});
                }else{
                        this.imageList.add(null);
                        tabelMenu.addRow(new Object[]{rs.getString("nama"),rs.getString("harga"),rs.getString("status"),rs.getString("deskripsi")});
                }
                count++;
            }
        
        this.tabelMenuAdmin.setRowHeight(40);
        
            
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
    
private void updateMenu() {
    try {
        String query = "";
        if(this.f == null && this.f2 != null){
            query = "UPDATE menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, gambar_dis = ? WHERE nama = ?";
        }else if(this.f == null && this.f2 == null){
            query = "UPDATE menu SET nama = ?, harga = ?, status = ?, deskripsi = ? WHERE nama = ?";
        }else if(this.f != null && this.f2 != null){
            query = "UPDATE menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, gambar = ?, gambar_dis = ? WHERE nama = ?";
        }else if(this.f != null && this.f2 == null){
            query = "UPDATE menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, gambar = ? WHERE nama = ?";
        }
        
        // Membuat PreparedStatement
        PreparedStatement pstmt = koneksi.prepareStatement(query);
        
        // Mengatur nilai parameter
        pstmt.setString(1, ubahNama.getText());
        pstmt.setDouble(2, Double.parseDouble(ubahHarga.getText())); // Pastikan harga adalah numerik
        pstmt.setString(3, ubahStatus.getText().toLowerCase());
        pstmt.setString(4, ubahDeskripsi.getText());
        
        // Membaca gambar sebagai byte array
        FileInputStream fis = null;
        if(this.f == null && this.f2 != null){
            System.out.println("Gambar 2");
            this.f2 = new File(namaFileTidakTersedia.getText());
            fis = new FileInputStream(this.f2);
            pstmt.setBinaryStream(5, fis, (int) f2.length());
            pstmt.setString(6, ubahNama.getText());
        }else if(this.f != null && this.f2 != null){
            System.out.println("Tidak ada");
            this.f = new File(namaFile.getText());
            fis = new FileInputStream(this.f);
            pstmt.setBinaryStream(5, fis, (int) f.length());
            
            this.f2 = new File(namaFileTidakTersedia.getText());
            fis = new FileInputStream(this.f2);
            pstmt.setBinaryStream(6, fis, (int) f2.length());
            pstmt.setString(7, ubahNama.getText());
        }else if(this.f != null && this.f2 == null){
            System.out.println("Gambar 1");
            this.f = new File(namaFile.getText());
            fis = new FileInputStream(this.f);
            pstmt.setBinaryStream(5, fis, (int) f.length());
            pstmt.setString(6, ubahNama.getText());
        }else if(this.f == null && this.f2 == null){
            System.out.println("Gambar kosong");
            pstmt.setString(5, ubahNama.getText());
        }
        
        // Menjalankan query
        pstmt.executeUpdate();
        
        // Menutup FileInputStream
        fis.close();
        
        System.out.println("Menu updated successfully.");
        JOptionPane.showMessageDialog(this, "Menu updated successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while updating the menu.");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "File not found.");
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while reading the file.");
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

        lihatGambar1 = new javax.swing.JLabel();
        namaFileTidakTersedia = new javax.swing.JTextField();
        tombolUbahGambarTidakTersedia = new javax.swing.JButton();
        tombolUbahGambar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        riwayat = new javax.swing.JButton();
        keranjang = new javax.swing.JButton();
        menuCamilan = new javax.swing.JButton();
        menuMinuman = new javax.swing.JButton();
        menuMakanan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMenuAdmin = new javax.swing.JTable();
        ubahDeskripsi = new javax.swing.JTextField();
        ubahStatus = new javax.swing.JTextField();
        ubahNama = new javax.swing.JTextField();
        namaFile = new javax.swing.JTextField();
        ubahHarga = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        nomorKamar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(lihatGambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 680, 290, 230));

        namaFileTidakTersedia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        namaFileTidakTersedia.setToolTipText("");
        namaFileTidakTersedia.setBorder(null);
        getContentPane().add(namaFileTidakTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 730, 210, 40));

        tombolUbahGambarTidakTersedia.setText("Tidak Tersedia");
        tombolUbahGambarTidakTersedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahGambarTidakTersediaActionPerformed(evt);
            }
        });
        getContentPane().add(tombolUbahGambarTidakTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1690, 740, 120, 30));

        tombolUbahGambar.setText("Tersedia");
        tombolUbahGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahGambarActionPerformed(evt);
            }
        });
        getContentPane().add(tombolUbahGambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1690, 660, 130, 30));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel5.setText("Deskripsi  :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 570, 80, 40));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel4.setText("Status   :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 490, 70, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel3.setText("Harga     :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 400, 300, 40));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel2.setText("Nama     :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 310, 300, 40));

        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png"))); // NOI18N
        riwayat.setToolTipText("");
        riwayat.setAlignmentY(0.0F);
        riwayat.setContentAreaFilled(false);
        riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayatActionPerformed(evt);
            }
        });
        getContentPane().add(riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 880, -1, -1));

        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png"))); // NOI18N
        keranjang.setToolTipText("");
        keranjang.setAlignmentY(0.0F);
        keranjang.setContentAreaFilled(false);
        keranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keranjangActionPerformed(evt);
            }
        });
        getContentPane().add(keranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 780, -1, -1));

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

        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png"))); // NOI18N
        menuMakanan.setToolTipText("");
        menuMakanan.setAlignmentY(0.0F);
        menuMakanan.setContentAreaFilled(false);
        menuMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMakananActionPerformed(evt);
            }
        });
        getContentPane().add(menuMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 940, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Ubah.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 850, -1, -1));

        tabelMenuAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelMenuAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Harga", "Status", "Deskripsi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelMenuAdmin.setAlignmentX(0.0F);
        tabelMenuAdmin.setAlignmentY(0.0F);
        tabelMenuAdmin.setGridColor(new java.awt.Color(204, 0, 204));
        tabelMenuAdmin.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabelMenuAdmin.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabelMenuAdmin.setShowGrid(true);
        tabelMenuAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMenuAdminMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelMenuAdmin);
        if (tabelMenuAdmin.getColumnModel().getColumnCount() > 0) {
            tabelMenuAdmin.getColumnModel().getColumn(0).setResizable(false);
            tabelMenuAdmin.getColumnModel().getColumn(1).setResizable(false);
            tabelMenuAdmin.getColumnModel().getColumn(2).setResizable(false);
            tabelMenuAdmin.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 690, 300));

        ubahDeskripsi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahDeskripsi.setBorder(null);
        ubahDeskripsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahDeskripsiActionPerformed(evt);
            }
        });
        getContentPane().add(ubahDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 572, 200, 40));

        ubahStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahStatus.setBorder(null);
        ubahStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahStatusActionPerformed(evt);
            }
        });
        getContentPane().add(ubahStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 490, 210, 40));

        ubahNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahNama.setBorder(null);
        getContentPane().add(ubahNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 310, 210, 50));

        namaFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        namaFile.setToolTipText("");
        namaFile.setBorder(null);
        namaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaFileActionPerformed(evt);
            }
        });
        getContentPane().add(namaFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 660, 230, 40));

        ubahHarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahHarga.setBorder(null);
        ubahHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahHargaActionPerformed(evt);
            }
        });
        getContentPane().add(ubahHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 400, 210, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambahadmin.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 850, -1, -1));

        nomorKamar.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        nomorKamar.setForeground(new java.awt.Color(255, 255, 255));
        nomorKamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomorKamar.setText("ADMIN");
        nomorKamar.setAlignmentY(0.0F);
        getContentPane().add(nomorKamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 320, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg home user admin.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ubahStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahStatusActionPerformed

    private void ubahHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahHargaActionPerformed

    private void riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatActionPerformed

    private void keranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keranjangActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang w.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.jScrollPane1.setVisible(false);

    }//GEN-LAST:event_keranjangActionPerformed

    private void menuCamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCamilanActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camil w.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.panelAktif = "Cemilan";
        this.getData("Cemilan");
        
        this.jScrollPane1.setVisible(true);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
    }//GEN-LAST:event_menuCamilanActionPerformed

    private void menuMinumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMinumanActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum w.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.panelAktif = "Minuman";
        this.getData("Minuman");
        this.jScrollPane1.setVisible(true);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
    }//GEN-LAST:event_menuMinumanActionPerformed

    private void menuMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMakananActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.panelAktif = "Makanan";
        this.getData("Makanan");
        this.jScrollPane1.setVisible(true);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
    }//GEN-LAST:event_menuMakananActionPerformed

    private void ubahDeskripsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahDeskripsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahDeskripsiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       if(!this.ubahNama.getText().isEmpty() && !this.ubahHarga.getText().isEmpty() && !this.ubahDeskripsi.getText().isEmpty() && !this.ubahStatus.getText().isEmpty()){
           this.updateMenu();
           this.getData(this.panelAktif);
           this.lihatGambar1.setIcon(null);
       }else{
           JOptionPane.showMessageDialog(this, "Kolom dan gambar tidak boleh kosong!");
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabelMenuAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMenuAdminMouseClicked
        // TODO add your handling code here:
        int row = this.tabelMenuAdmin.getSelectedRow();
this.ubahNama.setText((String) this.tabelMenuAdmin.getValueAt(row, 0));
this.ubahHarga.setText((String) this.tabelMenuAdmin.getValueAt(row, 1));
this.ubahStatus.setText((String) this.tabelMenuAdmin.getValueAt(row, 2));
this.ubahDeskripsi.setText((String) this.tabelMenuAdmin.getValueAt(row, 3));

try {
    this.st = this.koneksi.createStatement();
    String query = String.format("select * from menu where nama = \"%s\"", this.ubahNama.getText());
    System.out.println("query : " + query);
    this.rs = st.executeQuery(query);
    if(rs.next()){
        byte[] imgIc = null;
        ImageIcon imgIcon = null;
        if(rs.getString("status").equals("Tidak Tersedia".toLowerCase())){
                    imgIc = rs.getBytes("gambar_dis");
                    imgIcon = new ImageIcon(scaleImage(imgIc, 420, 320));
                    lihatGambar1.setIcon(imgIcon);
                }else if(rs.getString("status").equals("Tersedia".toLowerCase())){
                    imgIc = rs.getBytes("gambar");
                    imgIcon = new ImageIcon(scaleImage(imgIc, 420, 320));
                    lihatGambar1.setIcon(imgIcon);
                }
    }
}catch (SQLException e){
    e.printStackTrace();
}

        
    }//GEN-LAST:event_tabelMenuAdminMouseClicked

    private void tombolUbahGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahGambarActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setPreferredSize(new java.awt.Dimension(750, 700)); // Set preferred size

        // Menambahkan filter untuk format gambar
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);

        int returnValue = chooser.showOpenDialog(chooser);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.f = chooser.getSelectedFile();
            if (f != null) {
                String pathFile = f.getAbsolutePath();
                this.namaFile.setText(pathFile);
            } else {
                this.namaFile.setText("No file selected");
            }
        } else {
            this.namaFile.setText("No file selected");
        }

    }//GEN-LAST:event_tombolUbahGambarActionPerformed

    private void tombolUbahGambarTidakTersediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahGambarTidakTersediaActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setPreferredSize(new java.awt.Dimension(750, 700)); // Set preferred size

        // Menambahkan filter untuk format gambar
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);

        int returnValue = chooser.showOpenDialog(chooser);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.f2 = chooser.getSelectedFile();
            if (f != null) {
                String pathFile = f.getAbsolutePath();
                this.namaFileTidakTersedia.setText(pathFile);
            } else {
                this.namaFileTidakTersedia.setText("No file selected");
            }
        } else {
            this.namaFileTidakTersedia.setText("No file selected");
        }
    }//GEN-LAST:event_tombolUbahGambarTidakTersediaActionPerformed

    private void namaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaFileActionPerformed

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
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeAdmin().setVisible(true);
            }
        });
    }
    
    

class CustomTableModel extends AbstractTableModel {
    private Object[][] data;
    private String[] columnNames;

    public CustomTableModel(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 4) { // Kolom gambar
            return ImageIcon.class;
        } else {
            return super.getColumnClass(col);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false; // Ubah sesuai kebutuhan Anda
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton keranjang;
    private javax.swing.JLabel lihatGambar1;
    private javax.swing.JButton menuCamilan;
    private javax.swing.JButton menuMakanan;
    private javax.swing.JButton menuMinuman;
    private javax.swing.JTextField namaFile;
    private javax.swing.JTextField namaFileTidakTersedia;
    private javax.swing.JLabel nomorKamar;
    private javax.swing.JButton riwayat;
    private javax.swing.JTable tabelMenuAdmin;
    private javax.swing.JButton tombolUbahGambar;
    private javax.swing.JButton tombolUbahGambarTidakTersedia;
    private javax.swing.JTextField ubahDeskripsi;
    private javax.swing.JTextField ubahHarga;
    private javax.swing.JTextField ubahNama;
    private javax.swing.JTextField ubahStatus;
    // End of variables declaration//GEN-END:variables
}
