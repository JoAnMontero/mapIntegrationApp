package uo.sdm.mapintegrationapp.business;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.model.Collectible;
import uo.sdm.mapintegrationapp.model.PlaceMapElement;
import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 23/06/2015.
 */
public class CustomOnMarkerClickListener implements GoogleMap.OnMarkerClickListener {

    private final Activity activity;
    private final GoogleMap gameMap;
    private final MarkerCollection markerCollection;

    public CustomOnMarkerClickListener(Activity activity, GoogleMap gameMap, MarkerCollection markerCollection) {
        this.activity = activity;
        this.gameMap = gameMap;
        this.markerCollection = markerCollection;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Basic initialization of the PopupWindow, still needs a ContentView
        final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // View for the PopupWindow to show
        View popupView = null;

        // Check the kind of marker that has been clicked, show different popups (or no popup at all)
        switch (MapElementType.valueOf(marker.getTitle())) {
            case Character:
                // TODO tweak the options showing at the player marker
                return true;
                //break;
            case Place:
                final PlaceMapElement placeMapElement = markerCollection.findPlaceById(Long.parseLong(marker.getSnippet()));
                if (!placeMapElement.isInRange())
                    return false;
                if (!placeMapElement.isResearching()) {
                    // Obtain the view to be displayed inside the popup
                    popupView = activity.getLayoutInflater().inflate(R.layout.popup_research, (LinearLayout)activity.findViewById(R.id.place_research_popup));

                    // A button that starts the research of a zone
                    Button research = (Button) popupView.findViewById(R.id.research);
                    research.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            placeMapElement.startResearching(activity);
                            popupWindow.dismiss();
                        }
                    });

                    // A button that dismisses the popup
                    Button dismiss = (Button) popupView.findViewById(R.id.dismiss);
                    dismiss.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });


                    if(markerCollection.getCurrentActiveResearches() >= MapManager.maxConcurrentResearches) {
                        TextView label = (TextView) popupView.findViewById(R.id.label);
                        label.setText(activity.getString(R.string.msg_already_researching));
                        research.setEnabled(false);
                    }
                } else {
                    if (placeMapElement.getTimeLeft() > 0) {
                        // Obtain the view to be displayed inside the popup
                        popupView = activity.getLayoutInflater().inflate(R.layout.popup_research_in_progress, (LinearLayout)activity.findViewById(R.id.place_research_in_progress_popup));

                        // A text view that shows the research time left
                        TextView timeLeft = (TextView) popupView.findViewById(R.id.time_left);
                        timeLeft.setText(placeMapElement.getFormattedTimeLeft());

                        // A button that dismisses the popup
                        Button dismiss = (Button) popupView.findViewById(R.id.dismiss);
                        dismiss.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });

                    } else {
                        // TODO play a sound
                        // Obtain the view to be displayed inside the popup
                        popupView = activity.getLayoutInflater().inflate(R.layout.popup_research_completed, (LinearLayout)activity.findViewById(R.id.place_research_completed_popup));

                        // Restore the place to its default interaction state
                        placeMapElement.setAsResearched(activity);

                        // Generate a new random collectible
                        Collectible collectible = new CollectionManager(activity).generateCollection();

                        ImageView image = (ImageView) popupView.findViewById(R.id.image);
                        image.setImageDrawable(collectible.getCardImage(activity));

                        // A button that dismisses the popup
                        Button dismiss = (Button) popupView.findViewById(R.id.dismiss);
                        dismiss.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                    }
                }
                break;
            case Camp:
                // TODO add camps
                break;
        }

        // If the kind of marker we received needed to show a popup, we display the popup properly
        if (popupView != null) {
            // Disable the interactions with the map to prevent the marker to move away from the popup
            gameMap.getUiSettings().setAllGesturesEnabled(false);
            gameMap.getUiSettings().setCompassEnabled(false);
            gameMap.getUiSettings().setMyLocationButtonEnabled(false);
            gameMap.getUiSettings().setZoomControlsEnabled(false);

            // Disable interactions with markers.
            gameMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    return true;
                }
            });

            // Assign the view to be displayed by the popup
            popupWindow.setContentView(popupView);

            // Obtain the screen coordinates of the marker to display the popup next to it
            Projection projection = gameMap.getProjection();
            LatLng markerLocation = marker.getPosition();
            Point screenPosition = projection.toScreenLocation(markerLocation);
            popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, screenPosition.x - 50, screenPosition.y - 50);

            // Re-enable map interactions when the popup is dismissed
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    // Re-enable map interactions
                    gameMap.getUiSettings().setAllGesturesEnabled(true);
                    gameMap.getUiSettings().setCompassEnabled(true);
                    gameMap.getUiSettings().setMyLocationButtonEnabled(true);
                    gameMap.getUiSettings().setZoomControlsEnabled(true);
                    gameMap.setOnMarkerClickListener(new CustomOnMarkerClickListener(activity, gameMap, markerCollection));
                }
            });
            // Return true to indicate that the event is consumed.
            // It prevents the default behaviour to occur.
            return true;
        }

        // Return false to indicate that the event isn't consumed.
        return false;
    }
}
