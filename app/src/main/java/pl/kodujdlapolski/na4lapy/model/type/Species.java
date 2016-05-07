package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Species implements AnimalAttribute {
    DOG(R.string.species_dogs),
    CAT(R.string.speciec_cats),
    OTHER(R.string.species_others);

    private int mLabelResId;

    Species(@StringRes int labelResId) {
        mLabelResId = labelResId;
    }

    @StringRes
    public int getLabelResId() {
        return mLabelResId;
    }

    @DrawableRes
    public int getDrawableResId() {
        return -1;
    }
}
