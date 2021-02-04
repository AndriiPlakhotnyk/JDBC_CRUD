import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserRepoJDBC {

    private static Connection con = null;
    private static PreparedStatement preparedStatement = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    Scanner scanner = new Scanner(System.in);

    public void create(String connect, String name, String password) {

        System.out.print("Input Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Input email: ");
        String email = scanner.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connect, name, password);

            String sql = "INSERT INTO user (surname, email) Values (?, ?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, email);

            int rows = preparedStatement.executeUpdate();

            System.out.print(rows + " rows added");

        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Connection failed");
        }
    }

    public void readAll(String connect, String name, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connect, name, password);

            statement = con.createStatement();

            String sql = "SELECT * FROM user;";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String surname = resultSet.getString(2);
                String email = resultSet.getString(3);

                System.out.println("Id: " + id + " Surname: " + surname + " email: " + email);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка, класс не найден");
        } catch (SQLException throwables) {
            System.out.println("Проблема в SQL");
        }
    }

    public void delete(String connect, String name, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connect, name, password);

            statement = con.createStatement();

            System.out.println("Input id: ");
            int id = scanner.nextInt();
            int rows = statement.executeUpdate("DELETE FROM user WHERE iduser = " + id);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(String connect, String name, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connect, name, password);

            statement = con.createStatement();

            System.out.println("Input user's id which you need to update");
            String id = scanner.nextLine();

            System.out.println("Choose a number of  operation");
            System.out.println("1. Change Surname");
            System.out.println("2. Change email");
            System.out.println("3. Change all");
            String operation = scanner.nextLine();
            String sql = "";

            if (operation.equals("1")) {
                System.out.println("Input surname");
                String surname = scanner.nextLine();
                sql = "UPDATE `user` SET `surname` = ' " + surname + " ' WHERE `iduser` = ' " + id + " ' ";
            } else if (operation.equals("2")) {
                System.out.println("Input email");
                String email = scanner.nextLine();
                sql = "UPDATE `user` SET `email` = ' " + email + " ' WHERE `iduser` = ' " + id + " ' ";
            } else if (operation.equals("3")) {
                System.out.println("Input surname and email");
                String surname = scanner.nextLine();
                String email = scanner.nextLine();
                sql = "UPDATE `user` SET `surname` = ' " + surname + " ' , `email` = ' "  + email + " ' WHERE `iduser` = ' " + id + " ' ";
            } else {
                System.out.println("Your choose isn't correct");
            }

            int rows = statement.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
