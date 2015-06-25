package uo.sdm.mapintegrationapp.business;

import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 24/06/2015.
 */
public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final LayoutInflater inflater;

    public MarkerInfoWindowAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoView = null;
        switch (MapElementType.valueOf(marker.getTitle())) {
            case Character:
                infoView = inflater.inflate(R.layout.research_info_window, null);
                break;
            case Ruin:
                break;
            case Camp:
                break;
        }
        return infoView;
    }
}
