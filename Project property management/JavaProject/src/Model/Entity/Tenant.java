package Model.Entity;

import View.HomeScreenGUI;
import java.io.Serializable;


public class Tenant extends Person implements Serializable{
    
    private int tenantID;
    private String tenantStatus;
    private static int nextTenantID = 1;
        
    public Tenant(){
        super();
        setTenantStatus("ناشناس");
        tenantID = getNextTenantID();
    }
    
    public Tenant(String name, String address, String phone, String pps, String tenStatus){
        super(name, address, phone, pps);
        tenantID = getNextTenantID();
        setTenantStatus(tenStatus);
    }
     
    public int getTenantID(){
	return tenantID;
    }
    
    public void setTenantID(int id){
        tenantID = id;
    }
     
    public static int getNextTenantID(){
        if(HomeScreenGUI.tenantsAvailable.isEmpty()){
            int Id = nextTenantID;
            nextTenantID++;
            return Id;
        }
        else{
            int Id = HomeScreenGUI.tenantsAvailable.get(HomeScreenGUI.tenantsAvailable.size() - 1).getTenantID();
            return (Id +1);
        }
    }
    
    public void setTenantStatus(String tenStatus){
    	tenantStatus = tenStatus;
    }
    
    public String getTenantStatus(){
	return tenantStatus;
    }
    
    public String toString(){
		return "\n" + super.toString()+ "\nآیدی مستاجر: " + this.getTenantID() + "\nوضعیت مستاجر: " + this.getTenantStatus();
	}

   
}
