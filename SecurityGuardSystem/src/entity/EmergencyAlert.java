package entity;
/**
 *
 * @author Min
 */
public class EmergencyAlert implements Comparable<EmergencyAlert>{
    private String residentName;
    private String emergency;
    private String location;
    private String emergencyDetail;
    private int alertLevel;
    private String date;
    private String time;
    private boolean completed;

    public EmergencyAlert(String residentName, String emergency, String emergencyDetail, 
            String location, int alertLevel, String date, String time) {
        this.residentName = residentName;
        this.emergency = emergency;
        this.emergencyDetail = emergencyDetail;
        this.location = location;
        this.alertLevel = alertLevel;
        this.date = date;
        this.time = time;
        this.completed = false;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getEmergencyDetail() {
        return emergencyDetail;
    }

    public void setEmergencyDetail(String emergencyDetail) {
        this.emergencyDetail = emergencyDetail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(int alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }   

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    
    @Override
    public int compareTo(EmergencyAlert alert) {
        if(this.getAlertLevel() == alert.getAlertLevel())
            if(this.getDate().equals(alert.getDate()))
                if(this.getTime().compareTo(alert.getTime()) > 0)
                    return -1;
                else
                    return 1;
            
            else
                if(this.getDate().compareTo(alert.getDate())> 0)
                    return -1;
                else
                    return 1;
        
        else
            if(this.getAlertLevel() > alert.getAlertLevel())
                return -1;
            else
                return 1;
        
    }
    
}
