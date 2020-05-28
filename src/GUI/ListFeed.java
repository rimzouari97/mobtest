/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
    import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import Services.Feedservice;
import Entities.Feedback;
import java.util.List;

/**
 *
 * @author mon
 */

public class ListFeed extends Form {
   
    public ListFeed(Form previous) {
        setTitle("List feedback");
        setLayout(BoxLayout.y());
        
        List <Feedback> feedbacks = Feedservice.getInstance().getAllFeedback();
    
        for (int i = 0; i < feedbacks.size(); i++) {
            Label l = new Label("Feedback N°"+i);
            addAll(l);
            Feedback get = feedbacks.get(i);
            add(addFeed(get));
        }
        
        
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }   
    
     private Container addFeed(Feedback e){
          Container holder = new Container(BoxLayout.x());
          Container detaills = new Container(BoxLayout.y());
          
        
     
          Label l0=new Label ("id_event:"+e.getEvent_id());
          Label l1=new Label ("id:"+e.getId());
          Label l3=new Label ("rating:"+e.getRating());
   
    
          detaills.add(l0);
          detaills.add(l1);
          detaills.add(l3);
   
     
          
          holder.add(detaills);
          return (holder);
     }
}



