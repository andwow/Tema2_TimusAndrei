package com.example.tema2_timusandrei.models;

public class User {
    private int id;
    private String name;
    private String userName;
    public User(int id, String name, String userName)
    {
        this.id = id;
        this.name = name;
        this.userName = userName;
    }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUserName(String userName) { this.userName = userName; }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getUserName() { return userName; }

}
