package mx.com.migesa.loginBase;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import mx.com.migesa.loginBase.Core.Module.ActivityBase;
import mx.com.migesa.loginBase.Login.LoginActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends ActivityBase {

    private static int SPLASH_TIME_OUT = 2000;
    private static Retrofit serviceInstance;

    public static void setServiceInstance(Retrofit serviceInstance) {
        MainActivity.serviceInstance = serviceInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity();
    }

    private void startActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.negro));
        }

        setServiceInstance(new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               new LoginActivity().startAndDestroy(getSelf());
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);//Desvanece actividad
            }
        }, SPLASH_TIME_OUT);
    }



//
    public void forwardZoom(View button) {
        startActivity(new Intent(this, SecondActivity.class));
        this.overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
    }

    public void left(View button) {
        startActivity(new Intent(this, SecondActivity.class));
        this.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public void fade(View button) {
        startActivity(new Intent(this, SecondActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void byDefault(View button) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
