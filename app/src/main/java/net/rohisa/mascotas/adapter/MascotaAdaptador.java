package net.rohisa.mascotas.adapter;

import android.app.Activity;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.pojo.Mascota;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by frojash on 12/20/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    Activity activity;
    ArrayList<Mascota> mascotas;

    public MascotaAdaptador(ArrayList<Mascota> pMascotas, Activity pActivity) {

        this.mascotas = pMascotas;
        this.activity = pActivity;
    }


    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaAdaptador.MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int _cantidad = 0;

                if (mascota.isGusta()){
                    _cantidad = mascota.getLikes() - 1;
                    mascota.setGusta(false);
                    Toast.makeText(activity, "Ya no te gusta " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                    mascotaViewHolder.btnLike.setBackgroundResource(R.drawable.icons8_dog_bone_50);


                }
                else{
                    _cantidad = mascota.getLikes() + 1;
                    mascota.setLikes(_cantidad);
                    mascota.setGusta(true);
                    Toast.makeText(activity, "Te gusta " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                    mascotaViewHolder.btnLike.setBackgroundResource(R.drawable.icons8_xbox_x_40);

                }
                mascota.setLikes(_cantidad);
                mascotaViewHolder.tvLikes.setText(String.valueOf(_cantidad));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvLikes;
        private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgfoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
        }


    }
}
