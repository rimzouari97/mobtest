/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.material;
import Services.ServiceMaterial;
import Services.ServiceTransaction;
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
import java.util.Date;
import com.codename1.uikit.cleanmodern.BaseForm;

/**
 *
 * @author Memmicha
 */
public class AddMaterial extends BaseForm {
    public AddMaterial(Form previous) {
        setTitle("Add a new material");
        setLayout(BoxLayout.y());
     //   TextField tfId_address = new TextField("", "id_address");
        TextField tfNameAddress = new TextField("","Name_address");
        TextField tfAddress= new TextField("", "Address");
        TextField tfDescription = new TextField("", "Description");
       
        Button btnValider = new Button("Add task");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNameAddress.getText().length()==0)||(tfAddress.getText().length()==0)||(tfDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    //    int id_address=Integer.parseInt(tfId_address.getText());
                        material t = new material(tfNameAddress.getText(),tfAddress.getText(),tfDescription.getText());
                        if( ServiceMaterial.getInstance().addMaterial(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
              
                
            }
        });
        
        addAll(tfNameAddress,tfAddress,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
