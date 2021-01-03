
package Controller;

import Model.Entity.Property;
import Model.Service.PropertyServ;


public class PropertyCtrl {
    
    public void insert(int id, String address, String phone, String type, String rentAmount, String status) throws Exception{
        
        Property property = new Property();
        
        property.setPropertyID_(id);
        property.setAddress_(address);
        property.setPhone_(phone);
        property.setPropertyType_(type);
        property.setRentAmount_(rentAmount);
        property.setPropertyStatus_(status);
        
        PropertyServ.getInstance().save(property);
    }
    
    
    public void delete(String address) throws Exception{
        
        PropertyServ.getInstance().remove(address);
    }
}
