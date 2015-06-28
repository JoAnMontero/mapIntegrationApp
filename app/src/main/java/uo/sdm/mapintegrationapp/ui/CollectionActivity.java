package uo.sdm.mapintegrationapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.business.CollectionManager;
import uo.sdm.mapintegrationapp.infrastructure.factories.ServiceFactory;
import uo.sdm.mapintegrationapp.infrastructure.services.ICardService;
import uo.sdm.mapintegrationapp.infrastructure.services.ISoundService;
import uo.sdm.mapintegrationapp.model.Collectible;

public class CollectionActivity extends Activity {
    private static final String LOG_TAG = "CollectionActivity";

    private static int IMG_SIZE_L = 100;
    private static int IMG_SIZE_M = 400;
    private static int IMG_SIZE_H = 600;
    private static String key_collection_theme = "KEY_COLLECTION_THEME";


    //Services
    private ISoundService soundService = ServiceFactory.soundService;

    private CollectionManager collectionManager;
    private LinearLayout vLayout; //Vertical
    private LinearLayout hLayout; //horizontal
    private Integer img_size;
    /*
        This attributes allow initialize layouts objects properties.
     */
    private LinearLayout.LayoutParams hLParams;
    private LinearLayout.LayoutParams bLParams;

    private List<Collectible> collectibles;
    private Integer screenWidth;


    private View.OnClickListener onclickCard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundService.start(getApplicationContext(), R.raw.press_button, false, "");
            ImageButton b = (ImageButton) v;
            Integer type = (Integer) b.getTag();
            final Intent mIntent = new Intent(CollectionActivity.this, CardDetailActivity.class);
            mIntent.putExtra("TYPE", type);
            startActivity(mIntent);
        }
    };

    public void showCollection(List<Collectible> elements) {
        Log.v(LOG_TAG, "Init show collection.");
        screenWidth = getWindowSize();
        Log.v(LOG_TAG, "WidthLayout is: " + screenWidth + ".");
        Integer imgPerRow = getImagesPerRow();
        Log.v(LOG_TAG, "Will be " + elements.size() + " images distributed in " + imgPerRow + " images per row.");
        Integer numOfHLayouts = getNumOfHLayouts(elements.size(), imgPerRow);
        Log.v(LOG_TAG, "The number of horizontal layouts will be " + numOfHLayouts + ".");
        Integer elementsAdded = 0;
        for (int i = 0; i < numOfHLayouts; i++) {
            hLayout = createHLinearLayout();
<<<<<<< HEAD
            for(int j = 0; j < imgPerRow && elementsAdded < elements.size(); j++){
=======
            for (int j = 0; j < imgPerRow && elementsAdded < elements.size(); j++) {
                Integer id = getResources().getIdentifier(
                        "card_" + elements.get(elementsAdded).getType(),
                        "drawable",
                        CollectionActivity.this.getPackageName());
                Drawable img = getResources().getDrawable(id);
>>>>>>> 487960586bf318d68d53448057a0017fc848230f
                ImageButton imgButton = createImageButton(elements.get(elementsAdded).getType());
                imgButton.setImageDrawable(elements.get(elementsAdded).getCardImage(getApplicationContext()));
                hLayout.addView(imgButton, bLParams);
                elementsAdded++;
                Log.v(LOG_TAG, "Has been added " + elementsAdded + " elements.");
            }
            vLayout.addView(hLayout, hLParams);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        Log.v(LOG_TAG, "Collection Activity init.");
        initSound();
        initAttributes();
        showCollection(collectionManager.getOwnCollectibles());
        Log.v(LOG_TAG, "Collection Activity end.");
    }

    private void initSound() {
        soundService.start(getApplicationContext(), R.raw.collectheme_1, true, key_collection_theme);

    }

    private void initAttributes() {
        vLayout = (LinearLayout) findViewById(R.id.layout_collection);
        collectionManager = new CollectionManager(this);
        collectibles = collectionManager.getOwnCollectibles();
        setActualSize();
        setParamsHorizontalLayout();
        setParamsButtonLayout();
    }

    private int getWindowSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    private int getImagesPerRow() {
        return (screenWidth / img_size) + 1;
    }

<<<<<<< HEAD
    private int getNumOfHLayouts(int numOfElements, int elementsPerRow){
        return numOfElements%elementsPerRow == 0
                ? numOfElements/elementsPerRow
                : numOfElements/elementsPerRow+1;
=======
    private int getNumOfHLayouts(int numOfElements, int elementsPerRow) {
        return numOfElements % elementsPerRow == 0
                ? numOfElements / elementsPerRow
                : numOfElements / elementsPerRow + 1;
>>>>>>> 487960586bf318d68d53448057a0017fc848230f
    }

    private LinearLayout createHLinearLayout() {
        hLayout = new LinearLayout(this);
        hLayout.setId(View.generateViewId());
        hLayout.setOrientation(LinearLayout.HORIZONTAL);
        return hLayout;
    }

    private ImageButton createImageButton(Integer type) {
        ImageButton imgButton = new ImageButton(this);
        imgButton.setId(View.generateViewId());
        imgButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgButton.setAdjustViewBounds(true);
        imgButton.setClickable(true);
        imgButton.setOnClickListener(onclickCard);
        imgButton.setPadding(0, 0, 0, 0);
        imgButton.setBackgroundColor(Color.WHITE);
        imgButton.setTag(type);
        return imgButton;
    }

    private void setParamsButtonLayout() {
        bLParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, this.img_size);
        bLParams.setMargins(2, 2, 2, 2);
    }

    private void setParamsHorizontalLayout() {
        hLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void setActualSize() {
        img_size = CollectionActivity.IMG_SIZE_M;
    }

    @Override
    public void onBackPressed() {
        soundService.stop(key_collection_theme);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundService.stop(key_collection_theme);
    }
}
