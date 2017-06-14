package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Arbitro;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        textUsername = (EditText)findViewById(R.id.txtUsername);
        textPassword = (EditText)findViewById(R.id.txtPass);
    }


    private void onLoginClick(){

        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();

        Api.getInstance(getApplicationContext()).login(username, password, new Api.OnResultListener<Arbitro>() {
            @Override
            public void onSuccess(Arbitro data) {
                if ( data==null ) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();

                    Arbitro arbitro = data;

                    Api.getInstance(getApplicationContext()).setArbitro(arbitro);

                    startActivity(new Intent(LoginActivity.this, MenuArbitroActivity.class));

                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == buttonLogin ) {
            onLoginClick();
        }
    }
}
