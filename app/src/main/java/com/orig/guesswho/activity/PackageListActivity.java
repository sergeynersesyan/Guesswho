package com.orig.guesswho.activity;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.orig.guesswho.block.Package;
import com.orig.guesswho.PreferenceCOntroller;
import com.orig.guesswho.QuestionHelper;
import com.orig.guesswho.R;
import com.orig.guesswho.adapters.PackageListAdapter;

import java.util.ArrayList;


public class PackageListActivity extends AppCompatActivity {

    PackageListAdapter packageAdapter;
    ArrayList<Package> packages;
    TextView totalCoinsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);


        TextView selectPackageText = (TextView) findViewById(R.id.select_package_textView);
        totalCoinsTextView = (TextView) findViewById(R.id.total_coins_textView);
        Typeface typeFace= Typeface.createFromAsset(getAssets(), "fonts/aladin.ttf");
        selectPackageText.setTypeface(typeFace);
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
        updateConfiguration();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateConfiguration();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_package_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateConfiguration(){
        packageAdapter.notifyDataSetChanged();
        String totalCoins = Integer.toString(PreferenceCOntroller.getInstance(getApplicationContext()).getTotalCoins());
        totalCoinsTextView.setText(totalCoins);
    }

}
