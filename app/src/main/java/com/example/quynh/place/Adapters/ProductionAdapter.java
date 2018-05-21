package com.example.quynh.place.Adapters;

import android.app.Activity;
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

public class ProductionAdapter extends ArrayAdapter<String> {
    private static final String TAG = "ProductionAdapter";
    private List<String> mListItems;
    private Activity mActivity;

    public ProductionAdapter(Activity activity, List<String> items) {
        super(activity, 0, items);
        mListItems = items;
        mActivity = activity;
        DebugLogUtils.d(TAG, "Test second item: " + items.get(1) + ", items size: " + items.size());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ItemHolder itemHolder;
        if (view == null) {
            view = mActivity.getLayoutInflater().inflate(R.layout.adapter_production, parent, false);
            itemHolder = new ItemHolder(view);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) view.getTag();
        }
        String item = mListItems.get(position);
        DebugLogUtils.d(TAG, "Test get production: " + item + " at " + position);
        itemHolder.tvName.setText(item);
        itemHolder.adapterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.gotoActivity(mActivity, AddressActivity.class);
            }
        });
        return view;
    }


    class ItemHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.layout_production_adapter)
        RelativeLayout adapterLayout;

        ItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


