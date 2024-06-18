import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
            try {
                Class.forName("org.mariadb.jdbc.Driver");

                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pub_elections",
                        "root",
                        "1234");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM candidates");

                System.out.println("CARA PERTAMA (tanpa perulangan)");

                // CARA PERTAMA (tanpa perulangan)

                boolean adaBaris1 = resultSet.next();
                if (adaBaris1) {
                    String name = resultSet.getString("name");
                    System.out.println("baris pertama: " + name);
                }

                boolean adaBaris2 = resultSet.next();
                if (adaBaris2) {
                    String name = resultSet.getString("name");
                    System.out.println("baris kedua: " + name);
                }

                System.out.println();
                System.out.println("CARA KEDUA (dengan perulangan, tapi ditampung)");

                // CARA KEDUA (dengan perulangan, tapi ditampung)

                ResultSet resultSet2 = statement.executeQuery("SELECT * FROM generations");
                boolean adaBaris3 = resultSet2.next(); // cek baris pertama
                while (adaBaris3) {
                    String name = resultSet2.getString("name");
                    System.out.println("angkatan: " + name);
                    adaBaris3 = resultSet2.next(); // cek baris berikutnya
                }

                System.out.println();
                System.out.println("CARA KETIGA (dengan perulangan, tanpa ditampung)");

                // CARA KETIGA (dengan perulangan, tanpa ditampung)

                ResultSet resultSet3 = statement.executeQuery("SELECT * FROM generations");
                while (resultSet3.next()) {
                    String name = resultSet3.getString("name");
                    System.out.println("angkatan: " + name);
                }

                // PREPARED STATEMENT

                PreparedStatement statement2 = connection
                        .prepareStatement("INSERT INTO generations (number, name) VALUES (?, ?)");
                statement2.setInt(0, 23);
                statement2.setString(1, "Boolean");
                statement2.executeQuery();
                System.out.println("Angkatan berhasil ditambahkan.");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}