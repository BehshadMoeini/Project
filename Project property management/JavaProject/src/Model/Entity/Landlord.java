package Model.Entity;

import View.HomeScreenGUI;
import java.io.Serializable;


public class Landlord extends Person implements Serializable{
    
    private int landlordID_;
    private String landlordStatus_;
    private static int nextLandlordID_ = 1;
    
    public Landlord(){
        super();
        setLandlordStatus("Unknown");
        landlordID_ = getNextID();
    }
    
    public Landlord(String name, String address, String phone, String pps, String lanStatus){
        super(name, address, phone, pps);
        landlordID_ = getNextID();
        setLandlordStatus(lanStatus);
    }
        
    public int getLandlordID(){
	return landlordID_;
    }
    
    public static int getNextID(){
        if(HomeScreenGUI.landlordList.isEmpty()){
            int Id = nextLandlordID_;
            nextLandlordID_++;
            return Id;
        }
        else{
            int Id = HomeScreenGUI.landlordList.get(HomeScreenGUI.landlordList.size() - 1).getLandlordID();
            return (Id+1);
        }
    }
    
    public void setLandlordID(int id){
        landlordID_ = id;
    }
    
    public void setLandlordStatus(String lanStatus){
    	landlordStatus_ = lanStatus;
    }
    
    public String getLandlordStatus(){
	return landlordStatus_;
    }
    
    public String toString(){
		return "\n" + super.toString() + "\nآیدی مالک: " + this.getLandlordID() 
                        + "\nتوضیحات اضافه: " + this.getLandlordStatus();
	}
    
}
