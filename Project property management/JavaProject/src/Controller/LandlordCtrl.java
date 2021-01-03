package Controller;

import Model.Entity.Landlord;
import Model.Service.LandlordServ;


public class LandlordCtrl {
    
    public void insert(int id, String name, String address, String phoneNumber, String ppsNumber, String status) throws Exception{
        
        Landlord l = new Landlord();
        
        l.setName(name);
        l.setAddress(address);
        l.setPhoneNumber(phoneNumber);
        l.setPpsNumber(ppsNumber);
        l.setLandlordStatus(status);
        l.setLandlordID(id);
        LandlordServ.getInstance().save(l);
    }
    
    
    public void update(Landlord landlord) throws Exception{
        
        
        LandlordServ.getInstance().edit(landlord);
        
    }
    
    public void delete(String name) throws Exception{
        
        LandlordServ.getInstance().remove(name);
        
    }
    
}
