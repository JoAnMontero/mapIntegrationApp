package uo.sdm.mapintegrationapp.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Hans on 23/06/2015.
 */
public class RuinMapElement extends AbstractMapElement {
    protected RuinMapElement(GoogleMap gameMap, LatLng latLng) {
        super(gameMap, latLng);
    }

    @Override
    protected MarkerOptions createMarkerOptionsOnAddToMap() {
        return null;
    }
}
