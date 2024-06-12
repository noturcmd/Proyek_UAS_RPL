/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import ConnectionMySQL.ConnectionDB;
import java.awt.Color;
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
import javax.swing.border.LineBorder;
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
    
    private String adminnya;
    String usernameAdminnya;
    DefaultTableModel tabelMenu = null;
    DefaultTableModel tabelEditUser = null;
    DefaultTableModel tabelEditAdmin = null;
    DefaultTableModel tabelEditPesanan = null;
    DefaultTableModel tabelEditRiwayat = null;
    ArrayList<ImageIcon> imageList = new ArrayList<>();
    ArrayList<String> daftarKeranjang = new ArrayList<>();
    String panelAktif = "Makanan";
    ImageIcon imageIcon = null;
    File f = null;
    File f2 = null;
    
    Login log1 = null;
    
    Integer barisEditAkun = null;
    
    Integer barisUntukHapus = null;
    
    Integer barisEditPesanan = null;
    
    
    public HomeAdmin() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        getData("Makanan");
        this.panelDataUser.setVisible(false);
        this.panelPesanandanRiwayat.setVisible(false);
        this.idAkun.setEditable(false);
        try {
                this.st = this.koneksi.createStatement();
                String query = String.format("select * from transaksi where status = \"dikirim\" or status = \"diproses\";");
                System.out.println("query : " + query);
                this.rs = st.executeQuery(query);
                if(rs.next()){
                    this.pesananDanRiwayat.setBorder(new LineBorder(Color.GREEN, 2));
                }
                
                this.tabelPesanan.setRowHeight(40);
            }catch(SQLException e){
                e.printStackTrace();
            }
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
    
    String getUsernameAdmin(){
        return this.adminnya;
    }
    
    void setUsernameAdmin(String adminnya){
        this.adminnya = adminnya;
        this.usernameAdmin.setText(this.adminnya);
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
    
    void clear(){
        this.idAkun.setText("");
        this.namaAkun.setText("");
        this.passwordAkun.setText("");
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
        JOptionPane.showMessageDialog(this, "Menu berhasil diperbarui!");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi eror saat mencoba mengubah menu!");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "File tidak tersedia!");
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi eror saat mencoba membaca file!");
    }
}


        void tambahMenu(){
            try {
                String query = "";
                if(this.f == null && this.f2 != null){
                    query = "insert into menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, gambar_dis = ?, jenis = ?";
                }else if(this.f == null && this.f2 == null){
                    query = "insert into menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, jenis = ?";
                }else if(this.f != null && this.f2 != null){
                    query = "insert into menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, gambar = ?, gambar_dis = ?, jenis = ?";
                }else if(this.f != null && this.f2 == null){
                    query = "insert into menu SET nama = ?, harga = ?, status = ?, deskripsi = ?, gambar = ?,jenis = ?";
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
                    pstmt.setString(6, this.panelAktif);
                }else if(this.f != null && this.f2 != null){
                    System.out.println("Tidak ada");
                    this.f = new File(namaFile.getText());
                    fis = new FileInputStream(this.f);
                    pstmt.setBinaryStream(5, fis, (int) f.length());

                    this.f2 = new File(namaFileTidakTersedia.getText());
                    fis = new FileInputStream(this.f2);
                    pstmt.setBinaryStream(6, fis, (int) f2.length());
                    pstmt.setString(7, this.panelAktif);
                }else if(this.f != null && this.f2 == null){
                    System.out.println("Gambar 1");
                    this.f = new File(namaFile.getText());
                    fis = new FileInputStream(this.f);
                    pstmt.setBinaryStream(5, fis, (int) f.length());
                    pstmt.setString(6, this.panelAktif);
                }else if(this.f == null && this.f2 == null){
                    System.out.println("Gambar kosong");
                    pstmt.setString(5, this.panelAktif);
                }

                // Menjalankan query
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Menu berhasil ditambahkan!");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Terjadi eror saat menambahkan menu!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "File tidak tersedia!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Terjadi eror saat membaca file!");
            }
        }
        
       private void updateAdmin() {
    try {
        // Buat statement dan query untuk memeriksa username yang sudah ada di database
        this.st = this.koneksi.createStatement();
        String query = String.format("select * from daftar_akun where status = \"admin\" and username=\"%s\";", namaAkun.getText());
        this.rs = st.executeQuery(query);

        if(rs.next()) {
            // Jika username sudah ada, tampilkan pesan kesalahan
            JOptionPane.showMessageDialog(this, "Username sudah ada! Silakan pilih username lain.");
        } else {
            // Jika username belum ada, lakukan pembaruan data admin
            query = "UPDATE daftar_akun SET username = ?, password = ? WHERE id = ?";

            // Membuat PreparedStatement
            PreparedStatement pstmt = koneksi.prepareStatement(query);

            // Mengatur nilai parameter
            pstmt.setString(1, namaAkun.getText());
            pstmt.setString(2, passwordAkun.getText());
            pstmt.setString(3, idAkun.getText());

            // Menjalankan query
            pstmt.executeUpdate();

            System.out.println("Admin account updated successfully.");
            JOptionPane.showMessageDialog(this, "Sukses memperbarui akun admin.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memperbarui akun admin.");
    }
}
        void showUser(){
            this.tabelEditUser = (DefaultTableModel) this.tabelUser.getModel();
            this.tabelEditUser.setRowCount(0);
            try {
                this.st = this.koneksi.createStatement();
                String query = String.format("select * from daftar_akun where status = \"kamar\";");
                System.out.println("query : " + query);
                this.rs = st.executeQuery(query);
                while(rs.next()){
                    this.tabelEditUser.addRow(new Object[]{rs.getString("id"), rs.getString("username"), rs.getString("password")});
                }
                
                this.tabelUser.setRowHeight(40);
            }catch(SQLException e){
                e.printStackTrace();
            }
            this.tabelUser.setVisible(true);
        }
        
        void showAdmin(){
            this.tabelEditAdmin = (DefaultTableModel) this.tabelAdmin.getModel();
            this.tabelEditAdmin.setRowCount(0);
            try {
                this.st = this.koneksi.createStatement();
                String query = String.format("select * from daftar_akun where status = \"admin\";");
                this.rs = st.executeQuery(query);
                while(rs.next()){
                    this.tabelEditAdmin.addRow(new Object[]{rs.getString("id"), rs.getString("username"), rs.getString("password")});
                }
                
                this.tabelAdmin.setRowHeight(40);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        void showPesanan(){
            this.tabelEditPesanan = (DefaultTableModel) this.tabelPesanan.getModel();
            this.tabelEditPesanan.setRowCount(0);
            try {
                this.st = this.koneksi.createStatement();
                String query = String.format("select * from transaksi where status = \"dikirim\" or status = \"diproses\";");
                System.out.println("query : " + query);
                this.rs = st.executeQuery(query);
                while(rs.next()){
                    this.tabelEditPesanan.addRow(new Object[]{rs.getString("id_transaksi"), rs.getString("admin"), rs.getString("pembeli"),rs.getString("pesanan"),rs.getString("deskripsi"), rs.getString("harga"), rs.getString("status"),rs.getString("tanggalpesan")});
                }
                
                this.st = this.koneksi.createStatement();
                query = String.format("select * from transaksi where status = \"dikirim\" or status = \"diproses\";");
                System.out.println("query : " + query);
                this.rs = st.executeQuery(query);
                if(!rs.next()){
                    this.pesananDanRiwayat.setBorder(null);
                }
                
                this.tabelPesanan.setRowHeight(40);
                
                this.tabelPesanan.setRowHeight(40);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        private void updatePesanan(){
    try {
        this.st = this.koneksi.createStatement();
        String query = String.format("select * from transaksi where id_transaksi = \"%s\";", idPesanan.getText());
        this.rs = st.executeQuery(query);
        if(rs.next()){
                System.out.println("Ga Sama");
                query = "UPDATE transaksi SET admin = ?, status = ?, tanggalpesan = current_timestamp() WHERE id_transaksi = ?";

                // Membuat PreparedStatement
                PreparedStatement pstmt = koneksi.prepareStatement(query);

                // Mengatur nilai parameter
                pstmt.setString(1, this.usernameAdminnya);
                pstmt.setString(2, (String) this.editStatusPesanan.getSelectedItem()); // Pastikan harga adalah numerik
                pstmt.setString(3, idPesanan.getText());

                // Menjalankan query
                pstmt.executeUpdate();

                System.out.println("Menu updated successfully.");
                JOptionPane.showMessageDialog(this, "Sukses memperbarui status pesanan");
        }else{
            JOptionPane.showMessageDialog(this, "Tidak bisa mengubah data yang tidak ada di dalam database!");
        }
        
        
        
        
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ada eror ketika memperbarui data pesanan!");
    } 
}
        
        void showRiwayat(){
            this.tabelEditRiwayat = (DefaultTableModel) this.tabelRiwayat.getModel();
            this.tabelEditRiwayat.setRowCount(0);
            try {
                this.st = this.koneksi.createStatement();
                String query = String.format("select * from transaksi where status = \"selesai\";");
                System.out.println("query : " + query);
                this.rs = st.executeQuery(query);
                while(rs.next()){
                    this.tabelEditRiwayat.addRow(new Object[]{rs.getString("id_transaksi"), rs.getString("admin"), rs.getString("pembeli"),rs.getString("pesanan"),rs.getString("deskripsi"), rs.getString("harga"), rs.getString("status"),rs.getString("tanggalpesan")});
                }
                
                this.tabelRiwayat.setRowHeight(40);
            }catch(SQLException e){
                e.printStackTrace();
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

        panelPesanandanRiwayat = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelPesanan = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelRiwayat = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        idPesanan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tblUbahPesanan = new javax.swing.JButton();
        editStatusPesanan = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        kolomDeskripsi = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        kolomPesanan = new javax.swing.JTextArea();
        panelDataUser = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelUser = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelAdmin = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        idAkun = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        namaAkun = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        passwordAkun = new javax.swing.JTextField();
        tblUbahAdmin = new javax.swing.JButton();
        tombolLogout = new javax.swing.JButton();
        ubahJenis = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tombolUbahGambarTidakTersedia = new javax.swing.JButton();
        namaFileTidakTersedia = new javax.swing.JTextField();
        tombolUbahGambar = new javax.swing.JButton();
        tombolRefresh = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        tombolUbahMenu = new javax.swing.JButton();
        tombolTambahMenu = new javax.swing.JButton();
        lihatGambar1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        editAkun = new javax.swing.JButton();
        pesananDanRiwayat = new javax.swing.JButton();
        menuCamilan = new javax.swing.JButton();
        menuMinuman = new javax.swing.JButton();
        menuMakanan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMenuAdmin = new javax.swing.JTable();
        ubahDeskripsi = new javax.swing.JTextField();
        ubahStatus = new javax.swing.JTextField();
        ubahNama = new javax.swing.JTextField();
        namaFile = new javax.swing.JTextField();
        ubahHarga = new javax.swing.JTextField();
        usernameAdmin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPesanandanRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        panelPesanandanRiwayat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelPesanan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Admin", "Pembeli", "Pesanan", "Deskripsi", "Harga", "Status", "Tanggal Pesan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPesanan.setAlignmentX(0.0F);
        tabelPesanan.setAlignmentY(0.0F);
        tabelPesanan.setGridColor(new java.awt.Color(204, 0, 204));
        tabelPesanan.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabelPesanan.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabelPesanan.setShowGrid(true);
        tabelPesanan.getTableHeader().setReorderingAllowed(false);
        tabelPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPesananMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelPesanan);
        if (tabelPesanan.getColumnModel().getColumnCount() > 0) {
            tabelPesanan.getColumnModel().getColumn(0).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(1).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(2).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(3).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(4).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(5).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(6).setResizable(false);
            tabelPesanan.getColumnModel().getColumn(7).setResizable(false);
        }

        panelPesanandanRiwayat.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 1140, 300));

        tabelRiwayat.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Admin", "Pembeli", "Pesanan", "Deskripsi", "Harga", "Status", "Tanggal Pesan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelRiwayat.setAlignmentX(0.0F);
        tabelRiwayat.setAlignmentY(0.0F);
        tabelRiwayat.setGridColor(new java.awt.Color(204, 0, 204));
        tabelRiwayat.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabelRiwayat.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabelRiwayat.setShowGrid(true);
        tabelRiwayat.getTableHeader().setReorderingAllowed(false);
        tabelRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelRiwayatMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelRiwayat);
        if (tabelRiwayat.getColumnModel().getColumnCount() > 0) {
            tabelRiwayat.getColumnModel().getColumn(0).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(1).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(2).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(3).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(4).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(5).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(6).setResizable(false);
            tabelRiwayat.getColumnModel().getColumn(7).setResizable(false);
        }

        panelPesanandanRiwayat.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 1140, 300));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Pesanan dan Riwayat");
        panelPesanandanRiwayat.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 540, 90));

        jLabel12.setText("ID : ");
        panelPesanandanRiwayat.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 50, 30));
        panelPesanandanRiwayat.add(idPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 140, 30));

        jLabel13.setText("Status :");
        panelPesanandanRiwayat.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 50, 30));

        tblUbahPesanan.setBackground(new java.awt.Color(125, 0, 124));
        tblUbahPesanan.setForeground(new java.awt.Color(255, 255, 255));
        tblUbahPesanan.setText("Ubah");
        tblUbahPesanan.setToolTipText("");
        tblUbahPesanan.setAlignmentY(0.0F);
        tblUbahPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblUbahPesananActionPerformed(evt);
            }
        });
        panelPesanandanRiwayat.add(tblUbahPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 520, 120, 30));

        editStatusPesanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "dikirim", "diproses", "selesai" }));
        panelPesanandanRiwayat.add(editStatusPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, 130, -1));

        kolomDeskripsi.setColumns(20);
        kolomDeskripsi.setLineWrap(true);
        kolomDeskripsi.setRows(5);
        jScrollPane6.setViewportView(kolomDeskripsi);

        panelPesanandanRiwayat.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 480, 230, 120));

        kolomPesanan.setColumns(20);
        kolomPesanan.setLineWrap(true);
        kolomPesanan.setRows(5);
        jScrollPane7.setViewportView(kolomPesanan);

        panelPesanandanRiwayat.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 240, 120));

        getContentPane().add(panelPesanandanRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 1920, 1080));

        panelDataUser.setBackground(new java.awt.Color(255, 255, 255));
        panelDataUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelUser.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelUser.setAlignmentX(0.0F);
        tabelUser.setAlignmentY(0.0F);
        tabelUser.setGridColor(new java.awt.Color(204, 0, 204));
        tabelUser.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tabelUser.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabelUser.setShowGrid(true);
        tabelUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelUserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelUser);
        if (tabelUser.getColumnModel().getColumnCount() > 0) {
            tabelUser.getColumnModel().getColumn(0).setResizable(false);
            tabelUser.getColumnModel().getColumn(1).setResizable(false);
            tabelUser.getColumnModel().getColumn(2).setResizable(false);
        }

        panelDataUser.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 1140, 300));

        tabelAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tabelAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelAdmin.setAlignmentX(0.0F);
        tabelAdmin.setAlignmentY(0.0F);
        tabelAdmin.setGridColor(new java.awt.Color(204, 0, 204));
        tabelAdmin.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tabelAdmin.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabelAdmin.setShowGrid(true);
        tabelAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAdminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelAdmin);
        if (tabelAdmin.getColumnModel().getColumnCount() > 0) {
            tabelAdmin.getColumnModel().getColumn(0).setResizable(false);
            tabelAdmin.getColumnModel().getColumn(1).setResizable(false);
            tabelAdmin.getColumnModel().getColumn(2).setResizable(false);
        }

        panelDataUser.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 1140, 300));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Data User dan Admin");
        panelDataUser.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 540, 90));

        jLabel8.setText("ID : ");
        panelDataUser.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 50, 30));
        panelDataUser.add(idAkun, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 140, 30));

        jLabel9.setText("Nama  :");
        panelDataUser.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 50, 30));
        panelDataUser.add(namaAkun, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, 140, 30));

        jLabel10.setText("Password :");
        panelDataUser.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 70, 30));
        panelDataUser.add(passwordAkun, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 520, 140, 30));

        tblUbahAdmin.setBackground(new java.awt.Color(125, 0, 124));
        tblUbahAdmin.setForeground(new java.awt.Color(255, 255, 255));
        tblUbahAdmin.setText("Ubah");
        tblUbahAdmin.setToolTipText("");
        tblUbahAdmin.setAlignmentY(0.0F);
        tblUbahAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblUbahAdminActionPerformed(evt);
            }
        });
        panelDataUser.add(tblUbahAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 520, 170, 30));

        getContentPane().add(panelDataUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 1200, 950));

        tombolLogout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tombolLogout.setText("Logout");
        tombolLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(tombolLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1010, 100, 40));

        ubahJenis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahJenis.setBorder(null);
        ubahJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahJenisActionPerformed(evt);
            }
        });
        getContentPane().add(ubahJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 660, 200, 40));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel6.setText("Gambar : ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 770, 80, 40));

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel14.setText("Jenis : ");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 660, 80, 40));

        tombolUbahGambarTidakTersedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tidaktersediabutton.png"))); // NOI18N
        tombolUbahGambarTidakTersedia.setBorder(null);
        tombolUbahGambarTidakTersedia.setContentAreaFilled(false);
        tombolUbahGambarTidakTersedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahGambarTidakTersediaActionPerformed(evt);
            }
        });
        getContentPane().add(tombolUbahGambarTidakTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 810, -1, 60));

        namaFileTidakTersedia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        namaFileTidakTersedia.setToolTipText("");
        namaFileTidakTersedia.setBorder(null);
        getContentPane().add(namaFileTidakTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 830, 210, 40));

        tombolUbahGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tersediabutton.png"))); // NOI18N
        tombolUbahGambar.setBorder(null);
        tombolUbahGambar.setContentAreaFilled(false);
        tombolUbahGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahGambarActionPerformed(evt);
            }
        });
        getContentPane().add(tombolUbahGambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 730, 160, 60));

        tombolRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        tombolRefresh.setContentAreaFilled(false);
        tombolRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(tombolRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 880, -1, 60));

        tombolHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hapus.png"))); // NOI18N
        tombolHapus.setBorder(null);
        tombolHapus.setContentAreaFilled(false);
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });
        getContentPane().add(tombolHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 880, -1, -1));

        tombolUbahMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Ubah.png"))); // NOI18N
        tombolUbahMenu.setContentAreaFilled(false);
        tombolUbahMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahMenuActionPerformed(evt);
            }
        });
        getContentPane().add(tombolUbahMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 880, -1, 60));

        tombolTambahMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambahadmin.png"))); // NOI18N
        tombolTambahMenu.setContentAreaFilled(false);
        tombolTambahMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolTambahMenuActionPerformed(evt);
            }
        });
        getContentPane().add(tombolTambahMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 880, -1, 60));
        getContentPane().add(lihatGambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 740, 180, 120));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel5.setText("Deskripsi  :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 570, 80, 40));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel4.setText("Status   :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 490, 70, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel3.setText("Harga     :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 400, 80, 40));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel2.setText("Nama     :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 310, 80, 40));

        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editakun b.png"))); // NOI18N
        editAkun.setToolTipText("");
        editAkun.setAlignmentY(0.0F);
        editAkun.setContentAreaFilled(false);
        editAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAkunActionPerformed(evt);
            }
        });
        getContentPane().add(editAkun, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 890, -1, -1));

        pesananDanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png"))); // NOI18N
        pesananDanRiwayat.setToolTipText("");
        pesananDanRiwayat.setAlignmentY(0.0F);
        pesananDanRiwayat.setContentAreaFilled(false);
        pesananDanRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesananDanRiwayatActionPerformed(evt);
            }
        });
        getContentPane().add(pesananDanRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 780, -1, -1));

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
            tabelMenuAdmin.getColumnModel().getColumn(3).setHeaderValue("Deskripsi");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 690, 420));

        ubahDeskripsi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahDeskripsi.setBorder(null);
        ubahDeskripsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahDeskripsiActionPerformed(evt);
            }
        });
        getContentPane().add(ubahDeskripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 570, 200, 40));

        ubahStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahStatus.setBorder(null);
        ubahStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahStatusActionPerformed(evt);
            }
        });
        getContentPane().add(ubahStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 490, 210, 40));

        ubahNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahNama.setBorder(null);
        getContentPane().add(ubahNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 310, 210, 40));

        namaFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        namaFile.setToolTipText("");
        namaFile.setBorder(null);
        namaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaFileActionPerformed(evt);
            }
        });
        getContentPane().add(namaFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 770, 230, 40));

        ubahHarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ubahHarga.setBorder(null);
        ubahHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahHargaActionPerformed(evt);
            }
        });
        getContentPane().add(ubahHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 400, 180, 40));

        usernameAdmin.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        usernameAdmin.setForeground(new java.awt.Color(255, 255, 255));
        usernameAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameAdmin.setText("ADMIN");
        usernameAdmin.setAlignmentY(0.0F);
        getContentPane().add(usernameAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 420, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg home user.png"))); // NOI18N
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

    private void editAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAkunActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        pesananDanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editakun w.png")));
        this.jScrollPane1.setVisible(false);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
        this.ubahJenis.setText("");
        this.panelDataUser.setVisible(true);
        this.jScrollPane2.setVisible(true);
        showUser();
        showAdmin();
        this.panelPesanandanRiwayat.setVisible(false);
        
        
    }//GEN-LAST:event_editAkunActionPerformed

    private void pesananDanRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesananDanRiwayatActionPerformed
        // TODO add your handling code here:
        
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        pesananDanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan w.png")));
        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editakun b.png")));
        this.jScrollPane2.setVisible(false);
        this.panelDataUser.setVisible(false);
        this.panelPesanandanRiwayat.setVisible(true);
        showPesanan();
        showRiwayat();
        
    }//GEN-LAST:event_pesananDanRiwayatActionPerformed

    private void menuCamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCamilanActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camil w.png")));
        pesananDanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editakun b.png")));
        this.panelAktif = "Cemilan";
        this.getData("Cemilan");
        
        this.jScrollPane1.setVisible(true);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
        this.ubahJenis.setText("");
        this.panelDataUser.setVisible(false);
        this.panelPesanandanRiwayat.setVisible(false);
    }//GEN-LAST:event_menuCamilanActionPerformed

    private void menuMinumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMinumanActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum w.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        pesananDanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editakun b.png")));
        this.panelAktif = "Minuman";
        this.getData("Minuman");
        this.lihatGambar1.setIcon(null);
        this.lihatGambar1.setIcon(null);
        this.jScrollPane1.setVisible(true);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
        this.ubahJenis.setText("");
        this.panelDataUser.setVisible(false);
        this.panelPesanandanRiwayat.setVisible(false);
    }//GEN-LAST:event_menuMinumanActionPerformed

    private void menuMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMakananActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        pesananDanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        editAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editakun b.png")));
        this.panelAktif = "Makanan";
        this.getData("Makanan");
        this.jScrollPane1.setVisible(true);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
        this.ubahJenis.setText("");
        this.panelDataUser.setVisible(false);
        this.panelPesanandanRiwayat.setVisible(false);
    }//GEN-LAST:event_menuMakananActionPerformed

    private void ubahDeskripsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahDeskripsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahDeskripsiActionPerformed

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        // TODO add your handling code here:
        try {
            
            if(!this.ubahNama.getText().isEmpty()){
                this.st = this.koneksi.createStatement();
                String query = String.format("select * from menu where nama = \"%s\"", this.ubahNama.getText());
                System.out.println("query : " + query);
                this.rs = st.executeQuery(query);
                if(rs.next()){
                    query = String.format("delete from menu where nama = \"%s\"", this.ubahNama.getText());
                    System.out.println("query : " + query);
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(this, "Menu berhasil dihapus!");
                    this.getData(this.panelAktif);
                }else{
                    JOptionPane.showMessageDialog(this, "Data tidak bisa dihapus! Pastikan menghapus data yang terdaftar di database!");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Kolom nama tidak boleh kosong!");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void tombolTambahMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolTambahMenuActionPerformed
        // TODO add your handling code here:
        
        
        try {
            if(!this.ubahNama.getText().isEmpty() && !this.ubahHarga.getText().isEmpty() && !this.ubahDeskripsi.getText().isEmpty() && !this.ubahStatus.getText().isEmpty()){
            this.st = this.koneksi.createStatement();
              String query = String.format("select * from menu where nama = \"%s\";", this.ubahNama.getText());
              System.out.println("query : " + query);
              this.rs = st.executeQuery(query);
              if(rs.next()){
                  JOptionPane.showMessageDialog(this, "Menu dengan nama yang sama telah tersedia!");
              }else{
                  this.tambahMenu();
                  this.getData(this.panelAktif);
              }
          }   
        }catch (SQLException e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_tombolTambahMenuActionPerformed

    private void tombolUbahMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahMenuActionPerformed
        // TODO add your handling code here:
       if(!this.ubahNama.getText().isEmpty() && !this.ubahHarga.getText().isEmpty() && !this.ubahDeskripsi.getText().isEmpty() && !this.ubahStatus.getText().isEmpty()){
           this.updateMenu();
           this.getData(this.panelAktif);
           this.lihatGambar1.setIcon(null);
       }else{
           JOptionPane.showMessageDialog(this, "Kolom tidak boleh kosong!");
       }
    }//GEN-LAST:event_tombolUbahMenuActionPerformed

    private void tabelMenuAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMenuAdminMouseClicked
        // TODO add your handling code here:
        this.barisUntukHapus = this.tabelMenuAdmin.getSelectedRow();
this.ubahNama.setText((String) this.tabelMenuAdmin.getValueAt(barisUntukHapus, 0));
this.ubahHarga.setText((String) this.tabelMenuAdmin.getValueAt(barisUntukHapus, 1));
this.ubahStatus.setText((String) this.tabelMenuAdmin.getValueAt(barisUntukHapus, 2));
this.ubahDeskripsi.setText((String) this.tabelMenuAdmin.getValueAt(barisUntukHapus, 3));
this.ubahJenis.setText(this.panelAktif);

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
                    if(imgIc != null){
                       imgIcon = new ImageIcon(scaleImage(imgIc, 420, 320)); 
                       lihatGambar1.setIcon(imgIcon);
                    }else{
                        lihatGambar1.setIcon(null);
                    }
                    
                }else if(rs.getString("status").equals("Tersedia".toLowerCase())){
                    imgIc = rs.getBytes("gambar");
                    if(imgIc != null){
                       imgIcon = new ImageIcon(scaleImage(imgIc, 420, 320)); 
                       lihatGambar1.setIcon(imgIcon);
                    }else{
                        lihatGambar1.setIcon(imgIcon);
                    }
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

    private void ubahJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahJenisActionPerformed

    private void tombolRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolRefreshActionPerformed
        // TODO add your handling code here:
        getData(this.panelAktif);
        this.ubahNama.setText("");
        this.ubahHarga.setText("");
        this.ubahStatus.setText("");
        this.ubahDeskripsi.setText("");
        this.ubahJenis.setText("");
        this.namaFile.setText("");
        this.namaFileTidakTersedia.setText("");
        this.f = null;
        this.f2 = null;
        this.lihatGambar1.setIcon(null);
    }//GEN-LAST:event_tombolRefreshActionPerformed

    private void tombolLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolLogoutActionPerformed
        // TODO add your handling code here:
        Login logout = new Login();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_tombolLogoutActionPerformed

    private void tabelUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelUserMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabelUserMouseClicked

    private void tabelAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAdminMouseClicked
        // TODO add your handling code here:
        this.barisEditAkun = this.tabelAdmin.getSelectedRow();
        this.idAkun.setText((String) this.tabelAdmin.getValueAt(barisEditAkun, 0));
        this.namaAkun.setText((String) this.tabelAdmin.getValueAt(barisEditAkun, 1));
        this.passwordAkun.setText((String) this.tabelAdmin.getValueAt(barisEditAkun, 2));
    }//GEN-LAST:event_tabelAdminMouseClicked

    private void tblUbahAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblUbahAdminActionPerformed
        // TODO add your handling code here:
        if(!this.namaAkun.getText().isEmpty() && !this.passwordAkun.getText().isEmpty()){
                    System.out.println("Sini broooo");
                    this.updateAdmin();
                    this.showAdmin();
                    
                    clear();   
       }else{
           JOptionPane.showMessageDialog(this, "Kolom tidak boleh kosong!");
       }
    }//GEN-LAST:event_tblUbahAdminActionPerformed

    private void tabelPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPesananMouseClicked
        // TODO add your handling code here:
        this.barisEditPesanan = this.tabelPesanan.getSelectedRow();
        this.editStatusPesanan.setSelectedItem((String) this.tabelPesanan.getValueAt(this.barisEditPesanan, 6));
        this.idPesanan.setText((String) this.tabelPesanan.getValueAt(this.barisEditPesanan, 0));
        this.kolomDeskripsi.setText((String) this.tabelPesanan.getValueAt(this.barisEditPesanan, 4));
        String pesanannya = "";
        String[] teksPerMakanan = String.valueOf(this.tabelPesanan.getValueAt(this.barisEditPesanan, 3)).split("~");
        for (String nama : teksPerMakanan) {
            String[] jumlahPesananDanMenunya = nama.split(":");
            if(jumlahPesananDanMenunya.length > 1){
                pesanannya += String.format("Pesanan %s, jumlah %s\n", jumlahPesananDanMenunya[1], jumlahPesananDanMenunya[0]);
            }
        }
        
        this.kolomPesanan.setText(pesanannya);
    }//GEN-LAST:event_tabelPesananMouseClicked

    private void tabelRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelRiwayatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelRiwayatMouseClicked

    private void tblUbahPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblUbahPesananActionPerformed
        // TODO add your handling code here:
        if(!this.idPesanan.getText().isEmpty()){
                  this.updatePesanan();
                  this.idPesanan.setText("");
                    this.showPesanan();
                    this.showRiwayat();
                    if(this.editStatusPesanan.getSelectedItem().toString().equals("selesai")){
                        this.kolomDeskripsi.setText("");
                        this.kolomPesanan.setText("");
                    }
                    this.idPesanan.setText("");
                    this.editStatusPesanan.setSelectedIndex(0);
                    
       }else{
           JOptionPane.showMessageDialog(this, "Kolom tidak boleh kosong!");
       }
    }//GEN-LAST:event_tblUbahPesananActionPerformed

    
    

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
    private javax.swing.JButton editAkun;
    private javax.swing.JComboBox<String> editStatusPesanan;
    private javax.swing.JTextField idAkun;
    private javax.swing.JTextField idPesanan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea kolomDeskripsi;
    private javax.swing.JTextArea kolomPesanan;
    private javax.swing.JLabel lihatGambar1;
    private javax.swing.JButton menuCamilan;
    private javax.swing.JButton menuMakanan;
    private javax.swing.JButton menuMinuman;
    private javax.swing.JTextField namaAkun;
    private javax.swing.JTextField namaFile;
    private javax.swing.JTextField namaFileTidakTersedia;
    private javax.swing.JPanel panelDataUser;
    private javax.swing.JPanel panelPesanandanRiwayat;
    private javax.swing.JTextField passwordAkun;
    private javax.swing.JButton pesananDanRiwayat;
    private javax.swing.JTable tabelAdmin;
    private javax.swing.JTable tabelMenuAdmin;
    private javax.swing.JTable tabelPesanan;
    private javax.swing.JTable tabelRiwayat;
    private javax.swing.JTable tabelUser;
    private javax.swing.JButton tblUbahAdmin;
    private javax.swing.JButton tblUbahPesanan;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolLogout;
    private javax.swing.JButton tombolRefresh;
    private javax.swing.JButton tombolTambahMenu;
    private javax.swing.JButton tombolUbahGambar;
    private javax.swing.JButton tombolUbahGambarTidakTersedia;
    private javax.swing.JButton tombolUbahMenu;
    private javax.swing.JTextField ubahDeskripsi;
    private javax.swing.JTextField ubahHarga;
    private javax.swing.JTextField ubahJenis;
    private javax.swing.JTextField ubahNama;
    private javax.swing.JTextField ubahStatus;
    private javax.swing.JLabel usernameAdmin;
    // End of variables declaration//GEN-END:variables
}
