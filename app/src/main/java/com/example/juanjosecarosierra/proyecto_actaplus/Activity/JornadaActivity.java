package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.JornadaPartidosListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Anio;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.JornadasLiga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class JornadaActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemClickListener {

    private TextView textLiga;
    private Button buttonJornadas;
    private ListView lista;

    private JornadaPartidosListAdapter adaptador;

    List<JornadasLiga> jornadasliga;
    List<Partido> partidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornada);

        buttonJornadas = (Button)findViewById(R.id.buttonJornada);
        buttonJornadas.setOnClickListener(this);

        textLiga = (TextView)findViewById(R.id.jornadaLiga) ;
        lista = (ListView)findViewById(R.id.jornadas) ;

        request();
    }


    private void request() {

        //---------------------------------------------------------------------------------------//

        if ( jornadasliga == null || jornadasliga.isEmpty() ) {

            Api.getInstance(getApplicationContext()).getJornadasLiga(new Api.OnResultListener<List<JornadasLiga>>() {
                @Override
                public void onSuccess(List<JornadasLiga> data) {
                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();

                    jornadasliga = data;

                    //fillUi();

                    if (jornadasliga.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "No hay jornadas en la liga", Toast.LENGTH_LONG).show();
                    }
                    else {

                        final JornadasLiga jornada = jornadasliga.get(jornadasliga.size() - 1);

                        Api.getInstance(getApplicationContext()).getPartidosJornada(jornada, new Api.OnResultListener<List<Partido>>() {
                            @Override
                            public void onSuccess(List<Partido> data) {
                                partidos = data;

                                fillUi(jornada);
                            }

                            @Override
                            public void onError(String error) {
                                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Api.getInstance(getApplicationContext()).getPartidosJornada(Api.getInstance(getApplicationContext()).getJornada(), new Api.OnResultListener<List<Partido>>() {
                @Override
                public void onSuccess(List<Partido> data) {
                    partidos = data;

                    fillUi( Api.getInstance(getApplicationContext()).getJornada() );
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    private void fillUi(JornadasLiga jornada) {

        textLiga.setText(jornada.getNombre());

        if (partidos.isEmpty() ) {
            Toast.makeText(getApplicationContext(), "No hay partidos en esta jornada", Toast.LENGTH_LONG).show();
        }
        else {
            adaptador = new JornadaPartidosListAdapter(getApplicationContext(), partidos);
            lista.setAdapter(adaptador);
            lista.setOnItemClickListener(this);
        }
    }



    private void onJornadasClick() {
        Intent intent = new Intent(JornadaActivity.this, JornadasAnioActivity.class);
        int requestCode=0;
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == 0 ) {
            request();
        }
    }

    public void onClick(View v) {
        if ( v == buttonJornadas ) {
            onJornadasClick();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Partido partido = (Partido)lista.getItemAtPosition(position);
        Intent intent = new Intent(this, ImagenActaActivity.class);
        intent.putExtra("imagenActa", partido.getActa());
        startActivity(intent);

    }
}