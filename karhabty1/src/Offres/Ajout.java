/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offres;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
//import com.twilio.Twilio;
//import com.twilio.type.PhoneNumber;
//import com.twilio.rest.api.v2010.account.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author ELYES
 */
public class Ajout {

    String Imagecode;
    String filePath;

    public void Ajout() {

        Form f = new Form("Ajouter une offre", BoxLayout.y());
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        TextField tfNomOffre = new TextField("", "       nom de l'offre");
        TextField tfDescription = new TextField("", "       Description");
        TextField tfPrixInit = new TextField("", "       Prix initial");
        TextField tfTaux_Remise = new TextField("", "       Taux de remise");

      //  f.setUIID("ajout");
       // f.getToolbar().setUIID("ajoutToolbar");

        f.add(tfNomOffre);
        f.add(tfDescription);
        f.add(tfPrixInit);
        f.add(tfTaux_Remise);

        Button btnOk = new Button("Ajouter");
        Button ajouterimage = new Button("ajouter image");
        //btnOk.setUIID("btnAjouterOffre");
        
        ctn1.add(ctn2);
        ctn1.add(ctn3);
        ctn1.add(ctn4);
        
        
        Label notvisible = new Label();
        notvisible.setText("hhhhhhhhhhhhh");
        notvisible.setVisible(false);
        ctn2.add(notvisible);
        
        
        
        ctn3.add(ajouterimage);
        //ajouterimage.setUIID("ajoutimageajout");
        ctn4.add(btnOk);
        f.add(ctn1);
        ajouterimage.addActionListener(x -> {
            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filePath = (String) e.getSource();

                }
            };
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);

            } else {
                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }

        });

        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ImageIO imgIO = ImageIO.getImageIO();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    imgIO.save(Image.createImage(filePath), out, ImageIO.FORMAT_JPEG, 1);

                    byte[] ba = out.toByteArray();
                    Imagecode = Base64.encode(ba);
//System.out.println("data :"+Imagecode);

                } catch (IOException ex) {

                }
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://127.0.0.1/pi/Offres/insert.php?");
                req.setHttpMethod("POST");
                req.addArgument("Image", Imagecode);
                req.addArgument("nom_offre", tfNomOffre.getText());
                req.addArgument("description", tfDescription.getText());
                req.addArgument("prixinit", tfPrixInit.getText());
                req.addArgument("taux_remise", tfTaux_Remise.getText());
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);

//                        String accountSid = "ACb2ab61a33f24801f2c51c3b0530ab603";
//                        String authToken = "b60d6651123c2c4d6474c6fc91b2a156";
//                        Twilio.init(accountSid, authToken);
//                        Message message = Message.creator(
//                                new PhoneNumber("+21653602897"),
//                                new PhoneNumber("+12566661890"),
//                                "Votre offre a ete ajouter avec succès"
//                        ).create();
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });

        f.getToolbar().addCommandToSideMenu("Toute les offres", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                TouteLesOffres tteoffres = new TouteLesOffres();
                tteoffres.TouteLesOffres();
            }
        });

        f.getToolbar().addCommandToSideMenu("Mes offres", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                MesOffres mesoffres = new MesOffres();
                mesoffres.MesOffres();

            }
        });

        f.show();

    }

}
