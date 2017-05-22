package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.juanjosecarosierra.proyecto_actaplus.R;

public class PortadaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLigas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        buttonLigas = (Button)findViewById(R.id.buttonLigas);
        buttonLigas.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.liga_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.buttonLogin ) {
            onLoginClick();
            return true;
        }

        return false;
    }

    private void onLoginClick(){
        // go to login

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        //finish();
    }

    private void onLigasClick() {
        Intent intent = new Intent(this, CargaAniosActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if ( v == buttonLigas ) {
            onLigasClick();
        }
    }
}
