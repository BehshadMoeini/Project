
package Controller;

import Model.Entity.Tenant;
import Model.Service.TenantServ;


public class TenantCtrl {
    
    public void insert(int id, String name, String address, String phoneNumber, String ppsNumber, String status) throws Exception{
        
        Tenant t = new Tenant();
        
        t.setName(name);
        t.setAddress(address);
        t.setPhoneNumber(phoneNumber);
        t.setPpsNumber(ppsNumber);
        t.setTenantStatus(status);
        t.setTenantID(id);
        
        TenantServ.getInstance().save(t);
    }
    
    
    public void update(Tenant tenant) throws Exception{
        
        
        TenantServ.getInstance().edit(tenant);
        
    }
    
    public void delete(String name) throws Exception{
        
        TenantServ.getInstance().remove(name);
        
    }
}
