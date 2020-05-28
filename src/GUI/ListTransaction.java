/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.transaction;
import Services.ServiceTransaction;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;
import com.codename1.uikit.cleanmodern.BaseForm;

/**
 *
 * @author Memmicha
 */
public class ListTransaction extends BaseForm{
Resources res;

  public ListTransaction(Form previous) {
       transaction t = new transaction();
        setTitle("your transaction");
        Button back = new Button ("back");
        List <transaction> transactions = ServiceTransaction.getInstance().getTransaction();
    
        for (int i = 0; i < transactions.size(); i++) {
            Label l = new Label("Transaction N°"+i+"/n");
            addAll(l);
            transaction get = transactions.get(t.getId());
           
            add(addTransaction(get));
            
        }
        back.addActionListener(e -> previous.showBack());
        add(back);
       
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }   
    
     private Container addTransaction(transaction e){
          Container holder = new Container(BoxLayout.x());
          Container detaills = new Container(BoxLayout.y());
          
        
          //Label l0=new Label ("User:"+e.getId_user()+"/n");
          Label l1=new Label ("Name:"+e.getName()+"/n");
          Label l2=new Label ("Last Name:"+e.getLast_Name()+"/n");
         // Label l3=new Label ("RIB:"+e.getRIB()+"/n");
         // Label l6=new Label ("Date:"+e.getDate_Expiration()+"/n");
          Label l4=new Label ("Montant:"+e.getMontant()+"/n");
          Label l5=new Label ("Address:"+e.getAddress()+"/n");
    
        //  detaills.add(l0);
          detaills.add(l1);
          detaills.add(l2);
         // detaills.add(l3);
         // detaills.add(l6);
          detaills.add(l4);
          detaills.add(l5);
          
          holder.add(detaills);
          return (holder);
     }
}
