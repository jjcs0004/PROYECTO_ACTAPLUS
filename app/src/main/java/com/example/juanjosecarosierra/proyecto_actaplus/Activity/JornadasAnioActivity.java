package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.app.Activity;
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
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.JornadasLiga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;

public class JornadasAnioActivity extends AppCompatActivity {

    List<JornadasLiga> jornadasliga;

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

        Api.getInstance(getApplicationContext()).getJornadasLiga(new Api.OnResultListener<List<JornadasLiga>>() {
            @Override
            public void onSuccess(List<JornadasLiga> data) {
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

        List<JornadasLiga> listasJornadasLiga = new ArrayList<>();

        JornadasLiga jornada = new JornadasLiga();

        if(jornadasliga.size() < 1) {

            String juanjo = "No existen Jornadas";
            jornada.setNombre(juanjo);
            listasJornadasLiga.add(jornada);

        }else {

            for (int i = 0; i < jornadasliga.size(); i++) {

                listasJornadasLiga.add(jornadasliga.get(i));

            }
        }

        jornadasAdapter = new JornadasListAdapter(getApplicationContext(), listasJornadasLiga);

        listJornadas.setAdapter(jornadasAdapter);

        listJornadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                JornadasLiga jornada = (JornadasLiga)parent.getItemAtPosition(position);

                Api.getInstance(getApplicationContext()).setJornada(jornada);

                //Api.getInstance(getApplicationContext()).setJornadaConcreta(jornada.getId_jornadas());

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }


}
