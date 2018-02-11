package net.rohisa.mascotas.restApi;

/**
 * Created by frojash on 1/15/18.
 */

public final class ConstantesRestApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3566474836.f967fdd.7f6321311bd740c5934e1ad763733359";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_ACCESS_TOKEN2 = "&access_token=";

    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String KEY_GET_SEARCH = "users/search?q=";
    public static final String KEY_GET_MEDIA = "media/";
    public static final String KEY_LIKES = "/likes";

    public static final String KEY_GET_RECENT_MEDIA_USER_ONLY = "users/";
    public static final String KEY_GET_RECENT_MEDIA_USER_MEDIA = "/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_RECENT_MEDIA_USER_ANOTHER = KEY_GET_RECENT_MEDIA_USER_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_SEARCH = KEY_ACCESS_TOKEN2 + ACCESS_TOKEN;
    public static final String KEY_GET_LIKE_NOTIFICATION = "notificar-like/{id}/{usuario}";

    public static final String KEY_FOLLOW = "users/{usuario}/relationship" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    public static final String ROOT_URL_BASE = "https://tranquil-garden-45815.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario";
    public static final String KEY_POST_ID_LIKE = "registrar-like";
    public static String USUARIO;
    public static String USUARIO_OTRO;
    public static String USUARIO_ID;
    public static String USUARIO_OTRO_ID;


    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    //ttps://api.instagram.com/v1/media/{media-id}/likes
    //https://api.instagram.com/v1/users/{usuario}/relationship?access_token=3566474836.f967fdd.7f6321311bd740c5934e1ad763733359


}
