/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offres;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/**
 *
 * @author ELYES
 */
public class Accueil {

    Form f;

    public Accueil(Resources theme) {

        UIBuilder ui = new UIBuilder();
        f = ui.createContainer(theme, "Accueil").getComponentForm();
      //  Resources css = Resources.openLayered("/theme.css");
      //  UIManager.getInstance().addThemeProps(css.getTheme(css.getThemeResourceNames()[0]));
        Label lNom = (Label) ui.findByName("lNom", f);
      //  f.setUIID("Accueil");

        Button AccueilToTouteLesOffres = new Button("Toute les Offres");
      //  AccueilToTouteLesOffres.setUIID("AccueilToTouteLesOffres");
        Button AccueilToMesOffres = new Button("Mes Offres");
        AccueilToMesOffres.setUIID("AccueilToMesOffres");

      //  f.getToolbar().setUIID("accueilToolbar");

        f.setTitle("Accueil");

        lNom.setText("Bonjour");

        f.getToolbar().addCommandToSideMenu("Toute les offres", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                TouteLesOffres tteoffres = new TouteLesOffres();
                tteoffres.TouteLesOffres();
            }
        });

//        f.getToolbar().addComponentToSideMenu(AccueilToTouteLesOffres);
//        AccueilToTouteLesOffres.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                TouteLesOffres tteoffres = new TouteLesOffres();
//                tteoffres.TouteLesOffres();
//            }
//        });
//        f.getToolbar().addComponentToSideMenu(AccueilToMesOffres);
//        AccueilToMesOffres.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//           MesOffres mesoffres = new MesOffres();
//                mesoffres.MesOffres();
//            }
//        });
        f.getToolbar().addCommandToSideMenu("Mes offres", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                MesOffres mesoffres = new MesOffres();
                mesoffres.MesOffres();

            }
        });

        f.getToolbar().addCommandToSideMenu("Ajouter une offre", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                Ajout ajout = new Ajout();
                ajout.Ajout();

            }
        });

    }

    public Form getF() {
        return f;
       
    }

}
