/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author mon
 */
public class Suggservice {
      public ArrayList<Suggestion> suggestions;
    
    public static Suggservice instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Suggservice() {
         req = new ConnectionRequest();
    }

    public static Suggservice getInstance() {
        if (instance == null) {
            instance = new Suggservice();
        }
        return instance;
    }

    public boolean addSugg(Suggestion t) {
        String url = Statics.BASE_URL2 + "sug/new/" +t.getMail()+"/"+ t.getFondation() + "/" + t.getDescription();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

  public ArrayList<Suggestion> parseSugg(String jsonText){
        try {
            suggestions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for (Map<String,Object> obj : list){
                System.out.println(obj);
                Suggestion t = new Suggestion();
                t.setId((int)Float.parseFloat(obj.get("id").toString()));
                t.setMail(obj.get("mail").toString());
                    t.setFondation(obj.get("nomFondation").toString());
                        t.setDescription(obj.get("description").toString());
               
  
                    suggestions.add(t);
            }
                    
        } catch (IOException ex) {
            
        }
        return suggestions;
    } 
    
    public ArrayList<Suggestion> getAllSuggestion(){
        String url = Statics.BASE_URL2 +"sug/Api";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                suggestions = parseSugg(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return suggestions;
    }

   
    
}
