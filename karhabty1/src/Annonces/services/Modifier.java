/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Annonces;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author root
 */
public class Modifier {

    private Form modifier;
         String filePath;
     String Imagecode;
     int badgeNumber = 0 ;

    public Form getModifier() {
        return modifier;
    }

    public void setModifier(Form modifier) {
        this.modifier = modifier;
    }

    public Modifier(Annonces annonce, Resources theme_1) {
        UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme_1, "Modifier");

        modifier = ctn1.getComponentForm();
        modifier.setTitle("Modifier");


 
        modifier.getToolbar().setBackCommand("", e -> {

            new MesAnnonces(theme_1).showBack();
        });
        modifier.getToolbar().addCommandToOverflowMenu("supprimer", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://127.0.0.1/pi/delete.php?id=" + annonce.getId() + "");

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
              
               new MesAnnonces(theme_1).showBack();
               
                NetworkManager.getInstance().addToQueue(req);
            }

        
            
        });

       

        Label lbltitre = (Label) uib.findByName("lbltitre", ctn1);
        TextField txttitre = (TextField) uib.findByName("txttitre", ctn1);
        Label lblregion = (Label) uib.findByName("lblregion", ctn1);
        TextField txtregion = (TextField) uib.findByName("txtregion", ctn1);

        Label lblboite = (Label) uib.findByName("lblboite", ctn1);
        TextField txtboite = (TextField) uib.findByName("txtboite", ctn1);

        Label lblenergie = (Label) uib.findByName("lblenergie", ctn1);
        TextField txtenergie = (TextField) uib.findByName("txtenergie", ctn1);

        Label lblnombredecylindres = (Label) uib.findByName("lblnombre_de_cylindres", ctn1);
        TextField txtnombre_de_cylindres = (TextField) uib.findByName("txtnombre_de_cylindres", ctn1);
        Label lblpuissance_fiscale = (Label) uib.findByName("lblpuissance_fiscale", ctn1);
        TextField txtpuissance_fiscale = (TextField) uib.findByName("txtpuissance_fiscale", ctn1);
        Label lblcylindree = (Label) uib.findByName("lblcylindree", ctn1);
        TextField txtcylindree = (TextField) uib.findByName("txtcylindree", ctn1);
        Label lblprix = (Label) uib.findByName("lblprix", ctn1);
        TextField txtprix = (TextField) uib.findByName("txtprix", ctn1);
        Label lbldescripition = (Label) uib.findByName("lbldescripition", ctn1);
        TextArea txtdescripition = (TextArea) uib.findByName("txtdescripition", ctn1);
        Button btnajout = (Button) uib.findByName("btnajout", ctn1);
                Button btnajoutphoto = (Button) uib.findByName("btnajoutphoto", ctn1);

        
        txttitre.setText(annonce.getTITRE());
        txtregion.setText(annonce.getRegion());
        txtenergie.setText(annonce.getENERGIE());
        txtboite.setText(annonce.getBOITE());

        txtcylindree.setText(annonce.getCYLINDREE() + "");

        txtnombre_de_cylindres.setText(annonce.getNOMBRE_DE_CYLINDRES() + "");
        txtenergie.setText(annonce.getENERGIE());

        txtprix.setText(annonce.getPRIX() + "");

        btnajout.setText("Modifier");
        System.out.println(annonce);
        btnajoutphoto.addActionListener(x->{





ActionListener callback = e->{
   if (e != null && e.getSource() != null) {
    filePath = (String)e.getSource();

       //  Now do something with this file
   }
};

if (FileChooser.isAvailable()) {
    FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
} else {
    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
}











});
                btnajout.addActionListener(new ActionListener() {
            
//System.out.println("data :"+Imagecode);

ImageIO imgIO = ImageIO.getImageIO();
ByteArrayOutputStream out = new ByteArrayOutputStream();
      
       
           
            @Override
            public void actionPerformed(ActionEvent evt) {
                     try {
                   imgIO.save(Image.createImage(filePath), out, ImageIO.FORMAT_JPEG, 1);
           
byte[] ba = out.toByteArray();
Imagecode = Base64.encode(ba);
//System.out.println("data :"+Imagecode);


 
       }catch (IOException ex) {
       }
                if(txttitre.getText().equals("lol")){
                    Dialog dlg = new Dialog("At Bottom");
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                  dlg.show("Error", "Error", "ok",null);
                }else{
                    ConnectionRequest request = new ConnectionRequest(){
     @Override
     protected void handleErrorResponseCode(int code, String message) {
           System.out.println("Code :"+code+" Msg :"+message);
     }

};
                
request.setPost(true);
request.setHttpMethod("POST");
request.addArgument("Image", Imagecode);
request.addArgument("id", annonce.getId()+"");  

request.addArgument("TITRE", txttitre.getText());  
request.addArgument("CYLINDREE", txtcylindree.getText());  

request.addArgument("NOMBRE_DE_CYLINDRES", txtnombre_de_cylindres.getText());  
request.addArgument("PRIX", txtprix.getText());  
request.addArgument("region", txtregion.getText());  
request.addArgument("BOITE", txtboite.getText());  
request.addArgument("ENERGIE", txtenergie.getText());  
request.addArgument("PUISSANCE_FISCALE", txtpuissance_fiscale.getText());  

request.setUrl("http://127.0.0.1/pi/modifier.php");
                    
NetworkManager.getInstance().addToQueueAndWait(request);  
LocalNotification n = new LocalNotification();
           
                n.setAlertBody("ajout effectuer avec succes");
                n.setAlertTitle("ajout");
                n.setId("1");
               n.setBadgeNumber(badgeNumber++);
               

              

                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() , LocalNotification.REPEAT_NONE);
    
        
                    }
    }
                
        });


        //insertStudent(new Student(txtNom.getText(), txtPrenom.getText(), txtCIN.getText()));
        modifier.getContentPane().animateLayout(200);
        modifier.show();

    }
}
