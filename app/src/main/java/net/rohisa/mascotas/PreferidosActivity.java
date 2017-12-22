package net.rohisa.mascotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageButton;

import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PreferidosActivity extends AppCompatActivity {
    private RecyclerView rvMascotasPreferidas;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferidos);
        Serializable parametros = getIntent().getSerializableExtra("source");
        mascotas = (ArrayList<Mascota>) parametros;

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvMascotasPreferidas = (RecyclerView) findViewById(R.id.rvMascotasPreferidas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotasPreferidas.setLayoutManager(llm);

        //Ordena la lista
        Collections.sort(mascotas, new Comparator<Mascota>() {
            public int compare(Mascota o1, Mascota o2) {
                if (o1.getLikes() == o2.getLikes())
                    return 0;
                return o1.getLikes() > o2.getLikes() ? -1 : 1;
            }
        });

        InicializarAdaptador();
    }


    private void InicializarAdaptador() {
        ArrayList<Mascota> _nuevaMascotas = mascotas;
        _nuevaMascotas.subList(5, _nuevaMascotas.size()).clear();

        MascotaAdaptador adaptador = new MascotaAdaptador(_nuevaMascotas, this);
        rvMascotasPreferidas.setAdapter(adaptador);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("source", mascotas);
        startActivity(intent);

        return super.onOptionsItemSelected(item);

    }

}
