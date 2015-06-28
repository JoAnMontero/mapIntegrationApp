package uo.sdm.mapintegrationapp.model;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.business.MapManager;
import uo.sdm.mapintegrationapp.conf.GameParams;
import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 23/06/2015.
 */
public class CharacterMapElement {

    private GoogleMap gameMap = null;
    private Location location = null;
    private Marker marker = null;
    private Circle circle;

    public LatLng getLatLng() {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public Location getLocation() {
        return location;
    }

    public CharacterMapElement(GoogleMap gameMap, Location location) {
        if (gameMap == null)
            throw new IllegalArgumentException("The GoogleMap received is not initialized to a valid value.");
        if (location == null)
            throw new IllegalArgumentException("The LatLng received is not initialized to a valid value.");
        this.gameMap = gameMap;
        this.location = location;
    }

    public void addToMap() {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(getLatLng())
                .title(MapElementType.Character.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.character));
        marker = gameMap.addMarker(markerOptions);
        circle = gameMap.addCircle(new CircleOptions()
                .center(getLatLng())
                .radius(MapManager.maxInteractionDistance).strokeColor(Color.CYAN));
    }

    public void removeFromMap() {
        if (marker != null) {
            marker.remove();
            marker = null;
        }
    }

    public void moveTo(Location location) {
        if(location == null)
            throw new IllegalArgumentException("The LatLng received is not initialized to a valid value.");
        this.location = location;
        if(marker != null) {
            marker.setPosition(getLatLng());
        }
        if (circle != null) {
            circle.setCenter(getLatLng());
        }
    }
}
