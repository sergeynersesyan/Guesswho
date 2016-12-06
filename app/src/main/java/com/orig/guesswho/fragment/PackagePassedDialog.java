package com.orig.guesswho.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orig.guesswho.AppConstants;
import com.orig.guesswho.PreferenceController;
import com.orig.guesswho.R;
import com.orig.guesswho.activity.PackageListActivity;
import com.orig.guesswho.block.Package;

/**
 * Created by Mr Nersesyan on 25/11/2016.
 */

public class PackagePassedDialog extends DialogFragment {


    Button okButton;
    TextView earnedCoinsText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passed_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        okButton = (Button) view.findViewById(R.id.ok_button);
        earnedCoinsText = (TextView) view.findViewById(R.id.earned_coins_text);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();

        int earnedCoins = args.getInt(AppConstants.EXTRA_EARNED_COINS);
        String earnedText = getString(R.string.earned_coins) +" " + earnedCoins;
        earnedCoinsText.setText(earnedText);
        getDialog().setTitle(R.string.congrats);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(PackagePassedDialog.this).commit();
                getActivity().onBackPressed();
            }
        });


    }
}
