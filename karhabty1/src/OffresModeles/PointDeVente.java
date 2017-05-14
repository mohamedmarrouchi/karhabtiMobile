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
public class PointDeVente {
    private int id;
    private String reference;
    private String description;
    private String proprietaire;
    private String categorie;
    private String delegation;
    private String gouvernorat;
    private String nom;
    private String image;

    public PointDeVente() {
    }

    public PointDeVente(int id, String reference, String description, String proprietaire, String categorie, String delegation, String gouvernorat, String nom, String image) {
        this.id = id;
        this.reference = reference;
        this.description = description;
        this.proprietaire = proprietaire;
        this.categorie = categorie;
        this.delegation = delegation;
        this.gouvernorat = gouvernorat;
        this.nom = nom;
        this.image = image;
    }
    public PointDeVente( String reference, String description, String proprietaire, String categorie, String delegation, String gouvernorat, String nom, String image) {
        this.reference = reference;
        this.description = description;
        this.proprietaire = proprietaire;
        this.categorie = categorie;
        this.delegation = delegation;
        this.gouvernorat = gouvernorat;
        this.nom = nom;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PointDeVente{" + "id=" + id + ", reference=" + reference + ", description=" + description + ", proprietaire=" + proprietaire + ", categorie=" + categorie + ", delegation=" + delegation + ", gouvernorat=" + gouvernorat + ", nom=" + nom + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
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
        final PointDeVente other = (PointDeVente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
