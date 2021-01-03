
package Model.Service;

import Model.Entity.Tenant;
import Model.Repository.TenantRepo;


public class TenantServ {
    
    private TenantServ(){
        
    }
    
    private static TenantServ tenantServ = new TenantServ();
    
    public static TenantServ getInstance(){
        return tenantServ;
    }
    
    public void save(Tenant tenant) throws Exception{
        try(TenantRepo tenantRepo = new TenantRepo()){
            tenantRepo.insert(tenant);
            tenantRepo.commit();
        }
    }
    
    public void edit(Tenant tenant) throws Exception{
        try(TenantRepo tenantRepo = new TenantRepo()){
            tenantRepo.update(tenant);
            tenantRepo.commit();
        }
    }
    
    public void remove(String name) throws Exception{
        try(TenantRepo tenantRepo = new TenantRepo()){
            tenantRepo.delete(name);
            tenantRepo.commit();
        }
    }
}
