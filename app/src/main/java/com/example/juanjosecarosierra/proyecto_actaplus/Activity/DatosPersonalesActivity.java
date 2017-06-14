package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;


public class DatosPersonalesActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textNombreArbitro;

    private Button buttonGuardar;

    private Arbitro arbitro;


    private EditText nombre, apellidos, telefono, direccion, ccc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datospersonales);

        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        telefono = (EditText) findViewById(R.id.telefono);
        direccion = (EditText) findViewById(R.id.direccion);
        ccc = (EditText) findViewById(R.id.ccc);

        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);

        buttonGuardar.setOnClickListener(this);


        request();

    }

    private void request() {

        arbitro = Api.getInstance(getApplicationContext()).getArbitro();
        fillUi();
    }


    private void fillUi() {

        nombre.setText( arbitro.getNombre() + "" );
        apellidos.setText( arbitro.getApellidos() + "" );
        direccion.setText( arbitro.getDireccion() + "" );
        telefono.setText( arbitro.getTlf() + "" );
        ccc.setText( arbitro.getCCC() + "" );

    }

    @Override
    public void onClick(View v) {

        if ( v == buttonGuardar ) {


            String Nombre,  Apellidos,  Direccion,  Tlf,  CCC;

            Nombre=nombre.getText().toString();
            Apellidos=apellidos.getText().toString();
            Direccion=direccion.getText().toString();
            Tlf=telefono.getText().toString();
            CCC=ccc.getText().toString();


            Api.getInstance(getApplicationContext()).changedatos(arbitro.getId_arbitros(), Nombre, Apellidos, Direccion, Tlf, CCC, new Api.OnResultListener<Arbitro>() {
                @Override
                public void onSuccess(Arbitro data) {
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
