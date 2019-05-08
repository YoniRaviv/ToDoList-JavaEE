package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {

    private static final long serialVersionUID = 56037785502L;

    ArrayList<TdlTask> taskList = new ArrayList<>();//MAYBE NEED TO BE IN USERS CLASS??
    private String email;
    private String password;
    private String name;
    private int id;

    public Users() {
    }

    public Users(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
