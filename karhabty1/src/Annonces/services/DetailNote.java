/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Annonces;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author root
 */
public class DetailNote extends Form{
        private Form f1;

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }
    EncodedImage encoded;
            float fontsize = 2;

    public float getFontsize() {
        return fontsize;
    }

    public void setFontsize(float fontsize) {
        this.fontsize = fontsize;
    }

    public DetailNote(Annonces annonce, Resources theme_1) {
   setTitle("Détail");
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
         Label lblusername = new Label("username: "+annonce.getUser().getUsername());
         Label lblemail = new Label("email: "+annonce.getUser().getEmail());
          Label lbldetails = new Label("Details");
                    Label lblcontact = new Label("Contact");
    
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
         ctn4.add(lblcontact);
         ctn4.add(lblusername);
         ctn4.add(lblemail);
         
         ctn2.add(ctn3);
         ctn2.add(ctn4);
         
ctn.add(ctn1);
ctn.add(ctn2);
add(ctn);
getContentPane().animateLayout(200);
show();
    }
}
