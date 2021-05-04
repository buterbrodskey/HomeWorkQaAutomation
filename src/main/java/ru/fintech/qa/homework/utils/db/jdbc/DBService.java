package ru.fintech.qa.homework.utils.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    public static boolean addPlaces() throws SQLException {
        int result;
        Connection connection = DBClient.getConnection();
        Statement statement = connection.createStatement();
        try {
            result = statement.executeUpdate("insert into public.places(id) values (" + 200 + ")"); // случайное, что б не делать подзапрос на свободный id
        } catch (Exception e) {
            result = 0;
        }
        connection.close();
        return result != 0;
    }

    public static int executeQueryGetCountRows(final String table) throws SQLException {
        Connection connection = DBClient.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(*) as count from public." + table);
        resultSet.next();
        int actualQuantity = resultSet.getInt("count");
        connection.close();
        return actualQuantity;
    }

    public static boolean addAnimalWithId(final int id) throws SQLException {
        int result;
        Connection connection = DBClient.getConnection();
        Statement statement = connection.createStatement();
        try {
            result = statement.executeUpdate("insert into public.animal(id) values (" + id + ")");
        } catch (Exception e) {
            result = 0;
        }
        connection.close();
        return result != 0;
    }

    public static boolean addWorkmanWithNullName() throws SQLException {
        boolean result;
        Connection connection = DBClient.getConnection();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("insert into public.workman (id,\"name\") values (199,null)");
            // случайное число, чтоб подзапрос на количество занятых id не делать, показалось избыточным
            result = true;
        } catch (Exception e) {
            result = false;
        }
        connection.close();
        return result;
    }

    public static String[] getColumn(final String table, final String column) throws SQLException {
        List<String> result = new ArrayList<>();
        Connection connection = DBClient.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select " + column + "as w from " + table);
        while (resultSet.next()) {
            result.add(resultSet.getString("w"));
        }
        connection.close();
        return result.toArray(new String[0]);

    }
}
