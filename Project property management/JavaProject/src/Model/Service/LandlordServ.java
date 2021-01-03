package Model.Service;

import Model.Entity.Landlord;
import Model.Repository.LandlordRepo;


public class LandlordServ {
    
    private LandlordServ(){
        
    }
    
    private static LandlordServ landlordServ = new LandlordServ();
    
    public static LandlordServ getInstance(){
        return landlordServ;
    }
    
    public void save(Landlord landlord) throws Exception{
        try(LandlordRepo landlordRepo = new LandlordRepo()){
            landlordRepo.insert(landlord);
            landlordRepo.commit();
        }
    }
    
    public void edit(Landlord landlord) throws Exception{
        try(LandlordRepo landlordRepo = new LandlordRepo()){
            landlordRepo.update(landlord);
            landlordRepo.commit();
        }
    }
    
    public void remove(String name) throws Exception{
        try(LandlordRepo landlordRepo = new LandlordRepo()){
            landlordRepo.delete(name);
            landlordRepo.commit();
        }
    }
    
}
