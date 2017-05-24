package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Anio;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.JornadasLiga;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class JornadaActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textLiga;

    List<JornadasLiga> jornadasliga;

    private Button buttonJornadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornada);

        buttonJornadas = (Button)findViewById(R.id.buttonJornada);
        buttonJornadas.setOnClickListener(this);

        request();
    }


    private void request() {

        //---------------------------------------------------------------------------------------//

        Api.getInstance(getApplicationContext()).getJornadasLiga(new Api.OnResultListener<List<JornadasLiga>>() {
            @Override
            public void onSuccess(List<JornadasLiga> data) {
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


        //Log.d("JORNADAAAA","" + Api.getInstance(getApplicationContext()).getJornada().getNombre());

        List<JornadasLiga> JornadasLiga = new ArrayList<>();

        for(int i = 0 ; i < jornadasliga.size(); i++){

                JornadasLiga.add(jornadasliga.get(i)) ;

        }

        textLiga = (TextView)findViewById(R.id.jornadaLiga);

        if(JornadasLiga.size() > 0) {

            Log.d("LOKIIIIIIIIIIII",""+  Api.getInstance(getApplicationContext()).getJornadaConcreta());

            if (JornadasLiga.size()  != 1) {

                if(Api.getInstance(getApplicationContext()).getJornadaConcreta() == -1) {

                    textLiga.setText(JornadasLiga.get(JornadasLiga.size() - 1).getNombre());

                }else{

                    Log.d("LOKIIIIIIIIIIII",""+  Api.getInstance(getApplicationContext()).getJornadaConcreta());

                    textLiga.setText(JornadasLiga.get(Api.getInstance(getApplicationContext()).getJornadaConcreta()-1).getNombre());

                }


            } else {

                textLiga.setText(JornadasLiga.get(0).getNombre());

            }

        }else{
            textLiga.setText("No existe Jornada");
        }


    }



    private void onJornadasClick() {
        Intent intent = new Intent(JornadaActivity.this, JornadasAnioActivity.class);
        int juanjo=0;
        startActivityForResult(intent,juanjo);
    }

    public void onClick(View v) {
        if ( v == buttonJornadas ) {
            onJornadasClick();
        }
    }

}