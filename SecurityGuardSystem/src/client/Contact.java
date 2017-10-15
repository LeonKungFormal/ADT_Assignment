/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import adt.*;
import entity.ContactEntity;
import entity.EmergencyAlert;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author Jian
 */
public class Contact{
    SecurityListInterface<ContactEntity> contact;
    Scanner get = new Scanner(System.in);
    String emergencyUnit;
    String contactNum;
    int distance;
    String date;
    int choice;
  
    public Contact(){
        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        contact = new SortedSecurityList<>();
        ContactEntity contact1 = new ContactEntity("Police","999",2,"22/07/2017");
        ContactEntity contact2 = new ContactEntity("Hopital","2235",10,"21/07/2017");
        ContactEntity contact3 = new ContactEntity("Electricity emergency","99935",8,"19/07/2017");
        ContactEntity contact4 = new ContactEntity("Fire Department","353499",5,"29/04/2017");
        ContactEntity contact5 = new ContactEntity("Redcross Ambulance","2345299",3,"25/02/2017");
        
        contact.add(contact1);
        contact.add(contact2);
        contact.add(contact3);
        contact.add(contact4);
        contact.add(contact5);
    }
    
    
    public boolean addContact(){
        String yn;
        do{
        ContactReport();
        System.out.print("\nPlease enter the emergency unit you want to ADD : ");
        get.nextLine();
        emergencyUnit = get.nextLine();
        
        
        System.out.print("\nPlease enter the contact number you want to ADD : ");
        contactNum = get.nextLine();
        
        System.out.print("\nPlease enter the distance of the emergency unit : ");
        distance = get.nextInt();
        
        
        ContactEntity newContact = new ContactEntity(emergencyUnit, contactNum, distance, date);
        
        System.out.println("");
        System.out.println("==========================================");
        System.out.println("Emergency Contact Number Added Successfully!!");
        System.out.println("==========================================");
        ContactReport();
         contact.add(newContact); 
        
         System.out.print("Add another tool? PRESS Y to continue: ");
        get.nextLine();
        yn = get.nextLine();
        }while(yn.equals("Y") || yn.equals("y"));
        
        System.out.println("Thank you");
             
        return true;
        }
    
    public void removeContact(){
        int removeNum;
        String yn;
        do{
        ContactReport();
        try{
        System.out.println("Warning! Once the contact number has been DETELED !");
        System.out.println("The data CANNOT be recover !");
        System.out.print("Please choose which contact number you want to remove : ");
        removeNum = get.nextInt();
        
        contact.remove(removeNum);
        ContactReport();
         if (removeNum > contact.getLength() || removeNum < 1){
            System.out.println("=================");
            System.out.println("Invalid choice!");
            System.out.println("=================");
        }
        else{
        System.out.println("The selected contact has been removed.");
                }
        }
         catch(InputMismatchException e){
                System.out.println("Invalid input!, Please try again!");
            }
        System.out.print("Remove another tool? PRESS Y to continue: ");
        get.nextLine();
        yn = get.nextLine();
        }while(yn.equals("Y") || yn.equals("y"));
        System.out.println("Thank you");

    }
    
    public void updateContact(){
        int updateNum;
        String yn;
        do{
        ContactReport();
        try{
        System.out.print("Please choose the contact number you want to UPDATE : ");
            updateNum = get.nextInt();
            if (updateNum > contact.getLength() || updateNum < 1){
            System.out.println("=================");
            System.out.println("Invalid choice!");
            System.out.println("=================");
            }
            else{
            ContactEntity newContact = new ContactEntity(emergencyUnit, contactNum, distance, date);
            System.out.print("Emergency Unit :");
            get.nextLine();
            emergencyUnit = get.nextLine();
            newContact.setEmergencyUnit(emergencyUnit);
            System.out.print("Contact Number :");
            contactNum = get.nextLine();
            newContact.setContactNum(contactNum);
            System.out.print("Distance :"); 
            distance = get.nextInt();
            newContact.setDistance(distance);
            
            contact.remove(updateNum);
            ContactEntity tempContact = new ContactEntity(emergencyUnit, contactNum, distance, date);
            contact.add(tempContact);
            ContactReport();
            System.out.println("The details had been UPDATED ! ");
            }
        }
           catch(InputMismatchException e){
                System.out.println("Invalid input!, Please try again!");
            }
            
            System.out.print("Update another tool? PRESS Y to continue:");
            get.nextLine();
            yn = get.nextLine();
            }while(yn.equals("Y") || yn.equals("y"));
            System.out.println("Thank you");
    }
    
    public void ContactReport(){
        System.out.println("======================");
        System.out.println("List of Contact Number");
        System.out.println("======================");
        System.out.println("No. Emergency Unit           "
                + "Contact Number           "
                + "Distance(km) Date Added");
        
        for(int i=1; i <= contact.getLength();i++){
            System.out.printf("%d.  %-21s %12s %20d %17s\n",i,
                    
                contact.getEntry(i).getEmergencyUnit(),
                contact.getEntry(i).getContactNum(),
                contact.getEntry(i).getDistance() ,
                contact.getEntry(i).getDate());
        }
    }
    public void ContactMenu(){
        try{
        do{
         System.out.println("=============================");
         System.out.println("Contact Number Service");
         System.out.println("=============================");
         System.out.println("1.Add Contact Number");
         System.out.println("2.Remove Contact Number");
         System.out.println("3.Update Contact Number");
         System.out.println("4.Print Contact Report");
         System.out.println("5.Back");
         
         System.out.print("Enter your choice: ");
         choice = get.nextInt();
         System.out.println("");
         
         switch(choice){
            case 1: 
                this.addContact();
                break;
            case 2: 
                this.removeContact();              
                break;
            case 3:
                this.updateContact();
                break;
            case 4:  
                this.ContactReport();
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid choice, Please try again!");
                break;
        }
          }while(choice >= 6); }
    catch(InputMismatchException e){
                System.out.println("Invalid input!, Please try again!");
            }
     }
}
 

