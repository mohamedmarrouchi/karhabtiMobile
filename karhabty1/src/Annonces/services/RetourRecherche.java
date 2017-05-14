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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author root
 */
public class RetourRecherche {
        ArrayList<Annonces> listEtudiants = new ArrayList<>();
    ArrayList<Annonces> listEtudiant = new ArrayList<>();
       Form f1,list;
       public RetourRecherche(Resources theme_1,String titre,String region,String boite){

       
//        f1.getToolbar().setBackCommand("", e -> {
//
//            new ListAnnonces(theme_1).showBack();
//        });
        
      
            
                
              {

                    
                                    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/rechercher.php?&TITRE="+titre+"&REGION="+region+"&BOITE="+boite);
        con.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                System.out.println(getListEtudiant(new String(con.getResponseData())));
//                //sp.setText(getListEtudiant(new String(con.getResponseData())) + "");
//                //hi.refreshTheme();
//
 list = new Form("List", BoxLayout.y());
 list.setTitle("ListRechercher");
 


                   list.getToolbar().setBackCommand("", e -> {

            new Rechercher(theme_1).getF1().show();
        });


//            Ajout a = new Ajout(theme_1);
//            }else if(e.getCommand()==cmdback){
//                Acceuil a = new Acceuil(theme_1);
//                a.getF1().show();
   
for (Annonces a : getListEtudiant(new String(con.getResponseData()))){
                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label lblSurnom = new Label("TITRE : " + a.getTITRE());
        
        lblSurnom.addPointerReleasedListener(e->{
        
            //display detail
            DetailRechercher d = new DetailRechercher(a,theme_1,titre,region,boite);
            d.getF1().show();
                       // displayDetail(a);

        });
        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *2, p.getHeight() * 2), false);
  Label cadrephoto=new Label();
  cadrephoto.setIcon(URLImage.createToStorage(placeholder, a.getImage(), "http://127.0.0.1/pi/images/"+a.getImage()));      
        ctn1.add(cadrephoto);
        ctn1.setLeadComponent(lblSurnom);
        
        ctn2.add(lblSurnom);
        
        ctn2.add(new Label("REGION : " + a.getRegion()));
        ctn2.add(new Label("PRIX :"+a.getPRIX()));

        
        
        
        ctn1.add(ctn2);
        list.add(ctn1);
        }

        list.show();
            }
            

        });
        NetworkManager.getInstance().addToQueue(con);
        
    
              
           
       // f1.show();



       }
       
       }
       public int publicationNumber(Map<String, Object> data) {
        String v = null;
        int nb = 0;
        if (data.isEmpty()) {
            return 0;
        }
        else{
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value1 = entry.getValue();
            v = value1.toString();
        }
       
        StringTokenizer splitstring = new StringTokenizer(v, "}");
            while (splitstring.hasMoreElements()) {
                System.out.println(splitstring.nextToken());
                nb=nb+1;
            }
        //nb = splitstring.countTokens();
        return nb;

    }
    }
                
               public ArrayList<Annonces> getListEtudiant(String json) {
                   
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            int r = publicationNumber(etudiants);
            System.out.println("test="+r);
            if (r>1){
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("annonces");

            for (Map<String, Object> obj : list) {
                Annonces e = new Annonces();
                    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/selectUser.php?id="+obj.get("user"));
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
                 e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setRegion((obj.get("region").toString()));
                                e.setBOITE((obj.get("BOITE").toString()));
                e.setENERGIE((obj.get("ENERGIE").toString()));
                e.setNOMBRE_DE_CYLINDRES(Integer.parseInt(obj.get("NOMBRE_DE_CYLINDRES").toString()));
                e.setCYLINDREE(Float.parseFloat(obj.get("CYLINDREE").toString()));
                e.setPRIX(Float.parseFloat(obj.get("PRIX").toString()));
                e.setTITRE((obj.get("TITRE").toString()));
                e.setImage(obj.get("image").toString());
                System.out.println(e);
                listEtudiants.add(e);
           
            }
                  System.out.println("testl"+listEtudiants);
                return listEtudiants;
            }else if(r==1){
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
                listEtudiants.add(e);
                 System.out.println("test"+listEtudiants);
                 return listEtudiants;
            }else if (r==0){
                System.out.println("test");
                return listEtudiants;
            }
            

        } catch (IOException ex) {
         }
        System.out.println("test"+listEtudiants);
        return listEtudiants;
                  
    }
               
        public User getUser(String json) throws IOException{
     
          User e =null;
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

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }

    public Form getList() {
        return list;
    }

    public void setList(Form list) {
        this.list = list;
    }
    
}
