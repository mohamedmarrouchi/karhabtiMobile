/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.modeles;

/**
 *
 * @author root
 */
public class Favoris {
    
    private int id;
    private int id_annonce ;
    private String Titre;
    private String note;

    public Favoris() {
    }

    public Favoris(int id, int id_annonce, String Titre, String note) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.Titre = Titre;
        this.note = note;
    }

    public Favoris(int id_annonce, String Titre, String note) {
        this.id_annonce = id_annonce;
        this.Titre = Titre;
        this.note = note;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Favoris{" + "id=" + id + ", id_annonce=" + id_annonce + ", Titre=" + Titre + ", note=" + note + '}';
    }
    
    
    
    
    
    
    
}
