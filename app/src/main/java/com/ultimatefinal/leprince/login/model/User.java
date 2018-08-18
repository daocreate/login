package com.ultimatefinal.leprince.login.model;

/**
 * Created by Le prince on 13/08/2018.
 */
public class User {

    private int id;
    private  String name,email,password;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId()
    {
        return id;
    }


}
