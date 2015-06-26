package uo.sdm.mapintegrationapp.business;

import android.app.Activity;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.model.types.MapElementType;

/**
 * Created by Hans on 23/06/2015.
 */
public class CustomOnMarkerClickListener implements GoogleMap.OnMarkerClickListener {

    private final LayoutInflater inflater;
    private final GoogleMap gameMap;
    private Activity activity;

    public CustomOnMarkerClickListener(LayoutInflater inflater, GoogleMap gameMap, Activity activity) {
        this.inflater = inflater;
        this.gameMap = gameMap;
        this.activity = activity;
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
                // Obtain the view to be displayed inside the popup
                popupView = inflater.inflate(R.layout.research_popup,null);

                // A button that dismisses the popup
                Button btnDismiss = (Button) popupView.findViewById(R.id.researchPopup_button);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case Ruin:
                // TODO add the ruin research popup window
                // TODO add the research in progress popup window
                // TODO add the finish research popup window
                break;
            case Camp:
                break;
        }

        // If the kind of marker we received needed to show a popup, we display the popup properly
        if (popupView != null) {
            // Disable the interactions with the map to prevent the marker to move away from the popup
            gameMap.getUiSettings().setAllGesturesEnabled(false);

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
                    gameMap.getUiSettings().setAllGesturesEnabled(true);
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
