/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import adt.*;
/**
 *
 * @author Leon
 */
public class Guard implements Comparable<Guard>{
    
    private String ID;
    private String Name;
    private int amountTask;
    
    private SecurityListInterface<EmergencyAlert> emergencyAlertList;
    private SecurityListInterface<EmergencyAlert> emergencyAlertReport;
    
    private SecurityListInterface<ToolEntity> toolList;
    private SecurityListInterface<ToolEntity> toolListReport;    
    private int maximumItem = 20;

    public Guard(String ID,String Name){
        this.ID=ID;
        this.Name=Name;
        emergencyAlertList = new SortedSecurityList();
        toolList = new SortedSecurityList();
        
        //Report
        emergencyAlertReport = new SortedSecurityList();
        toolListReport = new SortedSecurityList();
    }
    
    public boolean addTask(EmergencyAlert emergency){
        if(emergencyAlertList.getLength()==0){
               emergencyAlertList.add(emergency);
               emergencyAlertReport.add(emergency);            
               return true;
        }
        else{
        for(int i = 1;i<=emergencyAlertList.getLength();i++){
            if(emergencyAlertList.getEntry(i).equals(emergency)==true){
               return false;
            }
        }
        emergencyAlertList.add(emergency);
        emergencyAlertReport.add(emergency);
        return true;
        }
    }
    
    public EmergencyAlert deleteFirstTask(){
        return emergencyAlertList.remove(1);
    }
    
    public ToolEntity addEquipment(ToolEntity equipment){
        if(toolList.isFullEquipment(maximumItem)){
            System.out.println("Guard could not hold more than "+maximumItem+" tools!");
        }
        else{
        toolList.add(equipment);
        toolListReport.add(equipment);        
        }
        return equipment;
    }    
    
    public ToolEntity deleteEquipment(int choice){
        return toolList.remove(choice);
    }   

    public int checkTaskAmount(){
        return emergencyAlertList.getLength();
    }
    

    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }    

    public SecurityListInterface<EmergencyAlert> getEmergencyAlertList() {
        return emergencyAlertList;
    }

    public void setEmergencyAlertList(SecurityListInterface<EmergencyAlert> emergencyAlertList) {
        this.emergencyAlertList = emergencyAlertList;
    }

    public SecurityListInterface<EmergencyAlert> getEmergencyAlertReport() {
        return emergencyAlertReport;
    }

    public void setEmergencyAlertReport(SecurityListInterface<EmergencyAlert> emergencyAlertReport) {
        this.emergencyAlertReport = emergencyAlertReport;
    }

    public SecurityListInterface<ToolEntity> getToolList() {
        return toolList;
    }

    public void setToolList(SecurityListInterface<ToolEntity> toolList) {
        this.toolList = toolList;
    }

    public SecurityListInterface<ToolEntity> getToolListReport() {
        return toolListReport;
    }

    public void setToolListReport(SecurityListInterface<ToolEntity> toolListReport) {
        this.toolListReport = toolListReport;
    }
    
    public int getMaximumItem() {
        return maximumItem;
    }

    public void setMaximumItem(int maximumItem) {
        this.maximumItem = maximumItem;
    }

    public int getAmountTask() {
        return amountTask;
    }

    public void setAmountTask(int amountTask) {
        this.amountTask = amountTask;
    }

    //Compare by task amount. More task will be at back while less task will be at front
    @Override
    public int compareTo(Guard guard) {
        return Integer.compare(this.getAmountTask(), guard.getAmountTask());
    }

    
    
    
    
    
}
