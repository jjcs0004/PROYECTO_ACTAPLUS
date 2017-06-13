package com.example.juanjosecarosierra.proyecto_actaplus.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Partido;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.ArrayList;
import java.util.List;

public class EditarPartidosListAdapter extends ArrayAdapter<Partido> {

    private static class ViewHolder {
        private TextView equipo1;
        private TextView equipo2;
        private EditText resultado1;
        private EditText resultado2;
        private ImageView imagen;
    }

    public EditarPartidosListAdapter(Context context, List<Partido> items) {
        super(context, R.layout.partido_item, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.partido_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.equipo1 = (TextView)convertView.findViewById(R.id.equipo1);
            viewHolder.equipo2 = (TextView)convertView.findViewById(R.id.equipo2);
            viewHolder.resultado1 = (EditText)convertView.findViewById(R.id.resultado1);
            viewHolder.resultado2 = (EditText)convertView.findViewById(R.id.resultado2);
            viewHolder.imagen = (ImageView) convertView.findViewById(R.id.imagen);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Partido item = getItem(position);
        if (item!= null) {

            viewHolder.equipo1.setText( item.getId_equipo1() );
            viewHolder.equipo2.setText( item.getId_equipo2() );

            viewHolder.resultado1.setText(item.getResultado1());
            viewHolder.resultado2.setText(item.getResultado2());

        }

        return convertView;
    }


}