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

import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.R;
import net.rohisa.mascotas.presentador.IRecyclerViewFragmentPresenter;
import net.rohisa.mascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Fernando Rojas on 19/12/2017.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {
    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        //Ordena la lista
        Collections.sort(mascotas, new Comparator<Mascota>() {
            public int compare(Mascota o1, Mascota o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}
