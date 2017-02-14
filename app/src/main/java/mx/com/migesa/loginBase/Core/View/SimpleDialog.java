/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mx.com.migesa.loginBase.Core.View;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import mx.com.migesa.loginBase.Core.Interface.OnFragmentListener;
import mx.com.migesa.loginBase.R;

/**
 * Created by GORTIZG on 26/09/2016.
 */

public class SimpleDialog extends DialogFragment {

    private static OnFragmentListener listener = null;


    public static SimpleDialog newInstance(String title, String msg, OnFragmentListener listenerFragment, int requestCode) {
        SimpleDialog frag = new SimpleDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);
        args.putInt("requestCode", requestCode);
        frag.setArguments(args);
        listener = listenerFragment;
        return frag;
    }

    public static SimpleDialog newInstance(String title, String msg) {
        SimpleDialog frag = new SimpleDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);
        frag.setArguments(args);
        return frag;
    }

    String msg;
    String title;
    int requestCode;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        msg = getArguments().getString("msg");
        title = getArguments().getString("title");
        requestCode = getArguments().getInt("requestCode", 0);
        return createSimpleDialog();

    }

    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (title != null)
            builder.setTitle(title);
        builder.setMessage(msg).setPositiveButton(R.string.ALERT_DIALOG_ACEPTAR,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onFragment(requestCode, null,null);
                    }
                });
        return builder.create();
    }
}
