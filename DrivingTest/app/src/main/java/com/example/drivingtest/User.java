package com.example.drivingtest;

public class User {
    private int id;
    private String name;
    private String password;


    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equal(User user){
        if(this.id == user.id && this.name.equals(user.name) && this.password.equals(user.password)){
            return true;
        }
        return false;
    }
}
