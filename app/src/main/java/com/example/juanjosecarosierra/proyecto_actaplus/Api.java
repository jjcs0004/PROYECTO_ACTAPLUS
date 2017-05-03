package com.example.juanjosecarosierra.proyecto_actaplus;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api {
    private static Api mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

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

    // ------------------------------------------------------------------------------------- //

    public interface OnResultListener<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void getJornadas(final OnResultListener<List<Jornada>> listener) {
        JsonArrayRequest request = new JsonArrayRequest("http://ctja.dyndns-server.com/SLIM/public/jornadas",new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Jornada> jornadas = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject jornadaJson = response.getJSONObject(i);
                        Jornada jornada = gson.fromJson(jornadaJson.toString(), Jornada.class);
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



}


















/*public void getJornadas(final OnResultListener<List<Jornada>> listener) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, "http://ctja.dyndns-server.com/SLIM/public/jornadas", new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final Gson gson = new Gson();
                List<Jornada> jornadas = new ArrayList<>();

                for ( int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject jornadaJson = response.getJSONObject(i);
                        Jornada jornada = gson.fromJson(jornadaJson.toString(), Jornada.class);
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
    }*/