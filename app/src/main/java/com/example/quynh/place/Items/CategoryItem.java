package com.example.quynh.place.Items;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.quynh.place.Utils.DebugLogUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Quynh on 12/8/2017.
 */

public class CategoryItem {


    private static final String TAG = "DrawableItem";
    private int mIconDrawableId;
    private String mItemName;
    private List<String> mListProduction;

    public CategoryItem(int iconDrawableId, String itemName) {
        DebugLogUtils.d(TAG, "Using drawable item");
        mIconDrawableId = iconDrawableId;
        mItemName = itemName;
        mListProduction = null;
    }

    public CategoryItem(int iconDrawableId, String itemName, List<String> productions) {
        DebugLogUtils.d(TAG, "Using drawable item");
        mIconDrawableId = iconDrawableId;
        mItemName = itemName;
        mListProduction = productions;
    }


    //All
    public String getItemName() {
        return mItemName;
    }

    public int getIconDrawableId() {
        return mIconDrawableId;
    }

    public List<String> getProductionList() {
        return mListProduction;
    }
}