package com.example.quynh.place;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quynh.place.Adapters.CategoryAdapter;
import com.example.quynh.place.Adapters.DrawerAdapter;
import com.example.quynh.place.Adapters.ProductionAdapter;
import com.example.quynh.place.Constants.AppConstants;
import com.example.quynh.place.Items.CategoryItem;
import com.example.quynh.place.Items.DrawerItem;
import com.example.quynh.place.Utils.DebugLogUtils;
import com.example.quynh.place.Utils.Utils;
import com.example.quynh.place.activities.FAQActivity;
import com.example.quynh.place.activities.LanguagesActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private ActionBarDrawerToggle mDrawerToggle;
    private String mCurrentVersion, mMarketVersion;
    private static int mDrawerLength;

    @BindView(R.id.left_drawer)
    ListView mDrawer;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.list_category)
    ListView mListCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupActionBar();
        setDrawerLayout();
        setupListView();
//        setupListView2();
    }


    private void setupActionBar() {
        Toolbar toolbar = ButterKnife.findById(this, R.id.tool_bar);
//        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(getString(R.string.title));
        }
    }

    private void setDrawerLayout() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;
        mDrawer.getLayoutParams().width = widthPixels * 2 / 3;
        final List<DrawerItem> drawerItems = new ArrayList<>();
        //Header
        drawerItems.add(new DrawerItem(R.drawable.ic_sub_facebook));

        //Other feature of app
        drawerItems.add(new DrawerItem(R.drawable.ic_share_white_24dp,
                getString(R.string.share_app)));
        drawerItems.add(new DrawerItem(R.drawable.ic_translate,
                getString(R.string.language)));
        drawerItems.add(new DrawerItem(R.drawable.ic_question,
                getString(R.string.faq)));
        drawerItems.add(new DrawerItem(R.drawable.ic_feedback,
                getString(R.string.send_feedback)));
        drawerItems.add(new DrawerItem(R.drawable.ic_quit,
                getString(R.string.quit)));
        mDrawer.setAdapter(new DrawerAdapter(this, drawerItems));
        mDrawer.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mDrawerLength = drawerItems.size();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle != null
                && mDrawerToggle.onOptionsItemSelected(item)) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0: //Subscribe
                mDrawerLayout.closeDrawers();
                Utils.openFacebook(this, AppConstants.PAGE_ID);
                break;
            case 1: //Share app
                mDrawerLayout.closeDrawers();
                Utils.shareLink(this, AppConstants.APP_LINK);
                break;

            case 2: //Language
                mDrawerLayout.closeDrawers();
                Intent languageIntent = new Intent(this, LanguagesActivity.class);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                String currentLanguage = sp.getString(getString(R.string.pref_display_language)
                        , AppConstants.DEVICE_LANGUAGE);
                languageIntent.putExtra(AppConstants.KEY_EXTRA_LANGUAGE, currentLanguage);
                startActivityForResult(languageIntent, AppConstants.REQUEST_CODE_SELECT_LANGUAGE);
                break;

            case 3: //FAQ
                mDrawerLayout.closeDrawers();
                Utils.gotoActivity(this, FAQActivity.class);
                break;

            case 4: //Send mail feedback
                mDrawerLayout.closeDrawers();
                Utils.sendMail(this, AppConstants.EXTRA_MAIL_SUBJECT_REPORT,
                        AppConstants.EXTRA_MAIL_MSG_REPORT);
                break;
            case 5: //Finish
                mDrawerLayout.closeDrawers();
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQUEST_CODE_SELECT_LANGUAGE
                && resultCode == RESULT_OK && data != null) {
            String selectedLanguage = data.getStringExtra(AppConstants.KEY_EXTRA_LANGUAGE);
            final Locale locale;
            if (selectedLanguage.equals(AppConstants.DEVICE_LANGUAGE)) {
                locale = Locale.getDefault();
            } else if (selectedLanguage.contains("_")) {
                String[] codes = selectedLanguage.split("_");
                locale = new Locale(codes[0], codes[1]);
            } else {
                locale = new Locale(selectedLanguage);
            }

            Resources res = getApplicationContext().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            conf.setLayoutDirection(locale);
            res.updateConfiguration(conf, dm);
            finish();
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            return;
        }
    }

    private void setupListView2() {
        String[] productions = getResources().getStringArray(R.array.food);
        List<String> productionsList = new ArrayList<>(Arrays.asList(productions));
        ProductionAdapter productionAdapter = new ProductionAdapter(this, productionsList);
        mListCategory.setAdapter(productionAdapter);

    }


    //TODO We will save this data on cloud and get over internet, so dont need update app when data of categories is changed
    private void setupListView() {
        final List<CategoryItem> listItem = new ArrayList<>();
        String[] categoryNameList = getResources().getStringArray(R.array.category_name);
        TypedArray categoryIconList = getResources().obtainTypedArray(R.array.category_icons);
        int[] categoryId = getResources().getIntArray(R.array.category_id);

        for (int i = 0; i < categoryNameList.length; i++) {
            int categoryIcon = categoryIconList.getResourceId(i, 0);
            String[] productions = null;
            switch (categoryId[i]) {
                case 1:
                    productions = getResources().getStringArray(R.array.food);
                    break;
                case 2:
                    productions = getResources().getStringArray(R.array.leisure_time_entertainment);
                    break;
                case 3:
                    productions = getResources().getStringArray(R.array.store);
                    break;
                case 4:
                    productions = getResources().getStringArray(R.array.beauty_and_health);
                    break;
                case 5:
                    productions = getResources().getStringArray(R.array.sport_tourism);
                    break;
                case 6:
                    productions = getResources().getStringArray(R.array.education);
                    break;
                case 7:
                    productions = getResources().getStringArray(R.array.financial_service);
                    break;
            }
            if (productions != null) {
                List<String> productionsList = new ArrayList<>(Arrays.asList(productions));
                listItem.add(new CategoryItem(categoryIcon, categoryNameList[i], productionsList));
            } else listItem.add(new CategoryItem(categoryIcon, categoryNameList[i]));
        }
        CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, listItem);
        mListCategory.setAdapter(categoryAdapter);
        mListCategory.setDivider(null);
        categoryIconList.recycle();

        //Test
//        mListCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ListView listView =view.findViewById( R.id.list_production);
//                listView.setVisibility(View.VISIBLE);
//                ProductionAdapter productionAdapter = new ProductionAdapter(MainActivity.this,listItem.get(i).getProductionList());
//                listView.setAdapter(productionAdapter);
//            }
//        });
    }
}
