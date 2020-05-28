/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Memmicha
 */
public class TwilioSms {
     public static final String ACCOUNT_SID = "AC93e113f18e7e520545dc90a84f0b0952";
    public static final String AUTH_TOKEN = "418bc7a0603e6a5c1be1ff7f533a2aee";

    public void sendSms(String body) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(new PhoneNumber("+21620996624"),
        new PhoneNumber("+12013792504"), 
        body).create();
    }
}
