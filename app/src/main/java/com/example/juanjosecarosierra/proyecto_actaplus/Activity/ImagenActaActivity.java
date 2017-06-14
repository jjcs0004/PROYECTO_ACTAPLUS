package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.LigasListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Anio;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;

public class ImagenActaActivity extends AppCompatActivity {

    private NetworkImageView imagenActa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenacta);

        imagenActa  = (NetworkImageView) findViewById(R.id.imagenActa);

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            String imagenActa = bundle.getString("imagenActa");
            request(imagenActa);
        }
    }

    private void request(String urlImagen) {

        Log.i(getClass().getName(), " URL: " + "http://ctja.dyndns-server.com/SLIM/public/" + urlImagen);

        imagenActa.setImageUrl("http://ctja.dyndns-server.com/SLIM/public/" + urlImagen, Api.getInstance(getApplicationContext()).getImageLoader());

    }
}
