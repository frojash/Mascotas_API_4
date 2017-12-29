package net.rohisa.mascotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.MainActivity;
import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;

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

        InicializarDatos();
        InicializarAdaptador();
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

        //Ordena la lista
        Collections.sort(mascotas, new Comparator<Mascota>() {
            public int compare(Mascota o1, Mascota o2) {
                if (o1.getLikes() == o2.getLikes())
                    return 0;
                return o1.getLikes() > o2.getLikes() ? -1 : 1;
            }
        });

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
