/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offres;

import Annonces.services.Acceuil;
import OffresModeles.Offre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ELYES
 */
public class TouteLesOffres {

    Form list;
    EncodedImage encoded;
    Resources theme;

    public void TouteLesOffres() {
//        Form hi = new Form("Hi World");
//        SpanLabel sp = new SpanLabel();
//        hi.add(sp);
        try {
            //        Form hi = new Form("Hi World");
//        SpanLabel sp = new SpanLabel();
//        hi.add(sp);
            encoded = EncodedImage.create("/load.png");
        } catch (IOException ex) {
           
        }

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/Offres/select.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

//                System.out.println(getListOffre(new String(con.getResponseData())));
//                sp.setText(getListOffre(new String(con.getResponseData())) + "");
//                hi.refreshTheme();
                list = new Form("Toute les offres", BoxLayout.y());
               // list.getToolbar().setUIID("toutelesoffrestoolbar");
                //list.setUIID("toutelesoffres");

                for (Offre o : getListOffre(new String(con.getResponseData()))) {
                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    //                Container ctn3 = new Container(new FlowLayout());

                   // ctn1.setUIID("toutelesoffresctn1");

                    Label lblnomoffre = new Label(o.getNom_offre());
                    //lblnomoffre.setUIID("toutelesoffreslblnomoffre");
                    Label lblDescription = new Label(o.getDescription());
                    //lblDescription.setUIID("toutelesoffresdescription");
                    Label lbl = new Label("jlkj");

                    lblnomoffre.addPointerReleasedListener(e -> {
                        DetailsTouteLesOffres d = new DetailsTouteLesOffres();
                        d.DetailsTouteLesOffres(o);
                    });
//                    Image imgserver = URLImage.createToStorage(encoded, o.getNom_offre(), "http://127.0.0.1/pi/Offres/images/"+o.getPhoto());
//                    System.out.println(o.getPhoto());
//                    Label image = new Label();
//                    image.setIcon(imgserver);
//                    ctn1.add(image);
                 Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);
                    Label cadrephoto = new Label();
                    cadrephoto.setIcon(URLImage.createToStorage(placeholder, o.getPhoto(), "http://127.0.0.1/pi/Offres/images/" + o.getPhoto()));
                    ctn1.add(cadrephoto);

                      ctn1.setLeadComponent(lblnomoffre);
                    ctn2.add(lblnomoffre);
                    ctn2.add(lblDescription);
                    //        ctn3.add(lbl);
                    //      ctn1.add(ctn3);
                    ctn1.add(ctn2);
                    list.add(ctn1);
                }
                                list.getToolbar().addCommandToSideMenu("Acceuil", null, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        new Acceuil(theme).show();

                    }
                });

                list.getToolbar().addCommandToSideMenu("Mes offres", null, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        MesOffres mesoffres = new MesOffres();
                        mesoffres.MesOffres();

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
        ArrayList<Offre> listOffre = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> offres = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) offres.get("offre");

            for (Map<String, Object> obj : list) {
                Offre e = new Offre();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setNom_offre(obj.get("nom_offre").toString());
                e.setDescription(obj.get("description").toString());
                //     e.setPtvente(obj.get("ptvente").toString());
                e.setPrixinit(Float.parseFloat(obj.get("prixinit").toString()));
                e.setTaux_remise(Float.parseFloat(obj.get("taux_remise").toString()));
                e.setPhoto(obj.get("photo").toString());
                listOffre.add(e);

            }

        } catch (IOException ex) {
        }
        return listOffre;
    }

    public Form getList() {
        return list;
    }

}
