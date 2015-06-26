package uo.sdm.mapintegrationapp.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import uo.sdm.mapintegrationapp.model.types.PlaceType;

/**
 * Created by Hans on 25/06/2015.
 */
public class Place {
    private long id;
    private Location location;
    private PlaceType type;
    private boolean researching;
    private long research_end;

    public long getId() {
        return id;
    }

    public double getLat() {
        return location.getLatitude();
    }

    public double getLng() {
        return location.getLongitude();
    }

    public PlaceType getType() {
        return type;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }

    public boolean isResearching() {
        return researching;
    }

    public void setResearching(boolean researching) {
        this.researching = researching;
    }

    public long getResearch_end() {
        return research_end;
    }

    public void setResearch_end(long research_end) {
        this.research_end = research_end;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LatLng getLatLng() {
        return new LatLng(getLat(), getLng());
    }

    public Place(long id) {
        this.id = id;
    }

    public Place(long id, Location location, PlaceType type,boolean researching, long research_end) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.researching = researching;
        this.research_end = research_end;
    }

    public float distanceTo(Location location) {
        return this.location.distanceTo(location);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", location=" + location +
                ", type=" + type +
                ", researching=" + researching +
                ", research_end=" + research_end +
                '}';
    }
}
