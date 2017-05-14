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
public class Offre {
    private int id;
    private String nom_offre;
    private String description;
    private String photo;
    private PointDeVente ptvente;
    private int etat;
    private Pi_User proprietaire;
    private float prixinit;
    private float taux_remise;
    

    public Offre() {
    }

    public Offre(int id, String nom_offre, String description, String photo, PointDeVente ptvente, int etat, Pi_User proprietaire, float prixinit, float taux_remise) {
        this.id = id;
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.ptvente = ptvente;
        this.etat = etat;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
    }
     public Offre( String nom_offre, String description, String photo, PointDeVente ptvente, int etat, Pi_User proprietaire, float prixinit, float taux_remise) {
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.ptvente = ptvente;
        this.etat = etat;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
    }
    
         public Offre( String nom_offre, String description,  PointDeVente ptvente, int etat, Pi_User proprietaire, float prixinit, float taux_remise) {
        this.nom_offre = nom_offre;
        this.description = description;
        this.ptvente = ptvente;
        this.etat = etat;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
    }

    public Offre(int id, String nom_offre, String description, String photo, int etat, Pi_User proprietaire, float prixinit, float taux_remise) {
        this.id = id;
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
        
    }

    public Offre(int id, String nom_offre, String description, String photo, int etat, float prixinit, float taux_remise) {
        this.id = id;
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
    }
    
    
    
    public Offre(String nom_offre, String description, String photo, int etat, Pi_User proprietaire, float prixinit, float taux_remise) {
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
        
    }
    public Offre(String nom_offre, String description, String photo, Pi_User proprietaire, float prixinit, float taux_remise) {
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
        
    }
    public Offre(int id,String nom_offre, String description, String photo, Pi_User proprietaire, float prixinit, float taux_remise) {
       this.id=id;
        this.nom_offre = nom_offre;
        this.description = description;
        this.photo = photo;
        this.proprietaire = proprietaire;
        this.prixinit = prixinit;
        this.taux_remise = taux_remise;
        
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public float getPrixinit() {
        return prixinit;
    }

    public Pi_User getProprietaire() {
        return proprietaire;
    }

    public float getTaux_remise() {
        return taux_remise;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public void setPrixinit(float prixinit) {
        this.prixinit = prixinit;
    }

    public void setProprietaire(Pi_User proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setTaux_remise(float taux_remise) {
        this.taux_remise = taux_remise;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

 


    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public PointDeVente getPtvente() {
        return ptvente;
    }

    public void setPtvente(PointDeVente ptvente) {
        this.ptvente = ptvente;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final Offre other = (Offre) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "offre{" + "id=" + id + ", nom_offre=" + nom_offre + ", description=" + description + ", prixinit=" + prixinit + ", taux_remise=" + taux_remise + ", proprietaire=" + proprietaire + '}';
    }

    
    
       
    
}
