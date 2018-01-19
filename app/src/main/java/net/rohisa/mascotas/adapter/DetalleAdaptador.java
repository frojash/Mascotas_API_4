package net.rohisa.mascotas.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.pojo.Mascota;

import java.util.ArrayList;


/**
 * Created by frojash on 12/20/17.
 */

public class DetalleAdaptador extends RecyclerView.Adapter<DetalleAdaptador.DetalleViewHolder> {
    Activity activity;
    ArrayList<Mascota> mascotas;

    public DetalleAdaptador(ArrayList<Mascota> pMascotas, Activity pActivity) {

        this.mascotas = pMascotas;
        this.activity = pActivity;
    }

    @Override
    public DetalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detalle, parent, false);
        return new DetalleAdaptador.DetalleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetalleViewHolder detalleViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.dog1)
                .into(detalleViewHolder.imgFotoDetalle);
//        detalleViewHolder.imgFotoDetalle.setImageResource(mascota.getFoto());
        detalleViewHolder.tvlikesDetalle.setText(String.valueOf(mascota.getLikes()));
    }


    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class DetalleViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFotoDetalle;
        private TextView tvlikesDetalle;

        public DetalleViewHolder(View itemView) {
            super(itemView);
            imgFotoDetalle = (ImageView) itemView.findViewById(R.id.imgfotoDetalle);
            tvlikesDetalle = (TextView) itemView.findViewById(R.id.tvLikesDetalle);
        }


    }
}
