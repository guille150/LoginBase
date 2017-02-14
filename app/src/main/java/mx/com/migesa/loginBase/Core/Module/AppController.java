package mx.com.migesa.loginBase.Core.Module;

import android.app.Application;

import mx.com.migesa.loginBase.Config;
import mx.com.migesa.loginBase.entidades.login.Usuario;


/**
 * Created by Guillermo Ortiz on 17/01/17.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

    public static AppController getInstance() {
        return AppController.mInstance;
    }


    private Usuario usuario;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //Inicializamos contantes
        Config.init();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public boolean isSesion_terminada() {
        return sesion_terminada;
    }

    public void setSesion_terminada(boolean sesion_terminada) {
        this.sesion_terminada = sesion_terminada;
    }

    /**
     * cuando la sesión se termine por tiempo, esta se le seteará por true
     * por que el fragmento en turno, enviará una nueva instancia de la actividad
     */
    private boolean sesion_terminada = false;
}
