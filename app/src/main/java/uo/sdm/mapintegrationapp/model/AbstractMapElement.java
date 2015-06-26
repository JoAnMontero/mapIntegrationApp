package uo.sdm.mapintegrationapp.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Hans on 23/06/2015.
 */
@Deprecated
public abstract class AbstractMapElement {
    private GoogleMap gameMap = null;
    private LatLng latLng = null;
    private Marker marker = null;

    protected AbstractMapElement(GoogleMap gameMap, LatLng latLng) {
        if (gameMap == null)
            throw new IllegalArgumentException("The GoogleMap received is not initialized to a valid value.");
        if (latLng == null)
            throw new IllegalArgumentException("The LatLng received is not initialized to a valid value.");
        this.gameMap = gameMap;
        this.latLng = latLng;
    }

    public void addToMap() {
        MarkerOptions markerOptions = createMarkerOptionsOnAddToMap();
        if(markerOptions == null)
            throw  new IllegalArgumentException("The MarkerOptions received is not initializad to a valid value.");

        marker = gameMap.addMarker(markerOptions);
    }

    protected abstract MarkerOptions createMarkerOptionsOnAddToMap();

    public void removeFromMap() {
        if (marker != null)
            marker.remove();
    }

    protected LatLng getLatLng() {
        return latLng;
    }
}
