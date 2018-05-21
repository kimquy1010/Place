package com.example.quynh.place.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quynh.place.Items.CategoryItem;
import com.example.quynh.place.R;
import com.example.quynh.place.Utils.DebugLogUtils;
import com.example.quynh.place.Utils.Utils;
import com.example.quynh.place.Utils.ViewUtils;
import com.example.quynh.place.activities.AddressActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends ArrayAdapter<CategoryItem> {
    private static final String TAG = "DrawerAdapter";
    private List<CategoryItem> mListItems;
    private Activity mActivity;

    public CategoryAdapter(Activity activity, List<CategoryItem> items) {
        super(activity, 0, items);
        mListItems = items;
        mActivity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ItemHolder itemHolder;
        if (view == null) {
            view = mActivity.getLayoutInflater().inflate(R.layout.adapter_category, parent, false);
            itemHolder = new ItemHolder(view);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) view.getTag();
        }

        final CategoryItem categoryItem = mListItems.get(position);
        itemHolder.tvName.setText(categoryItem.getItemName());
        itemHolder.ivIcon.setImageResource(categoryItem.getIconDrawableId());
        if (categoryItem.getProductionList() != null) {
            DebugLogUtils.d(TAG, "Setting up production adapter at position: " + position);

            //Method 1
            ProductionAdapter productionAdapter = new ProductionAdapter(mActivity, categoryItem.getProductionList());
            itemHolder.listProduction.setAdapter(productionAdapter);
            ViewUtils.setListViewHeightBasedOnChilden(itemHolder.listProduction);
            DebugLogUtils.d(TAG, "Test adapter size: " + itemHolder.listProduction.getCount() + ", height: " + itemHolder.listProduction.getHeight());
            itemHolder.adapterLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleListView(itemHolder.listProduction);
                }
            });

//            ArrayAdapter productionAdapter2 = new ArrayAdapter(mActivity, R.layout.adapter_production, R.id.tv_name, categoryItem.getProductionList());
//            itemHolder.listProduction.setAdapter(productionAdapter2);


        } else {
            itemHolder.adapterLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.gotoActivity(mActivity, AddressActivity.class);
                }
            });
        }
        return view;
    }

    class ItemHolder {
        @BindView(R.id.layout_adapter)
        RelativeLayout adapterLayout;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.list_production)
        ListView listProduction;
        @BindView(R.id.layout_production)
        LinearLayout linearLayout;

        ItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void toggleListView(ListView listView) {
        if (listView.getVisibility() == View.GONE) {
            listView.setVisibility(View.VISIBLE);
        } else listView.setVisibility(View.GONE);

    }
}


