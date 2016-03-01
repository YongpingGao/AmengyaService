/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amengya.db;

import com.amengya.model.ThingInfoMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author amazin
 */
public class DBConnection {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://50.62.166.22/gaodweets";
    
    private final String USER = "entplanner";
    private final String PASS = "test";
    
    private Connection connection;
    private Statement statement;
    
    private Gson gson;
    
    public DBConnection(){
         gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public boolean addNewThingInfo(ThingInfoMap info){
        getDBConnection();
        String sql = "INSERT INTO dweets (ThingName, TimeStamp, Data) " +
                 "VALUES ('" + info.getThing() + "','"+info.getCreated()+"','" + gson.toJson(info.getContent())+"');";
      
        try {
            statement = connection.createStatement();          
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
     public ThingInfoMap getLatestDweet(String thingName){
        
        getDBConnection();
        String sql = "SELECT * FROM dweets WHERE d_id=(SELECT MAX(d_id) FROM dweets where thingname='"+thingName+"');"; 
        try {
            statement = connection.createStatement();               
            ResultSet results = statement.executeQuery(sql);
           
           results.next();
         
           ThingInfoMap thingInfo = new ThingInfoMap();
           
           thingInfo.setThing(results.getString("ThingName"));
           thingInfo.setCreated(results.getString("TimeStamp"));
           thingInfo.setContent(gson.fromJson(results.getString("Data"), HashMap.class));
           
           results.close();
           statement.close();
           connection.close();
           return thingInfo;
           
       } catch(SQLException ex) {
            ex.printStackTrace();
       }
        
        return null;
    }
     
     
     public int addAlarm(ThingInfoMap infoMap){
        getDBConnection();
        String thingName = infoMap.getThing();
        String timeStamp = infoMap.getCreated();
        String status = infoMap.getStatus();
        String data = gson.toJson(infoMap.getContent());
        
        String sql = "INSERT INTO alarms (ThingName, TimeStamp, Data, Status) " +
                 "VALUES ('" + thingName + "','"+timeStamp+"','" + data + "','"+status+"');";
       try {
           statement = connection.createStatement();            
           statement.executeUpdate(sql);
           //return the inserted item id
           sql = "SELECT a_id FROM alarms ORDER BY a_id DESC LIMIT 1;";
           ResultSet res = statement.executeQuery(sql);
           res.next();
           int r = res.getInt("a_id");
           
           res.close();
           statement.close();
           connection.close();
           return r;
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
        return -1;
    }
     
     
    public int addCommand(ThingInfoMap infoMap){
        getDBConnection();
        String command = gson.toJson(infoMap.getCommand());
        String sql = "INSERT INTO commands (ThingName, TimeStamp, Data, Status) " +
                 "VALUES ('" + infoMap.getThing() + "','"+infoMap.getCreated()+"','" + command + "','"+infoMap.getStatus()+"');";
        try {
           statement = connection.createStatement();            
           statement.executeUpdate(sql);
           
           sql = "SELECT c_id FROM commands ORDER BY c_id DESC LIMIT 1;";
           ResultSet res = statement.executeQuery(sql);
           res.next();
           int r = res.getInt("c_id");
           
           res.close();
           statement.close();
           connection.close();
           return r;
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
        return -1;
    }
    
    
    
   
    
    
    private void getDBConnection(){
        try{
           Class.forName(JDBC_DRIVER);
           System.out.println("Connecting to database...");
           connection = DriverManager.getConnection(DB_URL,USER,PASS);
           
        } catch (SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch (Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        }
    }
    
    

    
    
}
