package com.example.juanjosecarosierra.proyecto_actaplus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Jornada;
import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class JornadasListAdapter extends ArrayAdapter<Jornada> {

    private static class ViewHolder {
        private TextView text;
    }

    public JornadasListAdapter(Context context, List<Jornada> items) {
        super(context, R.layout.jornada_item, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.jornada_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.text = (TextView)convertView.findViewById(R.id.nombreJornada);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Jornada item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.text.setText( item.getNombre() );
        }

        return convertView;
    }

}