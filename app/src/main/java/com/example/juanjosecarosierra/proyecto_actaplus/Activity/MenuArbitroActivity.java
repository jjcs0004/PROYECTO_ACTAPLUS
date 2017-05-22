package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.juanjosecarosierra.proyecto_actaplus.Adapter.MenuArbitroAdapter;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.*;

import java.util.ArrayList;

public class MenuArbitroActivity extends AppCompatActivity {

    private ListView listmenu;
    private MenuArbitroAdapter menuArbitroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuarbitro);

        createUi();
        fillUi();
    }

    private void createUi() {
        listmenu = (ListView)findViewById(R.id.menusarbitro);
    }

    private void fillUi() {

        List<String> menu = new ArrayList<>();

        menu.add("Datos Personales");
        menu.add("Designaciones y Resultados");
        menu.add("Cerrar Sesion");

        menuArbitroAdapter = new MenuArbitroAdapter(getApplicationContext(), menu);

        listmenu.setAdapter(menuArbitroAdapter);

        listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String menu = (String)parent.getItemAtPosition(position);
                //Api.getInstance(getApplicationContext()).setOpcion_menu(menu);
                if(position == 0) {
                    startActivity(new Intent(MenuArbitroActivity.this, DatosPersonalesActivity.class));
                }else{
                    if(position==1){
                        startActivity(new Intent(MenuArbitroActivity.this, PartidosArbitroActivity.class));
                    }else{
                        startActivity(new Intent(MenuArbitroActivity.this, PortadaActivity.class));
                    }
                }
            }
        });


    }
}
