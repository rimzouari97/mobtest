/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import Services.Suggservice;
import Entities.Suggestion;

/**
 *
 * @author mon
 */
public class AddSugg extends Form {
     public AddSugg(Form previous) {
       
        Suggestion t = new Suggestion();
        setTitle("Add a new Suggestion");
        setLayout(BoxLayout.y());
        TextField tfMail = new TextField("","Mail");
        TextField tfDescription = new TextField("", "description");
        TextField tfF = new TextField("", "nameFondation");
        Button btnValider = new Button("Add suggestion");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfMail.getText().length()==0)||(tfDescription.getText().length()==0)||(tfF.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Suggestion t = new Suggestion(tfMail.getText(),tfDescription.getText(),tfF.getText());
                        if( Suggservice.getInstance().addSugg(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }    
            }
        });
        
        addAll(tfMail,tfDescription,tfF,btnValider);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
    
}
