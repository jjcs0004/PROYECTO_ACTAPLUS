package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.LigasListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Anio;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CargaAniosActivity extends AppCompatActivity {
    private Spinner spProvincias, spLocalidades;



    List<Liga> ligas;
    List<Anio> anios;

    private ListView listLigas;
    private LigasListAdapter ligasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carganios);

        listLigas = (ListView)findViewById(R.id.ligas);






        request();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.liga_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.buttonLogin ) {
            onLoginClick();
            return true;
        }

        return false;
    }

    private void onLoginClick(){
        // go to login

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        //finish();
    }

    //---------------------------------------------------------------------------------------//

    private void request() {

        //---------------------------------------------------------------------------------------//


        //Api.getInstance(getApplicationContext()).getLigas(new Api.OnResultListener<List<Liga>>() {
        Api.getInstance(getApplicationContext()).getLigas(new Api.OnResultListener<List<Liga>>() {
            @Override
            public void onSuccess(List<Liga> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                ligas = data;

                Api.getInstance(getApplicationContext()).getAnio(new Api.OnResultListener<List<Anio>>() {
                    @Override
                    public void onSuccess(List<Anio> data) {
                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                        anios = data;


                        fillSpinnerAnios();


                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//







    }


    private void fillSpinnerAnios() {

        List<String> listaAnio = new ArrayList<String>();

        for(int i = 0 ; i < anios.size(); i++)
            listaAnio.add(anios.get(i).getAnio()) ;

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter adapter = new ArrayAdapter<Anio>(this, android.R.layout.simple_spinner_item, anios);
        ArrayAdapter adapter = new ArrayAdapter<Anio>(this, R.layout.spinner_dropdown_item, anios);
        //adapter = ArrayAdapter.createFromResource(this, anios, R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //spinner.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new SpinnerListener());
    }



    public class SpinnerListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            // Lamo a un método para cargar el Spinner de ciudades
            // pasandole la posicion del elemento seleccionado en
            // el Spinner de paises

            cargaLigas((Anio) parent.getItemAtPosition(pos));

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }


    private void cargaLigas(Anio anio){

        Api.getInstance(getApplicationContext()).setJornadaConcreta(-1);

        List<Liga> listaLigasAnio = new ArrayList<>();

        for (int i = 0; i < ligas.size(); i++) {
            if (anio.getId_anios() == ligas.get(i).getId_anio()) {
                listaLigasAnio.add(ligas.get(i));
            }
        }


        ligasAdapter = new LigasListAdapter(getApplicationContext(), listaLigasAnio);




        listLigas.setAdapter(ligasAdapter);

        listLigas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Liga liga = (Liga)parent.getItemAtPosition(position);
                Api.getInstance(getApplicationContext()).setLiga(liga);

                Intent intent = new Intent(CargaAniosActivity.this, JornadaActivity.class);
               // intent.putExtra("idJornada", Api.getInstance(getApplicationContext()).getJornadaConcreta());
                startActivity(intent);
            }
        });

    }


}
