package mx.com.migesa.loginBase;

/**
 * Created by Guillermo Ortiz on 18/01/17.
 */

public class Config {

    public static String BASE_URL;
    public static int CODIGO_ERROR;


    public static void init() {
        // BASE_URL = "https://arthropodal-milesto.000webhostapp.com";
//        BASE_URL = "http://192.168.8.50:80/~Admin/Casadebolsa/v1/";//local Red MGSWIRESLESS
        BASE_URL = "http://192.168.8.58/~Admin/Casadebolsa/v1/";//local Red Soft/M
        CODIGO_ERROR = 2;
    }
}
