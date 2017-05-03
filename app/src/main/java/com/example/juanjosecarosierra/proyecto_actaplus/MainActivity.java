package com.example.juanjosecarosierra.proyecto_actaplus;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    private TextView text_jornada;
    private TextView text_liga;
    private TextView text_anios;
    private TextView text_partidos;
    private TextView text_arbitros;

    private Spinner spProvincias, spLocalidades;

    List<Jornada> jornadas;
    List<Liga> ligas;
    List<Anio> anios;
    List<Partido> partidos;
    List<Arbitro> arbitros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        text_jornada = (TextView)findViewById(R.id.jornadaText);
//
//        text_liga = (TextView)findViewById(R.id.ligaText);
//
//        text_anios = (TextView)findViewById(R.id.AnioText);
//
//        text_partidos = (TextView)findViewById(R.id.partidoText);
//
//        text_arbitros = (TextView)findViewById(R.id.arbitroText);

        request();

    }

    //---------------------------------------------------------------------------------------//

    private void request() {

        Api.getInstance(getApplicationContext()).getJornadas(new Api.OnResultListener<List<Jornada>>() {
            @Override
            public void onSuccess(List<Jornada> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                jornadas = data;
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

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
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//

        Api.getInstance(getApplicationContext()).getPartidos(new Api.OnResultListener<List<Partido>>() {
            @Override
            public void onSuccess(List<Partido> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                partidos = data;
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//

        Api.getInstance(getApplicationContext()).getArbitro(new Api.OnResultListener<List<Arbitro>>() {
            @Override
            public void onSuccess(List<Arbitro> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                arbitros = data;
                spineerr();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//


    }


    private void spineerr() {

        List<String> listaAnio = new ArrayList<String>();

        for(int i = 0 ; i < anios.size(); i++)
            listaAnio.add(anios.get(i).getAnio()) ;

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaAnio));

        spinner.setOnItemSelectedListener(new SpinnerListener());

    }

    public class SpinnerListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            // Lamo a un método para cargar el Spinner de ciudades
            // pasandole la posicion del elemento seleccionado en
            // el Spinner de paises
            cargaSpinner2(parent.getSelectedItemPosition());
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    private void cargaSpinner2(int posicion){

        List<String> listaLigasAnio = new ArrayList<String>();

        for(int i = 0 ; i < ligas.size(); i++){

            if( anios.get(posicion).getId_anios() == ligas.get(i).getId_anio()){

                listaLigasAnio.add(ligas.get(i).getNombre()) ;

            }

        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLigasAnio));

        spinner.setOnItemSelectedListener(new SpinnerListener2());

    }

    public class SpinnerListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            // Lamo a un método para cargar el Spinner de ciudades
            // pasandole la posicion del elemento seleccionado en
            // el Spinner de paises
            cargaSpinner3(parent.getSelectedItemPosition());
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    private void cargaSpinner3(int posicion){
        



        


    }

}
