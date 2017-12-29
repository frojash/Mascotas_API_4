package net.rohisa.mascotas.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Fernando Rojas on 19/12/2017.
 */

public class RecyclerViewFragment extends Fragment {
    private RecyclerView rvMascotas;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
        InicializarDatos();
        InicializarAdaptador();

        return v;
    }



    private void InicializarAdaptador() {

        //Ordena la lista
        Collections.sort(mascotas, new Comparator<Mascota>() {
            public int compare(Mascota o1, Mascota o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
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

    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }




}
