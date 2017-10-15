/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import adt.*;
import entity.ToolEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author Jian
 */
public class Tool{
    SecurityListInterface<ToolEntity> tool;
    Scanner get = new Scanner(System.in);
    String toolName;
    String type;
    int quantity;
    String date;
    int choice;
    int tempQty;
    ToolEntity tempTool;
  
    public Tool(){
        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        tool = new SortedSecurityList<>();
        ToolEntity tool1 = new ToolEntity("Hammer","rescue",5,"21/07/2017");
        ToolEntity tool2 = new ToolEntity("Axe","rescue",6,"20/07/2017");
        ToolEntity tool3 = new ToolEntity("Fire Extingusher","fire",2,"29/02/2017");
        ToolEntity tool4 = new ToolEntity("First Aid Kit","medical",5,"19/04/2017");
        ToolEntity tool5 = new ToolEntity("Torch Light","survival",4,"03/02/2017");
        
        tool.add(tool1);
        tool.add(tool2);
        tool.add(tool3);
        tool.add(tool4);
        tool.add(tool5);
    }
    
    public void useTool(){
        int whichTool;
        int qty;
        do{
        ToolReport();
        System.out.print("\nPlease choose which tool you want to use : ");
        whichTool = get.nextInt();
        System.out.print("\nPlease enter the quantity of the tool : ");
        qty = get.nextInt();
        tempTool = tool.getEntry(whichTool);
        tempQty = tempTool.getQuantity();
        if( tempQty < qty){
            System.out.print("Not enough quantity!!!\n");
        }
        else{
        tempQty -= qty;
        tempTool.setQuantity(tempQty);
        tool.remove(whichTool);
        tool.add(tempTool);
        }
        }while(tempQty < qty);
        ToolReport();
     
    }
    
    public void addTool(){
        String yn;
        do{
            ToolReport();
        System.out.print("\nPlease enter the Tool you want to ADD : ");
        get.nextLine();
        toolName = get.nextLine();
        
        System.out.print("\nPlease enter the type of the tool : ");
        type = get.nextLine();
        
        System.out.print("\nPlease enter the quantity of the tool : ");
        quantity = get.nextInt();
        
        ToolEntity newTool = new ToolEntity(toolName, type, quantity, date);
        tool.add(newTool);
        System.out.println("");
        System.out.println("==========================================");
        System.out.println("New emergency tool Added Successfully!!");
        System.out.println("==========================================");
        ToolReport();
      
        System.out.print("Add another tool? PRESS Y to continue:");
        get.nextLine();
        yn = get.nextLine();
        }while(yn.equals("Y") || yn.equals("y"));
        
        System.out.println("Thank you");
        }
    
    public void removeTool(){
        int removeTool;
        String yn;
        do{
        ToolReport();
        try{
        System.out.println("Warning! Once the Tool has been DETELED !");
        System.out.println("The data CANNOT be recover !");
        System.out.print("Please choose which Tool you want to remove : ");
        removeTool = get.nextInt();

        tool.remove(removeTool);
        ToolReport();
        if (removeTool > tool.getLength() || removeTool < 1){
            System.out.println("=================");
            System.out.println("Invalid choice!");
            System.out.println("=================");
        }
        else{
        System.out.println("The selected tool has been removed.");
        }
        }
        catch(InputMismatchException e){
                System.out.println("Invalid input!, Please try again!");
            }
        System.out.print("Remove another tool? PRESS Y to continue:");
        get.nextLine();
        yn = get.nextLine();
        }while(yn.equals("Y") || yn.equals("y"));
        
        
        System.out.println("Thank you");
    }
    
    public void updateTool(){
        int updateTool = 0;
        String yn;
        do{
        ToolReport();
        try{
        System.out.print("Please choose the tool you want to UPDATE : ");
            updateTool= get.nextInt();   
            if (updateTool > tool.getLength() || updateTool < 1){
            System.out.println("=================");
            System.out.println("Invalid choice!");
            System.out.println("=================");
            }
            else{
            ToolEntity newTool = new ToolEntity(toolName, type, quantity, date);
            System.out.print("Tool :");
            get.nextLine();
            toolName = get.nextLine();
            newTool.setToolName(toolName);
            System.out.print("Type :");
            type = get.nextLine();
            newTool.setType(type);
            System.out.print("Quantity :");
            quantity = get.nextInt();
            newTool.setQuantity(quantity);
            
            tool.remove(updateTool);
            ToolEntity tempTool = new ToolEntity(toolName, type, quantity, date);
            tool.add(tempTool);
            ToolReport();
            
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
    
    public void ToolReport(){
        System.out.println("=============");
        System.out.println("List of Tool");
        System.out.println("=============");
        System.out.println("No. Tool Name           "
                + "Type                    "
                + "Quantity             " 
                + "Date Added");
        
        for(int i=1; i <= tool.getLength();i++){
            System.out.printf("%d.  %-20s %-22s %-19d %s\n",i,
                    
                tool.getEntry(i).getToolName(),
                tool.getEntry(i).getType(),
                tool.getEntry(i).getQuantity() ,
                tool.getEntry(i).getDate());
        }
    }
    
    public void ToolMenu(){
        try{
        do{
         System.out.println("=============================");
         System.out.println("Tool and Equipment Service");
         System.out.println("=============================");
         System.out.println("1.Add Tool");
         System.out.println("2.Remove Tool");
         System.out.println("3.Update Tool");
         System.out.println("4.Use Tool");
         System.out.println("5.Print Tool Report");
         System.out.println("6.Back");
         System.out.print("Enter your choice: ");
                choice = get.nextInt();
                System.out.println("");
         switch(choice){
            case 1: 
                this.addTool();
                break;
            case 2: 
                this.removeTool();              
                break;
            case 3:
                this.updateTool();
                break;
            case 4:  
                this.useTool();
                break;
            case 5:  
                this.ToolReport();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid choice!, Please try again!");
                break;
        }
          }while(choice >= 7); }
        catch(InputMismatchException e){
                System.out.println("Invalid input!, Please try again!");
            }
     }
    
    
    public SecurityListInterface<ToolEntity> getTool(){
        return tool;
    }
}
 

