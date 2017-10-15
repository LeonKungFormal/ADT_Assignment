package client;

import adt.*;
import entity.EmergencyAlert;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Name: Yam Wern Min
 *  ID  : 17WMR09650
 */

public class AlertRequest {
    SecurityListInterface<EmergencyAlert> alert;
    Scanner get = new Scanner(System.in);
    String date;
    String time;
    int lvl = 0;
    
    public AlertRequest(){
        alert = new SortedSecurityList<>();
        
        EmergencyAlert alert1 = new EmergencyAlert("Turkey","Fire","FIRE","5th floor",3,"29/07/2017","1000");
        EmergencyAlert alert2 = new EmergencyAlert("See Leon","Lift not working","middle lift","3rd floor",2,"29/07/2017","1234");
        EmergencyAlert alert3 = new EmergencyAlert("Kah Wai","Car blocking","FASTER","Parking lot",1,"30/07/2017","1430");
        EmergencyAlert alert4 = new EmergencyAlert("Wei Han","Fan malfunction","Ceiling fan not working","2nd floor",1,"30/07/2017","1615");
        EmergencyAlert alert5 = new EmergencyAlert("Wei Jian","Snake","Found snake at corridor","Parking lot",3,"3/08/2017","1400");
        
        alert.add(alert1);
        alert.add(alert2);
        alert.add(alert3);
        alert.add(alert4);
        alert.add(alert5);
    }
    
    public boolean addRequest(){
        String name;
        String emergency;
        String details;
        String location;
        int level;
        
        System.out.print("Enter the resident name: ");
        name = get.nextLine();
        
        System.out.print("Enter your emergency: ");
        emergency = get.nextLine();
        
        System.out.print("Enter the emergency details: ");
        details = get.nextLine();
        
        System.out.print("Enter the location: ");
        location = get.nextLine();
        
        do{
            System.out.print("Enter the emergency level(1-3): ");
            level = Integer.parseInt(get.nextLine());
            
            if(level < 1 || level > 3){
                System.out.println("Please enter the emergency level correctly(1-3)\n");
            }
        }while(level < 1 || level > 3);
        
        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        time = new SimpleDateFormat("HHmm").format(new Date(System.currentTimeMillis()));
        EmergencyAlert newEmergency = new EmergencyAlert(name,emergency,details,location,level,date,time);
        
        System.out.println("");
        System.out.println("Emergency Information Added Successfully!!");
        System.out.println("==========================================");
        System.out.println("Resident name: "+ name);
        System.out.println("Emergency: "+emergency);
        System.out.println("Emergency details: "+details);
        System.out.println("Emergency location: "+location);
        System.out.println("Emergency Level: "+level);
        
        alert.add(newEmergency);
        
        System.out.println("\nBack to Alert Menu ?");
        System.out.print("'y' to proceed and 'n' to go back main menu >> ");
        String yn = get.nextLine();
        
        return yn.equalsIgnoreCase("y");
    }
    
    public EmergencyAlert retrieveRequest(int position){
        EmergencyAlert request = null;
        
        for(int a=1;a <= alert.getLength();a++){
            if(position == a)
                request = alert.getEntry(position);
        }
        
        return request;
    }
    
    public boolean deleteRequest(){
        EmergencyAlert deleted;
        int deleteNo;
        
        displayReport();
        
        System.out.println("");
        
        do{
            System.out.print("Enter the Emergency Request No. to delete > ");
            deleteNo = Integer.parseInt(get.nextLine());

            if(deleteNo > alert.getLength())
                System.out.print("Invalid choice, ");
            else{
                System.out.println("Are you sure u want to delete this request?");
                System.out.print("Once deleted cannot be retrieve (y/n) >> ");
                
                String yn = get.nextLine();
                
                if(yn.equalsIgnoreCase("y")){
                    deleted = alert.remove(deleteNo);
                    
                    System.out.println("\nRequest Deleted Succesfully");
                    System.out.println("===========================");
                    System.out.println("Resident name: "+deleted.getResidentName());
                    System.out.println("Emergency: "+deleted.getEmergency());
                    System.out.println("Emergency detail: "+deleted.getEmergencyDetail());
                    System.out.println("Location: "+deleted.getLocation());
                    System.out.println("Alert Level: "+deleted.getAlertLevel());
                    System.out.println("");
                    System.out.println("This Emergency Request has been deleted");
                }
            }
        }while(deleteNo > alert.getLength());
        
        System.out.println("\nBack to Alert Menu ?");
        System.out.print("'y' to proceed and 'n' to go back main menu >> ");
        String yn = get.nextLine();
        
        return yn.equalsIgnoreCase("y");
    }
    
