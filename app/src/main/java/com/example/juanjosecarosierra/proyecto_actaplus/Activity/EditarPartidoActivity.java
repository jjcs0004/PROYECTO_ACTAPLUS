package com.example.juanjosecarosierra.proyecto_actaplus.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Api;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.mime.HttpMultipartMode;
import cz.msebera.android.httpclient.entity.mime.content.ByteArrayBody;
import cz.msebera.android.httpclient.entity.mime.content.StringBody;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

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
        bmp.compress(CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){

        // loading or check internet connection or something...
        // ... then
        String url = "http://ctja.dyndns-server.com/SLIM/public/subeActa/";
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                try {
                    JSONObject result = new JSONObject(resultResponse);
                    String status = result.getString("status");
                    String message = result.getString("message");

                    if (status.equals(Constant.REQUEST_SUCCESS)) {
                        // tell everybody you have succed upload image and post strings
                        Log.i("Messsage", message);
                    } else {
                        Log.i("Unexpected", message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Log.e("Error Status", status);
                        Log.e("Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {
            /*
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", mNameInput.getText().toString());
                params.put("location", mLocationInput.getText().toString());
                params.put("about", mAvatarInput.getText().toString());
                params.put("contact", mContactInput.getText().toString());
                return params;
            }
            */

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), imageView.getDrawable()), "image/jpeg"));

                return params;
            }
        };

        VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
    }


    }



}
