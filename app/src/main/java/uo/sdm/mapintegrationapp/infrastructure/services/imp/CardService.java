package uo.sdm.mapintegrationapp.infrastructure.services.imp;

import android.content.Context;
import android.graphics.drawable.Drawable;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.infrastructure.services.ICardService;

/**
 * Created by Adrián on 28/06/2015.
 */
public class CardService implements ICardService {
    @Override
    public Drawable getDrawableCard(Integer type,String defType,String defPackage, Context context) {
        Integer id = context.getResources().getIdentifier("card_"+type,defType,defPackage);
        return context.getResources().getDrawable(id);
    }
}
