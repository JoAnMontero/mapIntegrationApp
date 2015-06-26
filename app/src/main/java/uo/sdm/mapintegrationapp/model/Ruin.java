package uo.sdm.mapintegrationapp.model;

import uo.sdm.mapintegrationapp.model.types.RuinType;

/**
 * Created by Hans on 25/06/2015.
 */
public class Ruin {
    private long id;
    private double lat;
    private double lng;
    private RuinType type;
    private long research_end;

    public long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public RuinType getType() {
        return type;
    }

    public void setType(RuinType type) {
        this.type = type;
    }

    public long getResearch_end() {
        return research_end;
    }

    public void setResearch_end(long research_end) {
        this.research_end = research_end;
    }

    public Ruin(long id) {
        this.id = id;
    }

    public Ruin(long id, double lat, double lng, RuinType type, long research_end) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.research_end = research_end;
    }
}
