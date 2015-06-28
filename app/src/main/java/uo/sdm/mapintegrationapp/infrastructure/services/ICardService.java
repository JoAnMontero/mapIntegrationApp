package uo.sdm.mapintegrationapp.infrastructure.services;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Adrian on 28/06/2015.
 */
public interface ICardService {
    public Drawable getDrawableCard(Integer type,String defType,String defPackage,Context context);
}
