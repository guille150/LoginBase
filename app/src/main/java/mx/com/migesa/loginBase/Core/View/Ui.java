package mx.com.migesa.loginBase.Core.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Guillermo Ortiz on 19/01/17.
 */


public class Ui {
    private Context context = null;
    private View view = null;

    public Ui(Context context, View view) {
        this.setContext(context);
        this.setView(view);
    }

    public Ui(Context context) {
        this.setContext(context);
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }
            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }
        }
        return views;
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ImageView getImageView(int widgetId) {
        return (ImageView) this.getView().findViewById(widgetId);
    }

    public View getItemView(int widgetId) {
        return this.getView().findViewById(widgetId);
    }

    public EditText getEditText(int widgetId) {
        return (EditText) this.getView().findViewById(widgetId);
    }

    public Button getButton(int widgetId) {
        return (Button) this.getView().findViewById(widgetId);
    }

    public TextView getTextView(int widgetId) {
        return (TextView) this.getView().findViewById(widgetId);
    }

    public LinearLayout getLinearLayout(int widgetId) {
        return (LinearLayout) this.getView().findViewById(widgetId);
    }

    public RecyclerView getRecyclerView(int widgetId) {
        return (RecyclerView) this.getView().findViewById(widgetId);
    }

    public RelativeLayout getRelativeLayout(int widgetId) {
        return (RelativeLayout) this.getView().findViewById(widgetId);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout(int widgetId) {
        return (SwipeRefreshLayout) this.getView().findViewById(widgetId);
    }

    public Spinner getSpinner(int widgetId) {
        return (Spinner) this.getView().findViewById(widgetId);
    }

    public ProgressBar getProgressBar(int widgetId) {
        return (ProgressBar) this.getView().findViewById(widgetId);
    }

    public String string(int stringId) {
        if (getContext() != null) {
            try {
                return getContext().getResources().getString(stringId);
            } catch (NullPointerException e) {
                return "ERROR";
            }
        } else {
            return "ERROR";
        }
    }

    public int color(int colorId) {
        if (getContext() != null) {
            return getContext().getResources().getColor(colorId);
        } else {
            return 0;
        }
    }

    public Drawable drawable(int drawerId) {
        if (getContext() != null) {
            return getContext().getResources().getDrawable(drawerId);
        } else {
            return null;
        }
    }
}