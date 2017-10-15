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
public class WorkloadManage {
    
    //hardcoded data
    private Guard guard1 = new Guard("10WJ","Wei Jie");
    private Guard guard2 = new Guard("20JO","Jotaro");
    private Guard guard3 = new Guard("10SA","Saifu");    
    private SecurityListInterface<Guard> guardList = new SortedSecurityList();    
    
    SecurityListInterface<EmergencyAlert> emergencyAlertList;
    SecurityListInterface<ToolEntity> toolList;
    Guard guard;
    EmergencyAlert emergencyAlert;
    
    public int choice;
    public Scanner get = new Scanner(System.in);
    
    public WorkloadManage(SecurityListInterface<EmergencyAlert> emergencyAlertList,SecurityListInterface<ToolEntity> toolList){
        this.guardList.add(guard1);
        this.guardList.add(guard2);
        this.guardList.add(guard3);
        this.emergencyAlertList=emergencyAlertList;
        this.toolList = toolList;
    }

    public void selectionMenu(int select){        
            switch(select){
                    case 1:
                        assignGuardTaskUI();
                        break;
                    case 2:
                        removeFirstWorkloadUI();
                        break;
                    case 3:
                        receiveEquipmentUI();
                        break;
                    case 4:
                        removeEquipmentUI();
                        break;
                    case 5:
                        displayAllAssignment();
                        break;
                    case 6:
                        displayEquippedTools();
                        break;                        
                    case 7:
                        displayReport();
                        break;
                    case 8:
                        break;                           
                }        
        
    }
    
    public int WorkloadUI(){
        do{
            System.out.println("==================================");
            System.out.println("Workload Assignment & Coordination");
            System.out.println("==================================");
            System.out.println("1. Assign workload to guard");
            System.out.println("2. Delete guard workload");
            System.out.println("3. Assign equipment to guard");
            System.out.println("4. Remove equipment from guard");
            System.out.println("5. Display guard current assignment");
            System.out.println("6. Display currently equipped tools");              
            System.out.println("7. Display report");
            System.out.println("8. Back");
            System.out.print("Enter your choice: ");
                choice = get.nextInt();
            if(choice>8)
                System.out.println("Invalid input");                
                }while(choice > 8);
        return choice;
    }

