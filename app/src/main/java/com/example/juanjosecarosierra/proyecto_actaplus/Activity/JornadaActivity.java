package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Jornada;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;

public class JornadaActivity extends AppCompatActivity {

    private TextView textLiga;

    List<Jornada> jornadasliga;

    private Jornada jornada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornada);

        request();
    }


    private void request() {

        Api.getInstance(getApplicationContext()).getJornadaLiga(new Api.OnResultListener<List<Jornada>>() {
            @Override
            public void onSuccess(List<Jornada> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                jornadasliga = data;
                fillUi();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

        //---------------------------------------------------------------------------------------//



    }

    private void fillUi() {


        List<Jornada> JornadasLiga = new ArrayList<>();

        for(int i = 0 ; i < jornadasliga.size(); i++){

                JornadasLiga.add(jornadasliga.get(i)) ;

        }

        textLiga = (TextView)findViewById(R.id.jornadaLiga);

        if(JornadasLiga.get(JornadasLiga.size()-1).getNombre() != null) {

            textLiga.setText(JornadasLiga.get(JornadasLiga.size() - 1).getNombre());

        }else{

            textLiga.setText("No existe Jornada");

        }


    }
}
