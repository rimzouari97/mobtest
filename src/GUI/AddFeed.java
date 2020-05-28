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
import Entities.Feedback;
import Services.Feedservice;

/**
 *
 * @author mon
 */
public class AddFeed extends Form{
     public AddFeed(Form previous) {
        setTitle("Add a feedback");
        setLayout(BoxLayout.y());
        TextField tfEvent_id = new TextField("", "event_id");
        TextField tfRating = new TextField("","rating");
        Button btnValider = new Button("Add feedback");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfEvent_id.getText().length()==0)||(tfRating.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        int event_id=Integer.parseInt(tfEvent_id.getText());
                        int rating=Integer.parseInt(tfRating.getText());
                        Feedback t = new Feedback(event_id,rating);
                        if( Feedservice.getInstance().addFeed(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
              
                
            }
        });
        
        addAll(tfEvent_id,tfRating,btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
