package com.example.quynh.place.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quynh.place.R;
import com.example.quynh.place.Utils.DebugLogUtils;
import com.example.quynh.place.Utils.Utils;
import com.example.quynh.place.activities.AddressActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductionAdapter2 extends ArrayAdapter<String> {
    private static final String TAG = "ProductionAdapter";
    private List<String> mListItems;
    private Activity mActivity;

    public ProductionAdapter2(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}


