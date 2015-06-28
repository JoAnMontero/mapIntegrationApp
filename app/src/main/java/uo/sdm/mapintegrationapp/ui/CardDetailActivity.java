package uo.sdm.mapintegrationapp.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.business.CollectionManager;
import uo.sdm.mapintegrationapp.model.Collectible;

public class CardDetailActivity extends Activity {

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
}
