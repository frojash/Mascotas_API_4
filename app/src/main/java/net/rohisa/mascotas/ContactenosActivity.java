package net.rohisa.mascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import net.rohisa.mascotas.R;
import net.rohisa.mascotas.mail.SendMailTask;

import java.util.ArrayList;


public class ContactenosActivity extends AppCompatActivity {
    private EditText etNombre;
    private EditText etEmail;
    private EditText etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void Enviar_Correo(View view) {

//        Log.i("SendMailActivity", "Send Button Clicked.");

//        String fromEmail = ((TextView) findViewById(R.id.editText1))
//                .getText().toString();
//        String fromPassword = ((TextView) findViewById(R.id.editText2))
//                .getText().toString();
//        String toEmails = ((TextView) findViewById(R.id.editText3))
//                .getText().toString();
        ArrayList<String> toEmailList = new ArrayList<String>();
        toEmailList.add("frojash@outlook.com");
//                .split("\\s*,\\s*"));
//        String emailSubject = ((TextView) findViewById(R.id.editText4))
//                .getText().toString();
//        String emailBody = ((TextView) findViewById(R.id.editText5))
//                .getText().toString();
        new SendMailTask(this).execute("something@gmail.com",
                "1234", toEmailList, "Prueba Android", "Hola");

//        // TODO Auto-generated method stub
//        try {
//            etNombre = (EditText) findViewById(R.id.etNombre);
//            etEmail = (EditText) findViewById(R.id.etEmail);
//            etDescripcion = (EditText) findViewById(R.id.etDescripcion);
//
//            GMailSender sender = new GMailSender("crnando@gmail.com", "rjshF17.a");
//            sender.sendMail("Prueba Email Android",
//                    "Prueba de correo",
//                    "crnando@gmail.com",
//                    "frojash@outlook.com");
//            Toast.makeText(this,"Correo enviado satisfactoriamente",Toast.LENGTH_LONG);
//        } catch (Exception e) {
//            Log.e("SendMail", e.getMessage(), e);
//        }
    }

}
