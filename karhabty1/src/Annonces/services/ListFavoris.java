/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Favoris;
import com.codename1.components.FloatingActionButton;
import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import static com.mycompany.myapp.MyApplication.db;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class ListFavoris extends Form{
    
       private Form current, list, add;
    private Resources theme;


    ArrayList<Favoris> favoris = new ArrayList<>();
    public ListFavoris(Resources theme){

       getToolbar().setBackCommand("", e -> {

            new ListAnnonces(theme).showBack();
        });
    
                  
        try {
            Cursor c = db.executeQuery("select * from note");

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
            setTitle("Mes Notes");
            Label lblName = new Label("TITRE : " + s.getTitre() );
            Label lblCin = new Label("DESCRIPITION : " + s.getNote());
            
            lblName.addPointerReleasedListener(e->{
            
                edit(s);
                
            });
            
            ctn1.setLeadComponent(lblName);

            ctn1.add(lblName);
            ctn1.add(lblCin);

            add(ctn1);

        }
        
    }
    public void edit(Favoris s){
        
                            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
list = new Form("Edit", BoxLayout.y());
            setTitle("Note");
        Label lbltitre = new Label("Titre");
        TextField txttitre= new TextField();
        Label lbldescripition = new Label("descripition");
        TextField txtdescripition= new TextField();
                
       
        
        getToolbar().addCommandToOverflowMenu("supprimer", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                      deleteStudent(s);
                                }
                            });
            getToolbar().addCommandToOverflowMenu("show", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println("hhhhhhhh"+s.getId_annonce());
 new GetNoteA(theme,s.getId_annonce()).show();                                }
                            });


        Button btnOk = new Button("Modifier");
        txttitre.setText(s.getTitre());
        txtdescripition.setText(s.getNote());
      
        ctn2.add(lbltitre);
        ctn2.add(txttitre);
        ctn2.add(lbldescripition);
        ctn2.add(txtdescripition);
        ctn2.add(btnOk);
        
        btnOk.addActionListener(e->{
                   
            s.setTitre(txttitre.getText());
            s.setNote(txtdescripition.getText());
            s.setId_annonce(s.getId_annonce());
            
            
            updateStudent(s);
           
            
        });
        add(ctn2);
        getContentPane().animateLayout(200);
            show();
    }
        public void updateStudent(Favoris s) {
        try {
            db.execute("update note set titre='"+s.getTitre()+"', descripition='"+s.getNote()+"', id_annonce='"+s.getId_annonce()+"' where id='"+s.getId()+"'");
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        favoris.clear();
        new ListFavoris(theme).show();
    }
            public void deleteStudent(Favoris s) {
        try {
            db.execute("delete from note where id = '"+s.getId()+"'");
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        favoris.clear();
new ListFavoris(theme).show();    
            }
}
