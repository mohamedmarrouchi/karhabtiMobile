/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offres;

import OffresModeles.Offre;
import com.codename1.components.ShareButton;
import com.codename1.facebook.FBObject;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.share.FacebookShare;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 * @author ELYES
 */
public class DetailsMesOffres {
      EncodedImage encoded;
    public void DetailsMesOffres(Offre o) {
        
        try {
            //        Form hi = new Form("Hi World");
//        SpanLabel sp = new SpanLabel();
//        hi.add(sp);
            encoded = EncodedImage.create("/load.png");
        } catch (IOException ex) {
           
        }
        
        
        
        Form f = new Form("Details de l'offre " + o.getNom_offre(), BoxLayout.y());
        
        //f.setUIID("DetailsMesOffres");
        Container ctn = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     //   f.getToolbar().setUIID("DetailsMesOffresToolbar");
        
        Label tfNomOffre = new Label();
        TextField tffNomOffre = new TextField();
        TextField tffDescription = new TextField();
        // Label tfptvente= new Label();
        Label tfDescription = new Label();
        TextField tffPrixInit = new TextField();
        Label tfPrixInit = new Label();
        TextField tffTaux_Remise = new TextField();
        Label tfTaux_Remise = new Label();
        
        Image imgserver = URLImage.createToStorage(encoded, o.getNom_offre(), "http://127.0.0.1/pi/Offres/images/" + o.getPhoto());
        System.out.println(o.getPhoto());
        Label image = new Label();
        image.setIcon(imgserver);
        
       
        Label lol = new Label();
        lol.setText("hhhhhhhhhhhhhhhh");
      //  lol.setUIID("lol");
        
        lol.setVisible(false);
        
        ctn2.add(lol);
        
        
        tfNomOffre.setText("Nom de l'offre");
        //tfptvente.setText(o.getPtvente().getNom());
        tfDescription.setText("Description");
        tfPrixInit.setText("prix initial");
        tfTaux_Remise.setText("taux de remise");
        
        tffNomOffre.setText(o.getNom_offre());
        tffDescription.setText(o.getDescription());
        tffPrixInit.setText(o.getPrixinit() + "");
        tffTaux_Remise.setText(o.getTaux_remise() + "");
        Button modifier = new Button("Modifier");
        
        //modifier.setUIID("btnModifier");
        
        //tfNomOffre.setUIID("tfNomOffre");
        //System.out.println(tfNomOffre.getUIID());
        
        ShareButton sb = new ShareButton();
        sb.setText("partager offre");
        ctn1.add(modifier);
        ctn.add(ctn2);
        ctn.add(ctn1);
        ctn.add(ctn3);
        f.add(image);
        f.add(tfNomOffre);
        f.add(tffNomOffre);
        //     f.add(tfptvente);
        f.add(tfDescription);
        f.add(tffDescription);
        f.add(tfPrixInit);
        f.add(tffPrixInit);
        f.add(tfTaux_Remise);
        f.add(tffTaux_Remise);        
        f.add(sb);
        
        f.add(ctn);
        
        Button mesoffrespartagesfb = new Button("Partager");
   
       
        //mesoffrespartagesfb.setUIID("mesoffrespartagesfb");
        
        mesoffrespartagesfb.addActionListener(new ActionListener() {
                    

            
            @Override
            public void actionPerformed(ActionEvent evt) {


                
                        if (evt == null){
                            return;
                        }
                        String filename =  evt.getSource()+"";
                        if (Dialog.show("Partage", "partager cette offre ?", "OK", "cancel")) {
                            MultipartRequest req = new MultipartRequest();
                            String endpoint;

                            endpoint = "https://graph.facebook.com/me/photos?access_token=EAACEdEose0cBABNjijAKnWpqp3e5k05yLzvP0HVGnboEkrkZAaYZCuzKARFZAO2ZARTlrbeZANlMLB4u75ouZCc9O2Kph9rYI8OhTPav4JyD2coHECqJZCFRlBqESzLY14HfMaZBlg5OnFxsQQkrdQwjX4CwQOWZAViOrpRLYDoZA8a9ZCsdrGFieAi6D5JfB4fzD4ZD";

                            req.setUrl(endpoint);
                            req.addArgument("tes", "ok");
                            String is = generateSessionKey(10);
                           String i = generateSessionKey(10);
                            try {
                                Storage.getInstance().deleteStorageFile("screenshot.png");
                                Image screenshot = Image.createImage(f.getWidth(), f.getHeight());
                                
                                f.revalidate();
                                f.setVisible(true);
                                f.paintComponent(screenshot.getGraphics(), true);
                                
                                String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
                                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                                } catch (IOException err) {
                                    Log.e(err);
                                }
                                req.addData("source", imageFile, "image/png");
                                System.out.println(imageFile);
                                
                             
                                // FileSystemStorage.getInstance().rename(FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png", is);
                                NetworkManager.getInstance().addToQueue(req);

                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                            
                           
                        }
                   
            }
        });
        
       
        
//        Image screenshot = Image.createImage(f.getWidth(), f.getHeight());
//        f.revalidate();
//        f.setVisible(true);
//        f.paintComponent(screenshot.getGraphics(), true);
//        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
//        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
//            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
//        } catch (IOException err) {
//            Log.e(err);
//        }
//        System.out.println(imageFile);
//        sb.setImageToShare(imageFile, "image/jpg");       
        
        ctn3.add(mesoffrespartagesfb);
        modifier.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://127.0.0.1/pi/Offres/update.php?nom_offre=" + tffNomOffre.getText() + "&description=" + tffDescription.getText() + "&prixinit=" + tffPrixInit.getText() + "&taux_remise=" + tffTaux_Remise.getText() + "&id=" + o.getId() + "");
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {
                    
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "modif ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                        MesOffres m = new MesOffres();
                        m.MesOffres();
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
            }
        });
        
        f.getToolbar().addCommandToOverflowMenu("supprimer", null, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://127.0.0.1/pi/Offres/delete.php?id=" + o.getId() + "");
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {
                    
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "suppression ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                        MesOffres m = new MesOffres();
                        m.MesOffres();
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
            }
        });
        f.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                MesOffres m = new MesOffres();
                m.MesOffres();
            }
        });        
        f.show();
    }
    public static String generateSessionKey(int length){
String alphabet = 
        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
int n = alphabet.length(); //10

String result = new String(); 
Random r = new Random(); //11

for (int i=0; i<length; i++) //12
    result = result + alphabet.charAt(r.nextInt(n)); //13

return result;
}
    
}
