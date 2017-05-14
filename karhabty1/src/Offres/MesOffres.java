/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offres;

import OffresModeles.Offre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ELYES
 */
public class MesOffres {

    Form list;
    EncodedImage encoded;
    public void MesOffres() {
        try {
            //        Form hi = new Form("Hi World");
//        SpanLabel sp = new SpanLabel();
//        hi.add(sp);
            encoded = EncodedImage.create("/load.png");
        } catch (IOException ex) {
           
        }
        
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/Offres/selectmesoffres.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {

//                System.out.println(getListOffre(new String(con.getResponseData())));
//                sp.setText(getListOffre(new String(con.getResponseData())) + "");
//                hi.refreshTheme();
                list = new Form("Mes Offres", BoxLayout.y());
//                list.setUIID("mesoffres");
//                list.getToolbar().setUIID("mesoffresToolbar");
                
                for (Offre o : getListOffre(new String(con.getResponseData()))) {
                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//                    ctn1.setUIID("mesoffresctn1");
                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    
                    Label lblnomoffre = new Label("Nom Offre: " + o.getNom_offre());
//                    lblnomoffre.setUIID("mesoffreslblnomoffre");
                    
                    Label lblDescription = new Label("Description: " + o.getDescription());
//                    lblDescription.setUIID("mesoffreslbldescription");
                    
                    lblnomoffre.addPointerReleasedListener(e -> {
                        DetailsMesOffres d = new DetailsMesOffres();
                        d.DetailsMesOffres(o);
                    });
                   Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);
                    Label cadrephoto = new Label();
                    cadrephoto.setIcon(URLImage.createToStorage(placeholder, o.getPhoto(), "http://127.0.0.1/pi/Offres/images/" + o.getPhoto()));
                    ctn1.add(cadrephoto);
                   
                    ctn1.setLeadComponent(lblnomoffre);
                    ctn2.add(lblnomoffre);
                    ctn2.add(lblDescription);
                    ctn1.add(ctn2);
                    list.add(ctn1);
                    
                }
                list.getToolbar().addCommandToSideMenu("Toute les offres", null, new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        TouteLesOffres tteoffres = new TouteLesOffres();
                        tteoffres.TouteLesOffres();
                    }
                });
                list.getToolbar().addCommandToSideMenu("Ajouter une offre", null, new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Ajout ajout = new Ajout();
                        ajout.Ajout();
                        
                    }
                });
                
                list.show();
                
            }
        });
        NetworkManager.getInstance().addToQueue(con);

//        hi.show();
    }
    
    public ArrayList<Offre> getListOffre(String json) {
        ArrayList<Offre> listEtudiants = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> offres = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) offres.get("offre");
            
            for (Map<String, Object> obj : list) {
                Offre e = new Offre();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setNom_offre(obj.get("nom_offre").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrixinit(Float.parseFloat(obj.get("prixinit").toString()));
                e.setTaux_remise(Float.parseFloat(obj.get("taux_remise").toString()));
                e.setPhoto(obj.get("photo").toString());
                listEtudiants.add(e);
                
            }
            
        } catch (IOException ex) {
        }
        return listEtudiants;
    }
    
    public Form getList() {
        return list;
    }
    
}
