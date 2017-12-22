package net.rohisa.mascotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMascotas;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Serializable parametros = getIntent().getSerializableExtra("source");
        mascotas = (ArrayList<Mascota>) parametros;

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);

        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);


        if (parametros == null) {
            InicializarDatos();
            InicializarAdaptador();
        } else {
            InicializarAdaptador();
        }
    }

    private void InicializarAdaptador() {

        //Ordena la lista
        Collections.sort(mascotas, new Comparator<Mascota>() {
            public int compare(Mascota o1, Mascota o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        rvMascotas.setAdapter(adaptador);
    }

    private void InicializarDatos() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.dog1, 5, "Sam", false));
        mascotas.add(new Mascota(R.drawable.dog2, 2, "Jack", false));
        mascotas.add(new Mascota(R.drawable.dog3, 3, "Cheise", false));
        mascotas.add(new Mascota(R.drawable.dog4, 6, "Pipo", false));
        mascotas.add(new Mascota(R.drawable.dog5, 7, "Marshall", false));
        mascotas.add(new Mascota(R.drawable.dog6, 1, "Duke", false));
        mascotas.add(new Mascota(R.drawable.dog7, 8, "Spike", false));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mPreferidos:
                Intent intent = new Intent(this, PreferidosActivity.class);
                intent.putExtra("source", mascotas);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}

