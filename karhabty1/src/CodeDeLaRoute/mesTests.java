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
public class mesTests {
    private int id;
    private int niveau;
    private String image;
    private String question;
    private int reponse;
    private String choix1;
    private String choix2;
    private String choix3;
    private String reponseforja;
    
    public mesTests() {
    }

    public mesTests(int id, int niveau, String image, String question, int reponse, String choix1, String choix2, String choix3, String reponseforja) {
        this.id = id;
        this.niveau = niveau;
        this.image = image;
        this.question = question;
        this.reponse = reponse;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.reponseforja = reponseforja;
    }

    public mesTests(String image, String question, int reponse, String choix1, String choix2, String choix3) {
        this.image = image;
        this.question = question;
        this.reponse = reponse;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
    }

    public mesTests(String image, String question, String choix1, String choix2, String choix3, String reponseforja) {
        this.image = image;
        this.question = question;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.reponseforja = reponseforja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getChoix1() {
        return choix1;
    }

    public void setChoix1(String choix1) {
        this.choix1 = choix1;
    }

    public String getChoix2() {
        return choix2;
    }

    public void setChoix2(String choix2) {
        this.choix2 = choix2;
    }

    public String getChoix3() {
        return choix3;
    }

    public void setChoix3(String choix3) {
        this.choix3 = choix3;
    }

    public String getReponseforja() {
        return reponseforja;
    }

    public void setReponseforja(String reponseforja) {
        this.reponseforja = reponseforja;
    }

    @Override
    public String toString() {
        return "mes_tests{" + "id=" + id + ", niveau=" + niveau + ", image=" + image + ", question=" + question + ", reponse=" + reponse + ", choix1=" + choix1 + ", choix2=" + choix2 + ", choix3=" + choix3 + ", reponseforja=" + reponseforja + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        return hash;
    }

    public boolean equals(int id) {
        if (this.id != id) {
            return false;
        }
        return true;
    }
    
}
