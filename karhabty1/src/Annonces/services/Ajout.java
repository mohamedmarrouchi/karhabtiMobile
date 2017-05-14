/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import com.codename1.components.InteractionDialog;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author root
 */
public class Ajout extends Form {

    String filePath;
    String Imagecode;
    Form f1;
    private final static String apikey = "AIzaSyDA0d8HRi2sBxQeb8TMCWbGEIMhMLlc5K0";
    int badgeNumber = 0;


    public Ajout(Resources theme_1) {
        final DefaultListModel<String> options = new DefaultListModel<>();


        Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //f1 = new Form("List", BoxLayout.y());
        setTitle("Ajouter");

        

        //TextField txttitre = (TextField) uib.findByName("txttitre", ctn1);
        Label lbltitre = new Label("TITRE");
        TextField txttitre = new TextField();
        Label lblregion = new Label("REGION");
        AutoCompleteTextField ac1 = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if (text.length() == 0) {
                    return false;
                }
                String[] l = searchLocations(text);
                if (l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for (String s : l) {
                    options.addItem(s);
                }
                return true;
            }
        };
        ac1.setMinimumElementsShownInPopup(5);
        Label lblboite = new Label("BOITE");
        TextField txtboite = new TextField();
        Label lblenergie = new Label("ENERGIE");
        TextField txtenergie = new TextField();
        Label lblnombre_de_cylindres = new Label("NOMBRE DE CYLINDRES");
        TextField txtnombre_de_cylindres = new TextField();
        Label lblpuissance_fiscale = new Label("PUISSANCE FISCALE");
        TextField txtpuissance_fiscale = new TextField();
        Label lblcylindree = new Label("CYLINDREE");

        TextField txtcylindree = new TextField();
        Label lblprix = new Label("PRIX");

        TextField txtprix = new TextField();
        Button btnajout = new Button("Ajouter");
        Label lblimage = new Label("Image");

        Button btnajoutphoto = new Button("Choisir image");
//        TextField txtboite = (TextField) uib.findByName("txtboite", ctn1);
//
//        TextField txtenergie = (TextField) uib.findByName("txtenergie", ctn1);
//
//        TextField txtnombre_de_cylindres = (TextField) uib.findByName("txtnombre_de_cylindres", ctn1);
//        TextField txtpuissance_fiscale = (TextField) uib.findByName("txtpuissance_fiscale", ctn1);
//        TextField txtcylindree = (TextField) uib.findByName("txtcylindree", ctn1);
//        TextField txtprix = (TextField) uib.findByName("txtprix", ctn1);
//        TextArea txtdescripition = (TextArea) uib.findByName("txtdescripition", ctn1);
//        Button btnajout = (Button) uib.findByName("btnajout", ctn1);
//        Button btnajoutphoto = (Button) uib.findByName("btnajoutphoto", ctn1);

        getToolbar().setBackCommand("", e -> {

            new ListAnnonces(theme_1).showBack();
        });
        ctn1.add(lbltitre);
        ctn1.add(txttitre);
        ctn1.add(lblregion);
        ctn1.add(ac1);
        ctn1.add(lblboite);
        ctn1.add(txtboite);
        ctn1.add(lblenergie);
        ctn1.add(txtenergie);
        ctn1.add(lblnombre_de_cylindres);
        ctn1.add(txtnombre_de_cylindres);
        ctn1.add(lblcylindree);
        ctn1.add(txtcylindree);
        ctn1.add(lblpuissance_fiscale);
        ctn1.add(txtpuissance_fiscale);
        ctn1.add(lblprix);
        ctn1.add(txtprix);
        ctn1.add(lblimage);
        ctn1.add(btnajoutphoto);
        ctn1.add(btnajout);
        

        btnajoutphoto.addActionListener(x -> {

            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filePath = (String) e.getSource();

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

                } catch (IOException ex) {
                }
              
                 for (int i = 0 ; i<txttitre.getText().length();i++){
               char c = txttitre.getText().charAt(i);
              if (c =='$'||c =='&'||c =='%'||c =='-'||c =='{'||c =='}'||c =='"'||c =='#'||c =='['||c ==']'||c =='('||c ==')'){
                        Dialog dlg = new Dialog("At Bottom");
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show("Error", "Supprimer les caractere speciale", "ok", null);
               }        else   if (i == txttitre.getText().length()-1){
                       if(txtenergie.getText().length()<2){
                     Dialog dlg = new Dialog("At Bottom");
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show("Error", "l'energie est vide", "ok", null);
                       }else if (txtprix.getText().equals("")||Float.parseFloat(txtprix.getText())<1000 ){
                     Dialog dlg = new Dialog("At Bottom");
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show("Error", "donner une prix réel", "ok", null);
           
                   }
                    else {
                    ConnectionRequest request = new ConnectionRequest() {
                        @Override
                        protected void handleErrorResponseCode(int code, String message) {
                            System.out.println("Code :" + code + " Msg :" + message);
                        }
                        

                    };

                    request.setPost(true);
                    request.setHttpMethod("POST");
                    request.addArgument("Image", Imagecode);

                    request.addArgument("TITRE", txttitre.getText());
                    request.addArgument("CYLINDREE", txtcylindree.getText());

                    request.addArgument("NOMBRE_DE_CYLINDRES", txtnombre_de_cylindres.getText());
                    request.addArgument("PRIX", txtprix.getText());
                    request.addArgument("region", ac1.getText());
                    request.addArgument("BOITE", txtboite.getText());
                    request.addArgument("ENERGIE", txtenergie.getText());
                    request.addArgument("PUISSANCE_FISCALE", txtpuissance_fiscale.getText());

                    request.setUrl("http://127.0.0.1/pi/insert.php");

                    NetworkManager.getInstance().addToQueueAndWait(request);
                    new ListAnnonces(theme_1).showBack();
                       }}
                    LocalNotification n = new LocalNotification();

                    n.setAlertBody("ajout effectuer avec succes");
                    n.setAlertTitle("ajout");
                    n.setId("1");
                    n.setBadgeNumber(badgeNumber++);

                    Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis(), LocalNotification.REPEAT_NONE);
new ListAnnonces(theme_1).show();
                }
            }
        });
        add(ctn1);
        getContentPane().animateLayout(200);
    }

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }

    String[] searchLocations(String text) {
        try {
            if (text.length() > 0) {
                ConnectionRequest r = new ConnectionRequest();
                r.setPost(false);
                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
                r.addArgument("key", apikey);
                r.addArgument("input", text);
                NetworkManager.getInstance().addToQueueAndWait(r);
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;
            }
        } catch (Exception err) {
            Log.e(err);
        }

        return null;
    }
}
