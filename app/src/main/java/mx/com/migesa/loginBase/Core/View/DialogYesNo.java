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
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import mx.com.migesa.loginBase.Core.Interface.OnFragmentListener;
import mx.com.migesa.loginBase.R;


/**
 * Created by GORTIZG on 26/09/2016.
 */

public class DialogYesNo extends DialogFragment {

    public final static String TAG = DialogYesNo.class.getName();
    static OnFragmentListener listener;
    static DialogInterface.OnClickListener dialogListener;
    static int tipo_alert;

    public static DialogYesNo newInstance(String title, String msg, int requestCode, OnFragmentListener _listener, String result) {
        DialogYesNo frag = new DialogYesNo();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);
        args.putString("result", result);
        args.putInt("requestCode", requestCode);
        frag.setArguments(args);
        listener = _listener;
        tipo_alert = 0;
        return frag;
    }

    public static DialogYesNo newInstance(String title, String msg, int requestCode, DialogInterface.OnClickListener _listener) {
        DialogYesNo frag = new DialogYesNo();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("msg", msg);
        args.putInt("requestCode", requestCode);
        frag.setArguments(args);
        dialogListener = _listener;
        tipo_alert = 1;
        return frag;
    }


    String title;
    String msg;
    String result;
    int requestCode;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        title = getArguments().getString("title");
        msg = getArguments().getString("msg");
        requestCode = getArguments().getInt("requestCode", 0);

        if (tipo_alert == 0)
            return createSimpleDialog();
        else
            return createDialogListener();

    }


    public AlertDialog createSimpleDialog() {

        result = getArguments().getString("result");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (title != null)
            builder.setTitle(title);

        builder.setMessage(msg).setPositiveButton(R.string.ALERT_DIALOG_SI,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFragment(requestCode, result, null);
                    }
                })
                .setNegativeButton(R.string.ALERT_DIALOG_NO,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onFragment(0, null, null);
                            }
                        });
        return builder.create();
    }


    public AlertDialog createDialogListener() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (title != null)
            builder.setTitle(title);

        builder.setMessage(msg).setPositiveButton(R.string.ALERT_DIALOG_SI, dialogListener)
                .setNegativeButton(R.string.ALERT_DIALOG_NO, dialogListener);
        return builder.create();
    }


}