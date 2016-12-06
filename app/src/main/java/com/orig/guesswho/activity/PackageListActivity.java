package com.orig.guesswho.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.orig.guesswho.Utils;
import com.orig.guesswho.block.Package;
import com.orig.guesswho.PreferenceController;
import com.orig.guesswho.QuestionHelper;
import com.orig.guesswho.R;
import com.orig.guesswho.adapters.PackageListAdapter;

import java.util.ArrayList;


public class PackageListActivity extends AppCompatActivity {

    PackageListAdapter packageAdapter;
    ArrayList<Package> packages;
    TextView totalCoinsTextView;
    int totalCoinsCount;
    TextView plusCoins;
    View coinsLayout;
    InterstitialAd mInterstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);
        // ads
        initBannerAd();
        initInterstitialAd();
        requestNewInterstitial();
        //layout
        TextView selectPackageText = (TextView) findViewById(R.id.select_package_textView);
        totalCoinsTextView = (TextView) findViewById(R.id.total_coins_textView);
        coinsLayout = findViewById(R.id.coins_linearlayout);
        plusCoins = (TextView) findViewById(R.id.plus_coins_text);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
        selectPackageText.setTypeface(typeFace);
        coinsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCoins();
            }
        });

        packages = QuestionHelper.getInstance().getAllPackages(getApplicationContext());

        RecyclerView doorsRecyclerView = (RecyclerView) findViewById(R.id.package_list_recyclerview);
        packageAdapter = new PackageListAdapter(this);
        doorsRecyclerView.setAdapter(packageAdapter);
        doorsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        packageAdapter.setItems(packages);


    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_package_list_activity, menu);
        return true;
    }

    public void update() {
        packageAdapter.notifyDataSetChanged();
        totalCoinsCount = PreferenceController.getInstance(getApplicationContext()).getTotalCoins();
        String totalCoins = Integer.toString(totalCoinsCount);
        totalCoinsTextView.setText(totalCoins);
    }

    private void initBannerAd() {
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2649177375880705~1003834670");

        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdListener(new AdListener() {
        });
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(getString(R.string.device_id_huawei))  // An example device ID
                .build();
        mAdView.loadAd(request);
    }

    private void initInterstitialAd() {
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                Toast.makeText(PackageListActivity.this, "Thanks, You got 3 coins", Toast.LENGTH_SHORT).show();
                totalCoinsCount += 3;
                String str = Integer.toString(totalCoinsCount);
                totalCoinsTextView.setText(str);
                PreferenceController.getInstance(getApplicationContext()).setTotalCoins(totalCoinsCount);
                Utils.animateCoinsUp(PackageListActivity.this, plusCoins, 3);
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getString(R.string.device_id_huawei))
                .build();

        mInterstitial.loadAd(adRequest);
    }

    public void addCoins() {
        if (mInterstitial.isLoaded()) {
            mInterstitial.show();
        } else {
            Toast.makeText(PackageListActivity.this, "Ad is not loaded yet", Toast.LENGTH_SHORT).show();
        }
    }

}
