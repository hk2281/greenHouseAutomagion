package com.example.greenhouseautomagion;

import com.google.firebase.Timestamp;
import com.google.type.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Place {
    private String placeName;
    private String startTime;
    private ArrayList<Integer> activeDays = new ArrayList<Integer>();
    private Boolean blinding;
    private Boolean ventilation;
    private Integer watterVolume;

    private Timestamp creationTime;
    private String id;

    Place() {
        //mast be empty for serialisation
    }

    Place(String placeName, String startTime, ArrayList<Integer> activeDays, Boolean blinding,
          Boolean ventilation, Integer watterVolume, Timestamp creationTime){
        this.placeName = placeName;
        this.activeDays = activeDays;
        this.startTime = startTime;
        this.blinding = blinding;
        this.ventilation = ventilation;
        this.watterVolume = watterVolume;
        this.creationTime = creationTime;
    }
    Place(String placeName, String startTime, ArrayList<Integer> activeDays, Boolean blinding,
          Boolean ventilation, Integer watterVolume){
        this.placeName = placeName;
        this.activeDays = activeDays;
        this.startTime = startTime;
        this.blinding = blinding;
        this.ventilation = ventilation;
        this.watterVolume = watterVolume;
    }

    Place(String placeName, String startTime,Integer watterVolume){
        this.placeName = placeName;
        this.startTime = startTime;
        this.watterVolume = watterVolume;

    }

    public void setPlaceName(String placeName){
        this.placeName = placeName;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public void setActiveDays(ArrayList<Integer> activeDays){
        this.activeDays = activeDays;
    }
    public void setblinding(Boolean blinding){
        this.blinding = blinding;
    }
    public void setventilation(boolean ventilation){
        this.ventilation = ventilation;
    }
    public void setWatterVolume(Integer watterVolume){
        this.watterVolume = watterVolume;
    }
    public void setCreationTime(Timestamp creationTime){this.creationTime = creationTime;}
    public void setId(String id){this.id = id;}
    public String getPlaceName(){
        return this.placeName;
    }
    public String getId(){return this.id;}
    public String getStartTime(){return this.startTime;}
    public ArrayList<Integer> getActiveDays(){
        return this.activeDays;
    }
    public boolean isVentilation(){
        return this.ventilation;
    }
    public boolean isBlinding(){
        return this.blinding;
    }
    public Integer getWatterVolume(){
        return this.watterVolume;
    }
    public Timestamp getCreationTime(){return this.creationTime;}
}
