package uo.sdm.mapintegrationapp.business;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.model.Place;
import uo.sdm.mapintegrationapp.model.PlaceMapElement;
import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 24/06/2015.
 */
public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final LayoutInflater inflater;
    private final MarkerCollection markerCollection;

    public MarkerInfoWindowAdapter(LayoutInflater inflater, MarkerCollection markerCollection) {
        this.inflater = inflater;
        this.markerCollection = markerCollection;
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
                break;
            case Place:
                PlaceMapElement place = markerCollection.findPlaceById(Long.parseLong(marker.getSnippet()));

                infoView = inflater.inflate(R.layout.infowindow_places, null);
                TextView label = (TextView) infoView.findViewById(R.id.label);
                label.setText(place.getType().toString());

                TextView infoLabel = (TextView) infoView.findViewById(R.id.info_label);
                if (!place.isResearching())
                    infoLabel.setText("This is too far away!!");
                else {
                    if (place.getTimeLeft() > 0)
                        infoLabel.setText("Research in progress: " + place.getFormattedTimeLeft());
                    else {
                        infoLabel.setText("Research Completed!!");
                        TextView infoLabel2 = (TextView) infoView.findViewById(R.id.info_label2);
                        infoLabel2.setText("Get closer to claim your reward.");

                    }
                }
                break;
            case Camp:
                break;
        }
        return infoView;
    }
}
