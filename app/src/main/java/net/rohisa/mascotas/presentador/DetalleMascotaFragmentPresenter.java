package net.rohisa.mascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import net.rohisa.mascotas.db.ConstructorMascotas;
import net.rohisa.mascotas.fragments.IDetalleMascotaFragment;
import net.rohisa.mascotas.fragments.IRecyclerViewFragmentView;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by frojash on 1/16/18.
 */

public class DetalleMascotaFragmentPresenter implements IDetalleMascotaFragmentPresenter {
    private IDetalleMascotaFragment iDetalleMascotaFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;


    public DetalleMascotaFragmentPresenter(IDetalleMascotaFragment iDetalleMascotaFragment, Context context) {
        this.iDetalleMascotaFragment = iDetalleMascotaFragment;
        this.context = context;
        obtenerMediosRecientes();
    }


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

    @Override
    public void mostrarMascotasRV() {
        iDetalleMascotaFragment.inicializarAdaptadorRV(iDetalleMascotaFragment.crearAdaptador(mascotas));
        iDetalleMascotaFragment.generarGridLayout();
    }
}
