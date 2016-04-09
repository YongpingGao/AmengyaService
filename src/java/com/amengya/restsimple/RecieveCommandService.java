/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amengya.restsimple;

import com.amengya.db.DBConnection;
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
@Path("command")
public class RecieveCommandService {

    private Gson gson;
    private DBConnection database;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CommandService
     */
    public RecieveCommandService() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        database = new DBConnection();
    }

    /**
     * Retrieves representation of an instance of com.amengya.restsimple.CommandService
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{thingName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendCommand(@PathParam("thingName") String thingName, 
            @Context UriInfo info) {
       
        String status = "readytosend";
        MultivaluedMap queryMap = info.getQueryParameters();
        Iterator itr = queryMap.keySet().iterator();
        HashMap<String, String> dataMap = new HashMap<>();     
        while(itr.hasNext()){
            Object key = itr.next();
            Object value = queryMap.getFirst(key);
            dataMap.put(key.toString(), value.toString());
        }
        ThingInfoMap thingInfoMap = new ThingInfoMap(thingName, new Timestamp(new Date().getTime()).toString(),status);
        thingInfoMap.setCommand(dataMap);
       
        Dweet newDweet;
        
        int c_id = database.addCommand(thingInfoMap);
        if(c_id != -1){
            thingInfoMap.setCommandID(c_id);
            newDweet = new Dweet("succeeded", "sending", "command", thingInfoMap);
            newDweet.setFrom("myMobileA");
        } else {
            newDweet = new Dweet("failed", "failed to send command");
        }
        return gson.toJson(newDweet);
    }

    /**
     * PUT method for updating or creating an instance of CommandService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
