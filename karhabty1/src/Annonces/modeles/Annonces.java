/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.modeles;

import java.util.Date;

/**
 *
 * @author Mohamed
 */
public class Annonces {

    private int id;
    private String TITRE;
    private String BOITE;
    private String region;
    private int NOMBRE_DE_CYLINDRES;
    private String ENERGIE;
    private String DESCRIPITION;
    private int validation;
    private float PRIX;
    private float PUISSANCE_FISCALE;
    private float CYLINDREE;
    private User user;
    private String image;
    private Date date;
    


    public Annonces() {
    }

    public Annonces(int id) {
        this.id = id;
    }

    public Annonces(String TITRE, String BOITE, String region, int NOMBRE_DE_CYLINDRES, String ENERGIE, String DESCRIPITION, float PRIX, float PUISSANCE_FISCALE, float CYLINDREE) {
        this.TITRE = TITRE;
        this.BOITE = BOITE;
        this.region = region;
        this.NOMBRE_DE_CYLINDRES = NOMBRE_DE_CYLINDRES;
        this.ENERGIE = ENERGIE;
        this.DESCRIPITION = DESCRIPITION;
        this.PRIX = PRIX;
        this.PUISSANCE_FISCALE = PUISSANCE_FISCALE;
        this.CYLINDREE = CYLINDREE;
    }

    public Annonces(String TITRE, String BOITE, String region, int NOMBRE_DE_CYLINDRES, String ENERGIE, String DESCRIPITION, float PRIX, float PUISSANCE_FISCALE, float CYLINDREE, User user) {
        this.TITRE = TITRE;
        this.BOITE = BOITE;
        this.region = region;
        this.NOMBRE_DE_CYLINDRES = NOMBRE_DE_CYLINDRES;
        this.ENERGIE = ENERGIE;
        this.DESCRIPITION = DESCRIPITION;
        this.PRIX = PRIX;
        this.PUISSANCE_FISCALE = PUISSANCE_FISCALE;
        this.CYLINDREE = CYLINDREE;
        this.user = user;
    }
    
    
    
    public Annonces(String TITRE, String BOITE, String region, int NOMBRE_DE_CYLINDRES, String ENERGIE, String DESCRIPITION, float PRIX, float PUISSANCE_FISCALE, float CYLINDREE, User user, String image) {
        this.TITRE = TITRE;
        this.BOITE = BOITE;
        this.region = region;
        this.NOMBRE_DE_CYLINDRES = NOMBRE_DE_CYLINDRES;
        this.ENERGIE = ENERGIE;
        this.DESCRIPITION = DESCRIPITION;
        this.PRIX = PRIX;
        this.PUISSANCE_FISCALE = PUISSANCE_FISCALE;
        this.CYLINDREE = CYLINDREE;
        this.user = user;
        this.image = image;
        
    }

    public Annonces(int id, String TITRE, String BOITE, String region, int NOMBRE_DE_CYLINDRES, String ENERGIE, String DESCRIPITION, float PRIX, float PUISSANCE_FISCALE, float CYLINDREE, User user, String image) {
        this.id = id;
        this.TITRE = TITRE;
        this.BOITE = BOITE;
        this.region = region;
        this.NOMBRE_DE_CYLINDRES = NOMBRE_DE_CYLINDRES;
        this.ENERGIE = ENERGIE;
        this.DESCRIPITION = DESCRIPITION;
        this.PRIX = PRIX;
        this.PUISSANCE_FISCALE = PUISSANCE_FISCALE;
        this.CYLINDREE = CYLINDREE;
        this.user = user;
        this.image = image;
        
    }

    public Annonces(int id, String TITRE, String BOITE, String region, int NOMBRE_DE_CYLINDRES, String ENERGIE, String DESCRIPITION, float PRIX, float PUISSANCE_FISCALE, float CYLINDREE, User user, String image, Date date) {
        this.id = id;
        this.TITRE = TITRE;
        this.BOITE = BOITE;
        this.region = region;
        this.NOMBRE_DE_CYLINDRES = NOMBRE_DE_CYLINDRES;
        this.ENERGIE = ENERGIE;
        this.DESCRIPITION = DESCRIPITION;
        this.PRIX = PRIX;
        this.PUISSANCE_FISCALE = PUISSANCE_FISCALE;
        this.CYLINDREE = CYLINDREE;
        this.user = user;
        this.image = image;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTITRE() {
        return TITRE;
    }

    public void setTITRE(String TITRE) {
        this.TITRE = TITRE;
    }

    public float getPUISSANCE_FISCALE() {
        return PUISSANCE_FISCALE;
    }

    public void setPUISSANCE_FISCALE(float PUISSANCE_FISCALE) {
        this.PUISSANCE_FISCALE = PUISSANCE_FISCALE;
    }

    public float getCYLINDREE() {
        return CYLINDREE;
    }

    public void setCYLINDREE(float CYLINDREE) {
        this.CYLINDREE = CYLINDREE;
    }

    public String getBOITE() {
        return BOITE;
    }

    public void setBOITE(String BOITE) {
        this.BOITE = BOITE;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNOMBRE_DE_CYLINDRES() {
        return NOMBRE_DE_CYLINDRES;
    }

    public void setNOMBRE_DE_CYLINDRES(int NOMBRE_DE_CYLINDRES) {
        this.NOMBRE_DE_CYLINDRES = NOMBRE_DE_CYLINDRES;
    }

    public String getENERGIE() {
        return ENERGIE;
    }

    public void setENERGIE(String ENERGIE) {
        this.ENERGIE = ENERGIE;
    }

    public String getDESCRIPITION() {
        return DESCRIPITION;
    }

    public void setDESCRIPITION(String DESCRIPITION) {
        this.DESCRIPITION = DESCRIPITION;
    }

    public int isValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    public float getPRIX() {
        return PRIX;
    }

    public void setPRIX(float PRIX) {
        this.PRIX = PRIX;
    }

    @Override
    public String toString() {
        return "Annonces{" + "id=" + id + ", TITRE=" + TITRE + ", BOITE=" + BOITE + ", region=" + region + ", NOMBRE_DE_CYLINDRES=" + NOMBRE_DE_CYLINDRES + ", ENERGIE=" + ENERGIE + ", DESCRIPITION=" + DESCRIPITION + ", validation=" + validation + ", PRIX=" + PRIX + ", PUISSANCE_FISCALE=" + PUISSANCE_FISCALE + ", CYLINDREE=" + CYLINDREE + ", user=" + user + ", image=" + image + ",Date"+date+'}';
    }

   




}
