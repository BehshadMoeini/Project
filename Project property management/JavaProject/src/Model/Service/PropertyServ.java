
package Model.Service;

import Model.Entity.Property;
import Model.Repository.PropertyRepo;


public class PropertyServ {
    
    private PropertyServ(){
        
    }
    
    private static PropertyServ propertyServ = new PropertyServ();
    
    public static PropertyServ getInstance(){
        return propertyServ;
    }
    
    public void save(Property property) throws Exception{
        
        try(PropertyRepo propertyRepo = new PropertyRepo()){
            
            propertyRepo.insert(property);
            propertyRepo.commit();
        }
    }
    
    public void remove(String address) throws Exception{
        
        try(PropertyRepo propertyRepo = new PropertyRepo()){
            
            propertyRepo.delete(address);
            propertyRepo.commit();
        }
    }
}
