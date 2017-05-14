/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Annonces;
import Annonces.modeles.User;
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
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class MesAnnonces extends Form{

    ArrayList<Annonces> listEtudiants = new ArrayList<>();

    private Form list;

    public Form getList() {
        return list;
    }

    public void setList(Form list) {
        this.list = list;
    }

    public MesAnnonces(Resources theme_1) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/selectMesannonces.php?id=1");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                System.out.println(getListEtudiant(new String(con.getResponseData())));
//                //sp.setText(getListEtudiant(new String(con.getResponseData())) + "");
//                //hi.refreshTheme();
//
                //list = new Form("List", BoxLayout.y());

                getToolbar().setBackCommand("", e -> {

            new ListAnnonces(theme_1).showBack();
        });
            
                for (Annonces a : getListEtudiant(new String(con.getResponseData()))) {
                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    setTitle("Mes Annonces");
                            Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
  Label cadrephoto=new Label();
  cadrephoto.setIcon(URLImage.createToStorage(placeholder, a.getImage(), "http://127.0.0.1/pi/images/"+a.getImage()));      
        ctn1.add(cadrephoto);

                    Label lblSurnom = new Label("TITRE : " + a.getTITRE());

                    lblSurnom.addPointerReleasedListener(e -> {

                        //display detail
                        Modifier m = new Modifier(a, theme_1);
                        m.getModifier().show();
                        // displayDetail(a);

                    });
                    ctn1.setLeadComponent(lblSurnom);
                    ctn2.add(lblSurnom);

                    ctn2.add(new Label("REGION : " + a.getRegion()));
                    ctn2.add(new Label("PRIX :" + a.getPRIX()));

                    ctn1.add(ctn2);
                    add(ctn1);
                }
                
              
                getContentPane().animateLayout(200);
               
            }

        });
        getContentPane().animateLayout(200);
        NetworkManager.getInstance().addToQueue(con);

    }

    public ArrayList<Annonces> getListEtudiant(String json) {
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("annonces");

            for (Map<String, Object> obj : list) {
                Annonces e = new Annonces();
                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://127.0.0.1/pi/selectUser.php?id=" + obj.get("user"));
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        try {
                            e.setUser(getUser(new String(con.getResponseData())));
                        } catch (IOException ex) {
                        }
                    }

                });
                NetworkManager.getInstance().addToQueue(con);
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setRegion((obj.get("region").toString()));
                e.setBOITE((obj.get("BOITE").toString()));
                e.setENERGIE((obj.get("ENERGIE").toString()));
                e.setNOMBRE_DE_CYLINDRES(Integer.parseInt(obj.get("NOMBRE_DE_CYLINDRES").toString()));
                e.setCYLINDREE(Float.parseFloat(obj.get("CYLINDREE").toString()));
                e.setPRIX(Float.parseFloat(obj.get("PRIX").toString()));
                e.setTITRE((obj.get("TITRE").toString()));
                                e.setImage((obj.get("image").toString()));

                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }

    public User getUser(String json) throws IOException {

        User e = null;
        JSONParser j = new JSONParser();
        Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
        Map<String, Object> user = (Map<String, Object>) etudiants.get("PI_user");

        e = new User();

        e.setId(Integer.parseInt(user.get("id").toString()));
        e.setUsername((user.get("username").toString()));

        e.setEmail((user.get("email").toString()));
        System.out.println(e);
        ;

        return e;
    }
}
