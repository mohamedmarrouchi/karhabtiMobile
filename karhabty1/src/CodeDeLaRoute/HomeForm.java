/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

import Annonces.services.Acceuil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author HANY
 */
public class HomeForm {

    
    private Form current,f;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    public HomeForm(Resources theme) {

        UIBuilder ui = new UIBuilder();
      UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme, "Home");
        f = ctn1.getComponentForm();
       f.getToolbar().addCommandToSideMenu("Acceuil", null, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        new Acceuil(theme).show();

                    }
                });
        f.getToolbar().addCommandToRightBar("Back",null,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               Clock s1 = new Clock(theme);
            }
        });
        f.getToolbar().addCommandToSideMenu("Test", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
//                   ChartForm demos = new ChartForm(theme);
//                   demos.show(); 
            }
        });
        f.getToolbar().addCommandToSideMenu("Gerer les tests", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                   GererTests g = new GererTests(theme);
                   g.getForm().show();
            }
        });
        f.getToolbar().addCommandToSideMenu("Ajouter test", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                   InsertTest i = new InsertTest(theme);
                   i.getForm().show();

            }
        });
        f.getToolbar().addCommandToSideMenu("Statistiques", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                   ChartForm demos = new ChartForm(theme);
                   demos.show(); 
            }
        });
        Button niveau1 = (Button) ui.findByName("niveau1", ctn1);
        Button niveau2 = (Button) ui.findByName("niveau2", ctn1);
        Button niveau3 = (Button) ui.findByName("niveau3", ctn1);
        Button niveau4 = (Button) ui.findByName("niveau4", ctn1);
        Button niveau5 = (Button) ui.findByName("niveau5", ctn1);
        Button niveau6 = (Button) ui.findByName("niveau6", ctn1);
      
        niveau1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                NiveauUn un = new NiveauUn(theme);
                un.getNiveauUn().show();
            }
        });
        niveau2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                NiveauDeux deux = new NiveauDeux(theme);
                deux.getNiveauDeux().show();
            }
        });
        niveau3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                NiveauTrois trois = new NiveauTrois(theme);
                trois.getNiveauTrois().show();
            }
        });
//        niveau4.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                NiveauQuatre quatre = new NiveauQuatre(theme);
//                quatre.getNiveauQuatre().show();
//            }
//        });
        niveau5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                NiveauCinq cinq = new NiveauCinq(theme);
                cinq.getNiveauCinq().show();
            }
        });
        niveau6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                NiveauSix six = new NiveauSix(theme);
                six.getNiveauSix().show();
            }
        });
        f.show();
    }

}
