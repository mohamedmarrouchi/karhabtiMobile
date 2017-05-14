/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.Dialog.show;
import static com.codename1.ui.Dialog.show;
import static com.codename1.ui.Dialog.*;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author root
 */
public class Dialoge {
    private Form f1;
    public Dialoge(){
                        Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));

final Button show = new Button("Show Dialog");
final Button showPopup = new Button("Show Popup");
cnt.add(show).add(showPopup);
show.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        Dialog.show("Dialog Title", "This is the dialog body, it can contain anything...", "OK", "Cancel");
    }
});

showPopup.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        Dialog d = new Dialog("Popup Title");
        TextArea popupBody = new TextArea("This is the body of the popup", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
        d.showPopupDialog(showPopup);
    }
});
    }

}

