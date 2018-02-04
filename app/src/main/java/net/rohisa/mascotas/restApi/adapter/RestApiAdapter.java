package net.rohisa.mascotas.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.deserializador.MascotaDeserializador;
import net.rohisa.mascotas.restApi.deserializador.UsuarioDeserializador;
import net.rohisa.mascotas.restApi.model.MascotaResponse;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frojash on 1/16/18.
 */

public class RestApiAdapter {

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public EndpointsApi establecerConexionesRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }


    //https://tranquil-garden-45815.herokuapp.com/registrar-usuario
    //https://tranquil-garden-45815.herokuapp.com/registrar-like
    public EndpointsApi establecerConexionesRestApiIdToken(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();

    }

    public Gson construyeGsonDeserializadorSearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioDeserializador());
        return gsonBuilder.create();

    }
}
