/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Annonces;
import com.codename1.components.FloatingActionButton;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import Annonces.modeles.User;
import Offres.MesOffres;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.messaging.Message;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
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
public class ListAnnonces extends Form {

    private static final int[] COLORS = {
        0x44a1eb, 0x77ddbb, 0xbbe535,
        0xeeee22, 0xffbb22, 0xf56545,
        0xff5997, 0xa767ff, 0xffffff
    };

    private int color = 0xffffff;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    ArrayList<Annonces> listEtudiants = new ArrayList<>();
    ArrayList<Annonces> listS = new ArrayList<>();

    private Container getonContainer;
    private Form list;

    public Form getList() {
        return list;
    }

    public void setList(Form list) {
        this.list = list;
    }
    public Label lblregion,lblboite,lblSurnom;

    public ListAnnonces(Resources theme_1) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/select.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                System.out.println(getListEtudiant(new String(con.getResponseData())));
//                //sp.setText(getListEtudiant(new String(con.getResponseData())) + "");
//                //hi.refreshTheme();
//
                list = new Form("List", BoxLayout.y());
                setTitle("Annonces");
                //list.setUIID("background");
                getContentPane().getUnselectedStyle().setBgTransparency(255);
                getContentPane().getUnselectedStyle().setBgColor(getColor());
                getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PALETTE, e -> {
                    Dialog colorPicker = new Dialog("dialog_note_colour");
                    colorPicker.setDisposeWhenPointerOutOfBounds(true);
                    colorPicker.setBackCommand("", null, ee -> colorPicker.dispose());
                    colorPicker.setLayout(new GridLayout(3, 3));
                    for (int iter = 0; iter < COLORS.length; iter++) {
                        Button choose = new Button("");
                        Style s = choose.getAllStyles();
                        s.setAlignment(Component.CENTER);
                        int color = COLORS[iter];
                        s.setBorder(RoundBorder.create().color(color));
//                      
                        choose.addActionListener(ee -> {
                            colorPicker.dispose();
                            getContentPane().getUnselectedStyle().setBgColor(color);
                            repaint();
                        });
                        colorPicker.add(choose);
                    }
                    colorPicker.showPacked(BorderLayout.CENTER, true);
                });

        

       
           
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
                fab.bindFabToContainer(getContentPane());
                fab.addActionListener(e -> {
                    new Ajout(theme_1).show();
                });

//            Ajout a = new Ajout(theme_1);
//            }else if(e.getCommand()==cmdback){
//                Acceuil a = new Acceuil(theme_1);
//                a.getF1().show();
              
                for (Annonces a : getListEtudiant(new String(con.getResponseData()))) {
//for (Annonces a : listS){

                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                 //   ctn1.setUIID("Containerborder55");
                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    lblSurnom = new Label("TITRE : " + a.getTITRE());

                    lblSurnom.addPointerReleasedListener(e -> {

                        //display detail
                        Detail d = new Detail(a,theme_1);
                        d.getF1().show();
                        // displayDetail(a);

                    });
                    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 2, p.getHeight() * 2), false);
                    Label cadrephoto = new Label();
                    cadrephoto.setIcon(URLImage.createToStorage(placeholder, a.getImage(), "http://127.0.0.1/pi/images/" + a.getImage()));
                    ctn1.add(cadrephoto);
                    ctn1.setLeadComponent(lblSurnom);

                    ctn2.add(lblSurnom);
                     lblregion = new Label("REGION : " + a.getRegion()); 
                    lblboite = new Label("PRIX :" + a.getPRIX());

                    ctn2.add(lblregion);
                    ctn2.add(lblboite);

                    ctn1.add(ctn2);
                    ctn1.putClientProperty("annonce", a);
                    add(ctn1);
                }


                getContentPane().animateLayout(200);
            }

        });
        NetworkManager.getInstance().addToQueue(con);
        //slideMenu
        Command cmd = new Command("Acceuil");
        Command cmd1 = new Command("Mes Notes");
        Command cmd2 = new Command("Mes Annonces");
        Command cmd3 = new Command("Recherche Avancé");
       
        Command cmd5 = new Command("contacter nous");
        
getToolbar().addCommandToSideMenu(cmd);
        getToolbar().addCommandToSideMenu(cmd1);
        getToolbar().addCommandToSideMenu(cmd2);
        getToolbar().addCommandToSideMenu(cmd3);
        getToolbar().addCommandToSideMenu(cmd5);

        addCommandListener(e -> {

            if (e.getCommand() == cmd5) {
                Message m = new Message("");
                Display.getInstance().sendMessage(new String[]{"mohamed.marrouchi@esprit.tn"}, "Subject of message", m);
            }

        });

                   getToolbar().addSearchCommand(e -> search((String)e.getSource()));
        /*getToolbar().addCommandToOverflowMenu("Backup Notes", null, e -> Log.p("Todo"));
        getToolbar().addCommandToOverflowMenu("Restore Notes", null, e -> Log.p("Todo"));
        getToolbar().addCommandToOverflowMenu("Rate App", null, e -> Log.p("Todo"));*/
        addCommandListener(e -> {

            if (e.getCommand() == cmd1) {
             new ListFavoris(theme_1).show();


            } else if (e.getCommand() == cmd2) {
                
                new MesAnnonces(theme_1).show();
            } else if (e.getCommand() == cmd3) {
new Rechercher(theme_1).getF1().show();
            }else if (e.getCommand() == cmd) {
new Acceuil(theme_1).show();
            }

        });

    }
        void search(String text) {
        if(text == null || text.length() == 0) {
            for(Component c : getContentPane()) {
                c.setHidden(false);
                c.setVisible(true);
            }
        } else {
            for(Component c : getContentPane()) {
            Annonces an = (Annonces)c.getClientProperty("annonce");

                text = text.toLowerCase();
                boolean show = an.getTITRE().toLowerCase().indexOf(text) > -1 ;
                c.setHidden(!show);
                c.setVisible(show);
            }
        }
        getContentPane().animateLayout(200);
    }

    public ArrayList<Annonces> getListEtudiant(String json) {
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("annonces");

            for (Map<String, Object> obj : list) {
                Annonces e = new Annonces();
                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://127.0.0.1/pi/selectUser.php?id=" + obj.get("user"));
                con.addResponseListener(new ActionListener<NetworkEvent>() {
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

        } catch (IOException ex) {
        }

        return listEtudiants;

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
