package org.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    private static LoginService instance;

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (instance == null) instance = new LoginService();
        return instance;
    }

    public boolean userExists(String login) {
        String query="SELECT COUNT(nick) FROM klienci WHERE nick=?";
        try(Connection connection= DatabaseConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1,login);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next() ) {
                if(resultSet.getInt("COUNT(nick)")==1)
                    return true;
            }

            return false;
         } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
