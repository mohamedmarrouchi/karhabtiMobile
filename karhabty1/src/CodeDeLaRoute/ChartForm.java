/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDeLaRoute;

import com.codename1.io.Log;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HANY
 */
public class ChartForm extends Form{
     boolean drawOnMutableImages;
    List formMenu;
    
    class ListOption {
        Class<AbstractChart> chartClass;
        String name;
        
        ListOption(Class cls, String name){
            this.chartClass = cls;
            this.name = name;
        }
        
        public String toString(){
            return this.name;
        }
    }
    
    ListOption[] options = new ListOption[]{
        new ListOption(PieChart.class, "Niveau un"),
        new ListOption(PieChart2.class, "Niveau deux"),
        new ListOption(PieChart3.class, "Niveau trois"),
        new ListOption(PieChart4.class, "Niveau quatre"),
        new ListOption(PieChart5.class, "Niveau cinq"),
        new ListOption(PieChart6.class, "Niveau six")
    };
    
    public ChartForm(Resources theme){
        super("Les statistiques");
        super.getToolbar().addCommandToLeftBar("Back",null,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               HomeForm home = new HomeForm(theme);
            }
        });
        formMenu = new List();
        for ( int i=0; i<options.length; i++){
            formMenu.addItem(options[i]);
        }
        formMenu.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                int sel = formMenu.getCurrentSelected();
                ListOption opt = options[sel];
                Class cls = opt.chartClass;
                if ( ChartInBoxLayout.class.equals(cls) ){
                    Form f = new ChartInBoxLayout().getForm();
                    Command cmd = new Command("Menu"){

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ChartForm.this.showBack();
                        }
                        
                    };
                    f.setBackCommand(cmd);
                    
                    f.getStyle().setBgColor(0x0);
                    f.getStyle().setBgTransparency(0xff);
                    int numComponents = f.getComponentCount();
                    for (int i=0; i<numComponents; i++) {
                        f.getComponentAt(i).getStyle().setBgColor(0x0);
                        f.getComponentAt(i).getStyle().setBgTransparency(0xff);
                    }
                    
                    f.show();
                    return;
                }
                try {
                    AbstractChart demo = (AbstractChart)cls.newInstance();
                    demo.setDrawOnMutableImage(drawOnMutableImages);
                    Form intent = demo.execute();
                    if ( "".equals(intent.getTitle())){
                        intent.setTitle(demo.getName());
                    }
                    Command cmd = new Command("Menu"){

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ChartForm.this.showBack();
                        }
                        
                    };
                    intent.setBackCommand(cmd);
                    intent.getStyle().setBgColor(0x0);
                    intent.getStyle().setBgTransparency(0xff);
                    int numComponents = intent.getComponentCount();
                    for (int i=0; i<numComponents; i++) {
                        intent.getComponentAt(i).getStyle().setBgColor(0x0);
                        intent.getComponentAt(i).getStyle().setBgTransparency(0xff);
                    }
                    intent.show();
                } catch (InstantiationException ex) {
                    Log.e(ex);
                } catch (IllegalAccessException ex) {
                    Log.e(ex);
                }
            }
            
        });
        
        setLayout(new BorderLayout());
        
        addComponent(BorderLayout.CENTER, formMenu);
        
    }
    
}
