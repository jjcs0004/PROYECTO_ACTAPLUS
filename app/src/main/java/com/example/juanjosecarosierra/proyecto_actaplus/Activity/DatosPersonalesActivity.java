package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;


public class DatosPersonalesActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textNombreArbitro;

    private Button buttonGuardar;

    private Arbitro arbitro;

    String carpetaFuente = "fonts/Roboto-Italic.ttf";


    List<Arbitro> arbitros;


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


        // Cargamos la fuente
        Typeface fuente = Typeface.createFromAsset(getAssets(), carpetaFuente);

        // Aplicamos la fuente
        nombre.setTypeface(fuente);

        direccion.setTypeface(fuente);


        apellidos.setTypeface(fuente);

        telefono.setTypeface(fuente);

        ccc.setTypeface(fuente);


        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);

        buttonGuardar.setOnClickListener(this);

        request();
    }

    private void request() {

        //arbitro = Api.getInstance(getApplicationContext()).getArbitro();
        Api.getInstance(getApplicationContext()).getArbitroConcreto(new Api.OnResultListener<List<Arbitro>>() {
            @Override
            public void onSuccess(List<Arbitro> data) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                arbitros = data;
                fillUi();

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });

    }


    private void fillUi() {

        nombre.setText( arbitros.get(0).getNombre() );
        apellidos.setText( arbitros.get(0).getApellidos()  );
        direccion.setText( arbitros.get(0).getDireccion()  );
        telefono.setText( arbitros.get(0).getTlf() );
        ccc.setText( arbitros.get(0).getCCC());

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


            Api.getInstance(getApplicationContext()).changedatos(arbitros.get(0).getId_arbitros(), Nombre, Apellidos, Direccion, Tlf, CCC, new Api.OnResultListener<Arbitro>() {
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
