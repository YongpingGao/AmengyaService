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
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * REST Web Service
 *
 * @author amazin
 */
@Path("/")
public class DweetResource {

    private Gson gson;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DweetResource
     */
    public DweetResource() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of com.amengya.restsimple.DweetResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("dweet/for/{thingName}")  
    @Produces(MediaType.APPLICATION_JSON)
    public String createDweet(@Context UriInfo info) {
        // if thingName is in the database:
       
        List segments = info.getPathSegments();
        MultivaluedMap queryMap = info.getQueryParameters();
        
        String thingName = segments.get(segments.size()-1).toString();
        Iterator itr = queryMap.keySet().iterator();
        HashMap<String, String> dataMap = new HashMap<>();
      
        
        while(itr.hasNext()){
            Object key = itr.next();
            Object value = queryMap.getFirst(key);
            dataMap.put(key.toString(), value.toString());
        }
       
        ThingInfoMap thingInfoMap = new ThingInfoMap(thingName, new Timestamp(new Date().getTime()).toString());
        thingInfoMap.setContent(dataMap);
        Dweet newDweet = new Dweet("succeeded", "dweeting", "dweet", thingInfoMap);
        
        
        
        // else return false new Dweet("failed", "can't find your thing in database");
        
        return gson.toJson(newDweet);
    }

    /**
     * PUT method for updating or creating an instance of DweetResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
   
    
    
    
    
    
    
}
