package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class PartidosArbitroActivity extends AppCompatActivity {

    private TextView textNombrePartido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidosarbitro);

        fillUi();

    }


    private void fillUi() {

        textNombrePartido = (TextView)findViewById(R.id.partidoArbitro);

        textNombrePartido.setText("Nombre: " + Api.getInstance(getApplicationContext()).getArbitro().getNombre());

    }
}
