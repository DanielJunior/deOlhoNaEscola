package br.com.danieljunior.deolhonaescola.utils;

/**
 * Created by danieljunior on 09/01/17.
 */

public class UrlConstants {

    private static boolean DEVELOPMENT_MODE = false;

    public static final String API_BASE_URL_DEVELOPMENT = "http://172.28.0.15:3000";
    public static final String API_BASE_URL_PRODUCTION = "https://seeducschoolapi.herokuapp.com";
//    public static final String API_BASE_URL_DEVELOPMENT = "http://10.2.121.109:3000";

    public static String getBaseUrl(){
        return DEVELOPMENT_MODE ? API_BASE_URL_DEVELOPMENT : API_BASE_URL_PRODUCTION;
    }
}
