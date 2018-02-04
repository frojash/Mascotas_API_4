package net.rohisa.mascotas.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import net.rohisa.mascotas.CuentaActivity;
import net.rohisa.mascotas.R;
import net.rohisa.mascotas.db.ConstructorMascotas;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;


/**
 * Created by frojash on 12/20/17.
 */

public class DetalleAdaptador extends RecyclerView.Adapter<DetalleAdaptador.DetalleViewHolder> {
    Activity activity;
    ArrayList<Mascota> mascotas;

    public DetalleAdaptador(ArrayList<Mascota> pMascotas, Activity pActivity) {

        this.mascotas = pMascotas;
        this.activity = pActivity;
    }

    @Override
    public DetalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detalle, parent, false);
        return new DetalleAdaptador.DetalleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetalleViewHolder detalleViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.dog1)
                .into(detalleViewHolder.imgFotoDetalle);
//        detalleViewHolder.imgFotoDetalle.setImageResource(mascota.getFoto());
        detalleViewHolder.tvlikesDetalle.setText(String.valueOf(mascota.getLikes()));

        detalleViewHolder.btnLikeInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _cantidad = 0;
                String token = FirebaseInstanceId.getInstance().getToken();

                _cantidad = mascota.getLikes() + 1;
                mascota.setLikes(_cantidad);
                Toast.makeText(activity, "Te gusta la foto " + mascota.getFotoId().toString(), Toast.LENGTH_SHORT).show();
                enviarLikeInstagram(mascota.getFotoId().toString());
                enviarLikeRegistro(token, mascota.getFotoId().toString());
            }
        });

    }

    private void enviarLikeRegistro(String token, String fotoId) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiIdToken();
        retrofit2.Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarLikeID(token, ConstantesRestApi.USUARIO, fotoId);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(retrofit2.Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("CUENTA_FIREBASE", usuarioResponse.getUsuario());
                Log.d("FOTO_ID", usuarioResponse.getFoto());
                BuscarNotificacion(usuarioResponse);
            }

            @Override
            public void onFailure(retrofit2.Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    public void enviarLikeInstagram(String fotoId) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaSearch = restApiAdapter.construyeGsonDeserializadorSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaSearch);
        String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_MEDIA +
                fotoId + ConstantesRestApi.KEY_LIKES;
        retrofit2.Call<Gson> UsuarioResponseCall = endpointsApi.setLikeMedia(url, ConstantesRestApi.ACCESS_TOKEN);

        UsuarioResponseCall.enqueue(new Callback<Gson>() {
            @Override
            public void onResponse(retrofit2.Call<Gson> call, Response<Gson> response) {
                Log.d("Aqui vamos", "1");
                if (response.isSuccessful()) {
                    Log.d("Siiiiiiiiiii", response.body().toString());
                }
                Log.d("Aqui vamos", "2");
            }

            @Override
            public void onFailure(Call<Gson> call, Throwable t) {
                Log.d("ERRORRRRR", t.toString());

            }
        });

    }

    public void BuscarNotificacion(UsuarioResponse pUsuarioResponse) {
        final UsuarioResponse usuarioResponse = new UsuarioResponse(pUsuarioResponse.getId(), "0", pUsuarioResponse.getUsuario(), pUsuarioResponse.getFoto());
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiIdToken();
        Call <UsuarioResponse> usuarioResponseCall = endpointsApi.getlikeForNotification(usuarioResponse.getId(),usuarioResponse.getUsuario());
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 = response.body();
                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("USUARIO_FIREBASE", usuarioResponse1.getUsuario());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class DetalleViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFotoDetalle;
        private TextView tvlikesDetalle;
        private ImageButton btnLikeInstagram;

        public DetalleViewHolder(View itemView) {
            super(itemView);
            imgFotoDetalle = (ImageView) itemView.findViewById(R.id.imgfotoDetalle);
            tvlikesDetalle = (TextView) itemView.findViewById(R.id.tvLikesDetalle);
            btnLikeInstagram = (ImageButton) itemView.findViewById(R.id.btnLikeInstagram);

        }


    }
}
