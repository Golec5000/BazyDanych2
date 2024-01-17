package Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public static boolean userExists(String login) {
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
