/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

/**
 *
 * @author HANY
 */
public class Resultat {

    private int id;
    private int reponse_succes;
    private int reponse_echec;

    public Resultat() {
    }

    public Resultat(int id, int reponse_succes, int reponse_echec) {
        this.id = id;
        this.reponse_succes = reponse_succes;
        this.reponse_echec = reponse_echec;
    }

    public Resultat(int reponse_succes, int reponse_echec) {
        this.reponse_succes = reponse_succes;
        this.reponse_echec = reponse_echec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReponse_succes() {
        return reponse_succes;
    }

    public void setReponse_succes(int reponse_succes) {
        this.reponse_succes = reponse_succes;
    }

    public int getReponse_echec() {
        return reponse_echec;
    }

    public void setReponse_echec(int reponse_echec) {
        this.reponse_echec = reponse_echec;
    }

    @Override
    public String toString() {
        return "Resultat{" + "id=" + id + ", reponse_succes=" + reponse_succes + ", reponse_echec=" + reponse_echec + '}';
    }

}
