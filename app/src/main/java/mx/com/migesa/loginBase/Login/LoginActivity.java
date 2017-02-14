package mx.com.migesa.loginBase.Login;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import mx.com.migesa.loginBase.Core.Interface.OnFragmentListener;
import mx.com.migesa.loginBase.Core.Module.ActivityBase;
import mx.com.migesa.loginBase.Login.fragmentos.LoginFragment;
import mx.com.migesa.loginBase.R;
import mx.com.migesa.loginBase.SecondActivity;

public class LoginActivity extends ActivityBase implements OnFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment = new LoginFragment();
        FragmentTransaction FT = getSupportFragmentManager().beginTransaction();
        FT.replace(R.id.contenedor_fragmentos, fragment, LoginFragment.class.getCanonicalName());
        FT.commit();
    }

    @Override
    public void onFragment(int resquestCode, String msj, Bundle params) {
        switch (resquestCode) {
            case 1:
                //
//                Iniciamos otra pantalla

                Log.i("" + TAG, "Inicia sesión");
                new SecondActivity().startAndDestroy(getSelf());
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;

        }
    }
}
