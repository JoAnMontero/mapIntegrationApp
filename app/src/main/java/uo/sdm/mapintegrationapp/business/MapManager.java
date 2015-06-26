package uo.sdm.mapintegrationapp.business;


import android.app.Activity;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import uo.sdm.mapintegrationapp.persistence.PlacesDataSource;

/**
 * Manager que extiende la funcionalidad del mapa y se encarga de gestionarlo.
 * <p/>
 * Created by Hans on 27/12/2014.
 */
public class MapManager {
    private static final String LOG_TAG = "MapManager";

    private GoogleMap gameMap;
    private Activity activity;

    private MarkerCollection markerCollection;

    public MapManager(Activity activity, GoogleMap gameMap) {
        this.activity = activity;
        this.gameMap = gameMap;
        this.markerCollection =  new MarkerCollection(gameMap);
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

        // Update the character location
        markerCollection.moveCharacterTp(location);

        // Load the places already discovered from the database
        PlacesDataSource dataSource = new PlacesDataSource(activity);
        dataSource.open();
        markerCollection.refreshPlaces(dataSource.getAllPlaces());
        dataSource.close();

        // TODO Find new places in case there's no nearby ones, load em into the map and into the database

        // Move the camera to the player location
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to LatLng (refer to previous snippet)
                .zoom(19)            // Sets the zoom. More than15.5 allows maximum tilt.
                .bearing(0)          // Sets the orientation of the camera to north
                .tilt(67.5f)         // Sets the tilt of the camera to 67.5f degrees
                .build();            // Creates a CameraPosition from the builder
        gameMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
