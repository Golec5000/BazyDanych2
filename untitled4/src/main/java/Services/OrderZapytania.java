package Services;

import Entity.Order;
import Entity.Product;
import Services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderZapytania {

    // aktualizacja statusu zamowienia
    public boolean updateOrderStatus(int idZamowienia, String newStatus) throws SQLException {
        String query = "UPDATE Zamowienia SET statusZamowienia = ? WHERE idZamowienia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, newStatus);
            pst.setInt(2, idZamowienia);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // usuwanie zamówienia
    public boolean deleteOrder(int idZamowienia) throws SQLException {
        String query = "DELETE FROM Zamowienia WHERE idZamowienia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, idZamowienia);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // pobieranie wszystkich zamowien
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Zamowienia";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("idZamowienia"),
                        rs.getDate("dataZamowienia").toLocalDate(),
                        rs.getString("statusZamowienia"),
                        rs.getString("idKlienta")
                ));
            }
        }
        return orders;
    }

    // pobieranie zwmowien po ID
    public Order getOrderById(int idZamowienia) throws SQLException {
        String query = "SELECT * FROM Zamowienia WHERE idZamowienia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idZamowienia);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getInt("idZamowienia"),
                        rs.getDate("dataZamowienia").toLocalDate(),
                        rs.getString("statusZamowienia"),
                        rs.getString("idKlienta")
                );
            }
        }
        return null;
    }
    public int getLastID()throws SQLException{
        String query="SELECT MAX(NumerZamowienia) As id FROM zamowienia";
        try(Connection conn=DatabaseConnection.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(query)){
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next())
                    return resultSet.getInt("id");
        }
        return 0;
    }
//TWORZENIE NOWEGO ZAMOWIENIA
    public Order createOrder(List<Product> productList) throws SQLException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate now = LocalDate.now();
        String temp=now.toString();
        Date data=Date.valueOf(LocalDate.now());
        int currID=getLastID()+1;
        String currState="Nieoplacone";
        String nick="Entity.User";
        String products="";
        for(int i=0;i<productList.size();i++)
        {
            products+=productList.get(i).getNazwaProduktu();
            if(i<productList.size()-1)
                products+=",";
        }
        String query="INSERT INTO zamowienia(NumerZamowienia,DataTransakcji,StanZamowienia,Nick,produkt)VALUES (?, ?,?,?,?)";
        try(Connection connection=DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setInt(1,currID);
            preparedStatement.setDate(2,data);
            preparedStatement.setString(3,currState);
            preparedStatement.setString(4,nick);
            preparedStatement.setString(5,products);
            preparedStatement.executeUpdate();
            return new Order(currID,now,currState,nick);
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}