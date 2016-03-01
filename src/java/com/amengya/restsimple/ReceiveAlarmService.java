/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amengya.restsimple;

import com.amengya.model.Dweet;
import com.amengya.model.ThingInfoMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


/**
 * REST Web Service
 *
 * @author amazin
 */
@Path("alarm")
public class ReceiveAlarmService {

    private Gson gson;
 
    private final String DEVICE_TOKEN = "fzjb7JXd7kk:APA91bGn_n_CNx_zZUkv1Dz_gjrFJy265S54n_DDfU82J2zfdMBIs-3GNCx4oQmixiob24zw0IoKxG8DI5EloUCZlzgvg8gJdXFfpCMZAIYP_T69P66oX0Mzx23jubxIcpU6DPRHLgsm";
    private final String API_KEY = "AIzaSyD7NLBsfJ0r9vB3bbRpZR1uGXkhf4tSd48";
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReceiveAlarmService
     */
    public ReceiveAlarmService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of com.amengya.restsimple.ReceiveAlarmService
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{thingName}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public String recieveAlarm(@PathParam("thingName") String thingName, 
            @PathParam("status") String status,@Context UriInfo info) {

        MultivaluedMap queryMap = info.getQueryParameters();
        Iterator itr = queryMap.keySet().iterator();
        HashMap<String, String> dataMap = new HashMap<>();     
        while(itr.hasNext()){
            Object key = itr.next();
            Object value = queryMap.getFirst(key);
            dataMap.put(key.toString(), value.toString());
        }
        ThingInfoMap thingInfoMap = new ThingInfoMap(thingName, new Timestamp(new Date().getTime()).toString(),status);
        thingInfoMap.setContent(dataMap);

        Dweet newDweet = new Dweet("succeeded", "sending", "alarm", thingInfoMap);
        newDweet.setTo("myMobileA");
        
        
        // TODO: send alarm to mobile use GCM
        String warningMsg = gson.toJson(newDweet);
        sendToMobileByGCM(warningMsg);
        
        return warningMsg;
        

    }

    /**
     * PUT method for updating or creating an instance of ReceiveAlarmService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
  
    private void sendToMobileByGCM(String data) {
        try{
             JsonObject jGcmData = new JsonObject();    
             JsonObject jData = new JsonObject();
             jData.addProperty("message", data);
             
             jGcmData.addProperty("to", DEVICE_TOKEN);
             jGcmData.add("data", jData);
             

        // Create connection to send GCM Message request.
            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());
            conn.getInputStream();
            
        } catch (IOException e) {
            System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
        }
        

    }
}
