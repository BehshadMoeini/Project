package Model.Repository;

import Model.Entity.Landlord;
import Model.Entity.Tenant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class TenantRepo implements AutoCloseable{
    
    private Connection connect;
    private PreparedStatement preparedStatement;
    
    public TenantRepo() throws Exception{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "alirezakazemi", "myjava123");
        connect.setAutoCommit(false);
    }
    
    public void insert(Tenant tenant) throws Exception{
        preparedStatement = connect.prepareStatement("insert into Tenant(id,name,address,phoneNumber,ppsNumber,status)values(?,?,?,?,?,?)");
        preparedStatement.setLong(1, tenant.getTenantID());
        preparedStatement.setString(2, tenant.getName());
        preparedStatement.setString(3, tenant.getAddress());
        preparedStatement.setString(4, tenant.getPhoneNumber());
        preparedStatement.setString(5, tenant.getPpsNumber());
        preparedStatement.setString(6, tenant.getTenantStatus());
        preparedStatement.executeUpdate();
    }
    
    public void update(Tenant tenant) throws Exception{
        preparedStatement = connect.prepareStatement("update Tenant set address=?,phoneNumber=?,ppsNumber=?,status=? where name=?");
        preparedStatement.setString(1, tenant.getAddress());
        preparedStatement.setString(2, tenant.getPhoneNumber());
        preparedStatement.setString(3, tenant.getPpsNumber());
        preparedStatement.setString(4, tenant.getTenantStatus());
        preparedStatement.setString(5, tenant.getName());
        preparedStatement.executeUpdate();
    }
    
    public void delete(String name) throws Exception{
        preparedStatement = connect.prepareStatement("delete from Tenant where name=?");
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
