/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author HANY
 */
public class InsertTest {

    Form f1;
    String filePath;
    String fileName;
    TextField niveau;
    Label Lniveau;
    TextField question;
    Label Lquetsion;
    TextField choix1;
    Label Lchoix1;
    TextField choix2;
    Label Lchoix2;
    TextField choix3;
    Label Lchoix3;
    TextField reponse;
    Label Lreponse;

    public InsertTest(Resources theme) {
        UIBuilder uib = new UIBuilder();
        Container ctn1 = uib.createContainer(theme, "Ajouter");

        f1 = ctn1.getComponentForm();
        niveau = new TextField();
        Lniveau = new Label("Niveau");
        question = new TextField();
        Lquetsion = new Label("Question");
        choix1 = new TextField();
        Lchoix1 = new Label("Choix 1");
        choix2 = new TextField();
        Lchoix2 = new Label("Choix 2");
        choix3 = new TextField();
        Lchoix3 = new Label("Choix 3");
        reponse = new TextField();
        Lreponse = new Label("Reponse");
        Button b = new Button("choisir image");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
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

            }
        });
        f1.getToolbar().addCommandToLeftBar("Back",null,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               HomeForm h = new HomeForm(theme);
               
            }
        });
        f1.add(Lniveau);
        f1.add(niveau);
        f1.add(Lquetsion);
        f1.add(question);
        f1.add(Lchoix1);
        f1.add(choix1);
        f1.add(Lchoix2);
        f1.add(choix2);
        f1.add(Lchoix3);
        f1.add(choix3);
        f1.add(Lreponse);
        f1.add(reponse);
        f1.add(b);

        Button btnOk = new Button("Ajouter");

        f1.add(btnOk);
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                int fileNameIndex = filePath.lastIndexOf("/") + 1;
                fileName = filePath.substring(fileNameIndex);
                Image imgg;

                try {
                    imgg = Image.createImage(filePath);
                    ImageIO imgIO = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    imgIO.save(imgg, out, ImageIO.FORMAT_JPEG, 1);
                    byte[] ba = out.toByteArray();
                    fileName = Base64.encode(ba);

                } catch (IOException ex) {

                }

                ConnectionRequest req2 = new ConnectionRequest();
                req2.setHttpMethod("POST");
                req2.setUrl("http://127.0.0.1/pi/hani/insert.php");

                req2.addArgument("image", fileName);
                req2.addArgument("niveau", niveau.getText());
                req2.addArgument("question", question.getText());
                req2.addArgument("reponse", reponse.getText());
                req2.addArgument("choix1", choix1.getText());
                req2.addArgument("choix2", choix2.getText());
                req2.addArgument("choix3", choix3.getText());

                req2.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.charAt(s.length() - 1) == 'k') {
                            Dialog.show("Confirmation", "Test ajouté ", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req2);
            }
        });

        f1.show();
    }

    public Form getForm() {
        return f1;
    }
}
