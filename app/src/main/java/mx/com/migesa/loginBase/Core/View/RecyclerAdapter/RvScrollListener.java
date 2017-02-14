/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mx.com.migesa.loginBase.Core.View.RecyclerAdapter;

import android.support.v7.widget.RecyclerView;

import mx.com.migesa.loginBase.Core.Interface.OnFragmentListener;


/**
 * Created by GORTIZG on 19/10/2016.
 */


public class RvScrollListener extends RecyclerView.OnScrollListener {
    public RvScrollListener() {
    }

    OnFragmentListener listenerFragment;

    public RvScrollListener(OnFragmentListener listenerFragment) {
        this.listenerFragment = listenerFragment;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        listenerFragment.onFragment(12, null, null);

    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        listenerFragment.onFragment(12, null, null);
    }


}