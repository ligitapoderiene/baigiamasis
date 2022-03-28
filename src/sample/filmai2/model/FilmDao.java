package sample.filmai2.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao{
    //---------------------Kuriame įrašą---------------------
    public static void create(Film film) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "INSERT INTO `filmai`(`title`, `description`, `rating`, `category`,  VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getRating());
            preparedStatement.setString(4, film.getCategory());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---------------------Įrašo paieška pagal name---------------------
    public static List<Film> searchByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "";
        if (name.isEmpty()) {
            querry = "SELECT * FROM `filmai`";
        } else {
            querry = "SELECT * FROM `filmai` WHERE `title` LIKE '%" + name + "%'";
        }

        ArrayList<Film> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
//            if(!name.isEmpty()){
//                preparedStatement.setString(1, name);
//            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("rating"),
                        resultSet.getString("category")

                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //---------------------Atnaujiname įrašą---------------------
    public static void update(Film film) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String update = "UPDATE `filmai` SET `title`= ?,`description`= ?,`rating`= ?,`category`= ? WHERE `id` = ?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getRating());
            preparedStatement.setString(4, film.getCategory());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
    }

    //---------------------Triname įrašą---------------------
    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String delete = "DELETE FROM filmai WHERE id = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("Įrašo ištrinti nepavyko");
        }
    }


    //---------------------Įrašo paieška pagal id---------------------
    public static Film searchById(String id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT * FROM `filmai` WHERE `id` = ?";

        ArrayList<Film> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("rating"),
                        resultSet.getString("category")

                ));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // Ieskome pagal pirma sarase esanti id, nes id unikalus
            return list.get(0);
        }
        // Nepavyko rasti pagal id
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


}
