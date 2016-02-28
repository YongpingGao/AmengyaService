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
        
        return gson.toJson(newDweet);
        
        
    }

    /**
     * PUT method for updating or creating an instance of ReceiveAlarmService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
