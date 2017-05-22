package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class DatosPersonalesActivity extends AppCompatActivity {

    private TextView textNombreArbitro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datospersonales);


        fillUi();

    }


    private void fillUi() {

        textNombreArbitro = (TextView)findViewById(R.id.nombreArbitro);

        textNombreArbitro.setText("Nombre: " + Api.getInstance(getApplicationContext()).getArbitro().getNombre());
    }
}
