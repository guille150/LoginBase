package mx.com.migesa.loginBase.Core.Module;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import mx.com.migesa.loginBase.Core.Interface.OnBackPress;
import mx.com.migesa.loginBase.Core.View.DialogYesNo;
import mx.com.migesa.loginBase.Core.View.Ui;
import mx.com.migesa.loginBase.Login.LoginActivity;
import mx.com.migesa.loginBase.R;
import mx.com.migesa.loginBase.entidades.login.Usuario;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Guillermo Ortiz on 17/01/17.
 */

public class ActivityBase extends AppCompatActivity {

    public static String TAG = ActivityBase.class.getSimpleName();

    private OnBackPress backPress;

    public boolean CLOSE_ON_CRASH = true;

    private DrawerLayout drawer = null;
    private boolean enableNavigationDrawer = false;
    private int navigationDrawerId = 0;


    private Toolbar toolbar;


    private int activityLayout;
    private Ui ui;
    private Bundle params = new Bundle();
    private ActivityBase self = null;


    private FragmentBase fragmentBase = null;

    private boolean isStopped = false;
    private boolean isPaused = false;
    private Handler handler = null;

    private static Context runningContext = null;
    private static Context context_base = null;
    private static Activity runningActivity = null;
    static AppController appController;

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }


    public FragmentBase getFragmentBase() {
        return fragmentBase;
    }

    public void setFragmentBase(FragmentBase fragmentBase) {
        this.fragmentBase = fragmentBase;
    }

    public ActivityBase getSelf() {
        return this;
    }

    public void setSelf(ActivityBase self) {
        this.self = self;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setRunning(Context runningContext, Activity runningActivity) {
        this.runningContext = runningContext;
        this.runningActivity = runningActivity;
    }

    public Activity getRunningActivity() {
        return runningActivity;
    }

    public static Context getRunningContext() {
        return runningContext;
    }


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        context_base = this;

        setSelf(this);
        setParams(getIntent().getExtras());
        setContentView(getActivityLayout());
        getUi().setView(findViewById(android.R.id.content));
        if (getRunningActivity() != null)
            TAG = getRunningActivity().getClass().getSimpleName();
    }

    @Override
    public void onStart() {
        super.onStart();
        setHandler(new Handler());
        setStopped(false);
        appController = AppController.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setPaused(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    protected void onStop() {
        super.onStop();
        setHandler(new Handler());
        setStopped(true);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

        /**
         * Esto sirve para que la sesión se esté actualizando en caso que el fragmento esté activo
         */

    }

    public static AppController getAppController() {
        return appController;
    }


    public static void setUsuario(Usuario usuario) {
        getAppController().setUsuario(usuario);
    }

    public Usuario getUsuario() {
        return getAppController().getUsuario();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void startOf(ActivityBase activity, boolean endLast) {
        Intent intent = new Intent(activity, this.getClass());
        if (getParams().size() > 0) {
            intent.putExtras(getParams());
        }
        activity.startActivity(intent);
        getParams().clear();
        if (endLast) {
            activity.finish();
        }
    }


    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public Bundle getParams() {
        return params;
    }

    public void setParams(Bundle params) {
        this.params = params;
    }

    public ActivityBase() {
        setUi(new Ui(this));
    }

    public void setActivityLayout(int activityLayout) {
        this.activityLayout = activityLayout;
    }

    public int getActivityLayout() {
        return this.activityLayout;
    }


    public void startOf(ActivityBase activity) {
        startOf(activity, false);
    }

    public void startAndDestroy(ActivityBase activity) {
        startOf(activity, true);
    }

    public void transitionLeft() {
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public void transitionRight() {
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }


    public LinearLayoutManager getLinearLayoutManagerVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }


    public void goHome() {
        //  startActivity(new Intent(this, ContratosActivity.class));
        finish();
        transitionRight();

    }

    public void goBack() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int fragmentCount = fragmentManager.getBackStackEntryCount();
        if (fragmentCount > 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(fragmentCount - 1);
            String fragmentTag = backEntry.getName();
            Fragment fragmentBase = fragmentManager.findFragmentByTag(fragmentTag);
            if (fragmentBase != null) {
                if (((FragmentBase) fragmentBase).onBack()) {
                    goBack();
                }
            } else {
                goBack();
            }
        } else {
            goBack();
        }
    }


    /*****************************************************************************************************************
     * SECCION DRAWER
     ****************************************************************************************************************/
    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public void menuToggle() {
        if (getDrawer() != null) {
            if (!isMenuVisible()) {
                getDrawer().openDrawer(GravityCompat.START);
            } else {
                getDrawer().closeDrawer(GravityCompat.START);
            }
        }
    }

    public boolean isMenuVisible() {
        return (getDrawer() != null && getDrawer().isDrawerOpen(GravityCompat.START)) ? true : false;
    }

    public void menuActivated(boolean show) {

        if (getDrawer() != null) {

            if (show) {
                DrawerArrowDrawable menuIcon = new DrawerArrowDrawable(this);
                menuIcon.setColor(getResources().getColor(R.color.blanco));
                toolbar.setNavigationIcon(menuIcon);
                getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            } else {
                toolbar.setNavigationIcon(null);
                getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

        }

    }

    public boolean closeDrawer() {
        if (getDrawer() != null && getDrawer().isDrawerOpen(GravityCompat.START)) {
            getDrawer().closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    public DrawerLayout initNavigationDrawer(int navigationDrawerId, final boolean isInit) {

        if (getToolbar() == null)
            throw new RuntimeException(getSelf().toString() + " Es necesario inicializar el toolbar \n setToolbar(toolbar);");

        DrawerLayout drawer = (DrawerLayout) findViewById(navigationDrawerId);
        final ActivityBase activity = this;

        setDrawer(drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, getDrawer(), getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getDrawer().setDrawerListener(toggle);
        toggle.syncState();


       /* if (getContrato() == null) {

            Drawer.cargarNavigationDrawerHeaderNormal(this, this, getUsuario());
            NavigationDrawer.construirMenuDinamicoPrincipal(this, this);

        } else {
            Drawer.cargarNavigationDrawerHeaderContratoSelected(this, this, getUsuario(), getContrato());
            NavigationDrawer.construirMenuDinamicoContratoSelected(this, this);

            if (!isInit) {
                ImageView botonContrato = (ImageView) findViewById(R.id.imgvNavHeaderContratoDetalleContrato);
                botonContrato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContrato(null);
                        closeDrawer();
                        finish();
                    }
                });
            }
        }

        LinearLayout cerrarSesion = (LinearLayout) getDrawer().findViewById(R.id.llActivityPrincipalBotonSimuladoCerrarSesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInit) {
                    ContratosActivity.getInstanceContratosActivity().menuPresionado = true;
                }
                alertDialogCerrarSesion();
                // FuncionesBr.alertDialogCerrarSesion(activity, activity, Principal.getInstancePrincipal().userValidation.getClientID(), Principal.getInstancePrincipal().userValidation.getToken());
            }
        });*/

        return getDrawer();
    }

    public DrawerLayout initNavigationDrawer(int navigationDrawerId) {
        return initNavigationDrawer(navigationDrawerId, false);
    }


    public void alertDialogCerrarSesion() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        /**CERRAR SESION COMPLETAMENTE***/

                        dialog.dismiss();

                        setUsuario(null);

                        new LoginActivity().startAndDestroy(getSelf());
                        transitionRight();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        /***CANCELAR Y NO CERRAR NADA**/
                        dialog.dismiss();
                        break;
                }
            }
        };

        String mensaje = getResources().getString(R.string.generalAvisoCerrarSesion);
        DialogYesNo dialogYesNo = DialogYesNo.newInstance("", mensaje, 1, dialogClickListener);
        dialogYesNo.show(getSupportFragmentManager(), "DialogYesNo");

    }


// PERMISOS  APP


    public boolean hasPermission(String perm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (PackageManager.PERMISSION_GRANTED == checkSelfPermission(perm));
        }
        return true;
    }

    public boolean canAccesReadStorage() {
        return (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    public boolean canAccesWriteStorage() {
        return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    public boolean canAccesPhone() {
        return (hasPermission(Manifest.permission.CALL_PHONE));
    }

    /**
     * https://android-developers.googleblog.com/2009/01/can-i-use-this-intent.html
     * <p>
     * Indicates whether the specified action can be used as an intent. This
     * method queries the package manager for installed packages that can
     * respond to an intent with the specified action. If no suitable package is
     * found, this method returns false.
     *
     * @param context The application's environment.
     * @param action  The Intent action to check for availability.
     * @return True if an Intent with the specified action can be sent and
     * responded to, false otherwise.
     */

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

}
