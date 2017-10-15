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
public class ToolEntity implements Comparable<ToolEntity>{
    
    private String toolName;
    private String type;
    private int quantity;
    private String date;
    
public ToolEntity(String toolName, String type, int quantity, String date){
    this.toolName = toolName;
    this.type = type;
    this.quantity = quantity;
    this.date = date;
}

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public int compareTo(ToolEntity quantity ) {
        return Integer.compare(this.getQuantity(), quantity.getQuantity());
    }
    
}

    
