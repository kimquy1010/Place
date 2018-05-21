package com.example.quynh.place.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewUtils {

    public static void setListViewHeightBasedOnChilden(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter==null){
            return;
        }
        int totalLength = 0;
        for (int i= 0; i<listView.getCount();i++){
            View listItem = listAdapter.getView(i, null,listView);
            listItem.measure(0,0);
            totalLength+=listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalLength;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
