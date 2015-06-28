package uo.sdm.mapintegrationapp.model;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.TimeUnit;

import uo.sdm.mapintegrationapp.business.MapManager;
import uo.sdm.mapintegrationapp.model.types.MapElementType;
import uo.sdm.mapintegrationapp.model.types.PlaceType;
import uo.sdm.mapintegrationapp.persistence.PlacesDataSource;

/**
 * Created by Hans on 23/06/2015.
 */
public class PlaceMapElement {
    private GoogleMap gameMap = null;
    private Marker marker = null;
    Place place = null;
    Location characterLocation;
    boolean inRange = false;

    public long getId() {
        return place.getId();
    }

    public String getMarkerId() {
        return marker.getId();
    }

    public LatLng getLatLng() {
        return place.getLatLng();
    }

    public Location getLocation() {
        return place.getLocation();
    }

    public PlaceType getType() {
        return place.getType();
    }

    public boolean isResearching() {
        return place.isResearching();
    }

    public long getResearchEnd() {
        return place.getResearch_end();
    }

    public boolean isInRange() {
        return inRange;
    }

    public void setCharacterLocation(Location location) {
        this.characterLocation = location;
        inRange = (place.distanceTo(characterLocation) < MapManager.maxInteractionDistance) ? true : false;
    }

    public long getTimeLeft() {
        return (getResearchEnd() - System.currentTimeMillis());
    }

    public String getFormattedTimeLeft() {
        long millisLeft = getTimeLeft();
        return String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(millisLeft),
                TimeUnit.MILLISECONDS.toSeconds(millisLeft) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisLeft)));

    }

    public PlaceMapElement(GoogleMap gameMap, Place place, Location characterLocation) {
        if (gameMap == null)
            throw new IllegalArgumentException("The GoogleMap received is not initialized to a valid value.");
        if (place == null)
            throw new IllegalArgumentException("The Place received is not initialized to a valid value.");
        if (characterLocation == null)
            throw new IllegalArgumentException("The Location received is not initialized to a valid value.");
        this.gameMap = gameMap;
        this.place = place;
        setCharacterLocation(characterLocation);
    }

    public void addToMap() {
        MarkerOptions markerOptions = createMarkerOptions();
        marker = gameMap.addMarker(markerOptions);
    }

    public void removeFromMap() {
        if (marker != null)
            marker.remove();
    }

    private MarkerOptions createMarkerOptions() {
        int resource_id = getResourceId();
        return new MarkerOptions()
                .position(getLatLng())
                .title(MapElementType.Place.toString())
                .snippet(place.getId() + "")
                .icon(BitmapDescriptorFactory.fromResource(resource_id));
    }

    private int getResourceId() {
        // TODO maybe: add resources for researched places.
        // Selects the highlighted icon for places that are close to the character
        // and the grey icon for places that are too far.
        return (isInRange()) ? place.getType().getResource_id() : place.getType().getGreyResource_id();
    }

    public void updateIcon() {
        marker.setIcon(BitmapDescriptorFactory.fromResource(getResourceId()));
    }

    public void startResearching(Context context) {
        place.setResearching(true);
        place.setResearch_end(System.currentTimeMillis() + MapManager.researchTimeInMillis);

        PlacesDataSource dataSource = new PlacesDataSource(context);
        dataSource.open();
        dataSource.update(place);
        dataSource.close();
    }

    public void setAsResearched(Context context) {
        place.setResearching(false);
        place.setResearch_end(0);

        PlacesDataSource dataSource = new PlacesDataSource(context);
        dataSource.open();
        dataSource.update(place);
        dataSource.close();
    }
}
