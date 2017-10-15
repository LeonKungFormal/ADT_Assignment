/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.*;
import entity.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Leon
 */
public class MainMenu {
    
    //general variable
    public int choice;
    public Scanner get = new Scanner(System.in);
    
    //Emergency Management
    SecurityListInterface<ToolEntity> toolList = new SortedSecurityList<>();
    
    //Emergency Alert list
    public SecurityListInterface<EmergencyAlert> emergencyAlertList = new SortedSecurityList();
    
    //Workload Assignment and Coordination variable
    public SecurityListInterface<Guard> guardList = new SortedSecurityList();
    public WorkloadManage workloadManage;
    
    public Tool tool;
    public Contact contact;
    public AlertRequest alertRequest;
    //Need a constructor for tools availble to obtain tool

        
   public MainMenu(){
    //Initialised all hard code list
    tool = new Tool();
    contact = new Contact();    
    alertRequest = new AlertRequest();
    workloadManage = new WorkloadManage(alertRequest.getAlert(),tool.getTool());

    //transfer data from client to new list
    toolList = tool.getTool();
    emergencyAlertList = alertRequest.getAlert();
    guardList = workloadManage.getGuardList();
    login();
    do
    {
	choice=selection(MainMenuUI());
    }while(choice==-1);
    }
    
   public int MainMenuUI(){
       
        //main menu
        do{
            try{
            String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
            String time = new SimpleDateFormat("HHmm").format(new Date(System.currentTimeMillis()));
            System.out.println("==================================================================");
            System.out.println("Security Guard System                Date: "+ dateFormat+ "   Time: "+time);
            System.out.println("==================================================================");
            System.out.println("1. Emergency Sevices Management");
            System.out.println("2. Emergency Alert");
            System.out.println("3. Workload Assignment & Coordination");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = get.nextInt();        
            System.out.println("");        
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();
            }        
        }while(choice >= 5);
        return choice;
    }
    
   public int selection(int choice){
        switch(choice){            
            case 1:
                System.out.println("Emergency Services Management");
                System.out.println("=============================");
                System.out.println("1. Tool and Equipment Service");
                System.out.println("2. Contact Number Service");
                System.out.println("3. Back");
                System.out.print("Enter your choice: ");
                choice = get.nextInt();
                System.out.println("");
                do{
                    switch(choice){
                        case 1:
                            tool.ToolMenu();
                            break;
                        case 2:
                            contact.ContactMenu();
                            break;
                        case 3:
                            break;
                    }
                }while(choice >= 4);
                System.out.println("");
                break;
            case 2: 
                alertRequest.alertMenu();
                break;
            case 3:
                choice = workloadManage.WorkloadUI();
                workloadManage.selectionMenu(choice);                
                break;
            case 4:
                System.exit(0);
    }
        return -1;
    }
   
    public void login(){
        UserGuardInterface<User> user = new UserGuardList<>();
        
        boolean match=false;
        
        User user1 = new User("wernmin","abc123","Officer");
        User user2 = new User("weijian","abc123","Manager");
        User user3 = new User("leonkung","abc123","Officer");
        User user4 = new User("yeoh","yeoh","Officer");
        
        user.add(user1);
        user.add(user2);
        user.add(user3);
        user.add(user4);
        System.out.println("Security Guard System");
        System.out.println("=====================");
        do{
            
            System.out.print("Enter the login id: ");
            String id = get.nextLine();
            
            System.out.print("Enter the password: ");
            String pw = get.nextLine();

            for(int a=1; a <= user.getNumberOfEntries(); a++){
                if(id.equals(user.getEntry(a).getId()) && pw.equals(user.getEntry(a).getPassword())){
                    match = true;
                    break;
                }
            }
            if(match == false){
                System.out.println("The login id or password is wrong");
                System.out.println("Please try again.\n");
            }
        }while(match != true);
        
        System.out.println("");
    }
   
/*   
   public void EmergencyServicesManagement(){
        do{
        System.out.println("=============================");
                System.out.println("Emergency Services Management");
                System.out.println("=============================");
                System.out.println("1.Tool and Equipment Service");
                System.out.println("2.Contact Number Service");
                System.out.println("3.Back");
                System.out.print("Enter your choice: ");
                choice = get.nextInt();                
                switch(choice){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:                        
                        break ;
                }                
                }while(choice >= 4);             
   }
    
   public void EmergencyAlert(){
        do{
        System.out.println("=============================");
                System.out.println("Emergency Services Management");
                System.out.println("=============================");
                System.out.println("1.Tool and Equipment Service");
                System.out.println("2.Contact Number Service");
                System.out.println("3.Back");
                System.out.print("Enter your choice: ");
                choice = get.nextInt();                                
                switch(choice){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:                        
                        break;
                }
                }while(choice >= 4);                
   }
    
   public void WorkloadManagement(){
        do{
    System.out.println("==================================");
                System.out.println("Workload Assignment & Coordination");
                System.out.println("==================================");
                System.out.println("1.Assign workload to guard");
                System.out.println("2.update guard workload");
                System.out.println("3.delete guard workload");
                System.out.println("4.back");
                System.out.print("Enter your choice: ");
                choice = get.nextInt();
                switch(choice){
                    case 1:
                        ;
                        break;
                    case 2:
                        break;
                    case 3:                        
                        break;
                    case 4:                        
                        break;    
                }
                }while(choice >= 5);                
   }   
*/   
   public static void main(String[] args) {       
    MainMenu UI = new MainMenu();
    }
       
}
