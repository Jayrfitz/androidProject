package edu.csumb.jfitzgerald.airplanereserationsystem;

/**
 * Created by jasonfitzgerald on 5/9/16.
 */

public class User {
    String username, password;

    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }
    String getUN(){ return this.username; }
    String getPW(){ return this.password; }

    public String toString()
    {
        String message = ( "User : " +username+ "\n");
        return message;
    }




}