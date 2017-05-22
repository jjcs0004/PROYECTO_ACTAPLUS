package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.JornadasListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.LigasListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Anio;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Jornada;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;

public class JornadasAnioActivity extends AppCompatActivity {

    List<Jornada> jornadasliga;

    private ListView listJornadas;
    private JornadasListAdapter jornadasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornadasanio);

        listJornadas = (ListView)findViewById(R.id.jornadas);

        request();

    }

    //---------------------------------------------------------------------------------------//

    private void request() {

        Api.getInstance(getApplicationContext()).getJornadaLiga(new Api.OnResultListener<List<Jornada>>() {
            @Override
            public void onSuccess(List<Jornada> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                jornadasliga = data;
                cargaJornadas();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//



    }

    private void cargaJornadas(){

        List<Jornada> listasJornadasLiga = new ArrayList<>();

        for(int i = 0 ; i < jornadasliga.size(); i++){

            listasJornadasLiga.add(jornadasliga.get(i)) ;

        }

        jornadasAdapter = new JornadasListAdapter(getApplicationContext(), listasJornadasLiga);

        listJornadas.setAdapter(jornadasAdapter);

        listJornadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Jornada jornada = (Jornada)parent.getItemAtPosition(position);
                Api.getInstance(getApplicationContext()).setJornada(jornada);
                startActivity(new Intent(JornadasAnioActivity.this, JornadaActivity.class));
            }
        });
    }


}
