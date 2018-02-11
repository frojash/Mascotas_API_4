package net.rohisa.mascotas.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.adapter.DetalleAdaptador;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.presentador.DetalleOtroFragmentPresenter;
import net.rohisa.mascotas.presentador.IDetalleMascotaFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleOtroFragment extends Fragment implements IDetalleMascotaFragment {
    RecyclerView rvFotosMascota;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    private IDetalleMascotaFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("VALORLISTA",  "1");

        View v = inflater.inflate(R.layout.fragment_detalle_otro, container, false);

        rvFotosMascota = (RecyclerView) v.findViewById(R.id.rvFotosMascota2);
        generarGridLayout();
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(v.getContext(), R.dimen.espacio_cardview);
        rvFotosMascota.addItemDecoration(itemDecoration);

        presenter = new DetalleOtroFragmentPresenter(this, getContext());

        return v;

    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvFotosMascota.setLayoutManager(gridLayoutManager);

    }

    @Override
    public DetalleAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {

        DetalleAdaptador adaptador = new DetalleAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(DetalleAdaptador adaptador) {
        rvFotosMascota.setAdapter(adaptador);

    }
}


