/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ACER
 */
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class Test extends JFrame {
    private JButton browseButton;
    private JLabel imageLabel;
    private String imagePath;
    private Connection connection;

    public Test() {
        browseButton = new JButton("Browse");
        imageLabel = new JLabel();
        
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath = selectedFile.getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    imageLabel.setIcon(imageIcon);
                }
            }
        });

        // Buat koneksi database di sini (gunakan try-catch untuk menangani pengecualian)
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warungkostungu", "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Tambahkan komponen ke frame
        add(browseButton);
        add(imageLabel);

        // Set properties frame
        setSize(400, 400);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Metode untuk menyimpan gambar ke database
    private void saveImageToDatabase() {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO images(image) VALUES(?)");
            FileInputStream fileInputStream = new FileInputStream(imagePath);
            statement.setBinaryStream(1, fileInputStream, (int) new File(imagePath).length());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Image saved to database successfully!");
        } catch (SQLException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test();
    }
}

