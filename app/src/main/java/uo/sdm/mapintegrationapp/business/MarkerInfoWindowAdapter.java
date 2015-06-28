package uo.sdm.mapintegrationapp.business;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private final Activity activity;

    public MarkerInfoWindowAdapter(Activity activity, LayoutInflater inflater, MarkerCollection markerCollection) {
        this.activity = activity;
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

                infoView = inflater.inflate(R.layout.infowindow_places, (LinearLayout)activity.findViewById(R.id.info_window_places));
                TextView label = (TextView) infoView.findViewById(R.id.label);
                label.setText(place.getType().toString());

                TextView infoLabel = (TextView) infoView.findViewById(R.id.info_label);
                if (!place.isResearching())
                    infoLabel.setText(activity.getString(R.string.msg_far_away));
                else {
                    if (place.getTimeLeft() > 0)
                        infoLabel.setText(activity.getString(R.string.msg_research_in_progress) + place.getFormattedTimeLeft());
                    else {
                        infoLabel.setText(activity.getString(R.string.msg_research_completed));
                        TextView infoLabel2 = (TextView) infoView.findViewById(R.id.info_label2);
                        infoLabel2.setText(activity.getString(R.string.msg_get_closer));

                    }
                }
                break;
            case Camp:
                break;
        }
        return infoView;
    }
}
