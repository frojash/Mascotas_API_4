package net.rohisa.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.rohisa.mascotas.adapter.PageAdapter;
import net.rohisa.mascotas.fragments.DetalleMascotaFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //    private RecyclerView rvMascotas;
//    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Serializable parametros = getIntent().getSerializableExtra("source");
//        mascotas = (ArrayList<Mascota>) parametros;

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.icons8_cat_footprint_filled_50);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();
//        android.support.v7.widget.Toolbar miActonBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActonBar);
//        getSupportActionBar().hide();

//        rvContactos = (RecyclerView) findViewById(R.id.rvContactos);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
////        GridLayoutManager glm = new GridLayoutManager(this,2);
//        rvContactos.setLayoutManager(llm);
//        InicializarDatos();
//        InicializarAdaptador();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rvMascotas.setLayoutManager(llm);
//
//
//        if (parametros == null) {
//            InicializarDatos();
//            InicializarAdaptador();
//        } else {
//            InicializarAdaptador();
//        }
    }

    private ArrayList<android.support.v4.app.Fragment> agregarFragments() {
        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();
        fragments.add(new net.rohisa.mascotas.fragments.RecyclerViewFragment());
        fragments.add(new DetalleMascotaFragment());
        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intent = new Intent(this, ContactenosActivity.class);
                startActivity(intent);
                break;
            case R.id.mAcerca:
                intent = new Intent(this, AcercaDeActivity.class);
                startActivity(intent);
                break;
            case R.id.mPreferidos:
                intent = new Intent(this, PreferidosActivity.class);
//                intent.putExtra("source", mascotas);
                startActivity(intent);
                break;
            case R.id.mCuenta:
                intent = new Intent(this, CuentaActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_face);


    }


}

