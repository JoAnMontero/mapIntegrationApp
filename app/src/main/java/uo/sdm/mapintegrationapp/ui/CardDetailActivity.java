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

<<<<<<< HEAD
public class CardDetailActivity extends ActionBarActivity {
    private static String key_collection_theme = "KEY_COLLECTION_THEME";
=======
public class CardDetailActivity extends Activity {
>>>>>>> 487960586bf318d68d53448057a0017fc848230f

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
<<<<<<< HEAD

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundService.stop(key_collection_theme);
    }
=======
>>>>>>> 487960586bf318d68d53448057a0017fc848230f
}
