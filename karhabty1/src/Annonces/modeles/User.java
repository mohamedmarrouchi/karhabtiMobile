/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.modeles;


/**
 *
 * @author Mohamed
 */
public class User {
    private int id;
private String username;
private String image;
private String nom;
private String email;
private String password;
private String CodePostal;
private String adresse;
private String NumTel;
public static int connectedUser;

    public static int getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(int connectedUser) {
        User.connectedUser = connectedUser;
    }


    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String image, String nom, String email, String password, String CodePostal, String adresse, String NumTel) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.CodePostal = CodePostal;
        this.adresse = adresse;
        this.NumTel = NumTel;
    }

    public User(String username, String image, String nom, String email, String password, String CodePostal, String adresse, String NumTel) {
        this.username = username;
        this.image = image;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.CodePostal = CodePostal;
        this.adresse = adresse;
        this.NumTel = NumTel;
    }

    public User(int id, String username, String image, String email) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.email = email;
    }
    

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String CodePostal) {
        this.CodePostal = CodePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return NumTel;
    }

    public void setNumTel(String NumTel) {
        this.NumTel = NumTel;
    }





    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", image=" + image + ", nom=" + nom + ", email=" + email + ", password=" + password + ", CodePostal=" + CodePostal + ", adresse=" + adresse + ", NumTel=" + NumTel + '}';
    }
    



}
