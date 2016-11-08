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
import com.orig.guesswho.block.Package;
import com.orig.guesswho.PreferenceCOntroller;
import com.orig.guesswho.R;

public class UnlockDialog extends DialogFragment {

    Button yesButton, noButton, addCoinsButton;
    TextView headerText, messageText;
    Package aPackage;

    public UnlockDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unlock_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yesButton = (Button) view.findViewById(R.id.yes_button);
        noButton = (Button) view.findViewById(R.id.no_button);
        addCoinsButton = (Button) view.findViewById(R.id.add_coins_button);
        headerText = (TextView) view.findViewById(R.id.dialog_header);
        messageText = (TextView) view.findViewById(R.id.dialog_message);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Bundle args = getArguments();

        aPackage = (Package) args.getSerializable(AppConstants.PACKAGE_EXTRA);
        final PreferenceCOntroller prefController = PreferenceCOntroller.getInstance(getContext());
        final int totalCoins = prefController.getTotalCoins();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aPackage.price < totalCoins) {
                    aPackage.locked = false;
                    prefController.setLock(aPackage.packageUID, false);
                    prefController.setTotalCoins(totalCoins - aPackage.price);
                    getActivity().getSupportFragmentManager().beginTransaction().remove(UnlockDialog.this).commit();
                    getActivity().onConfigurationChanged(null);
                } else {
                    Toast.makeText(getContext(), "You have no enough coins", Toast.LENGTH_SHORT).show();
                }

            }
        });


        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(UnlockDialog.this).commit();
            }
        });
    }


}
