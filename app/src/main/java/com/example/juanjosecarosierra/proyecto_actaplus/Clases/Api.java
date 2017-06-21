package com.example.juanjosecarosierra.proyecto_actaplus.Clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Api {
    private static Api mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;


    //variables "globales" declaradas

    private Liga liga;

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    private int jornadaConcreta;

    public int getJornadaConcreta() {
        return jornadaConcreta;
    }

    public void setJornadaConcreta(int jornadaConcreta) {
        this.jornadaConcreta = jornadaConcreta;
    }

    private Arbitro arbitro;

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {this.arbitro = arbitro;}

    private JornadasLiga jornada;

    public JornadasLiga getJornada() {
        return jornada;
    }

    public void setJornada(JornadasLiga jornada) {this.jornada = jornada;}

    private Partido partido;



    private Api(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized Api getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Api(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    // ------------------------------------------------------------------------------------- //

    public interface OnResultListener<T> {
        void onSuccess(T data);
        void onError(String error);
    }



    // ------------------------------------------------------------------------------------- //

    public void getLigas(final OnResultListener<List<Liga>> listener) {

        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/ligas",new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Liga> ligas = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject ligasJson = response.getJSONObject(i);
                        Liga liga = gson.fromJson(ligasJson.toString(), Liga.class);
                        ligas.add(liga);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(ligas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    // ------------------------------------------------------------------------------------- //

    public void getAnio(final OnResultListener<List<Anio>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/anios",new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Anio> anios = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject PartidosJson = response.getJSONObject(i);
                        Anio anio = gson.fromJson(PartidosJson.toString(), Anio.class);
                        anios.add(anio);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(anios);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    // ------------------------------------------------------------------------------------- //

    public void getPartidos(final OnResultListener<List<Partido>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/partidos/1",new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Partido> partidos = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject partidoJson = response.getJSONObject(i);
                        Partido partido = gson.fromJson(partidoJson.toString(), Partido.class);
                        partidos.add(partido);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(partidos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    // ------------------------------------------------------------------------------------- //

    public void getArbitro(final OnResultListener<List<Arbitro>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/arbitros",new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Arbitro> arbitros = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject arbitrosJSON = response.getJSONObject(i);
                        Arbitro arbitro = gson.fromJson(arbitrosJSON.toString(), Arbitro.class);
                        arbitros.add(arbitro);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(arbitros);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    public void getArbitroConcreto(final OnResultListener<List<Arbitro>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/arbitros/" + arbitro.getId_arbitros(),new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Arbitro> arbitros = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject arbitrosJSON = response.getJSONObject(i);
                        Arbitro arbitro = gson.fromJson(arbitrosJSON.toString(), Arbitro.class);
                        arbitros.add(arbitro);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(arbitros);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }


    public void getJornadasLiga(final OnResultListener<List<JornadasLiga>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com:80/SLIM/public/jornadas/"+getLiga().getId_ligas(),new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<JornadasLiga> jornadas = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject arbitrosJSON = response.getJSONObject(i);
                        JornadasLiga jornada = gson.fromJson(arbitrosJSON.toString(), JornadasLiga.class);
                        jornadas.add(jornada);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(jornadas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    public void getPartidosJornada(JornadasLiga jornada, final OnResultListener<List<Partido>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/partidos/jornada/"+jornada.getId_jornadas(),new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Partido> partidos = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject arbitrosJSON = response.getJSONObject(i);
                        Partido partido = gson.fromJson(arbitrosJSON.toString(), Partido.class);
                        partidos.add(partido);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(partidos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    public void getPartidosArbitro(final OnResultListener<List<Partido>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/partidos/arbitro/"+getArbitro().getId_arbitros() ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Partido> partidos = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject arbitrosJSON = response.getJSONObject(i);
                        Partido partido = gson.fromJson(arbitrosJSON.toString(), Partido.class);
                        partidos.add(partido);
                    }
                    catch ( JSONException ex ) {
                        listener.onError(ex.getMessage());
                    }
                }

                listener.onSuccess(partidos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    // ------------------------------------------------------------------------------------- //



    // ------------------------------------------------------------------------------------- //

    public void login(String user, String password, final OnResultListener<Arbitro> listener) {

        String url = "http://ctja.dyndns-server.com/SLIM/public/loginArbitro/" + user + "?pass=" + toMd5(password);

        Log.i(getClass().getName(), url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                if ( response == null ) {
                    listener.onError("Error response");
                }
                else {

                    final Gson gson = new Gson();
                    Arbitro arbitro = gson.fromJson(response.toString(), Arbitro.class);


                    listener.onSuccess(arbitro);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }


    private String toMd5(String pass){
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(pass.getBytes(),0,pass.length());
            return new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

    public void changeResult(int partidoId, int r1, int r2, final OnResultListener<Partido> listener) {

        /*
        JSONObject parameters = null;

        try {
            parameters = new JSONObject();
            parameters.put("r1", r1);
            parameters.put("r2", r2);
        }
        catch (JSONException ex) {
            listener.onError(ex.getMessage());
        }
        */


        StringRequest request = new StringRequest(Request.Method.POST, "http://ctja.dyndns-server.com/SLIM/public/actualizaResultado/" + partidoId + "?r1=" + r1 + "&r2=" + r2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess( getPartido() );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }

    public void changedatos(int id, String Nombre, String apellidos, String direccion, String Tlf, String CCC, final OnResultListener<Arbitro> listener) {

        // http://ctja.dyndns-server.com/SLIM/public/actualizaArbitro/1?nombre=Juan Carlos?apellidos=Gonzalez Ruiz?direccion=Avenida de mi casa?tlf=999444111?CCC=
        StringRequest request = new StringRequest(Request.Method.POST, "http://ctja.dyndns-server.com/SLIM/public/actualizaArbitro/"+ id +"?nombre="+ Nombre + "&apellidos=" + apellidos + "&direccion="+direccion+"&tlf="+Tlf+"&CCC="+ CCC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess( getArbitro() );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        addToRequestQueue(request);
    }



}

















