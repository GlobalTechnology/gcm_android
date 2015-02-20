package com.expidev.gcmapp.map;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.expidev.gcmapp.R;
import com.expidev.gcmapp.model.Church;

public class ChurchMarker extends Marker<Church> {
    public ChurchMarker(@NonNull final Church obj) {
        super(obj);
    }

    @Override
    public String getName() {
        return mObj.getName();
    }

    @DrawableRes
    @Override
    public int getItemImage() {
        switch(mObj.getDevelopment()) {
            case GROUP:
                return R.drawable.groupicon;
            case CHURCH:
                return R.drawable.churchicon;
            case MULTIPLYING_CHURCH:
                return R.drawable.multiplyingchurchicon;
            case TARGET:
            default:
                return R.drawable.targeticon;
        }
    }
}