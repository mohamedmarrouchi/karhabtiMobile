/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

import java.util.Map;

/**
 *
 * @author HANY
 */
public class GererTests {
    
    private Form Gerer;
    //EncodedImage enc;
    ArrayList<mesTests> listTests= new ArrayList<>();
    ConnectionRequest con = new ConnectionRequest();
    //Image imgs;
    ImageViewer imgv;
    String url = "http://127.0.0.1/pi/hani";
    mesTests testmodif = new mesTests();
    private Resources theme1;
    public GererTests(Resources theme) {
        Gerer = new Form();
        Gerer.getToolbar().addCommandToLeftBar("Back",null,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               HomeForm h = new HomeForm(theme);
               
            }
        });
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        con.setUrl("http://127.0.0.1/pi/hani/selectAll.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                //System.out.println(getListProduit(new String(con.getResponseData())));
                for (mesTests test : getListProduit(new String(con.getResponseData()))) {
                    cnt.add(addContact(test));
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);

        Gerer.add(cnt);
        Gerer.show();
    }
    public ArrayList<mesTests> getListProduit(String json) {

        
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> test = j.parseJSON(new CharArrayReader(json.toCharArray()));
            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) test.get("mes_tests");
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
                listTests.add(t);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listTests;
    }
    public Container addContact(mesTests test) {
        
            Label lblNiveau   = new Label("Niveau : " + test.getNiveau());
            Label lblQuestion = new Label("Question : " + test.getQuestion());
        Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ctn1.add(lblNiveau);
            ctn1.add(lblQuestion);
            lblQuestion.addPointerReleasedListener(ev ->{
                testmodif.setChoix1(test.getChoix1());
                testmodif.setChoix2(test.getChoix2());
                testmodif.setChoix3(test.getChoix3());
                testmodif.setId(test.getId());
                testmodif.setQuestion(test.getQuestion());
                testmodif.setReponse(test.getReponse());
                testmodif.setNiveau(test.getNiveau());
                testmodif.setImage(test.getImage());
                System.out.println(testmodif);
                ModifierTest t =new ModifierTest(theme1, testmodif);
               t.getForm().show();
            });
            ctn1.setLeadComponent(lblQuestion);
        
        return ctn1;
    }
     
    public Form getForm() {
        return Gerer;
    }

    public void setFrom(Form form) {
        this.Gerer = form;
    }
}
