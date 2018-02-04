package net.rohisa.mascotas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.iid.FirebaseInstanceId;

import net.rohisa.mascotas.adapter.PageAdapter;
import net.rohisa.mascotas.fragments.DetalleMascotaFragment;
import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    //    private RecyclerView rvMascotas;
//    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MisCuentas", Context.MODE_PRIVATE);
        ConstantesRestApi.USUARIO = sharedPreferences.getString("cuenta", "no existe esa variable").toString();
        ConstantesRestApi.USUARIO_ID = sharedPreferences.getString("cuenta_id", "no existe esa variable").toString();


//        Serializable parametros = getIntent().getSerializableExtra("source");
//        mascotas = (ArrayList<Mascota>) parametros;

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();


        if(extras != null) {
            Integer tab = extras.getInt("Tab");
            if (tab == 1){
                viewPager.setCurrentItem(1);
            }

        }

//        android.support.v7.widget.Toolbar miActonBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActonBar);
//        getSupportActionBar().hide();

//        rvContactos = (RecyclerView) findViewById(R.id.rvContactos);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
////        GridLayoutManager glm = new GridLayoutManager(this,2);
//        rvContactos.setLayoutManager(llm);
//        InicializarDatos();
//        InicializarAdaptador();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rvMascotas.setLayoutManager(llm);
//
//
//        if (parametros == null) {
//            InicializarDatos();
//            InicializarAdaptador();
//        } else {
//            InicializarAdaptador();
//        }
    }

    private ArrayList<android.support.v4.app.Fragment> agregarFragments() {
        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();
        fragments.add(new net.rohisa.mascotas.fragments.RecyclerViewFragment());
        fragments.add(new DetalleMascotaFragment());
        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intent = new Intent(this, ContactenosActivity.class);
                startActivity(intent);
                break;
            case R.id.mAcerca:
                intent = new Intent(this, AcercaDeActivity.class);
                startActivity(intent);
                break;
            case R.id.mPreferidos:
                intent = new Intent(this, PreferidosActivity.class);
//                intent.putExtra("source", mascotas);
                startActivity(intent);
                break;
            case R.id.mCuenta:
                intent = new Intent(this, CuentaActivity.class);
                startActivity(intent);
                break;
            case R.id.mNotificaciones:
                RecibirNotificaciones();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void RecibirNotificaciones() {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token, ConstantesRestApi.USUARIO);
    }

    private void enviarTokenRegistro(String token, String usuario) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiIdToken();
        retrofit2.Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarTokenID(token, usuario);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(retrofit2.Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("CUENTA_FIREBASE", usuarioResponse.getUsuario());
            }

            @Override
            public void onFailure(retrofit2.Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_face);


    }


}

