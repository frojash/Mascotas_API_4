package net.rohisa.mascotas.restApi;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.restApi.model.MascotaResponse;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by frojash on 1/15/18.
 */

public interface EndpointsApi {

    //Trae las immagenes recientes de mi usuario
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    //Da like a una imagen de instagram
    @FormUrlEncoded
    @POST()
    Call<Gson> setLikeMedia(@Url String url, @Field("access_token") String token);

    //Trae las immagenes recientes de otro usuario
    @GET
    Call<MascotaResponse> getRecentMediaAnother(@Url String url);

    //Trae el dato de un like, con el fon de notificar
    @GET(ConstantesRestApi.KEY_GET_LIKE_NOTIFICATION)
    Call<UsuarioResponse> getlikeForNotification(@Path("id") String id, @Path ("usuario") String usuario);

    //Trae la informacion de un usuario (id codigo), a partir de su username
    @GET
    Call<UsuarioResponse> getSearchUserCode(@Url String url);

    //Registra un token y su usuario de instagram
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("token") String token, @Field("usuario") String usuario);

    //Registra un like en mi servidor o mi conteo personal
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_LIKE)
    Call<UsuarioResponse> registrarLikeID(@Field("token") String token, @Field("usuario") String usuario,  @Field("foto") String foto);

}
