package uo.sdm.mapintegrationapp.business;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.model.CharacterMapElement;
import uo.sdm.mapintegrationapp.model.Place;
import uo.sdm.mapintegrationapp.model.PlaceMapElement;

/**
 * Created by Hans on 26/06/2015.
 */
public class MarkerCollection {
    private final GoogleMap gameMap;

    private CharacterMapElement character = null;
    private List<PlaceMapElement> placesMapElements = new ArrayList<PlaceMapElement>();

    public MarkerCollection(GoogleMap gameMap) {
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

    public void refreshPlaces(List<Place> places) {
        placesMapElements = new ArrayList<PlaceMapElement>();

        for (Place place : places) {
            PlaceMapElement marker = new PlaceMapElement(gameMap, place, getCharacterLocation());
            placesMapElements.add(marker);
            marker.addToMap();
        }
    }

    private void updatePlaces() {
        for (PlaceMapElement place : placesMapElements) {
            place.setCharacterLocation(character.getLocation());
            place.updateIcon();
        }
    }
}
