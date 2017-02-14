package mx.com.migesa.loginBase.Core.Module;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import mx.com.migesa.loginBase.Config;
import mx.com.migesa.loginBase.Core.Animation.FragmentAnimation;
import mx.com.migesa.loginBase.Core.Interface.OnBackPress;
import mx.com.migesa.loginBase.Core.View.Ui;
import mx.com.migesa.loginBase.entidades.login.Usuario;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Guillermo Ortiz on 17/01/17.
 */

public class FragmentBase extends Fragment implements OnBackPress {


    LayoutInflater inflater;
    private View view = null;
    private FragmentAnimation animation = null;
    private int viewId;
    private String tagName = null;
    private String tagBackStack = null;
    private boolean checkLogin = true;
    private Handler handler = null;
    private int rtTry = 0;
    private boolean isPaused = false;
    private boolean checkAlarm = true;

    private Ui ui;
    private int layoutView;
    private boolean replaceAll = false;
    private ActivityBase activityBase;

    private final String TAG_Base = FragmentBase.class.getSimpleName();
    public static String TAG = FragmentBase.class.getSimpleName();
    private Context context;
    /**
     * Fragmento hijo de FragmentPadre
     */
    private Fragment fragment;


    AppController appController;

    private Retrofit mRestAdapter = null;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isCheckAlarm() {
        return checkAlarm;
    }

    public void setCheckAlarm(boolean checkAlarm) {
        this.checkAlarm = checkAlarm;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }

    private void setReplaceAll(boolean replaceAll) {
        this.replaceAll = replaceAll;
    }


    public FragmentAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(FragmentAnimation animation) {
        this.animation = animation;
    }

    public FragmentBase() {
        setUi(new Ui(this.getContext()));
        setAnimation(FragmentAnimation.getAnimation1());
    }

    public FragmentBase getSelf() {
        return this;
    }

    public ActivityBase getActivityBase() {
        ActivityBase activity;
        if (activityBase == null) {
            activity = (ActivityBase) getActivity();
        } else {
            activity = activityBase;
        }
        return activity;
    }

    public void setActivityBase(ActivityBase activityBase) {
        this.activityBase = activityBase;
    }


    /**
     * Este LinearLauout, contendrá el header con la información del usuario y contrato seleccionado
     */
    LinearLayout LLContenedorHeaders = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        appController = AppController.getInstance();
        handler = new Handler();
        inflater = LayoutInflater.from(getContext());


        if (getFragment() != null)
            TAG = getFragment().getClass().getSimpleName();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), container, false);
        getUi().setView(view);


        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        setPaused(true);

        if (getHandler() != null) {
            getHandler().removeCallbacksAndMessages(null);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        setPaused(false);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();


    }


    public AppController getAppController() {
        return appController;
    }

    public void setUsuario(Usuario usuario) {
        getAppController().setUsuario(usuario);
    }

    public Usuario getUsuario() {
        return getAppController().getUsuario();
    }


    public Retrofit getRetrofit() {
        if (mRestAdapter == null)
            mRestAdapter = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

        return mRestAdapter;
    }

    public Context getContext() {
        return context;
    }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public void showToast(String msj) {
        Toast.makeText(getContext(), msj, Toast.LENGTH_LONG).show();
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public int getLayoutView() {
        return layoutView;
    }

    public void setLayoutView(int layoutView) {
        this.layoutView = layoutView;
    }


    public View getView() {
        if (view != null)
            return view;

        throw new RuntimeException("\n\n\t\tDebes inicializar el View con setView(v) en el fragmento\n" +
                "\n");
    }

    public void setView(View view) {
        this.view = view;
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

    public ImageView getImageView(int widgetId) {
        return (ImageView) this.getView().findViewById(widgetId);
    }

    public View getView(int widgetId) {
        return this.getView().findViewById(widgetId);
    }


    public void transaction(@NonNull ActivityBase activityBase, FragmentBase fragmentBase, boolean backstack) {
        FragmentAnimation animation = getAnimation();
        setActivityBase(activityBase);
        if (activityBase != null && !activityBase.isDestroyed() && !activityBase.isFinishing()) {
            FragmentManager manager = activityBase.getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (fragmentBase != null) {
                transaction.hide(fragmentBase);
            }
            if (backstack) {
                transaction.addToBackStack(this.getClass().getCanonicalName());
            }
            if (getAnimation() != null) {
                transaction.setCustomAnimations(animation.getEnter(), animation.getExit(), animation.getPopEnter(), animation.getPopExit());
            }
            if (replaceAll) {
                transaction.replace(getViewId(), this, this.getClass().getCanonicalName());
            } else {
                transaction.add(getViewId(), this, this.getClass().getCanonicalName());
            }
            try {
                transaction.commit();
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
    }

    public void transaction(FragmentBase fragmentBase, boolean backstack) {
        transaction(fragmentBase.getActivityBase(), fragmentBase, backstack);
    }

    @Override
    public boolean onBack() {
        return true;
    }

}
