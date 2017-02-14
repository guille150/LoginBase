package mx.com.migesa.loginBase.Login.Services;

import mx.com.migesa.loginBase.entidades.login.LoginBody;
import mx.com.migesa.loginBase.entidades.login.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Guillermo Ortiz on 18/01/17.
 */

public interface LoginApi {
    @POST("inicio/login")
    Call<Usuario> login(@Body LoginBody loginBody);
}
