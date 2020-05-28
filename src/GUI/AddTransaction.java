/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.transaction;
import Entities.user;
import Services.ServiceTransaction;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import java.util.Date;





/**
 *
 * @author Memmicha
 */
public class AddTransaction extends BaseForm{
Resources res;

    public AddTransaction(Form previous) {
        
      res = UIManager.initFirstTheme("/theme");
        transaction t = new transaction();
       
        setTitle("Add a new transaction");
        setLayout(BoxLayout.y());
      //  TextField tfId_user = new TextField("", "id_user");
        TextField tfName = new TextField("","Name");
        TextField tfLastName = new TextField("", "Last_Name");
        TextField tfRIB = new TextField("", "RIB");
        Picker datePicker = new Picker();
        datePicker.setDate(new Date());
        datePicker.setType(Display.PICKER_TYPE_DATE);
        TextField tfMontant = new TextField("", "Montant");
        TextField tfAddress = new TextField("", "address");
        Button btnValider = new Button("Add task");
        user u = new user();
 
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfLastName.getText().length()==0)||(tfRIB.getText().length()==0)||(datePicker.getText().length()==0)||(tfMontant.getText().length()==0)||(tfAddress.getText().length()==0)||(datePicker.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                      //  int id_user=Integer.parseInt(tfId_user.getText());
                        Float Montant=Float.parseFloat(tfMontant.getText());
                        Date d1 = datePicker.getDate();
                        transaction t = new transaction(tfName.getText(),tfLastName.getText(),tfRIB.getText(),d1,Montant,tfAddress.getText());
       
                            new AddPaiement(previous,t,res,u).show();
                       
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                  
            }
            }});
        
        addAll(tfName,tfLastName,tfRIB,datePicker,tfMontant,tfAddress,btnValider);
      //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
}
