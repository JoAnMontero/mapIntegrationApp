package uo.sdm.mapintegrationapp.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Hans on 24/06/2015.
 */
public class CampMapElement {
    protected CampMapElement(GoogleMap gameMap, LatLng latLng) {

    }

    protected MarkerOptions createMarkerOptionsOnAddToMap() {
        return null;
    }
}
