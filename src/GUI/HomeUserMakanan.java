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
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
    DefaultTableModel tabelUntukKeranjang = null;
    ArrayList<ImageIcon> imageList = new ArrayList<>();
    ArrayList<String> daftarKeranjang = new ArrayList<>();
    String jenisPesanan = null;
    DefaultTableModel tabelPesanan = null;
    DefaultTableModel tabelRiwayatPesanan = null;
    int row;
    
    
    int total = 0;
    
    Integer row2 = null;
    
    int addKurang = 0;

    /**
     * Creates new form HomeUserMakanan
     */
    public HomeUserMakanan() {
        this.koneksi = ConnectionDB.getInstance().getConnection();
        initComponents();
        this.jenisPesanan = "Makanan";
        getData(jenisPesanan);
        this.gbr1.setIcon(this.imageList.get(0));
        this.hrg1.setText(tabelTabel.getValueAt(0, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(0, 0).toString());
        this.tabelTabel.setBackground(new Color(255, 255, 255));
        this.setVisible(false);
        this.panelKeranjang.setVisible(false);
        this.panelProsesdanPesanan.setVisible(false);
    }
    
    String getNomorKamar(){
        return this.kamar;
    }
    
    void setNomorKamar(String kamar){
        this.kamar = kamar;
        this.nomorKamar.setText(this.kamar);
    }
    

    
    void getData(String param){
        this.imageList.clear();
        tabelMenu = (DefaultTableModel) this.tabelTabel.getModel();
        tabelMenu.setRowCount(0);
        try {
            this.st = this.koneksi.createStatement();
            String query = String.format("select * from menu where jenis = \"%s\" and status =\"tersedia\"", param);
            System.out.println("query : " + query);
            this.rs = st.executeQuery(query);
            while(rs.next()){
                byte[] imageData= null;
                if(rs.getString("status").equals("Tidak Tersedia")){
                    imageData = rs.getBytes("gambar_dis");
                }else{
                    imageData = rs.getBytes("gambar");
                }
                tabelMenu.addRow(new Object[]{rs.getString("nama"),rs.getString("harga"),rs.getString("status"),rs.getString("deskripsi")});
                if(imageData != null){
                    ImageIcon imageIcon = new ImageIcon(scaleImage(imageData, 420, 320));
                    this.imageList.add(imageIcon);
                }else{
                    this.imageList.add(null);
                }
            }
        
        this.tabelTabel.setRowHeight(40);
            
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
    
    void showPesanan(){
        tabelPesanan = (DefaultTableModel) this.tabelProsesPesan.getModel();
        tabelPesanan.setRowCount(0);
        try {
            this.st = this.koneksi.createStatement();
            String query = String.format("select * from transaksi where (status = \"dikirim\" or status = \"proses\") and pembeli = \"%s\";", this.getNomorKamar());
            System.out.println("query : " + query);
            this.rs = st.executeQuery(query);
            while(rs.next()){
                tabelPesanan.addRow(new Object[]{rs.getString("id_transaksi"),rs.getString("admin"),rs.getString("pembeli"),rs.getString("pesanan"),rs.getString("deskripsi"), rs.getString("harga"),rs.getString("status"), rs.getString("tanggalpesan")});
            }
        
        this.tabelProsesPesan.setRowHeight(40);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    void showRiwayat(){
        tabelRiwayatPesanan = (DefaultTableModel) this.tabelRiwayat.getModel();
        tabelRiwayatPesanan.setRowCount(0);
        try {
            this.st = this.koneksi.createStatement();
            String query = String.format("select * from transaksi where status = \"selesai\" and pembeli = \"%s\";", this.getNomorKamar());
            System.out.println("query : " + query);
            this.rs = st.executeQuery(query);
            while(rs.next()){
                tabelRiwayatPesanan.addRow(new Object[]{rs.getString("id_transaksi"),rs.getString("admin"),rs.getString("pembeli"),rs.getString("pesanan"),rs.getString("deskripsi"), rs.getString("harga"),rs.getString("status"), rs.getString("tanggalpesan")});
            }
        
        this.tabelRiwayat.setRowHeight(40);
            
        } catch (SQLException e) {
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

        panelProsesdanPesanan = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelRiwayat = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelProsesPesan = new javax.swing.JTable();
        tombolLogout = new javax.swing.JButton();
        panelKeranjang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelKeranjang = new javax.swing.JTable();
        tombolKurangiMakanan = new javax.swing.JButton();
        tombolAddMakanan = new javax.swing.JButton();
        jumlah = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        totalHarga = new javax.swing.JLabel();
        pesan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        deskripsi = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTabel = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        nmMKn1 = new javax.swing.JLabel();
        tombolTambahPesanan = new javax.swing.JButton();
        gbr1 = new javax.swing.JLabel();
        hrg1 = new javax.swing.JLabel();
        jumlahPesanan = new javax.swing.JLabel();
        deskripsiMenu = new javax.swing.JLabel();
        menuCamilan = new javax.swing.JButton();
        menuMakanan = new javax.swing.JButton();
        riwayat = new javax.swing.JButton();
        keranjang = new javax.swing.JButton();
        menuMinuman = new javax.swing.JButton();
        nomorKamar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelProsesdanPesanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pesanan/Riwayat", "Admin", "Pembeli", "Pesanan", "Deskripsi", "Harga", "Status", "Tanggal Pesan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabelRiwayat);
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

        panelProsesdanPesanan.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 1040, -1));

        tabelProsesPesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pesanan/Riwayat", "Admin", "Pembeli", "Pesanan", "Deskripsi", "Harga", "Status", "Tanggal Pesan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabelProsesPesan);
        if (tabelProsesPesan.getColumnModel().getColumnCount() > 0) {
            tabelProsesPesan.getColumnModel().getColumn(0).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(1).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(2).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(3).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(4).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(5).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(6).setResizable(false);
            tabelProsesPesan.getColumnModel().getColumn(7).setResizable(false);
        }

        panelProsesdanPesanan.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 1040, -1));

        getContentPane().add(panelProsesdanPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 1240, 930));

        tombolLogout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tombolLogout.setText("Logout");
        tombolLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(tombolLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1010, 100, 40));

        panelKeranjang.setBackground(new java.awt.Color(204, 204, 204));
        panelKeranjang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Harga", "Jumlah", "Kamar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKeranjangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelKeranjangMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tabelKeranjang);
        if (tabelKeranjang.getColumnModel().getColumnCount() > 0) {
            tabelKeranjang.getColumnModel().getColumn(0).setResizable(false);
            tabelKeranjang.getColumnModel().getColumn(1).setResizable(false);
            tabelKeranjang.getColumnModel().getColumn(2).setResizable(false);
            tabelKeranjang.getColumnModel().getColumn(3).setResizable(false);
        }

        panelKeranjang.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 760, 420));

        tombolKurangiMakanan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tombolKurangiMakanan.setText("<");
        tombolKurangiMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolKurangiMakananActionPerformed(evt);
            }
        });
        panelKeranjang.add(tombolKurangiMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 40, -1));

        tombolAddMakanan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tombolAddMakanan.setText(">");
        tombolAddMakanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tombolAddMakananItemStateChanged(evt);
            }
        });
        tombolAddMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolAddMakananActionPerformed(evt);
            }
        });
        panelKeranjang.add(tombolAddMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, 40, -1));

        jumlah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jumlah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelKeranjang.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, 70, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Total Harga :");
        panelKeranjang.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 120, -1));

        totalHarga.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        panelKeranjang.add(totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 70, 190, 30));

        pesan.setText("Pesan");
        pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesanActionPerformed(evt);
            }
        });
        panelKeranjang.add(pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, 90, 30));

        jLabel3.setText("Deskripsi  :");
        panelKeranjang.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 70, 50));

        deskripsi.setColumns(20);
        deskripsi.setRows(5);
        jScrollPane3.setViewportView(deskripsi);

        panelKeranjang.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, -1, 280));

        jButton1.setText("Hapus");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelKeranjang.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, 80, 40));

        getContentPane().add(panelKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 1220, 780));

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
            tabelTabel.getColumnModel().getColumn(0).setHeaderValue("Nama");
            tabelTabel.getColumnModel().getColumn(1).setResizable(false);
            tabelTabel.getColumnModel().getColumn(1).setHeaderValue("Harga");
            tabelTabel.getColumnModel().getColumn(2).setResizable(false);
            tabelTabel.getColumnModel().getColumn(2).setHeaderValue("Status");
            tabelTabel.getColumnModel().getColumn(3).setResizable(false);
            tabelTabel.getColumnModel().getColumn(3).setHeaderValue("Deskripsi");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 600, 1190, 420));

        jPanel1.setBackground(new java.awt.Color(204, 0, 204));

        nmMKn1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        nmMKn1.setForeground(new java.awt.Color(255, 255, 255));
        nmMKn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nmMKn1.setText("MAKANAN");
        nmMKn1.setAlignmentY(0.0F);

        tombolTambahPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tambah.png"))); // NOI18N
        tombolTambahPesanan.setContentAreaFilled(false);
        tombolTambahPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolTambahPesananActionPerformed(evt);
            }
        });

        hrg1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        hrg1.setForeground(new java.awt.Color(255, 255, 255));
        hrg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hrg1.setText("HARGA");
        hrg1.setAlignmentY(0.0F);

        deskripsiMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gbr1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hrg1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tombolTambahPesanan)
                        .addGap(150, 150, 150)
                        .addComponent(jumlahPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nmMKn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deskripsiMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(663, 663, 663))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gbr1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nmMKn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hrg1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tombolTambahPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jumlahPesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deskripsiMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 1190, 290));

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
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camil w.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.getData("Cemilan");
        this.gbr1.setIcon(this.imageList.get(0));
        this.hrg1.setText(tabelTabel.getValueAt(0, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(0, 0).toString());
        this.panelKeranjang.setVisible(false);
        this.jPanel1.setVisible(true);
        this.jScrollPane1.setVisible(true);
        this.panelProsesdanPesanan.setVisible(false);
    }//GEN-LAST:event_menuCamilanActionPerformed

    private void menuMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMakananActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.getData("Makanan");
        this.gbr1.setIcon(this.imageList.get(0));
        this.hrg1.setText(tabelTabel.getValueAt(0, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(0, 0).toString());
        this.panelKeranjang.setVisible(false);
        this.jPanel1.setVisible(true);
        this.panelProsesdanPesanan.setVisible(false);
        this.jScrollPane1.setVisible(true);
    }//GEN-LAST:event_menuMakananActionPerformed

    private void riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayatActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan w.png")));
        showPesanan();
        this.panelKeranjang.setVisible(false);
        this.jPanel1.setVisible(false);
        this.jScrollPane1.setVisible(false);
        this.panelProsesdanPesanan.setVisible(true);
        
    }//GEN-LAST:event_riwayatActionPerformed

    private void keranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keranjangActionPerformed
        // TODO add your handling code here:
        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang w.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.panelKeranjang.setVisible(true);
        this.jPanel1.setVisible(false);
        this.jScrollPane1.setVisible(false);
        this.panelProsesdanPesanan.setVisible(false);
        
    }//GEN-LAST:event_keranjangActionPerformed

    private void menuMinumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMinumanActionPerformed

        menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan b.png")));
        menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum w.png")));
        menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
        keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
        riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
        this.getData("Minuman");
        this.gbr1.setIcon(this.imageList.get(0));
        this.hrg1.setText(tabelTabel.getValueAt(0, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(0, 0).toString());
        this.panelKeranjang.setVisible(false);
        this.jPanel1.setVisible(true);
        this.jScrollPane1.setVisible(true);
        this.panelProsesdanPesanan.setVisible(false);
    }//GEN-LAST:event_menuMinumanActionPerformed

    private void tabelTabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTabelMouseClicked
        // TODO add your handling code here:
        int row = tabelTabel.getSelectedRow();
        this.gbr1.setIcon(this.imageList.get(row));
        this.hrg1.setText(tabelTabel.getValueAt(row, 1).toString());
        this.nmMKn1.setText(tabelTabel.getValueAt(row, 0).toString());
        if(!String.valueOf(tabelTabel.getValueAt(row, 3)).equals("null")){
            this.deskripsiMenu.setText(tabelTabel.getValueAt(row, 3).toString());
        }else{
            this.deskripsiMenu.setText("");
        }
        
    }//GEN-LAST:event_tabelTabelMouseClicked

    private void tombolTambahPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolTambahPesananActionPerformed
        // TODO add your handling code here:
        this.tabelUntukKeranjang = (DefaultTableModel) this.tabelKeranjang.getModel();
    
    boolean itemExists = false;

    for (int i = 0; i < this.tabelKeranjang.getRowCount(); i++) {
        if(this.tabelUntukKeranjang.getValueAt(i, 0).equals(this.nmMKn1.getText())){
            itemExists = true;
            break;
        }
    }

    if(itemExists) {
        JOptionPane.showMessageDialog(this, "Pesan sekali saja. Untuk menambah atau mengurangi pesanan, silakan ke menu Keranjang!");
    } else {
        this.tabelUntukKeranjang.addRow(new Object[]{this.nmMKn1.getText(), this.hrg1.getText(), 1, this.getNomorKamar()});
        
        this.total = 0;
        for (int i = 0; i < tabelUntukKeranjang.getRowCount(); i++) {
            int jumlah = (Integer) this.tabelUntukKeranjang.getValueAt(i, 2);
            int harga = Integer.valueOf(this.tabelUntukKeranjang.getValueAt(i, 1).toString());
            total += jumlah * harga;
        }
        this.totalHarga.setText(String.valueOf(total));
    }
    }//GEN-LAST:event_tombolTambahPesananActionPerformed

    private void tombolLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolLogoutActionPerformed
        // TODO add your handling code here:
        Login logout = new Login();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_tombolLogoutActionPerformed

    private void tombolKurangiMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolKurangiMakananActionPerformed
        // TODO add your handling code here:
        if(this.row2 != null) {
        --addKurang;
        
        if(this.addKurang < 1) {
            JOptionPane.showMessageDialog(this, "Jumlah minimal 1");
            addKurang = 1; // Pastikan jumlah minimal adalah 1
        } else {
            this.jumlah.setText(String.valueOf(addKurang));
            this.tabelUntukKeranjang.setValueAt(addKurang, row2, 2);

            int total = 0;
    for (int i = 0; i < tabelUntukKeranjang.getRowCount(); i++) {
        int jumlah = (Integer) tabelUntukKeranjang.getValueAt(i, 2);
        int harga = Integer.valueOf(tabelUntukKeranjang.getValueAt(i, 1).toString());
        total += jumlah * harga;
    }
    this.totalHarga.setText(String.valueOf(total));
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih menu dahulu!");
    }
        
    }//GEN-LAST:event_tombolKurangiMakananActionPerformed

    private void tabelKeranjangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKeranjangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelKeranjangMouseEntered

    private void tabelKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKeranjangMouseClicked
        // TODO add your handling code here:
        this.row2 = tabelKeranjang.getSelectedRow();
        this.jumlah.setText(String.valueOf(tabelKeranjang.getValueAt(row, 2)));
        this.addKurang = Integer.valueOf(this.jumlah.getText());
    }//GEN-LAST:event_tabelKeranjangMouseClicked

    private void tombolAddMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolAddMakananActionPerformed
        // TODO add your handling code here:
        if(this.row2 != null) {
        ++addKurang;
        
        if(this.addKurang < 1) {
            JOptionPane.showMessageDialog(this, "Jumlah minimal 1");
            addKurang = 1; // Pastikan jumlah minimal adalah 1
        } else {
            this.jumlah.setText(String.valueOf(addKurang));
            this.tabelUntukKeranjang.setValueAt(addKurang, row2, 2);

            int total = 0;
    for (int i = 0; i < tabelUntukKeranjang.getRowCount(); i++) {
        int jumlah = (Integer) tabelUntukKeranjang.getValueAt(i, 2);
        int harga = Integer.valueOf(tabelUntukKeranjang.getValueAt(i, 1).toString());
        total += jumlah * harga;
    }
    this.totalHarga.setText(String.valueOf(total));
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih menu dahulu!");
    }
        
        
    }//GEN-LAST:event_tombolAddMakananActionPerformed

    private void tombolAddMakananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tombolAddMakananItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tombolAddMakananItemStateChanged

    private void pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesanActionPerformed
        // TODO add your handling code here:
        try {
            if(this.tabelUntukKeranjang != null){
                if(this.tabelUntukKeranjang.getRowCount() != 0){
                    for (int i = 0; i < this.tabelUntukKeranjang.getRowCount(); i++) {
                this.daftarKeranjang.add(String.format("%s:%s~", this.tabelUntukKeranjang.getValueAt(i, 2), this.tabelUntukKeranjang.getValueAt(i, 0)));
            }
            
            String pesan123 = "";
            for (int i = 0; i < this.daftarKeranjang.size(); i++) {
                pesan123 += this.daftarKeranjang.get(i);
            }
            
            System.out.println("Isi pesan123 : " + pesan123);
            
            String query = "";
            query = "insert into transaksi SET pembeli = ?, pesanan = ?, harga = ?, deskripsi = ?, admin = ?, status = ? ";

                // Membuat PreparedStatement
                PreparedStatement pstmt = koneksi.prepareStatement(query);

                // Mengatur nilai parameter
                pstmt.setString(1, this.getNomorKamar());
                pstmt.setString(2, pesan123);
                pstmt.setString(3, this.totalHarga.getText());
                pstmt.setString(4, this.deskripsi.getText());
                pstmt.setString(5, "");
                pstmt.setString(6, "dikirim");

                // Membaca gambar sebagai byte array
                

                // Menjalankan query
                pstmt.executeUpdate();
                
                this.row = 0;
                this.row2 = null;
                this.addKurang = 0;
                this.total = 0;
                tabelMenu.setRowCount(0);
                tabelUntukKeranjang.setRowCount(0);
//                imageList.clear();
                daftarKeranjang.clear();
                JOptionPane.showMessageDialog(this, "Berhasil dipesan!");
                this.panelKeranjang.setVisible(false);
                this.jPanel1.setVisible(true);
                this.jScrollPane1.setVisible(true);
                this.getData(jenisPesanan);
                menuMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/makan w.png")));
                menuMinuman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minum b.png")));
                menuCamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cemil b.png")));
                keranjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/keranjang b.png")));
                riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Riwayat Pesanan b.png")));
                this.addKurang = 1;
                this.jumlah.setText(String.valueOf(1));
                this.totalHarga.setText(String.valueOf(0));
                }else{
                    JOptionPane.showMessageDialog(this, "Tabel Keranjang kosong!");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Pilihlah menu dahulu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_pesanActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(this.row2 != null){
            this.tabelUntukKeranjang.removeRow(this.row2);
        this.jumlah.setText("");
//        this.row2 = 0;
        int total = 0;
    for (int i = 0; i < tabelUntukKeranjang.getRowCount(); i++) {
        int jumlah = (Integer) tabelUntukKeranjang.getValueAt(i, 2);
        int harga = Integer.valueOf(tabelUntukKeranjang.getValueAt(i, 1).toString());
        total += jumlah * harga;
    }
    this.totalHarga.setText(String.valueOf(total));
        }else{
            JOptionPane.showMessageDialog(this, "Pilih menu dahulu!");
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JTextArea deskripsi;
    private javax.swing.JLabel deskripsiMenu;
    private javax.swing.JLabel gbr1;
    private javax.swing.JLabel hrg1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jumlah;
    private javax.swing.JLabel jumlahPesanan;
    private javax.swing.JButton keranjang;
    private javax.swing.JButton menuCamilan;
    private javax.swing.JButton menuMakanan;
    private javax.swing.JButton menuMinuman;
    private javax.swing.JLabel nmMKn1;
    private javax.swing.JLabel nomorKamar;
    private javax.swing.JPanel panelKeranjang;
    private javax.swing.JPanel panelProsesdanPesanan;
    private javax.swing.JButton pesan;
    private javax.swing.JButton riwayat;
    private javax.swing.JTable tabelKeranjang;
    private javax.swing.JTable tabelProsesPesan;
    private javax.swing.JTable tabelRiwayat;
    private javax.swing.JTable tabelTabel;
    private javax.swing.JButton tombolAddMakanan;
    private javax.swing.JButton tombolKurangiMakanan;
    private javax.swing.JButton tombolLogout;
    private javax.swing.JButton tombolTambahPesanan;
    private javax.swing.JLabel totalHarga;
    // End of variables declaration//GEN-END:variables
}
