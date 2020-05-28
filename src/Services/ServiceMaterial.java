/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.material;
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
 * @author Memmicha
 */
public class ServiceMaterial {
     public ArrayList<material> materials;
    
    public static ServiceMaterial instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceMaterial() {
         req = new ConnectionRequest();
    }

    public static ServiceMaterial getInstance() {
        if (instance == null) {
            instance = new ServiceMaterial();
        }
        return instance;
    }
  public boolean addMaterial(material t) {
        String url = Statics.BASE_URL + "/material/newapi/" + t.getName_address() + "/" + t.getAddress()+ "/" + t.getDescription() ;
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
    public ArrayList<material> parseMaterial(String jsonText){
        try {
            materials =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for (Map<String,Object> obj : list){
                System.out.println(obj);
                material t = new material();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
             //   t.setId_address((int)Float.parseFloat(obj.get("idAddress").toString()));
                t.setName_address(obj.get("nameAddress").toString());
                t.setAddress(obj.get("address").toString());
                t.setDescription(obj.get("description").toString());
                materials.add(t);
            } 
        } catch (IOException ex) {
            
        }
        return materials;
    } 
    
    public ArrayList<material> getAllMaterial(){
        String url = Statics.BASE_URL +"/material/api";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                materials = parseMaterial(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return materials;
    }
}
