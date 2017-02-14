package mx.com.migesa.loginBase.entidades.login;

import android.util.Log;

/**
 * Created by Guillermo Ortiz on 17/01/17.
 */

public class Usuario {


    private String username;
    private String password;

    private int codigo;
    private String mensaje;
    private String inicio_sesion;
    private String refresh_token;
    private int tiempo_vida;
    private String token;

    //Datos Usuario
    private String nombre;
    private String apellidos;
    private String client_id;
    private String email;
    private int estatus;
    private String fecha_actualizacion;
    private String fecha_registro;
    private int id_tipo_usuario;
    private String imagen;


    private String ubicacionZonaHoraria = "Ciudad de México";

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(int codigo, String mensaje, String inicio_sesion, String refresh_token, int tiempo_vida, String token, String nombre, String apellidos, String client_id, String email, int estatus, String fecha_actualizacion, String fecha_registro, int id_tipo_usuario, String imagen) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.inicio_sesion = inicio_sesion;
        this.refresh_token = refresh_token;
        this.tiempo_vida = tiempo_vida;
        this.token = token;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.client_id = client_id;
        this.email = email;
        this.estatus = estatus;
        this.fecha_actualizacion = fecha_actualizacion;
        this.fecha_registro = fecha_registro;
        this.id_tipo_usuario = id_tipo_usuario;
        this.imagen = imagen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getInicio_sesion() {
        return inicio_sesion;
    }

    public void setInicio_sesion(String inicio_sesion) {
        this.inicio_sesion = inicio_sesion;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getTiempo_vida() {
        return getRemainingTime();
    }

    public int getRemainingTime() {
        // Log.v("tiempo_vida", "" + tiempo_vida);

        int UN_MINUTO_EN_MILISEGUNDOS = 60000;
        int TIEMPO_DEFAULT_VEINTE_MINUTOS = 20;
        /***CONVERTIMOS EL TIEMPO RESTANTE DE MINUTOS A MILLISEGUNDOS**/
        int remainingTimeToMillis;
        int remainingTime;
        try {
            remainingTime = tiempo_vida;
            remainingTimeToMillis = UN_MINUTO_EN_MILISEGUNDOS * remainingTime;
        } catch (Exception e) {
            /**SI OCURRIESE UN ERROR DE PARSEO TOMAMOS POR DEFAULT EL TIEMPO DE 20 MINUTOS***/
            Log.e(Usuario.class.getCanonicalName(), e.toString());
            remainingTimeToMillis = UN_MINUTO_EN_MILISEGUNDOS * TIEMPO_DEFAULT_VEINTE_MINUTOS;
        }
        return remainingTimeToMillis;
    }

    public void setTiempo_vida(int tiempo_vida) {
        this.tiempo_vida = tiempo_vida;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUbicacionZonaHoraria() {
        return ubicacionZonaHoraria;
    }

    public void setUbicacionZonaHoraria(String ubicacionZonaHoraria) {
        this.ubicacionZonaHoraria = ubicacionZonaHoraria;
    }
}
