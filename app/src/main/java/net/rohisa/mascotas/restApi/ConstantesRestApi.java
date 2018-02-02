package net.rohisa.mascotas.restApi;

/**
 * Created by frojash on 1/15/18.
 */

public final class ConstantesRestApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3566474836.f967fdd.7f6321311bd740c5934e1ad763733359";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER =  "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    public static final String ROOT_URL_BASE = "https://tranquil-garden-45815.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario";

}
