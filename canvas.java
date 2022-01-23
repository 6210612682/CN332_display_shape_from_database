import java.awt.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

public class canvas {
    public static void main(String[] args) {

        // set frame //
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 800);

        try {

            // database set //
            String url = "jdbc:mysql://localhost/shape";
            String user = "root";
            String pass = "";
            String sql = "SELECT * FROM `shape`";

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(sql);

            // Shape //
            while (resultset.next()) {
                String location = resultset.getString("location");
                String shape = resultset.getString("shape");
                String param1 = resultset.getString("param1");
                String param2 = resultset.getString("param2");
                String param3 = resultset.getString("param3");
                String color = resultset.getString("color");

                String[] info = new String[] { location, shape, param1, param2, param3, color };

                Drawing Drawing = new Drawing(info);
                f.add(Drawing);
                f.setVisible(true);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}