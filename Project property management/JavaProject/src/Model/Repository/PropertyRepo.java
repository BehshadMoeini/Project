
package Model.Repository;

import Model.Entity.Property;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PropertyRepo implements AutoCloseable{
    
    private Connection connect;
    private PreparedStatement preparedStatement;
    
    public PropertyRepo() throws Exception{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "alirezakazemi", "myjava123");
        connect.setAutoCommit(false);
    }
    
    public void insert(Property property) throws Exception{
        
        preparedStatement = connect.prepareStatement("insert into Property(id,address,phone,type,rentAmount,status)values(?,?,?,?,?,?)");
        preparedStatement.setLong(1, property.getPropertyID_());
        preparedStatement.setString(2, property.getAddress_());
        preparedStatement.setString(3, property.getPhone_());
        preparedStatement.setString(4, property.getPropertyType_());
        preparedStatement.setString(5, property.getRentAmount_());
        preparedStatement.setString(6, property.getPropertyStatus_());
        preparedStatement.executeUpdate();
    }
    
    public void delete(String address)throws Exception{
        
        preparedStatement = connect.prepareStatement("delete from Property where address=?");
        preparedStatement.setString(1, address);
        preparedStatement.executeUpdate();
    }
    
    
    public void commit() throws Exception{
        connect.commit();
    }
    
    public void rollback() throws Exception{
        connect.rollback();
    }
    
    
    
    @Override
    public void close() throws Exception {
        
        preparedStatement.close();
        connect.close();
    }
}
