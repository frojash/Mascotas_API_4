package net.rohisa.mascotas;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.rohisa.mascotas.mail.SendMailTask;
import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.MascotaResponse;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuentaActivity extends AppCompatActivity {
    private EditText etCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mostrarPreferencia();
    }


    public void GuardarCuenta(View view) {
        EditText etCuenta = (EditText) findViewById(R.id.etCuenta);
        String cuenta = etCuenta.getText().toString();
        obtenerIdUsuario(cuenta);

        Toast.makeText(CuentaActivity.this, "Se ha creado el usuario", Toast.LENGTH_SHORT).show();
    }

    public void mostrarPreferencia() {
        SharedPreferences sharedPreferences = getSharedPreferences("MisCuentas", Context.MODE_PRIVATE);
        String cuenta = sharedPreferences.getString("cuenta", "no existe esa variable").toString();
        EditText etCuenta = (EditText) findViewById(R.id.etCuenta);
        ConstantesRestApi.USUARIO = cuenta;
        etCuenta.setText(cuenta);

    }

    public void obtenerIdUsuario(String cuenta) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaSearch= restApiAdapter.construyeGsonDeserializadorSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaSearch);
        String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_SEARCH +
                cuenta + ConstantesRestApi.URL_GET_SEARCH;
        retrofit2.Call<UsuarioResponse> UsuarioResponseCall = endpointsApi.getSearchUserCode(url);

        UsuarioResponseCall.enqueue (new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(retrofit2.Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                SharedPreferences sharedPreferences = getSharedPreferences("MisCuentas", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("cuenta", usuarioResponse.getUsuario());
                editor.putString("cuenta_id", usuarioResponse.getId());
                ConstantesRestApi.USUARIO_ID = usuarioResponse.getId();
                ConstantesRestApi.USUARIO = usuarioResponse.getUsuario();
                editor.commit();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(CuentaActivity.this, "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Fallo en la conexion", t.toString());
            }
        });
    }
}
