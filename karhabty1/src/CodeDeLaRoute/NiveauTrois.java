/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author HANY
 */
public class NiveauTrois {

    private Form niveauTrois;
    int repSuccniv1 = 0;
    int repEchniv1 = 0;
    private Form current, listt, menu, f, f1;
    public Resources theme;
    EncodedImage enc;
    Form form = new Form();
    public int id_s;
    public double p;
    public int nbplaces, nbplacesD;
    public int restant;
    public int houssem;
    int i = 0;
    private int ii = 1;

    private ArrayList<mesTests> lists = new ArrayList<>();
    private ArrayList<String> response = new ArrayList<String>();
    private ArrayList<String> responseC = new ArrayList<String>();
    Button b = new Button("Valider");
    Image imgs;
    ImageViewer imgv;
    String url = "http://127.0.0.1/pi/hani/";

    public NiveauTrois(Resources theme) {
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {
        }
        form.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm home = new HomeForm(theme);
            }
        });
        niveauTrois = new Form();
        Button valider = new Button("Valider");
        Container cnt2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Command cmd1 = new Command("quizzs");
        SpanLabel sp = new SpanLabel();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/pi/hani/selectNivTrois.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //System.out.println(getListser(new String(con.getResponseData())));
                    ArrayList<mesTests> l = getListser(new String(con.getResponseData()));
                    for (mesTests e : l) {
                        SpanLabel sp = new SpanLabel();
//                  f1.add(sp);
                        sp.setText(e.toString());
                        Button b = new Button();
                        // f1.add(b);
                        b.setText("reserver");
                    }
                } catch (IOException ex) {
                    // Logger.getLogger(NiveauDeux.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(con);

    }

    public ArrayList<mesTests> getListser(String json) throws IOException {
        JSONParser j = new JSONParser();
        Map<String, Object> test = j.parseJSON(new CharArrayReader(json.toCharArray()));
        java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) test.get("mes_tests");

        listt = new Form("List", BoxLayout.y());

        for (Map<String, Object> obj : list) {
            mesTests t = new mesTests();
            t.setId(Integer.parseInt(obj.get("id").toString()));
            t.setNiveau(Integer.parseInt(obj.get("niveau").toString()));
            t.setQuestion(obj.get("question").toString());
            t.setChoix1((obj.get("choix1").toString()));
            t.setChoix2((obj.get("choix2").toString()));
            t.setChoix3((obj.get("choix3").toString()));
            t.setReponse(Integer.parseInt(obj.get("reponse").toString()));
            t.setImage(obj.get("image").toString());
            t.setReponseforja(obj.get("responseforja").toString());
            responseC.add(t.getReponseforja());
            imgs = URLImage.createToStorage(enc, t.getChoix1(), url + t.getImage(), URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(imgs);
            Container c1 = new Container();
            Container c2 = new Container();
            Container c3 = new Container();
            Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container ctn7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label lb1 = new Label("         ");
            Label lb2 = new Label("         ");
            Label lb3 = new Label("         ");
            Label lb = new Label(" ");
            Label lb4 = new Label(" ");
            Label lblSurnom = new Label("Question " + i + " : " + t.getQuestion());
            Label r1 = new Label("Reponse 1 : " + t.getChoix1());
            Label r2 = new Label("Reponse 2 : " + t.getChoix2());
            Label r3 = new Label("Reponse 3 : " + t.getChoix3());
            RadioButton a = new RadioButton();
            RadioButton aa = new RadioButton();
            RadioButton aaa = new RadioButton();
            ButtonGroup bg = new ButtonGroup(a, aa, aaa);
            // System.out.println(test);
            c1.add(lb1);
            c1.add(a);
            c1.add(r1);

            c2.add(lb2);
            c2.add(aa);
            c2.add(r2);

            c3.add(lb3);
            c3.add(aaa);
            c3.add(r3);
            ctn3.add(c1);
            ctn3.add(c2);
            ctn3.add(c3);
            ctn7.add(imgv);
            ctn7.add(lblSurnom);
            ctn7.add(lb);
            ctn7.add(ctn3);
            ctn7.add(lb4);

            form.add(ctn7);
// form.add(cellForRow(s,theme));

            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    int test = bg.getSelectedIndex();

                    if (a.isSelected()) {
                        response.add(t.getChoix1());
                    } else if (aa.isSelected()) {
                        response.add(t.getChoix2());
                    } else if (aaa.isSelected()) {
                        response.add(t.getChoix3());
                    }
                    System.err.println(list.size());
                    if (ii == list.size()) {
                        checkrep(response, responseC);
                    }
                    ii++;

                }

            });

        }

        form.add(b);
        form.show();

        return lists;

    }

    public Form getNiveauTrois() {
        return niveauTrois;
    }

    public void setNiveauTrois(Form niveauTrois) {
        this.niveauTrois = niveauTrois;
    }

    public void checkrep(ArrayList<String> x, ArrayList<String> y) {
        Iterator<String> iter2 = x.iterator();
        Iterator<String> iter1 = y.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            if (iter1.next().toString().equals(iter2.next().toString())) {
                repSuccniv1++;
            } else {
                repEchniv1++;
            }
        }
        int tot = repEchniv1 + repSuccniv1;
        Form res = new Form();
        Label r = new Label("resultat" + repSuccniv1 + "/" + tot);
        Button b = new Button("Nouveau test");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm h = new HomeForm(theme);
            }
        });
        res.addComponent(BorderLayout.CENTER, r);
        res.addComponent(BorderLayout.CENTER, b);
        res.show();
    }
}
