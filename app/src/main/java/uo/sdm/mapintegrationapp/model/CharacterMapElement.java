package uo.sdm.mapintegrationapp.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 23/06/2015.
 */
public class CharacterMapElement extends AbstractMapElement {

    public CharacterMapElement(GoogleMap gameMap, LatLng latLng) {
        super(gameMap, latLng);
    }

    @Override
    protected MarkerOptions createMarkerOptionsOnAddToMap() {
        return new MarkerOptions()
                .position(getLatLng())
                .title(MapElementType.Character.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marche));
    }
}
