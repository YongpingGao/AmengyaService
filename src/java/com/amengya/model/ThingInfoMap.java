/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amengya.model;

 
import java.util.HashMap;

 
 

/**
 *
 * @author amazin
 */
public class ThingInfoMap {
    
 
    private String thing;
    private String created;
    private String status;
    private HashMap<String, String> content;
    private HashMap<String, String> recieve;
    
    private HashMap<String, String> command;
    
    
    
    
    public ThingInfoMap(){}

    // new dweet
    public ThingInfoMap(String thing, String created){
        this.thing = thing;
        this.created = created;
    }
    
    //alarm
    public ThingInfoMap(String thing, String created,  String status){
        this(thing, created);
        this.status = status;
    }
    
    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
   

    public void setCommandMap(HashMap<String, String> commandMap) {
        this.command = commandMap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, String> getContent() {
        return content;
    }

    public void setContent(HashMap<String, String> content) {
        this.content = content;
    }

    public HashMap<String, String> getCommand() {
        return command;
    }

    public void setCommand(HashMap<String, String> command) {
        this.command = command;
    }
    
    
    
      
     
    
}
