package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.JornadaPartidosListAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class PartidosArbitroActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaPartidos;
    private JornadaPartidosListAdapter adaptador;

    private List<Partido> partidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidosarbitro);

        listaPartidos = (ListView)findViewById(R.id.partidos);

    }

    @Override
    protected void onResume() {
        request();
        super.onResume();
    }

    private void request() {

        Api.getInstance(getApplicationContext()).getPartidosArbitro(new Api.OnResultListener<List<Partido>>() {
            @Override
            public void onSuccess(List<Partido> data) {
                partidos = data;
                fillUi();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void fillUi() {
        adaptador = new JornadaPartidosListAdapter(getApplicationContext(), partidos);
        listaPartidos.setAdapter(adaptador);
        listaPartidos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, EditarPartidoActivity.class);

        // Añadir aqui el id de partido
        Partido partido = (Partido) listaPartidos.getItemAtPosition(position);
        Api.getInstance(getApplicationContext()).setPartido(partido);

        startActivity(i);
    }
}
