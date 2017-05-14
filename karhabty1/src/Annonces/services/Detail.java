/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.modeles.Annonces;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author root
 */
public class Detail {

    private Form f1;

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }
    EncodedImage encoded;
            float fontsize = 2;

    public float getFontsize() {
        return fontsize;
    }

    public void setFontsize(float fontsize) {
        this.fontsize = fontsize;
    }

    public Detail(Annonces annonce, Resources theme_1) {
        f1 = new Form("Detail", BoxLayout.y());
        f1.setTitle("Détail");
        Container ctn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        f1.getToolbar().setBackCommand("", e -> {

            new ListAnnonces(theme_1).showBack();
        });
                f1.getToolbar().addCommandToOverflowMenu("Note", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new EditeFavoris(theme_1,annonce.getId()).show();
            }
        });
        Label lbltitre = new Label("TITRE: " + annonce.getTITRE());
        Label lblregion = new Label("region : " + annonce.getRegion());

        Label lblboite = new Label("boite: " + annonce.getBOITE());

        Label lblenergie = new Label("energie: " + annonce.getENERGIE());

        Label lblnombredecylindres = new Label("nombre de cylindres: " + annonce.getNOMBRE_DE_CYLINDRES());
        Label lblpuissance_fiscale = new Label("puissance fiscale: " + annonce.getPUISSANCE_FISCALE());;
        Label lblcylindree = new Label("cylindree: " + annonce.getCYLINDREE());;
        Label lblprix = new Label("prix: " + annonce.getPRIX());
        Label lblusername = new Label("username: " + annonce.getUser().getUsername());
        Label lblemail = new Label("email: " + annonce.getUser().getEmail());
        Label lbldetails = new Label("Details");
        Label lblcontact = new Label("Contact");
        Font fnt = lbltitre.getUnselectedStyle().getFont();
        Font fn = lblboite.getUnselectedStyle().getFont();
        Font f = lblregion.getUnselectedStyle().getFont();
        Font g = lblenergie.getUnselectedStyle().getFont();
        Font h = lblnombredecylindres.getUnselectedStyle().getFont();
        Font p = lblcylindree.getUnselectedStyle().getFont();
        Font j = lblpuissance_fiscale.getUnselectedStyle().getFont();
        Font k = lblprix.getUnselectedStyle().getFont();
        Font l = lblusername.getUnselectedStyle().getFont();
        Font m = lblemail.getUnselectedStyle().getFont();
        Font n = lblcontact.getUnselectedStyle().getFont();
        Font o = lbldetails.getUnselectedStyle().getFont();
        lbltitre.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
        lblboite.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblregion.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
        lblenergie.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblnombredecylindres.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblcylindree.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblpuissance_fiscale.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblprix.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblusername.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblemail.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lblcontact.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        lbldetails.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));

        f1.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_FORMAT_SIZE, e -> {
            Slider s = new Slider();
            s.setMinValue(0);
            s.setMaxValue(50);
            s.setEditable(true);
            InteractionDialog id = new InteractionDialog();
            id.setUIID("Dialog");
            id.setLayout(new BorderLayout());
            id.add(BorderLayout.CENTER, s);
            s.addDataChangedListener((i, ii) -> {
                setFontsize(1 + ((float) s.getProgress()) / 10.0f);
                lbltitre.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
                lblregion.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
                lblboite.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblnombredecylindres.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblcylindree.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblpuissance_fiscale.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblprix.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblusername.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblenergie.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblemail.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lblcontact.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
lbldetails.getAllStyles().setFont(fnt.derive(Display.getInstance().convertToPixels(getFontsize()), Font.STYLE_PLAIN));
                lbltitre.repaint();
                lblregion.repaint();
                lblboite.repaint();
                lblnombredecylindres.repaint();
                lblcylindree.repaint();
                lblpuissance_fiscale.repaint();
                lblusername.repaint();
                lblenergie.repaint();
                lblemail.repaint();
                lblcontact.repaint();
                lbldetails.repaint();
                lblprix.repaint();
            });
            Button ok = new Button("OK");
            id.add(BorderLayout.EAST, ok);
            ok.addActionListener(ee -> id.dispose());
            id.show(f1.getLayeredPane().getHeight() - ok.getPreferredH() * 2, 0, 0, 0);

        });

        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
        FontImage x = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(x.scaled(x.getWidth() * 2, p.getHeight() * 2), false);
        Label cadrephoto = new Label();
        cadrephoto.setIcon(URLImage.createToStorage(placeholder, annonce.getImage(), "http://127.0.0.1/pi/images/" + annonce.getImage()));
        ctn1.add(cadrephoto);
        ctn3.add(lbldetails);

        ctn3.add(lbltitre);
        ctn3.add(lblregion);
        ctn3.add(lblboite);
        ctn3.add(lblenergie);
        ctn3.add(lblnombredecylindres);
        ctn3.add(lblpuissance_fiscale);
        ctn3.add(lblcylindree);
        ctn3.add(lblprix);
        ctn4.add(lblcontact);
        ctn4.add(lblusername);
        ctn4.add(lblemail);

        ctn2.add(ctn3);
        ctn2.add(ctn4);

        ctn.add(ctn1);
        ctn.add(ctn2);
        f1.add(ctn);
        f1.getContentPane().animateLayout(200);
        f1.show();
    }
}
