package uo.sdm.mapintegrationapp.business;


import android.app.Activity;
import android.location.Location;
import android.view.View;
import android.widget.PopupWindow;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.model.CharacterMapElement;
import uo.sdm.mapintegrationapp.model.Ruin;
import uo.sdm.mapintegrationapp.model.RuinMapElement;
import uo.sdm.mapintegrationapp.persistence.RuinsDataSource;

/**
 * Manager que extiende la funcionalidad del mapa y se encarga de gestionarlo.
 * <p/>
 * Created by Hans on 27/12/2014.
 */
public class MapManager {
    private GoogleMap gameMap;
    private Activity activity;

    private CharacterMapElement character = null;
    private List<RuinMapElement> ruins = new ArrayList<RuinMapElement>();

    public MapManager(Activity activity, GoogleMap gameMap) {
        this.activity = activity;
        this.gameMap = gameMap;
    }

    public void setGameMap(GoogleMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setUpMap() {
        gameMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Setting up the listener that shows popups on marker click
        gameMap.setOnMarkerClickListener(new CustomOnMarkerClickListener(activity.getLayoutInflater(), gameMap, activity));

        // Setting up an adapter to show custom InfoWindows on marker click
        gameMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(activity.getLayoutInflater()));
    }

    public void handleNewLocation(Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);


        // Add to the map the cursor that represents the player, using the current location.
        if (character != null)
            character.removeFromMap();
        character = new CharacterMapElement(gameMap, latLng);
        character.addToMap();

        // Load the ruins already discovered from the database
        RuinsDataSource dataSource = new RuinsDataSource(activity);
        dataSource.open();
        for(Ruin ruin: dataSource.getAllRuins()) {
            RuinMapElement marker = new RuinMapElement(gameMap, ruin);
            ruins.add(marker);
            marker.addToMap();
        }
        dataSource.close();

        // TODO Find new ruins in case there's no nearby ones, load em into the map and into the database

        // Move the camera to the player location
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to LatLng (refer to previous snippet)
                .zoom(18)            // Sets the zoom. More than15.5 allows maximum tilt.
                .bearing(0)          // Sets the orientation of the camera to north
                .tilt(67.5f)         // Sets the tilt of the camera to 67.5f degrees
                .build();            // Creates a CameraPosition from the builder
        gameMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
