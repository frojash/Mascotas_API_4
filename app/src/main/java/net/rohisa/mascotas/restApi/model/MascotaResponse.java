package net.rohisa.mascotas.restApi.model;

import android.media.MediaActionSound;

import net.rohisa.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by frojash on 1/15/18.
 */

public class MascotaResponse {
    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas(){
        return  mascotas;
    }

    public  void setMascotas (ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;

    }
}
