package net.rohisa.mascotas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by frojash on 2/10/18.
 */

public class FollowUsuario extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "SEGUIR_USUARIO";
        Bundle extras = intent.getExtras();

        String accion = intent.getAction();
        String usuarioId = extras.getString("usuarioId");
        String usuario = extras.getString("usuario");

        if (accion.equals(ACCION_KEY)) {
            seguirUsuario(usuarioId);
            Toast.makeText(context,"Ahora sigues a " + usuario, Toast.LENGTH_SHORT).show();
        }
    }

    public void seguirUsuario(String cuentaId) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaSearch = restApiAdapter.construyeGsonDeserializadorSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaSearch);

        retrofit2.Call<Gson> UsuarioResponseCall = endpointsApi.seguirUsuario(cuentaId, "follow");

    }
}

