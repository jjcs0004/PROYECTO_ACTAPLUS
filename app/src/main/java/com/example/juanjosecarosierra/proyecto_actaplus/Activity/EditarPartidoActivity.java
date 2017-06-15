package com.example.juanjosecarosierra.proyecto_actaplus.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class EditarPartidoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText resultado1, resultado2;
    private Button buttonImagen, buttonGuardar;

    private Partido partido;

    //private ImageView imageView;
    private NetworkImageView imageView;

    private EditText editTextName;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    private String UPLOAD_URL ="http://ctja.dyndns-server.com/SLIM/public/subeActa/";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    private NetworkImageView imagenActa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarpartido);


        editTextName = (EditText) findViewById(R.id.editText);
        imageView  = (NetworkImageView) findViewById(R.id.imageView);



        resultado1 = (EditText) findViewById(R.id.resultado1);
        resultado2 = (EditText) findViewById(R.id.resultado2);
        buttonImagen = (Button) findViewById(R.id.buttonImagen);
        buttonGuardar = (Button) findViewById(R.id.buttonGuardar);

        buttonImagen.setOnClickListener(this);
        buttonGuardar.setOnClickListener(this);

       // imagenActa  = (NetworkImageView) findViewById(R.id.imagenActa);

        request();
    }


    private void request() {

        partido = Api.getInstance(getApplicationContext()).getPartido();

        imageView.setImageUrl("http://ctja.dyndns-server.com/SLIM/public/" + partido.getActa(), Api.getInstance(getApplicationContext()).getImageLoader());

        fillUi();

    }

    private void fillUi() {

        resultado1.setText( partido.getResultado1() + "" );
        resultado2.setText( partido.getResultado2() + "" );

    }

    @Override
    public void onClick(View v) {
        if ( v == buttonImagen ) {
            showFileChooser();
        }
        else if ( v == buttonGuardar ) {

            uploadImage();

            uploadResultado();


        }

    }

    private void uploadResultado(){

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL + partido.getId_partidos(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(EditarPartidoActivity.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(EditarPartidoActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Converting Bitmap to String
                String image = getStringImage(bitmap);
                //Getting Image Name
                String name = editTextName.getText().toString().trim();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();
                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);
                //returning parameters
                return params;
            }
        };
        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


}
