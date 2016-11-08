package com.orig.guesswho.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orig.guesswho.AppConstants;
import com.orig.guesswho.block.Package;
import com.orig.guesswho.R;
import com.orig.guesswho.activity.QuestionActivity;
import com.orig.guesswho.fragment.UnlockDialog;

import java.util.ArrayList;

/*
 * Created by SergSamArm on 9/18/2016.
 */
public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.PackageViewHolder> {

    private AppCompatActivity activity;
    private LayoutInflater inflater;
    private ArrayList<Package> packageItems = new ArrayList<>();

    public PackageListAdapter(AppCompatActivity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(this.activity);
    }

    @Override
    public PackageListAdapter.PackageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PackageViewHolder(inflater.inflate(R.layout.item_package_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(PackageViewHolder packageViewHolder, int i) {
        final Package currentPackage = packageItems.get(i);
        packageViewHolder.packageName.setText(currentPackage.title);
        packageViewHolder.description.setText(currentPackage.description);
        if (currentPackage.locked) {
            packageViewHolder.lockIcon.setVisibility(View.VISIBLE);
        } else {
            packageViewHolder.lockIcon.setVisibility(View.INVISIBLE);
        }

        packageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPackage.locked) {
                    UnlockDialog dialog = new UnlockDialog();
                    Bundle args = new Bundle();
                    args.putSerializable(AppConstants.PACKAGE_EXTRA, currentPackage);
                    dialog.setArguments(args);
                    dialog.show(activity.getSupportFragmentManager(), "tag");
                } else {
                    Intent intent = new Intent(activity, QuestionActivity.class);
                    intent.putExtra(AppConstants.PACKAGE_EXTRA, currentPackage);
                    activity.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return packageItems.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView packageName;
        ImageView lockIcon;
        View itemView;
        CardView cardView;


        public PackageViewHolder(View itemView) {
            super(itemView);
            packageName = (TextView) itemView.findViewById(R.id.name_of_package_textview);
            description = (TextView) itemView.findViewById(R.id.package_description_textView);
            lockIcon = (ImageView) itemView.findViewById(R.id.lock_icon);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            this.itemView = itemView;

            cardView.setPreventCornerOverlap(false);
            Typeface typeFace= Typeface.createFromAsset(activity.getAssets(), "fonts/aladin.ttf");
            packageName.setTypeface(typeFace);

        }
    }

    public void setItems(ArrayList<Package> items) {
        packageItems.clear();
        packageItems.addAll(items);
        notifyDataSetChanged();
    }
}
