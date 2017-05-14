/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HANY
 */
public class ModifierTest {

    Form modifier;
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

    public ModifierTest(Resources theme, mesTests test) {
        Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        modifier = new Form("", BoxLayout.y());

        Command cmdDelete = new Command("Supprimer");

        modifier.getToolbar().addCommandToRightBar(cmdDelete);

        modifier.addCommandListener(e -> {

             deleteTest(test);
        });
        niveau = new TextField(Integer.toString(test.getNiveau()));
        Lniveau = new Label("Niveau");
        question = new TextField(test.getQuestion());
        Lquetsion = new Label("Question");
        choix1 = new TextField(test.getChoix1());
        Lchoix1 = new Label("Choix 1");
        choix2 = new TextField(test.getChoix2());
        Lchoix2 = new Label("Choix 2");
        choix3 = new TextField(test.getChoix3());
        Lchoix3 = new Label("Choix 3");
        reponse = new TextField(Integer.toString(test.getReponse()));
        Lreponse = new Label("Reponse");
        Button c = new Button("Annuler");
        c.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                GererTests g = new GererTests(theme);
                g.getForm().show();
            }
        });
       
        Button btnOk = new Button("Enregistrer");
        
         btnOk.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(ActionEvent evt) {

                   
                ConnectionRequest req = new ConnectionRequest();     
                req.setHttpMethod("POST");
               req.setUrl("http://127.0.0.1/pi/hani/update.php?id=" + test.getId() + "&niveau="+ niveau.getText()+  "&question="+ question.getText()+  "&choix1="+ choix1.getText() +"&choix2="+ choix2.getText()+"&choix3="+ choix3.getText() +"&image="+ test.getImage() +  "&reponse="+reponse.getText()+"");
                
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.charAt(s.length()-1)=='k') {
                            Dialog.show("Confirmation", "Modification ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });
        modifier.add(Lniveau);
        modifier.add(niveau);
        modifier.add(Lquetsion);
        modifier.add(question);
        modifier.add(Lchoix1);
        modifier.add(choix1);
        modifier.add(Lchoix2);
        modifier.add(choix2);
        modifier.add(Lchoix3);
        modifier.add(choix3);
        modifier.add(Lreponse);
        modifier.add(reponse);
        modifier.add(btnOk);
        modifier.add(c);
        modifier.show();
    }
    public void deleteTest(mesTests test)
    {
        ConnectionRequest req = new ConnectionRequest();           
               req.setUrl("http://127.0.0.1/pi/hani/delete.php?id=" +test.getId());
                
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.charAt(s.length()-1)=='k') {
                            Dialog.show("Confirmation", "Suppression ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(req);
            }
    public Form getForm() {
        return modifier;
    }
}
