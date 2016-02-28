/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amengya.model;
 
 

/**
 *
 * @author amazin
 */
public class Dweet {
    
    private String _this;
    private String by;
    private String the;
    private String to;
    private String from;
    private ThingInfoMap with;
    
    
    private String because;
    
    public Dweet(){}
    
    public Dweet(String _this, String _by, String _the, ThingInfoMap _with){
        this._this = _this;
        this.by = _by;
        this.the = _the;
        this.with = _with;
    }

    
  
    
    public Dweet(String _this, String _because){
         this._this = _this;
         this.because = _because;
    }

    public String getThis() {
        return _this;
    }

    public void setThis(String _this) {
        this._this = _this;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String _by) {
        this.by = _by;
    }

    public String getThe() {
        return the;
    }

    public void setThe(String _the) {
        this.the = _the;
    }

    public ThingInfoMap getWith() {
        return with;
    }

    public void setWith(ThingInfoMap _with) {
        this.with = _with;
    }

    public String getBecause() {
        return because;
    }

    public void setBecause(String _because) {
        this.because = _because;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
    
    
    
    
    
    
    
    
}
