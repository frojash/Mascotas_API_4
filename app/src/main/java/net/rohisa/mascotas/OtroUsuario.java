package net.rohisa.mascotas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.iid.FirebaseInstanceId;

import net.rohisa.mascotas.adapter.DetalleAdaptador;
import net.rohisa.mascotas.adapter.PageAdapter;
import net.rohisa.mascotas.fragments.DetalleMascotaFragment;
import net.rohisa.mascotas.fragments.DetalleOtroFragment;
import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;


public class OtroUsuario extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_otro_usuario);

        String usuario = extras.getString("Usuario");
        String usuarioId = extras.getString("usuarioId");


        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);

        viewPager = (ViewPager) findViewById(R.id.viewPager2);

        setUpViewPager();

    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();
        fragments.add(new DetalleOtroFragment());
        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
    }


}

