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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author amazin
 */
@Path("get/latest/dweet")
public class GetLatestDweetResource {

    private Gson gson;
    
    @Context
    private UriInfo context;
    
    private  DBConnection database;

    /**
     * Creates a new instance of GetLatestDweetResource
     */
    public GetLatestDweetResource() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        database = new DBConnection();
    }

    /**
     * Retrieves representation of an instance of com.amengya.restsimple.GetLatestDweetResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("for/{thingName}" )
    @Produces(MediaType.APPLICATION_JSON)
    public String getLatestDweet(@PathParam("thingName") String thingName) {
        ThingInfoMap info = database.getLatestDweet(thingName);
        Dweet newDweet;
        if(info != null){
             newDweet = new Dweet("succeeded", "getting", "dweets", info);
        } else{
             newDweet = new Dweet("failed", "cannot find a thing name: " + thingName);
        }
        
        return gson.toJson(newDweet);
        
    }

    /**
     * PUT method for updating or creating an instance of GetLatestDweetResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
