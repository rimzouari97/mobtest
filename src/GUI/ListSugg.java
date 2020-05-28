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
import Services.Suggservice;
import Entities.Suggestion;
import java.util.List;

/**
 *
 * @author mon
 */
public class ListSugg extends Form {

    public ListSugg(Form previous) {
        setTitle("List suggestion");
        setLayout(BoxLayout.y());

        List<Suggestion> suggs = Suggservice.getInstance().getAllSuggestion();

        for (int i = 0; i < suggs.size(); i++) {
            Label l = new Label("suggestion N°" + i);
            addAll(l);
            Suggestion get = suggs.get(i);
            add(addSugg(get));

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        }   
    
    } 

    private Container addSugg(Suggestion e){
        Container holder = new Container(BoxLayout.x());
        Container detaills = new Container(BoxLayout.y());

        
        Label l1 =  new Label("id:"+ e.getId());
        Label l3 = new Label("nomFondation:" + e.getFondation());
        Label l4 = new Label("description:" + e.getDescription());
        Label l2 = new Label("mail:" + e.getMail());

       detaills.add(l1);
        detaills.add(l2);
        detaills.add(l3);
        detaills.add(l4);

        holder.add(detaills);
        return (holder);
    }

}