    public boolean updateRequest(){
        int updateNo;
        String input;
        
        displayReport();
        
        System.out.println("");
        do{
            System.out.print("Enter the Emergency Request No. to update >> ");
            updateNo = Integer.parseInt(get.nextLine());

            if(updateNo > alert.getLength())
                System.out.print("Invalid choice, ");
            else{
                EmergencyAlert tempAlert;
                tempAlert = alert.remove(updateNo);
                
                System.out.print("Enter the new resident name(-1 to skip): ");
                input = get.nextLine();
                
                if(!input.equals("-1"))
                    tempAlert.setResidentName(input);
                
                System.out.print("Enter the new emergency (-1 to skip): ");
                input = get.nextLine();
                
                if(!input.equals("-1"))
                    tempAlert.setEmergency(input);
                
                System.out.print("Enter the new emergency detail (-1 to skip): ");
                input = get.nextLine();
                
                if(!input.equals("-1"))
                    tempAlert.setEmergencyDetail(input);
                
                System.out.print("Enter the new emergency location (-1 to skip): ");
                input = get.nextLine();
                
                if(!input.equals("-1"))
                    tempAlert.setLocation(input);
                
                System.out.print("Enter the new alert level (-1 to skip): ");
                input = get.nextLine();
                
                if(!input.equals("-1") && Integer.parseInt(input) <= 3)
                    tempAlert.setAlertLevel(Integer.parseInt(input));
                
                System.out.println("\nUpdated alert request");
                System.out.println("=====================");
                System.out.println("Resident name: "+tempAlert.getResidentName());
                System.out.println("Emergency: "+tempAlert.getEmergency());
                System.out.println("Emergency detail: "+tempAlert.getEmergencyDetail());
                System.out.println("Location: "+tempAlert.getLocation());
                System.out.println("Alert Level: "+tempAlert.getAlertLevel());
                
                alert.add(tempAlert);
            }
            
        }while(updateNo > alert.getLength());
        
        System.out.println("\nBack to Alert Menu ?");
        System.out.print("'y' to proceed and 'n' to go back main menu >> ");
        String yn = get.nextLine();
        
        return yn.equalsIgnoreCase("y");
    }
    
    public void displayReport(){
        System.out.println("List of Emergency Alert");
        System.out.println("No. Resident Name          Emergency            "
                + "Emergency details                    Location               "
                + "Emergency Level     Date Added   Time Added  Completed");
        
        for(int i=1; i <= alert.getLength();i++){
            System.out.printf("%d.  %-22s %-20s %-36s %-29s %-12d %-15s %-11s ",i, 
                    alert.getEntry(i).getResidentName(),
                alert.getEntry(i).getEmergency(),
                alert.getEntry(i).getEmergencyDetail(),
                alert.getEntry(i).getLocation() ,
                alert.getEntry(i).getAlertLevel(),alert.getEntry(i).getDate(),
                alert.getEntry(i).getTime());
            
            if(alert.getEntry(i).isCompleted())
                System.out.println("Yes");
            else
                System.out.println("No");
        }
        
    }
    
    public void alertMenu(){
        int choice;
        int error = 0;
        String input;
        boolean cont;
        
        try{
            System.out.println("Emergency Alert Menu");
            System.out.println("====================");
            System.out.println("1. Add Emergency Request");
            System.out.println("2. Update Emergency Request");
            System.out.println("3. Delete Emergency Request");
            System.out.println("4. View Emergency Request REPORT");
            System.out.println("5. Back");

            do{
                System.out.print("Enter your choice: ");
                input = get.nextLine();
                
                if(Character.isLetter(input.charAt(0))){
                    error++;
                    System.out.print("Invalid choice, ");
                }
            }while(error != 0);
            
                choice = Integer.parseInt(input);
                System.out.println("");
            
                switch(choice){
                    case 1:
                        System.out.println("Add Emergency Request");
                        System.out.println("=====================");
                        cont = addRequest();
                        
                        if(cont)
                            alertMenu();
                        
                        System.out.println("");
                        break;
                    case 2:
                        System.out.println("Update Emergency Request");
                        System.out.println("========================");
                        cont = updateRequest();
                        
                        if(cont)
                            alertMenu();
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("Delete Emergency Request");
                        System.out.println("========================");
                        cont = deleteRequest();
                        
                        if(cont)
                            alertMenu();
                        System.out.println("");
                        break;
                    case 4:
                        System.out.println("Alert Request Report");
                        System.out.println("====================");
                        displayReport();
                        System.out.println("\nBack to Alert Menu ?");
                        System.out.print("'y' to proceed and 'n' to go back main menu >> ");
                        String yn = get.nextLine();
                        
                        System.out.println("");
                        if(yn.equalsIgnoreCase("y"))
                            alertMenu();
                        else
                            break;
                    case 5:
                        break;
                    default:
                        System.out.print("Invalid choice, ");
                }
                
                
        } catch(InputMismatchException e){
            System.out.println("Invalid input!, Please try again!");
            System.out.println("");
        }
    }
    
    public SecurityListInterface<EmergencyAlert> getAlert(){
        return alert;
    }
    
}
