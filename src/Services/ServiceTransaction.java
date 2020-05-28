/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.transaction;
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
import java.util.Date;
import java.util.Map;


/**
 *
 * @author Memmicha
 */
public class ServiceTransaction {
     public ArrayList<transaction> transactions;
    
    public static ServiceTransaction instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceTransaction() {
         req = new ConnectionRequest();
    }

    public static ServiceTransaction getInstance() {
        if (instance == null) {
            instance = new ServiceTransaction();
        }
        return instance;
    }
 
    public boolean addTransaction(transaction t) {
        String url = Statics.BASE_URL + "/transaction/newapi/"+ t.getName() + "/" + t.getLast_Name()+ "/" + t.getRIB()+  "/" +t.getDate_Expiration()+"/"+t.getMontant()+ "/" + t.getAddress() ;
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

   public ArrayList<transaction> parseTransaction(String jsonText){
        try {
            transactions =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List <Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for (Map<String,Object> obj : list){
         
                transaction t = new transaction();
                float id = Float.parseFloat(obj.get("id").toString());
               t.setId((int)id);
              // t.setId_user((int)Float.parseFloat(obj.get("idUser").toString()));
               t.setName(obj.get("name").toString());
               t.setLast_Name(obj.get("lastName").toString());
               t.setRIB(obj.get("rIB").toString());
               Map<String, Object> date1 = (Map) obj.get("dateExpiration");
                
              long dateTimeStamp = (long) (Double.parseDouble(date1.get("timestamp").toString())*1000);
              Date d2 = new Date(dateTimeStamp);
              t.setDate_Expiration(d2);
              
               t.setMontant(Float.parseFloat(obj.get("montant").toString()));
               t.setAddress(obj.get("address").toString());
                transactions.add(t);
            } 
        } catch (IOException ex) {
            
        }
        return transactions;
    } 
    
    public ArrayList<transaction> getTransaction(){
        String url = Statics.BASE_URL +"/transaction/api";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                transactions = parseTransaction(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return transactions;
    }

   
}
