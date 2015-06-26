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
public class RuinMapElement extends AbstractMapElement {

    Ruin ruin = null;

    public RuinMapElement(GoogleMap gameMap, Ruin ruin) {
        super(gameMap, new LatLng(ruin.getLat(), ruin.getLng()));
        this.ruin = ruin;
    }


    @Override
    protected MarkerOptions createMarkerOptionsOnAddToMap() {
        // TODO change resource
        // TODO maybe: add different resources for different marker states.
        return new MarkerOptions()
                .position(getLatLng())
                .title(MapElementType.Ruin.toString())
                .snippet(ruin.getType().toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marche));
    }
}
