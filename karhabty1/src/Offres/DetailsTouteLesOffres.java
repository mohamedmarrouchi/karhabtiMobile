/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offres;

import OffresModeles.Offre;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import static Offres.DetailsMesOffres.generateSessionKey;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ELYES
 */
public class DetailsTouteLesOffres {
    EncodedImage encoded;
    public void DetailsTouteLesOffres(Offre o){
        
        try {
            //        Form hi = new Form("Hi World");
//        SpanLabel sp = new SpanLabel();
//        hi.add(sp);
            encoded = EncodedImage.create("/load.png");
        } catch (IOException ex) {
           
        }
        
        
        
        Form f = new Form("Details de l'offre "+o.getNom_offre(), BoxLayout.y());
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

       // f.setUIID("DetailsTouteLesOffres");
       f.getToolbar();
        Label tfNomOffre = new Label();
        Label tfptvente= new Label();
        Label tfDescription = new Label();
        Label tfPrixInit = new Label();
        Label tfTaux_Remise = new Label();
        Label hhh = new Label();
        hhh.setText("hhhhhhhhhhhhhh");
        hhh.setVisible(false);
        ctn2.add(hhh);
        
        Label lnomoffre = new Label();
        //lnomoffre.setUIID("toutelesoffresnomoffre");
        Label ldescription = new Label();
        //ldescription.setUIID("toutelesoffresdescription");
        Label lprixinitial = new Label();
        //lprixinitial.setUIID("toutelesOffresprixinitial");
        Label ltaux = new Label();
       // ltaux.setUIID("toutelesoffrestaux");
        lnomoffre.setText("Nom de loffre:");
        ldescription.setText("Description:");
      //  lprixinitial.setText("Prix initial:");
        ltaux.setText("Taux de remise:");
        
        
        tfNomOffre.setText(o.getNom_offre());
 //       tfptvente.setText(o.getPtvente().getNom());
        tfDescription.setText(o.getDescription());
        tfPrixInit.setText(o.getPrixinit()+"");
        tfTaux_Remise.setText(o.getTaux_remise()+"");
        Image imgserver = URLImage.createToStorage(encoded, o.getNom_offre(), "http://127.0.0.1/pi/Offres/images/" + o.getPhoto());
        System.out.println(o.getPhoto());
        Label image = new Label();
        image.setIcon(imgserver);
       // image.setUIID("toutelesoffresimages");
        ctn1.add(ctn2);
        ctn1.add(ctn3);
        f.add(image);
        f.add(lnomoffre);
        f.add(tfNomOffre);
        f.add(tfptvente);
        f.add(ldescription);
        f.add(tfDescription);
        f.add(lprixinitial);
        f.add(tfPrixInit);
        f.add(ltaux);
        f.add(tfTaux_Remise);
        f.add(ctn1);
        
        f.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                TouteLesOffres t = new TouteLesOffres();
                t.TouteLesOffres();
            }
        });
        
        Button hh = new Button("partage");
        
       // hh.setUIID("DetailsTouteLesOffresShare");
        
        hh.addActionListener(new ActionListener() {
                    

            
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
        
        ctn3.add(hh);
        
        

        
        f.show();
    }
    
}
