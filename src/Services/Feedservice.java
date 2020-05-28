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
public class Feedservice {
         public ArrayList<Feedback> feedbacks;
    
    public static Feedservice instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Feedservice() {
         req = new ConnectionRequest();
    }

    public static Feedservice getInstance() {
        if (instance == null) {
            instance = new Feedservice();
        }
        return instance;
    }
  public boolean addFeed(Feedback t) {
        String url = Statics.BASE_URL2 + "fed/fed/newApi/" + t.getEvent_id()+"/"+ t.getRating() ;
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
    public ArrayList<Feedback> parseFeed(String jsonText){
        try {
            feedbacks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for (Map<String,Object> obj : list){
                System.out.println(obj);
                Feedback t = new Feedback();
                t.setEvent_id((int)Float.parseFloat(obj.get("ev").toString()));
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setRating((int)Float.parseFloat(obj.get("rating").toString()));
                    feedbacks.add(t);
            }
                    
        } catch (IOException ex) {
            
        }
        return feedbacks;
    } 
    
    public ArrayList<Feedback> getAllFeedback(){
        String url = Statics.BASE_URL2 +"fed/fed/Api";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                feedbacks = parseFeed(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return feedbacks;
    }

    
    
}
