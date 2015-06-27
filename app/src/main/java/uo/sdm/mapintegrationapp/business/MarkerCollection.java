package uo.sdm.mapintegrationapp.business;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.model.CharacterMapElement;
import uo.sdm.mapintegrationapp.model.Place;
import uo.sdm.mapintegrationapp.model.PlaceMapElement;
import uo.sdm.mapintegrationapp.model.types.PlaceType;
import uo.sdm.mapintegrationapp.persistence.PlacesDataSource;

/**
 * Created by Hans on 26/06/2015.
 */
public class MarkerCollection {
    private Context context;
    private final GoogleMap gameMap;

    private CharacterMapElement character = null;
    private List<PlaceMapElement> placeMapElements = new ArrayList<PlaceMapElement>();

    private int placesInRange = 0;

    public int getPlacesInRange() {
        return placesInRange;
    }

    public MarkerCollection(Context context, GoogleMap gameMap) {
        this.context = context;
        this.gameMap = gameMap;
    }

    public void moveCharacterTp(Location newLocation) {
        if (character != null)
            character.moveTo(newLocation);
        else {
            character = new CharacterMapElement(gameMap, newLocation);
            character.addToMap();
        }

        updatePlaces();
    }

    public Location getCharacterLocation() {
        return character.getLocation();
    }

    public void refreshPlaces() {
        PlacesDataSource dataSource = new PlacesDataSource(context);
        dataSource.open();
        List<Place> places = dataSource.getAllPlaces();
        dataSource.close();

        placesInRange = 0;
        placeMapElements = new ArrayList<PlaceMapElement>();

        for (Place place : places) {
            PlaceMapElement marker = new PlaceMapElement(gameMap, place, getCharacterLocation());
            placeMapElements.add(marker);
            marker.addToMap();
            if (marker.isInRange())
                placesInRange++;
        }
    }

    private void updatePlaces() {
        placesInRange = 0;
        for (PlaceMapElement place : placeMapElements) {
            place.setCharacterLocation(character.getLocation());
            place.updateIcon();
            if (place.isInRange())
                placesInRange++;
        }
    }

    public void generatePlacesInRange(int generatedPlaces) {
        while (generatedPlaces > 0) {
            int latOffset = (int) ((Math.random() * 4001) - 2001);
            int lngOffset = (int) ((Math.random() * 4001) - 2001);
            Location location = new Location(getCharacterLocation());
            location.setLatitude(location.getLatitude() + (latOffset * 0.000001));
            location.setLongitude(location.getLongitude() + (lngOffset * 0.000001));

            float distance = location.distanceTo(getCharacterLocation());
            if (50 < distance && distance < 250) {
                PlacesDataSource dataSource = new PlacesDataSource(context);
                dataSource.open();
                Place place = dataSource.createPlace(location.getLatitude(), location.getLongitude(), PlaceType.randomPlaceType(), false, 0);
                dataSource.close();

                PlaceMapElement placeMapElement = new PlaceMapElement(gameMap, place, getCharacterLocation());
                placeMapElements.add(placeMapElement);
                placeMapElement.addToMap();
                generatedPlaces--;
            }
        }
    }

    public PlaceMapElement findPlaceById(long placeId) {
        for(PlaceMapElement mapElement : placeMapElements)
            if (mapElement.getId() == placeId)
                return mapElement;
        return null;
    }
}
