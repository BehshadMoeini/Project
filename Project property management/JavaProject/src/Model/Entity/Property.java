package Model.Entity;

import View.HomeScreenGUI;
import java.io.Serializable;


public class Property extends PropertyRent implements Serializable{
    
    private int propertyID_;
    private String address_;
    private String phone_;
    private String propertyType_;
    private String rentAmount_;
    private String propertyStatus_;
    private static int nextPropertyID_ = 1;
        
    public Property(){
        propertyID_ = getNextPropertyID_();
        setAddress_("Unassigned");
        setPhone_("Unassigned");
        setPropertyType_("Unassigned");
        setRentAmount_("Unassigned");
        setPropertyStatus_("Unassigned");
    }
    
    public Property(String address, String beds, String pType, String rent, String pStatus){
        propertyID_ = getNextPropertyID_();
        nextPropertyID_++;
        setAddress_(address);
        setPhone_(beds);
        setPropertyType_(pType);
        setRentAmount_(rent);
        setPropertyStatus_(pStatus);
    }
          
    public int getPropertyID_(){
	return propertyID_;
    }
    
    public void setPropertyID_(int id){
        propertyID_ = id;
    }
    
    public static int getNextPropertyID_(){
        if(HomeScreenGUI.propertyAvailable.isEmpty()){
            int Id = nextPropertyID_;
            nextPropertyID_++;
            return Id;
        }
        else{
            int Id = HomeScreenGUI.propertyAvailable.get(HomeScreenGUI.propertyAvailable.size() - 1).getPropertyID_();
            return (Id +1);
        }
    }
     
    public void setAddress_(String address){
    	address_ = address;
    }
    
    public String getAddress_(){
	return address_;
    }
    
    public void setPhone_(String phone){
        phone_ = phone;
    }
    
    public String getPhone_(){
        return phone_;
    }
    
    public void setPropertyType_(String pType){
        propertyType_ = pType;
    }
    
    public String getPropertyType_(){
        return propertyType_;
    }
    
    public void setRentAmount_(String rent){
        rentAmount_ = rent;
    }
    
    public String getRentAmount_(){
        return rentAmount_;
    }
    
    public void setPropertyStatus_(String pStatus){
        propertyStatus_ = pStatus;
    }
    
    public String getPropertyStatus_(){
        return propertyStatus_;
    }
        
    public String toString(){
		return "\n" + super.toString()+ "\nآیدی مشخصه: " + this.getPropertyID_() + "\nآدرس: " + this.getAddress_() +
			"\nبد: " + this.getPhone_()  + "\nنوع مشخصه: " + this.getPropertyType_()  + "\nمقدار پول : " + this.getRentAmount_() +
                        "\nوضعیت: " + this.getPropertyStatus_();
	}

    
    
    
}
