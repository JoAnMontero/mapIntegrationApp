package uo.sdm.mapintegrationapp.ui;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.business.CollectionManager;
import uo.sdm.mapintegrationapp.model.Collectible;

public class CardDetailActivity extends ActionBarActivity {

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

        Integer id = getResources().getIdentifier(
                "card_" + type,
                "drawable",
                CardDetailActivity.this.getPackageName());
        Drawable img = getResources().getDrawable(id);
        imageView.setImageDrawable(img);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        initAttributes();
        loadDetails();
    }

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
}
