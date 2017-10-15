package entity;
/**
 *
 * @author Min
 */
public class User {
    private String id;
    private String password;
    private String position;

    public User(String id, String password, String position) {
        this.id = id;
        this.password = password;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
