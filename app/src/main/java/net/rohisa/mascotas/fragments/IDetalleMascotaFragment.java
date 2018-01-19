package net.rohisa.mascotas.fragments;

import net.rohisa.mascotas.adapter.DetalleAdaptador;
import net.rohisa.mascotas.adapter.MascotaAdaptador;
import net.rohisa.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by frojash on 1/16/18.
 */

public interface IDetalleMascotaFragment {

    public void generarGridLayout();
    public DetalleAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(DetalleAdaptador adaptador);
}
