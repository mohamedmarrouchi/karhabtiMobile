/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Annonces;
import Annonces.modeles.Favoris;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import static com.mycompany.myapp.MyApplication.db;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class EditeFavoris extends Form{
        private Form current, list, add;
    private Resources theme;


    ArrayList<Favoris> favoris = new ArrayList<>();
    
     
    
    

        

        
        
        
            
        public EditeFavoris(Resources theme,int id_annonce ){

                            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
list = new Form("List", BoxLayout.y());
            setTitle("Note");
        Label lbltitre = new Label("Titre");
        TextField txttitre= new TextField();
        Label lbldescripition = new Label("descripition");
        TextField txtdescripition= new TextField();
        
        Button btnOk = new Button("Ajouter");
        ctn2.add(lbltitre);
        ctn2.add(txttitre);
        ctn2.add(lbldescripition);
        ctn2.add(txtdescripition);
        ctn2.add(btnOk);
        
        btnOk.addActionListener(e->{
                    System.out.println(id_annonce+txttitre.getText()+ txtdescripition.getText());

            insertStudent(new Favoris(id_annonce,txttitre.getText(), txtdescripition.getText()));
            
        });
        add(ctn2);
        getContentPane().animateLayout(200);
            show();
        }
        public void addnote(){
            
        }
            public void insertStudent(Favoris f) {
        try {
            System.out.println("test"+f);
            db.execute("insert into note (titre, descripition, id_annonce) values('" + f.getTitre() + "','" + f.getNote() + "','" + f.getId_annonce() + "')");
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
                public void selectStudents() {
        try {
            Cursor c = db.executeQuery("select * from student");

            while (c.next()) {
                Row r = c.getRow();

                Favoris s = new Favoris();

                s.setId(r.getInteger(0));
                s.setTitre(r.getString(1));
                s.setNote(r.getString(2));
                s.setId_annonce(r.getInteger(3));

                favoris.add(s);

            }
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

        displayStudents();
    }
    public void displayStudents() {
        list = new Form("Etudiants", BoxLayout.y());

        


        for (Favoris s : favoris) {

            Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label lblName = new Label("Nom : " + s.getTitre() );
            Label lblCin = new Label("CIN : " + s.getNote());
            
            lblName.addPointerReleasedListener(e->{
            
                //editStudent(s);
                
            });
            
            ctn1.setLeadComponent(lblName);

            ctn1.add(lblName);
            ctn1.add(lblCin);

            list.add(ctn1);

        }
        list.show();
    }
}
       
    
    
