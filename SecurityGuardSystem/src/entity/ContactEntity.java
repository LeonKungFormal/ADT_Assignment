/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Jian
 */
public class ContactEntity implements Comparable<ContactEntity>{
   
    private String emergencyUnit; 
    private String contactNum;
    private int distance;
    private String date;
    
public ContactEntity(String emergencyUnit, String contactNum, int distance, String date){
    this.emergencyUnit = emergencyUnit;
    this.contactNum = contactNum;
    this.distance = distance;
    this.date = date;
    
}

    public String getEmergencyUnit() {
        return emergencyUnit;
    }

    public void setEmergencyUnit(String emergencyUnit) {
        this.emergencyUnit = emergencyUnit;
    }
    
    public String getContactNum() {
        return contactNum;
    }
    
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
    
    public int getDistance() {
        return distance;
    }
    
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
@Override
    public int compareTo(ContactEntity distance) {
        return Integer.compare(this.getDistance(),distance.getDistance());
    }
    
}

