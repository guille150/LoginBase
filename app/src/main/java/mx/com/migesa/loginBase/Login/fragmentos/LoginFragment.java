package mx.com.migesa.loginBase.Login.fragmentos;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mx.com.migesa.loginBase.Core.Interface.OnFragmentListener;
import mx.com.migesa.loginBase.Core.Module.FragmentBase;
import mx.com.migesa.loginBase.Login.Services.LoginApi;
import mx.com.migesa.loginBase.R;
import mx.com.migesa.loginBase.entidades.login.LoginBody;
import mx.com.migesa.loginBase.entidades.login.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link FragmentBase} subclass.
 */
public class LoginFragment extends FragmentBase implements View.OnClickListener {


    private OnFragmentListener listenerFragment;

    public LoginFragment() {
        // Required empty public constructor
    }

    Button btn_iniciar;
    LoginApi loginApi;
    EditText et_usuario;
    EditText et_password;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView mLogoView;
    private TextInputLayout mFloatLabelUserId;
    private TextInputLayout mFloatLabelPassword;
    RelativeLayout rl_logo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginApi = getRetrofit().create(LoginApi.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        setView(v);
        setCheckAlarm(false);
        et_usuario = (EditText) v.findViewById(R.id.et_usuario);
        et_password = (EditText) v.findViewById(R.id.et_password);
        mLoginFormView = getView(R.id.login_form);
        mProgressView = getView(R.id.login_progress);
       // mLogoView = getImageView(R.id.image_logo);
        btn_iniciar = getButton(R.id.btn_iniciar);
        mFloatLabelUserId = (TextInputLayout) v.findViewById(R.id.float_label_user_id);
        mFloatLabelPassword = (TextInputLayout) v.findViewById(R.id.float_label_password);
        rl_logo = getRelativeLayout(R.id.rl_logo);

        et_usuario.setText("27000");
        et_password.setText("12345678");

        btn_iniciar.setOnClickListener(this);

        // Setup
        et_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    if (!isOnline()) {
                        showToast(getString(R.string.error_network));
                        return false;
                    }
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_iniciar:

                if (isOnline()) {
                    attemptLogin();
                } else
                    showToast(getString(R.string.error_network));
                break;
        }
    }

    private void attemptLogin() {

        // Reset errors.
        mFloatLabelUserId.setError(null);
        mFloatLabelPassword.setError(null);

        // Store values at the time of the login attempt.
        String userId = et_usuario.getText().toString();
        String password = et_password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Verificar si el ID tiene contenido.
        if (TextUtils.isEmpty(userId)) {
            mFloatLabelUserId.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelUserId;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Mostrar el indicador de carga y luego iniciar la petición asíncrona.
            showProgress(true);

            Call<Usuario> loginCall = loginApi.login(new LoginBody(userId, password, "1234"));

            Log.i("Login", "Url" + loginCall.request().url());
            ;
            loginCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    // Mostrar progreso
                    showProgress(false);

                    Log.i("LoginFragment", "-" + response.body());

                    int codigo = 0;
                    String mensaje = "";
                    String inicio_sesion = "";
                    String refresh_token = "b823eb28-b3c1-45f7-a247-2670a8dc3c29";
                    int tiempo_vida = 20;
                    String token = "0334bdbe-b0cb-4d59-a7e3-55b0231d05d5";

                    //Datos Usuario
                    String nombre = "Laura";
                    String apellidos = "Flores Perez";
                    String client_id = "23143519";
                    String email = "laura_flores@migesa.com.mx";
                    int estatus = 2;
                    String fecha_actualizacion = "23/01/2017 12:00:00 a.m.";
                    String fecha_registro = "23/01/2017 12:00:00 a.m.";
                    int id_tipo_usuario = 1;
                    String imagen = "default";

                    Usuario usuario = new Usuario(codigo, mensaje, inicio_sesion, refresh_token, tiempo_vida, token, nombre, apellidos, client_id, email, estatus, fecha_actualizacion, fecha_registro, id_tipo_usuario, imagen);

//                  getAppController().setUsuario(response.body());
                    getAppController().setUsuario(usuario);

                    listenerFragment.onFragment(1, "", null);

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    showProgress(false);
                    showToast(t.getMessage());
                }
            });
        }
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        int visibility = show ? View.GONE : View.VISIBLE;
        rl_logo.setVisibility(visibility);
        mLoginFormView.setVisibility(visibility);
        btn_iniciar.setVisibility(visibility);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            listenerFragment = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debes implementar OnFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerFragment = null;
    }
}
