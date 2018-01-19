package net.rohisa.mascotas;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.rohisa.mascotas.mail.SendMailTask;

import java.util.ArrayList;

public class CuentaActivity extends AppCompatActivity {
    private EditText etCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mostrarPreferencia();
    }


    public void  GuardarCuenta(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MisCuentas", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText etCuenta = (EditText) findViewById(R.id.etCuenta);

        String cuenta = etCuenta.getText().toString();

        editor.putString("cuenta", cuenta);

        editor.commit();
        Toast.makeText(CuentaActivity.this, "Se ha creado el usuario", Toast.LENGTH_SHORT).show();
    }

    public void mostrarPreferencia() {
        SharedPreferences sharedPreferences = getSharedPreferences("MisCuentas", Context.MODE_PRIVATE);
        String cuenta = sharedPreferences.getString("cuenta", "no existe esa variable").toString();
        EditText etCuenta = (EditText) findViewById(R.id.etCuenta);
        etCuenta.setText(cuenta);

    }
}
