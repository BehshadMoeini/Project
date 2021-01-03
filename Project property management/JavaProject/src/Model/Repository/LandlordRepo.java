package Model.Repository;

import Model.Entity.Landlord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LandlordRepo implements AutoCloseable {
    
    private Connection connect;
    private PreparedStatement preparedStatement;
    
    public LandlordRepo() throws Exception{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "alirezakazemi", "myjava123");
        connect.setAutoCommit(false);
    }
    
    public void insert(Landlord landlord) throws Exception{
        preparedStatement = connect.prepareStatement("insert into Landlord(id,name,address,phoneNumber,ppsNumber,status)values(?,?,?,?,?,?)");
        preparedStatement.setLong(1, landlord.getLandlordID());
        preparedStatement.setString(2, landlord.getName());
        preparedStatement.setString(3, landlord.getAddress());
        preparedStatement.setString(4, landlord.getPhoneNumber());
        preparedStatement.setString(5, landlord.getPpsNumber());
        preparedStatement.setString(6, landlord.getLandlordStatus());
        preparedStatement.executeUpdate();
    }
    
    public void update(Landlord landlord) throws Exception{
        preparedStatement = connect.prepareStatement("update Landlord set address=?,phoneNumber=?,ppsNumber=?,status=? where name=?");
        preparedStatement.setString(1, landlord.getAddress());
        preparedStatement.setString(2, landlord.getPhoneNumber());
        preparedStatement.setString(3, landlord.getPpsNumber());
        preparedStatement.setString(4, landlord.getLandlordStatus());
        preparedStatement.setString(5, landlord.getName());
        preparedStatement.executeUpdate();
    }
    
    public void delete(String name) throws Exception{
        preparedStatement = connect.prepareStatement("delete from Landlord where name=?");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }
    
    public void commit() throws Exception{
        connect.commit();
    }
    
    public void rollback() throws Exception{
        connect.rollback();
    }
    
    public void close() throws Exception{
        preparedStatement.close();
        connect.close();
    }
    
}
