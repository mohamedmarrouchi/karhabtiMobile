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
public class GetNoteA extends Form {
    Form f;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    ArrayList<Annonces> annonces = new ArrayList<Annonces>();
    public GetNoteA (Resources theme_1, int id){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/Note.php?id="+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                System.out.println(getListEtudiant(new String(con.getResponseData())));
//                //sp.setText(getListEtudiant(new String(con.getResponseData())) + "");
//                //hi.refreshTheme();
//
               //f = new Form("List", BoxLayout.y());
             
                
              

   setTitle("Détail");
      getToolbar().setBackCommand("", e -> {

            new ListFavoris(theme_1).showBack();
        });
   for (Annonces annonce : getListEtudiant(new String(con.getResponseData()))){
    Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container ctn2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
    Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container ctn4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
        Label lbltitre = new Label("TITRE: "+annonce.getTITRE());
        Label lblregion = new Label("region : "+annonce.getRegion());

        Label lblboite = new Label("boite: "+annonce.getBOITE());

        Label lblenergie = new Label("energie: "+annonce.getENERGIE());

        Label lblnombredecylindres = new Label("nombre de cylindres: "+annonce.getNOMBRE_DE_CYLINDRES());
        Label lblpuissance_fiscale = new Label("puissance fiscale: "+annonce.getPUISSANCE_FISCALE());;
        Label lblcylindree =new Label("cylindree: "+annonce.getCYLINDREE());;
        Label lblprix = new Label("prix: "+annonce.getPRIX());
//       Label lblusername = new Label("username: "+annonce.getUser().getUsername());
//        Label lblemail = new Label("email: "+annonce.getUser().getEmail());
          Label lbldetails = new Label("Details");
//                    Label lblcontact = new Label("Contact");
    
                            Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
  Label cadrephoto=new Label();
  cadrephoto.setIcon(URLImage.createToStorage(placeholder, annonce.getImage(), "http://127.0.0.1/pi/images/"+annonce.getImage())); 
  ctn1.add(cadrephoto);
  ctn3.add(lbldetails);

         ctn3.add(lbltitre);
         ctn3.add(lblregion);
         ctn3.add(lblboite);
         ctn3.add(lblenergie);
         ctn3.add(lblnombredecylindres);
         ctn3.add(lblpuissance_fiscale);
         ctn3.add(lblcylindree);
         ctn3.add(lblprix);
//         ctn4.add(lblcontact);
//         ctn4.add(lblusername);
//         ctn4.add(lblemail);
         
         ctn2.add(ctn3);
         ctn2.add(ctn4);
         
ctn.add(ctn1);
ctn.add(ctn2);
add(ctn);
getContentPane().animateLayout(200);
show();
   }
 
            }
            
//                for (Annonces a : getListEtudiant(new String(con.getResponseData()))) {
//                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//                    setTitle("Mes Annonces");
//                            Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
//    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
//    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
//  Label cadrephoto=new Label();
//  cadrephoto.setIcon(URLImage.createToStorage(placeholder, a.getImage(), "http://127.0.0.1/pi/images/"+a.getImage()));      
//        ctn1.add(cadrephoto);
//
//                    Label lblSurnom = new Label("TITRE : " + a.getTITRE());
//
//                    lblSurnom.addPointerReleasedListener(e -> {
//
//                        //display detail
//                        Modifier m = new Modifier(a, theme_1);
//                        m.getModifier().show();
//                        // displayDetail(a);
//
//                    });
//                    ctn1.setLeadComponent(lblSurnom);
//                    ctn2.add(lblSurnom);
//
//                    ctn2.add(new Label("REGION : " + a.getRegion()));
//                    ctn2.add(new Label("PRIX :" + a.getPRIX()));
//
//                    ctn1.add(ctn2);
//                    add(ctn1);
//                }
//                
//                
//                getContentPane().animateLayout(200);
//               
//            }
//
//        });
//        getContentPane().animateLayout(200);
//        NetworkManager.getInstance().addToQueue(con);
          
         

    
        });
         NetworkManager.getInstance().addToQueue(con);
       

                }

    public ArrayList<Annonces> getListEtudiant(String json) {
        Annonces annonc = null;
        System.out.println(json);
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
Map<String, Object> list = (Map<String, Object>) etudiants.get("annonces");

           
                Annonces e = new Annonces();
                    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/selectUser.php?id="+list.get("user"));
        con.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    e.setUser(getUser(new String(con.getResponseData())));
                } catch (IOException ex) {
                }
            } 
       
        });
         NetworkManager.getInstance().addToQueue(con);
                 e.setId(Integer.parseInt(list.get("id").toString()));
                e.setRegion((list.get("region").toString()));
                                e.setBOITE((list.get("BOITE").toString()));
                e.setENERGIE((list.get("ENERGIE").toString()));
                e.setNOMBRE_DE_CYLINDRES(Integer.parseInt(list.get("NOMBRE_DE_CYLINDRES").toString()));
                e.setCYLINDREE(Float.parseFloat(list.get("CYLINDREE").toString()));
                e.setPRIX(Float.parseFloat(list.get("PRIX").toString()));
                e.setTITRE((list.get("TITRE").toString()));
                e.setImage(list.get("image").toString());
                System.out.println(e);
                annonces.add(e);
                 System.out.println("test"+annonces);
                
            
        } catch (IOException ex) {
            //Logger.getLogger(GetNoteA.class.getName()).log(Level.SEVERE, null, ex);
        }
       return annonces;
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
