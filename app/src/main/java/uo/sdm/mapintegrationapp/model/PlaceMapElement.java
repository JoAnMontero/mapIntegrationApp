package uo.sdm.mapintegrationapp.model;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 23/06/2015.
 */
public class PlaceMapElement {
    private GoogleMap gameMap = null;
    private Marker marker = null;
    Place place = null;
    Location characterLocation;

    public LatLng getLatLng() {
        return place.getLatLng();
    }

    public Location getLocation() {
        return place.getLocation();
    }

    public void setCharacterLocation(Location location) {
        this.characterLocation = location;
    }

    public PlaceMapElement(GoogleMap gameMap, Place place, Location characterLocation) {
        if (gameMap == null)
            throw new IllegalArgumentException("The GoogleMap received is not initialized to a valid value.");
        if (place == null)
            throw new IllegalArgumentException("The Place received is not initialized to a valid value.");
        if (characterLocation == null)
            throw new IllegalArgumentException("The Location received is not initialized to a valid value.");
        this.gameMap = gameMap;
        this.place = place;
        this.characterLocation = characterLocation;
    }

    public void addToMap() {
        MarkerOptions markerOptions = createMarkerOptions();
        marker = gameMap.addMarker(markerOptions);
    }

    public void removeFromMap() {
        if (marker != null)
            marker.remove();
    }

    private MarkerOptions createMarkerOptions() {
        int resource_id = getResourceId();
        return new MarkerOptions()
                .position(getLatLng())
                .title(MapElementType.Place.toString())
                .snippet(place.getType().toString())
                .icon(BitmapDescriptorFactory.fromResource(resource_id));
    }

    private int getResourceId() {
        // TODO maybe: add resources for researched places.
        // Selects the highlighted icon for places that are close to the character
        // and the grey icon for places that are too far.
        return (place.distanceTo(characterLocation) < 250) ?
                place.getType().getResource_id() : place.getType().getGreyResource_id();
    }

    public void updateIcon() {
        marker.setIcon(BitmapDescriptorFactory.fromResource(getResourceId()));
    }
}
