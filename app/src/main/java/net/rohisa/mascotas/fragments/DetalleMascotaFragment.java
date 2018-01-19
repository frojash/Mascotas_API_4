package net.rohisa.mascotas.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.adapter.DetalleAdaptador;
import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;
import net.rohisa.mascotas.presentador.DetalleMascotaFragmentPresenter;
import net.rohisa.mascotas.presentador.IDetalleMascotaFragmentPresenter;
import net.rohisa.mascotas.presentador.IRecyclerViewFragmentPresenter;
import net.rohisa.mascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleMascotaFragment extends Fragment implements IDetalleMascotaFragment {
    RecyclerView rvFotosMascota;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    private IDetalleMascotaFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_mascota, container, false);

        CircularImageView circularImageView = (CircularImageView) v.findViewById(R.id.miImagenCircular);
        circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        circularImageView.setBorderWidth(10);
        circularImageView.addShadow();
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(Color.RED);

        rvFotosMascota = (RecyclerView) v.findViewById(R.id.rvFotosMascota);
        generarGridLayout();
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(v.getContext(), R.dimen.espacio_cardview);
        rvFotosMascota.addItemDecoration(itemDecoration);

        presenter = new DetalleMascotaFragmentPresenter(this, getContext());

        return v;

    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvFotosMascota.setLayoutManager(gridLayoutManager);

    }

    @Override
    public DetalleAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
//        //Ordena la lista
//        Collections.sort(mascotas, new Comparator<Mascota>() {
//            public int compare(Mascota o1, Mascota o2) {
//                return o1.getNombre().compareTo(o2.getNombre());
//            }
//        });

        DetalleAdaptador adaptador = new DetalleAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(DetalleAdaptador adaptador) {
        rvFotosMascota.setAdapter(adaptador);

    }
}


class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public ItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
}