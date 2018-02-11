package net.rohisa.mascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import net.rohisa.mascotas.db.ConstructorMascotas;
import net.rohisa.mascotas.fragments.IDetalleMascotaFragment;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.MascotaResponse;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleOtroFragmentPresenter  implements IDetalleMascotaFragmentPresenter{
    private IDetalleMascotaFragment iDetalleMascotaFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    public String CuentaActual;


    public DetalleOtroFragmentPresenter(IDetalleMascotaFragment iDetalleMascotaFragment, Context context) {
        this.iDetalleMascotaFragment = iDetalleMascotaFragment;
        this.context = context;
        obtenerIdUsuario2();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaRecent);
        retrofit2.Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(retrofit2.Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Fallo en la conexion", t.toString());
            }
        });
    }

    public void obtenerMediosRecientesOtro() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaRecent);
        String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_ONLY +
                ConstantesRestApi.USUARIO_OTRO_ID + ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ANOTHER;
        retrofit2.Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaAnother(url);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(retrofit2.Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Fallo en la conexion", t.toString());
            }
        });
    }


    @Override
    public void mostrarMascotasRV() {
        iDetalleMascotaFragment.inicializarAdaptadorRV(iDetalleMascotaFragment.crearAdaptador(mascotas));
        iDetalleMascotaFragment.generarGridLayout();
    }

    public void obtenerIdUsuario2() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaSearch= restApiAdapter.construyeGsonDeserializadorSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaSearch);
        String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_SEARCH +
                ConstantesRestApi.USUARIO_OTRO + ConstantesRestApi.URL_GET_SEARCH;
        retrofit2.Call<UsuarioResponse> UsuarioResponseCall = endpointsApi.getSearchUserCode(url);

        UsuarioResponseCall.enqueue (new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(retrofit2.Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                final String USUARIO_SELF= "3566474836";

                UsuarioResponse usuarioResponse = response.body();
                ConstantesRestApi.USUARIO_OTRO_ID = usuarioResponse.getId();
                Log.d("VALORLISTA",  ConstantesRestApi.USUARIO_OTRO_ID);
                if (ConstantesRestApi.USUARIO_OTRO_ID.equals(USUARIO_SELF)) {
                    obtenerMediosRecientes();
                } else {
                    obtenerMediosRecientesOtro();
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Fallo en la conexion", t.toString());
            }
        });
    }


}

