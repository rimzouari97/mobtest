/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.material;
import Services.ServiceMaterial;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.List;
import com.codename1.uikit.cleanmodern.BaseForm;

/**
 *
 * @author Memmicha
 */
public class ListMaterial extends BaseForm {
 
     material m = new material();
   
    public ListMaterial(Form previous) {
        setTitle("List Materials");
        setLayout(BoxLayout.y());
        Button back = new Button ("back");
        List <material> materials = ServiceMaterial.getInstance().getAllMaterial();
    
        for (int i = 0; i < materials.size(); i++) {
            Label l = new Label("Material N°"+i);
            addAll(l);
            material get = materials.get(i);
            add(addMaterial(get));
        }
        
        back.addActionListener(e -> previous.showBack());
        add(back);
            Form hi = new Form("PDF Viewer", BoxLayout.y());
            Button devGuide = new Button("Show PDF");
            devGuide.addActionListener(e -> {
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
             if(!fs.exists(fileName)) {
               Util.downloadUrlToFile("http://www.polyu.edu.hk/iaee/files/pdf-sample.pdf", fileName, true);
       }
           Display.getInstance().execute(fileName);
       });
       add(devGuide);
       hi.show();
    }   
    
     private Container addMaterial(material e){
          Container holder = new Container(BoxLayout.x());
          Container detaills = new Container(BoxLayout.y());
          
        
       //   Label l0=new Label ("idAddress:"+e.getId_address());
          Label l1=new Label ("nameAddress:"+e.getName_address());
          Label l2=new Label ("address:"+e.getAddress());
          Label l3=new Label ("description:"+e.getDescription());
    
        //  detaills.add(l0);
          detaills.add(l1);
          detaills.add(l2);
          detaills.add(l3);
          
          holder.add(detaills);
          return (holder);
     }
}

