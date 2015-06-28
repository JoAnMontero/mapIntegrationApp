package uo.sdm.mapintegrationapp.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.business.CollectionManager;
import uo.sdm.mapintegrationapp.infrastructure.factories.ServiceFactory;
import uo.sdm.mapintegrationapp.infrastructure.services.ICardService;
import uo.sdm.mapintegrationapp.infrastructure.services.ISoundService;
import uo.sdm.mapintegrationapp.infrastructure.services.imp.CardService;
import uo.sdm.mapintegrationapp.model.Collectible;


public class CardDetailActivity extends Activity {
    private static String key_collection_theme = "KEY_COLLECTION_THEME";

    //Services
    private ISoundService soundService = ServiceFactory.soundService;
    private CollectionManager collectionManager;
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewCategory;
    private TextView textViewAmount;
    private Integer type;

    private void initAttributes(){
        collectionManager = new CollectionManager(this);
        type = getIntent().getExtras().getInt("TYPE");
        imageView = (ImageView)findViewById(R.id.view_card);
        textViewName = (TextView)findViewById(R.id.card_detail_name);
        textViewCategory = (TextView)findViewById(R.id.card_detail_category);
        textViewAmount = (TextView)findViewById(R.id.card_detail_amount);
    }

    private void loadDetails(){
        Collectible c = collectionManager.getCollectibleByType(type);
        textViewName.setText(c.getName());
        textViewCategory.setText(c.getCategory());
        textViewAmount.setText(c.getAmount().toString());
        imageView.setImageDrawable(c.getCardImage(getApplicationContext()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        initAttributes();
        loadDetails();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundService.start(getApplicationContext(), R.raw.collectheme_1, true, key_collection_theme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundService.start(getApplicationContext(), R.raw.collectheme_1, true, key_collection_theme);
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundService.stop(key_collection_theme);
    }

}
