package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

public class EditarPartidoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText resultado1, resultado2;
    private Button buttonImagen, buttonGuardar;

    private Partido partido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarpartido);

        resultado1 = (EditText) findViewById(R.id.resultado1);
        resultado2 = (EditText) findViewById(R.id.resultado2);
        buttonImagen = (Button) findViewById(R.id.buttonImagen);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);

        buttonImagen.setOnClickListener(this);
        buttonGuardar.setOnClickListener(this);

        request();
    }

    private void request() {

        partido = Api.getInstance(getApplicationContext()).getPartido();
        fillUi();
    }

    private void fillUi() {

        resultado1.setText( partido.getResultado1() + "" );
        resultado2.setText( partido.getResultado2() + "" );

    }

    @Override
    public void onClick(View v) {
        if ( v == buttonImagen ) {

        }
        else if ( v == buttonGuardar ) {
            int r1, r2;

            r1 = Integer.valueOf(resultado1.getText().toString());
            r2 = Integer.valueOf(resultado2.getText().toString());

            Api.getInstance(getApplicationContext()).changeResult(partido.getId_partidos(), r1, r2, new Api.OnResultListener<Partido>() {
                @Override
                public void onSuccess(Partido data) {
                    finish();
                }

                @Override
                public void onError(String error) {
                    finish();
                }
            });
        }
    }
}
