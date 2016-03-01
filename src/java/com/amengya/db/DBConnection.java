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
    private final String DB_URL = "jdbc:mysql://localhost:3306/GaoDweets";
    
    private final String USER = "root";
    private final String PASS = "";
    
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
    
    
    
    
    private void excuteUpdateSQL(String sql) throws SQLException {
        getDBConnection();
        statement = connection.createStatement();               
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
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
