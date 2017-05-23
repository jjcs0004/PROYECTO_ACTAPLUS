package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.LigasListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Anio;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.JornadasLiga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

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

    //---------------------------------------------------------------------------------------//

    private void request() {

        //---------------------------------------------------------------------------------------//

        Api.getInstance(getApplicationContext()).getLigas(new Api.OnResultListener<List<Liga>>() {
            @Override
            public void onSuccess(List<Liga> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                ligas = data;
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//

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


    private void fillSpinnerAnios() {
        List<String> listaAnio = new ArrayList<String>();
        for(int i = 0 ; i < anios.size(); i++)
            listaAnio.add(anios.get(i).getAnio()) ;

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<Anio>(this, android.R.layout.simple_spinner_item, anios));
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

        List<Liga> listaLigasAnio = new ArrayList<>();

        for(int i = 0 ; i < ligas.size(); i++){
            if( anio.getId_anios() == ligas.get(i).getId_anio()){
                listaLigasAnio.add(ligas.get(i)) ;
            }
        }

        ligasAdapter = new LigasListAdapter(getApplicationContext(), listaLigasAnio);
        listLigas.setAdapter(ligasAdapter);

        listLigas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Liga liga = (Liga)parent.getItemAtPosition(position);
                Api.getInstance(getApplicationContext()).setLiga(liga);



                startActivity(new Intent(CargaAniosActivity.this, JornadaActivity.class));
            }
        });

    }


}