    public void assignGuardTaskUI(){
        EmergencyAlert emergency = null;
        UserGuardInterface<Integer> storedInt = new UserGuardList();        
        boolean ifTaskless = false;
        boolean errorAdding = true;
        int removeEmergency = 0;
        emergency = selectWorkload();
        
        if(emergency == null)
            return;
        else{
        for(int l = 1;l<=guardList.getLength();l++){        
            if(guardList.getEntry(l).getAmountTask()==0){      
                ifTaskless = true;
            }
        for(int m = 1;m<=emergencyAlertList.getLength();m++){    
            if(emergencyAlertList.getEntry(m)==emergency)
                removeEmergency = m;
        }
        }
                do{ 
                System.out.println("=================================================");
                System.out.println("Select guard to receive workload(type -1 to exit)");
                System.out.println("=================================================");
            for(int i = 1;i<=guardList.getLength();i++){                
                if(guardList.getEntry(i).getAmountTask()==0){
                    storedInt.add(i);
                System.out.println(i+".ID:"+guardList.getEntry(i).getID()+"     Name:"+guardList.getEntry(i).getName());
                        }
            }            
            System.out.print("\nEnter your guard choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return;
            
            for(int l =1;l<=storedInt.getNumberOfEntries();l++){
            if(choice==storedInt.getEntry(l)){
                errorAdding = false;
                emergencyAlertList.remove(removeEmergency);
                guardList.getEntry(choice).addTask(emergency);
                guardList.getEntry(choice).setAmountTask(1);                
                System.out.println("Successfully added");
            }
            }
            if(choice>guardList.getLength())
                System.out.println("Invalid input!");                  
            else if(errorAdding==true){
                System.out.println("Error: add task to guard that already have a task");                
                return;                
            }
            }while(choice > guardList.getLength());
            if(ifTaskless==false){
                System.out.println("No task available");
                return;
            }
            }
                }
/*    
    public void receiveWorkload(EmergencyAlert emergency){
        
        
        if(guardSelected==guardList.getLength())
            guardSelected = 1;
        guard = (Guard) guardList.getEntry(guardSelected);
        int taskAmount = guard.checkTaskAmount();
        //Search for 
        for(int i = 1;i<=guardList.getLength();i++){
            if(guard.checkTaskAmount()==0){
            guard.addTask(emergency);
            guardSelected++;
            }
        else
            if(guard.checkTaskAmount()>taskAmount)
                taskAmount = guard.checkTaskAmount();                
            }
        
    }
*/
    
    //return value is returned what emergency has been dealt with
    public EmergencyAlert removeFirstWorkloadUI(){
        UserGuardInterface<Integer> storedInt = null;
        boolean detectNoGuard = true;
do{        
        for(int i = 1;i<=guardList.getLength();i++)
            if(guardList.getEntry(i).checkTaskAmount()>0){
            detectNoGuard = false;
            storedInt = new UserGuardList();
            }
        if(detectNoGuard==false){
                          
            System.out.println("==================================================");
            System.out.println("Select guard to remove workload(Type -1 to return)");
            System.out.println("==================================================");
            for(int k =1;k<=guardList.getLength();k++){
            if(guardList.getEntry(k).checkTaskAmount()>0){  
          
                storedInt.add(k);
                System.out.println("\n"+k+".ID:"+guardList.getEntry(k).getID()+"     Name:"+guardList.getEntry(k).getName());
                System.out.println("Task assigned detail");
                System.out.println("============================================");
            System.out.println("No. Resident Name          Emergency            "
            + "Emergency details                    Location               "
            + "Emergency Level     Date Added   Time Added");
            System.out.println("==============================================="
                    + "========================================================"
                    + "================================================");
                for(int j = 1;j<=guardList.getEntry(k).getEmergencyAlertList().getLength();j++){

                    EmergencyAlert printReport = guardList.getEntry(k).getEmergencyAlertList().getEntry(j);
                        System.out.printf("%d.  %-22s %-20s %-36s %-29s %-12d %-15s %-11s\n"
                                ,j,printReport.getResidentName()
                                ,printReport.getEmergency()
                                ,printReport.getEmergencyDetail()
                                ,printReport.getLocation()
                                ,printReport.getAlertLevel()
                                ,printReport.getDate()
                                ,printReport.getTime());
                    }

            }
            }
        }if(detectNoGuard==true){
            System.out.println("Currently no task being executed!");
            return emergencyAlert=null;
        }
            System.out.print("\nEnter your guard choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return emergencyAlert=null;
            else  
                for(int j =1;j<=storedInt.getNumberOfEntries();j++)
                    if(storedInt.getEntry(j).equals(choice)){
                    emergencyAlert=guardList.getEntry(choice).deleteFirstTask();
                    guardList.getEntry(choice).setAmountTask(0);                     
                    for(int m =1;m<=emergencyAlertList.getLength();m++)
                    if(emergencyAlertList.getEntry(m).equals(emergencyAlert))
                        emergencyAlertList.remove(m);
                    for(int l =1;l<=guardList.getEntry(choice).getEmergencyAlertReport().getLength();l++){
                    if(guardList.getEntry(choice).getEmergencyAlertReport().getEntry(l).equals(emergencyAlert))
                    guardList.getEntry(choice).getEmergencyAlertReport().getEntry(l).setCompleted(true);
                    }
                    System.out.println("Successfully delete");
                    return emergencyAlert;
                    }
                System.out.println("Invalid input!");
            }while(choice >guardList.getLength());
        return emergencyAlert;
    }
    
    //return value is what item and amount of item that taken
    public ToolEntity receiveEquipmentUI(){
        ToolEntity returnTool = null;
        ToolEntity toolSelected = null;
        int toolQuantity = 0;
        toolSelected = selectTool(toolSelected);
        if(toolSelected == null)
            return returnTool;
        else
            toolSelected = selectToolQuantity(toolSelected);
                if(toolSelected == null)
                    return returnTool;
                else
                    do{
                        try{
                System.out.println("Selected tools");
                System.out.println("==================================");
                System.out.println("Number Name   Type   Current Quantity   Date added");
                System.out.println("============================================");                
                System.out.println(toolSelected.getToolName()+"   "
                            +toolSelected.getType()+"   "
                            +toolSelected.getQuantity()+"   "
                            +toolSelected.getDate());                
                System.out.println("==================================");
                System.out.println("Select guard to receive equipment(Type -1 to return)");
                System.out.println("==================================");
                for(int i = 1;i<=guardList.getLength();i++){                
                System.out.println(i+".ID:"+guardList.getEntry(i).getID()+"     Name:"+guardList.getEntry(i).getName());
                for(int j = 1;j<=guardList.getEntry(i).getToolList().getLength();j++){
                        System.out.println(j+"."+guardList.getEntry(i).getToolList().getEntry(j).getToolName()
                        +" Quantity:"+guardList.getEntry(i).getToolList().getEntry(j).getQuantity()
                        );
                        }
                }
            System.out.print("\nEnter your guard choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return returnTool = null;
            else if(choice>guardList.getLength()||choice<0)
                System.out.println("Invalid input!");
            else{
                guardList.getEntry(choice).addEquipment(toolSelected);
                for(int m = 1;m<=toolList.getLength();m++){
                        for(int o = 1;o<=guardList.getEntry(choice).getToolList().getLength();o++)
                            if(guardList.getEntry(choice).getToolList().getEntry(o).getToolName().equals(toolList.getEntry(m).getToolName())){
                                    toolQuantity = toolList.getEntry(m).getQuantity()-guardList.getEntry(choice).getToolList().getEntry(o).getQuantity();
                                    if(toolQuantity==0){
                                        toolList.remove(m);
                                        return returnTool;
                                    }
                                    else if(toolQuantity<0){
                                        System.out.println("Error: take more item than available");
                                        return returnTool;
                                    }
                                    else{
                                        toolList.getEntry(m).setQuantity(toolQuantity);
                                        System.out.println("Tools successfully added!");
                                        returnTool=toolSelected;
                                        return returnTool;                                        
                                    }
                                        }
                }
                

            }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();
            } 
            }while(choice > guardList.getLength());             

        //select guard
                    
        return returnTool;
    }
    
    //return value is what item and amount of item that returned
    public ToolEntity removeEquipmentUI(){
        ToolEntity returnTool = null;
        int guardSelected = 0;       
        //select guard
        choice =selectGuardRemoveEquip();
            if(choice == -1)
                return returnTool;
            else if(choice>guardList.getLength()||choice<0){
                System.out.println("Invalid input!");
                return returnTool;
            }
            else
                guardSelected = choice; 
                do{
            try{
            System.out.println("================================================");
            System.out.println("Select equipped tools to remove(type -1 to exit)");
            System.out.println("================================================");
                System.out.println("ID:"+guardList.getEntry(guardSelected).getID()+"     Name:"+guardList.getEntry(guardSelected).getName());
                System.out.println("Equiped tools lists");
                System.out.println("============================================");                
                for(int j = 1;j<=guardList.getEntry(guardSelected).getToolList().getLength();j++){
                        System.out.println(j+"."+guardList.getEntry(guardSelected).getToolList().getEntry(j).getToolName()
                        +" Quantity:"+guardList.getEntry(guardSelected).getToolList().getEntry(j).getQuantity()
                        );
                        }  
            System.out.print("\nEnter your tools choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return returnTool;
            
            if(choice>guardList.getEntry(guardSelected).getToolList().getLength()||choice<0)
                System.out.println("Invalid input!");
            else{
                returnTool = guardList.getEntry(guardSelected).deleteEquipment(choice);
                int addBackTools = 0;
                for(int j = 1;j<=toolList.getLength();j++){
                    if(returnTool.getToolName().equals(toolList.getEntry(j).getToolName())){
                        addBackTools = returnTool.getQuantity() + toolList.getEntry(j).getQuantity();
                        toolList.getEntry(j).setQuantity(addBackTools);
                        System.out.println("Tools successfully removed!");
                        return returnTool;                       
                    }
                }
                toolList.add(returnTool);
                System.out.println("Tools successfully removed!");
                return returnTool;
            }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();
            }                 
            }while(choice >guardList.getEntry(guardSelected).getToolList().getLength());  

        //select tools
      
        return returnTool;
    }        

    public void displayAllAssignment(){
            String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));        
            int totalObject = 0;
            System.out.println("==================================");
            System.out.println("All current task assigned to guard      Current date:"+dateFormat);
            System.out.println("==================================");
            for(int i = 1;i<=guardList.getLength();i++){
                if(!guardList.getEntry(i).getEmergencyAlertList().isEmpty()){
                totalObject = 0;
                System.out.println("\n"+i+".ID:"+guardList.getEntry(i).getID()+"     Name:"+guardList.getEntry(i).getName());
                System.out.println("Task assigned detail");
                System.out.println("============================================");
                if(guardList.getEntry(i).getEmergencyAlertList()==null)
                    System.out.println("No assignment being assigned!");    
                else{
            System.out.println("No. Resident Name          Emergency            "
            + "Emergency details                    Location               "
            + "Emergency Level     Date Added   Time Added");
            System.out.println("==============================================="
                    + "========================================================"
                    + "================================================");
                for(int j = 1;j<=guardList.getEntry(i).getEmergencyAlertList().getLength();j++){
                    totalObject++;
                    EmergencyAlert printReport = guardList.getEntry(i).getEmergencyAlertList().getEntry(j);
                        System.out.printf("%d.  %-22s %-20s %-36s %-29s %-12d %-15s %-11s\n"
                                ,j,printReport.getResidentName()
                                ,printReport.getEmergency()
                                ,printReport.getEmergencyDetail()
                                ,printReport.getLocation()
                                ,printReport.getAlertLevel()
                                ,printReport.getDate()
                                ,printReport.getTime());
                    }
                        }
                System.out.println("Total assignment for "+guardList.getEntry(i).getName()+":"+totalObject+"\n");
                }
            }
    }    

    public void displayEquippedTools(){
            int totalObject = 0;
            String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
            System.out.println("==================================");
            System.out.println("List of current equipped tools      Current date:"+dateFormat);
            System.out.println("==================================");
            for(int i = 1;i<=guardList.getLength();i++){
                if(!guardList.getEntry(i).getToolList().isEmpty()){
                totalObject = 0;
                System.out.println("\n"+i+".ID:"+guardList.getEntry(i).getID()+"     Name:"+guardList.getEntry(i).getName());
                System.out.println("Equiped tools lists");
                System.out.println("============================================");                
                for(int j = 1;j<=guardList.getEntry(i).getToolList().getLength();j++){
                        totalObject++;
                        System.out.println(j+"."+guardList.getEntry(i).getToolList().getEntry(j).getToolName()
                        +" Quantity:"+guardList.getEntry(i).getToolList().getEntry(j).getQuantity()
                        );
                        }
                System.out.println("Total different type of tools equipped for "+guardList.getEntry(i).getName()+":"+totalObject+"\n");                
                }
            }
            
    }    
    
    public void displayReport(){
            int totalObject = 0;
            String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
            System.out.println("==================================");
            System.out.println("Summary of guard detail      Current date:"+dateFormat);
            System.out.println("==================================");
            for(int i = 1;i<=guardList.getLength();i++){
                totalObject = 0;
                System.out.println("\n"+i+".ID:"+guardList.getEntry(i).getID()+"     Name:"+guardList.getEntry(i).getName());
                System.out.println("Task assigned detail");
                System.out.println("============================================");
                if(guardList.getEntry(i).getEmergencyAlertReport()==null)
                    System.out.println("No assignment being assigned!");    
                else{
            System.out.println("No. Resident Name          Emergency            "
            + "Emergency details                    Location               "
            + "Emergency Level     Date Added   Time Added   Status");
            System.out.println("==============================================="
                    + "========================================================"
                    + "=========================================================");
                for(int j = 1;j<=guardList.getEntry(i).getEmergencyAlertReport().getLength();j++){
                    totalObject++;                    
                    EmergencyAlert printReport = guardList.getEntry(i).getEmergencyAlertReport().getEntry(j);
                    String status;
                    if(printReport.isCompleted())
                        status = "Completed";
                    else
                        status = "Incomplete";
                        System.out.printf("%d.  %-22s %-20s %-36s %-29s %-12d %-15s %-10s %-7s\n"
                                ,j,printReport.getResidentName()
                                ,printReport.getEmergency()
                                ,printReport.getEmergencyDetail()
                                ,printReport.getLocation()
                                ,printReport.getAlertLevel()
                                ,printReport.getDate()
                                ,printReport.getTime()
                                ,status);                   
                    }
                System.out.println("Total Assignment for "+guardList.getEntry(i).getName()+":"+totalObject+"\n");                 
                        }
            }
    }   

//Select tools    
    public ToolEntity selectTool(ToolEntity toolSelected){
        boolean checkExist = false;
        //select tool
        for(int k=0;k<=toolList.getLength();k++){
            checkExist=true;
        do{
            try{
            System.out.println("======================================");
            System.out.println("Select tool to assign(type -1 to exit)");
            System.out.println("======================================");
            System.out.println("Tools list");
            System.out.println("No. Name                Type        Current Quantity   Date added");
            System.out.println("=================================================================");
                for(int j = 1;j<toolList.getLength();j++){
                    ToolEntity tool = new ToolEntity(toolList.getEntry(j).getToolName(),toolList.getEntry(j).getType(),toolList.getEntry(j).getQuantity()
                    ,toolList.getEntry(j).getDate());
                    System.out.printf("%d.  %-19s %-18s %-11d %s\n",
                            j,tool.getToolName()
                            ,tool.getType()
                            ,tool.getQuantity()
                            ,tool.getDate());
                        }
                
            System.out.print("\nEnter your tools choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return toolSelected = null;
            else if(choice>toolList.getLength()||choice<0)
                System.out.println("Invalid input!");
            else{
                toolSelected = new ToolEntity(toolList.getEntry(choice).getToolName()
                ,toolList.getEntry(choice).getType(),toolList.getEntry(choice).getQuantity(),
                toolList.getEntry(choice).getDate());                
                return toolSelected;
            }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();
            }
        }while(choice > toolList.getLength());
        }
        if(checkExist==false){
        System.out.println("Invalid input!");
        return toolSelected = null;
        }
        return toolSelected=null;                
    
        }
//select tools quantity
    public ToolEntity selectToolQuantity(ToolEntity toolSelected){
                    //select quantity    
        int toolQuantityLimit;
        toolQuantityLimit = toolSelected.getQuantity();
            do{
            try{
                
                System.out.println("==================================");
                System.out.println("Select quantity of tools to assign(type -1 to exit)");
                System.out.println("==================================");
            System.out.println("Number Name   Type   Current Quantity   Date added");
                System.out.println("============================================");                
                System.out.println(toolSelected.getToolName()+"   "
                            +toolSelected.getType()+"   "
                            +toolSelected.getQuantity()+"   "
                            +toolSelected.getDate());
                System.out.print("\nEnter quantity required: ");
                choice = get.nextInt();
                if(choice == -1)
                   return toolSelected = null;
                else if(choice>toolQuantityLimit||choice<0)
                    System.out.println("Invalid input!");
                else{                   
                    toolSelected.setQuantity(choice); 
                    return toolSelected;                    
                }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();
            }    
            }while(choice > toolQuantityLimit);
        return toolSelected = null;
    }    

    public int selectGuardRemoveEquip(){
        UserGuardInterface<Integer> storedInt;
        boolean detectNoGuard = true;
        for(int k =1;k<=guardList.getLength();k++)
        if(guardList.getEntry(k).getToolList().getLength()>0){
            detectNoGuard = false;        
        do{
            try{
                storedInt = new UserGuardList();
            System.out.println("==================================");
            System.out.println("Select guard to remove equipment(Type -1 to return)");
            System.out.println("==================================");
            for(int i = 1;i<=guardList.getLength();i++){
                if(guardList.getEntry(i).getToolList().getLength()>0){
                storedInt.add(i);                    
                System.out.println("\n"+i+".ID:"+guardList.getEntry(i).getID()+"     Name:"+guardList.getEntry(i).getName());
                System.out.println("Equiped tools lists");
                System.out.println("============================================");                
                for(int j = 1;j<=guardList.getEntry(i).getToolList().getLength();j++){
                        System.out.println(j+"."+guardList.getEntry(i).getToolList().getEntry(j).getToolName()
                        +" Quantity:"+guardList.getEntry(i).getToolList().getEntry(j).getQuantity()
                        );
                        }
                }
            }
            System.out.print("\nEnter your guard choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return choice = -1;
            else 
                for(int j =1;j<=storedInt.getNumberOfEntries();j++)
                    if(storedInt.getEntry(j).equals(choice))
                        return choice;

            System.out.println("Invalid input!");                
            return choice = -1;
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();
            }                             
            }while(choice > guardList.getLength());
        }
        if(detectNoGuard==true){
            System.out.println("Currently no tools being equipped!");
            return choice=-1;
        }        
        return choice;
    }

    public EmergencyAlert selectWorkload(){
        UserGuardInterface<Integer> storedInt = new UserGuardList();        
        EmergencyAlert emergency = null;
        int chooseLevel = 0;        
        for(int j = 1;j<=emergencyAlertList.getLength();j++){        
            if(!emergencyAlertList.getEntry(j).isCompleted()){
                if(chooseLevel<emergencyAlertList.getEntry(j).getAlertLevel()){
                    chooseLevel = emergencyAlertList.getEntry(j).getAlertLevel();
                }
            }
        if(chooseLevel==0){
            System.out.println("No task available");
            return emergency;
        }
        }        
        do{
            try{
            System.out.println("==========================================");
            System.out.println("Select workload to assign(type -1 to exit)");
            System.out.println("==========================================");
            System.out.println("\nList of emergency request");
            System.out.println("No. Resident Name          Emergency            "
            + "Emergency details                    Location               "
            + "Emergency Level     Date Added   Time Added");
            System.out.println("==============================================="
                    + "========================================================"
                    + "================================================");
                for(int j = 1;j<=emergencyAlertList.getLength();j++){
                    if(!emergencyAlertList.getEntry(j).isCompleted()){
                        if(chooseLevel==emergencyAlertList.getEntry(j).getAlertLevel()){
                        storedInt.add(j);
                        EmergencyAlert printReport = emergencyAlertList.getEntry(j);
                        System.out.printf("%d.  %-22s %-20s %-36s %-29s %-12d %-15s %-11s\n"
                                ,j,printReport.getResidentName()
                                ,printReport.getEmergency()
                                ,printReport.getEmergencyDetail()
                                ,printReport.getLocation()
                                ,printReport.getAlertLevel()
                                ,printReport.getDate()
                                ,printReport.getTime());
                        }
                    }
                }
            System.out.print("\nEnter your workload choice: ");
            choice = get.nextInt();
            if(choice == -1)
                return emergency;
            else{
                for(int k =1;k<=storedInt.getNumberOfEntries();k++){
                if(choice==storedInt.getEntry(k)){
                emergency = emergencyAlertList.getEntry(choice);
                return emergency;                    
                }
            }
                //when wrong input
                System.out.println("Invalid input!");
                return emergency;
            }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input, Please try again!");
                get.next();        
            }
            }while(choice > guardList.getLength());
        return emergency;
    }
    
    public SecurityListInterface<Guard> getGuardList() {
        return guardList;
    }

    public void setGuardList(SecurityListInterface<Guard> guardList) {
        this.guardList = guardList;
    }
    
    


    
    
/**
    receive task is for assign work to guard
    * 
    */
}

