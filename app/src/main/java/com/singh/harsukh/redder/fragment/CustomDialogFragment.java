package com.singh.harsukh.redder.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.singh.harsukh.redder.R;
import com.singh.harsukh.redder.model.Reddit.RedditAccount;

/**
 * Created by Henry on 3/9/2016.
 */
public class CustomDialogFragment extends DialogFragment {

    private TextView mJsonContainer;

    public CustomDialogFragment(){
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View layout = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog, null);

        mJsonContainer = (TextView) layout.findViewById(R.id.dialog_text);

        builder.setView(layout)
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CustomDialogFragment.this.getDialog().dismiss();
                    }
                });

        objectToJSON();
        return builder.create();
    }

    public void objectToJSON(){
        RedditAccount account = new RedditAccount(5, false, false, false, "JerryZaz",
                false, false, false, 1, "this", "ohThat!", false);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String accountJson = gson.toJson(account);
        mJsonContainer.setText(accountJson);
    }
}
