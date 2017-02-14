package mx.com.migesa.loginBase.entidades.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillermo Ortiz on 18/01/17.
 */

public class LoginBody {

    @SerializedName("username")
    private String usuario;
    private String password;
    private String device_id;

    public LoginBody(String usuario, String password, String device_id) {
        this.usuario = usuario;
        this.password = password;
        this.device_id = device_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
