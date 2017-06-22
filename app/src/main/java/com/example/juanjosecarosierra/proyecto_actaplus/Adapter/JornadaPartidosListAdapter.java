package com.example.juanjosecarosierra.proyecto_actaplus.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class JornadaPartidosListAdapter extends ArrayAdapter<Partido> {

    private static class ViewHolder {
        private TextView equipo1;
        private TextView equipo2;
        private TextView resultado;
        private ImageView imagen;
    }

    public JornadaPartidosListAdapter(Context context, List<Partido> items) {
        super(context, R.layout.partido_item, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.partido_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.equipo1 = (TextView)convertView.findViewById(R.id.equipo1);
            viewHolder.resultado = (TextView)convertView.findViewById(R.id.resultado);
            viewHolder.equipo2 = (TextView)convertView.findViewById(R.id.equipo2);

            viewHolder.equipo1.setTextColor(Color.WHITE);
            viewHolder.equipo1.setTextSize(25);


            viewHolder.resultado.setTextColor(Color.WHITE);
            viewHolder.resultado.setTextSize(25);


            viewHolder.equipo2.setTextColor(Color.WHITE);
            viewHolder.equipo2.setTextSize(25);

            viewHolder.imagen = (ImageView) convertView.findViewById(R.id.imagen);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Partido item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.equipo1.setText( item.getId_equipo1() );
            viewHolder.resultado.setText( item.getResultado1() + " - " + item.getResultado2() );
            viewHolder.equipo2.setText( item.getId_equipo2() );

        }

        return convertView;
    }

}