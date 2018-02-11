package net.rohisa.mascotas.db;

import android.content.ContentValues;
import android.content.Context;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by frojash on 1/2/18.
 */

public class ConstructorMascotas {

    private static final Integer LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;

    }

    public ArrayList<Mascota> ObtenerDatos() {
//        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
//
//        mascotas.add(new Mascota( R.drawable.dog1, 5, "Sam", false));
//        mascotas.add(new Mascota( R.drawable.dog2, 2, "Jack", false));
//        mascotas.add(new Mascota( R.drawable.dog3, 3, "Cheise", false));
//        mascotas.add(new Mascota( R.drawable.dog4, 6, "Pipo", false));
//        mascotas.add(new Mascota( R.drawable.dog5, 7, "Marshall", false));
//        mascotas.add(new Mascota( R.drawable.dog6, 1, "Duke", false));
//        mascotas.add(new Mascota( R.drawable.dog7, 8, "Spike", false));
//        return mascotas;

        BaseDatos db = new BaseDatos(context);
        //insertarContactos(db);

        return db.obtenerTodasLasMascotas();
    }

    public void insertarContactos(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Sam");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog1);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Jack");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog2);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Cheise");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog3);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Pipo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog4);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Marshall");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog5);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Duke");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog6);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Spike");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog7);
        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Oso");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_GUSTA, 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog8);
        db.insertarMascotas(contentValues);

    }

    public void darLikeMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikesMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
}
