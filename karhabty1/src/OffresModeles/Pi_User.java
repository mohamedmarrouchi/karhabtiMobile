/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffresModeles;

/**
 *
 * @author ELYES
 */
public class Pi_User {
    public static int connectedUser;
    private int id;
    private String username;
    private int numTel;
    private String image;
    private String email;
    private String password;
    

    public Pi_User() {
    }
    
    public Pi_User(int id) {
        this.id = id;
       }

    public Pi_User(int id, String username, int numTel, String image, String email, String password) {
        this.id = id;
        this.username = username;
        this.numTel = numTel;
        this.image = image;
        this.email = email;
        this.password = password;
    }
    
    public Pi_User( String username, int numTel, String image, String email, String password) {
        this.username = username;
        this.numTel = numTel;
        this.image = image;
        this.email = email;
        this.password = password;
    }
    
    

    public Pi_User(int id, String username, String image, String email, String password) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.email = email;
        this.password = password;
    }
    public Pi_User(String username, String image, String email, String password) {
        this.username = username;
        this.image = image;
        this.email = email;
        this.password = password;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public static int getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(int connectedUser) {
        Pi_User.connectedUser = connectedUser;
    }

    
    

   

    public int getId() {
        return id;
    }

    

    public String getUsername() {
        return username;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }
    
    

    

    public void setId(int id) {
        this.id = id;
    }

   
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pi_User other = (Pi_User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pi_User{" + "id=" + id + ", username=" + username + ", image=" + image + ", email=" + email + ", password=" + password + '}';
    }

   
    

    

    
    
    
    
    
}
