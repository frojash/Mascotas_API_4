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
import net.rohisa.mascotas.pojo.Mascota;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleMascotaFragment extends Fragment {
    RecyclerView rvFotosMascota;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();


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
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        rvFotosMascota.setLayoutManager(glm);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(v.getContext(), R.dimen.espacio_cardview);
        rvFotosMascota.addItemDecoration(itemDecoration);


        InicializarDatos();
        InicializarAdaptador();


        return v;

    }

    private void InicializarAdaptador() {
        DetalleAdaptador adaptador = new DetalleAdaptador(mascotas, getActivity());
        rvFotosMascota.setAdapter(adaptador);
    }

    private void InicializarDatos() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.dog3, 10));
        mascotas.add(new Mascota(R.drawable.dog3, 2));
        mascotas.add(new Mascota(R.drawable.dog3, 8));
        mascotas.add(new Mascota(R.drawable.dog3, 6));
        mascotas.add(new Mascota(R.drawable.dog3, 23));
        mascotas.add(new Mascota(R.drawable.dog3, 1));
        mascotas.add(new Mascota(R.drawable.dog3, 7));
        mascotas.add(new Mascota(R.drawable.dog3, 1));
        mascotas.add(new Mascota(R.drawable.dog3, 4));
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
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