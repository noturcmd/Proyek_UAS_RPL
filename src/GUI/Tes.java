package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;

public class Tes extends JFrame {

    private Connection connection;
    private JPanel panel;

    public Tes() {
        super("Image Display");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        connectToDatabase();
        fetchDataFromDatabase();
    }

    private void connectToDatabase() {
        try {
            // Ganti "jdbc:mysql://localhost:3306/image_database" dengan URL database kamu
            // Ganti "username" dan "password" dengan kredensial yang sesuai
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/warungkostungu", "root", "");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchDataFromDatabase() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, gambar FROM menu");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                byte[] imageData = resultSet.getBytes("gambar");
                ImageIcon imageIcon = new ImageIcon(scaleImage(imageData, 200, 200));
                JLabel imageLabel = new JLabel(imageIcon);
                JLabel idLabel = new JLabel("ID: " + id);
                panel.add(imageLabel);
                panel.add(idLabel);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tes imageDisplay = new Tes();
            imageDisplay.setVisible(true);
        });
    }
}
