package com.example.juanjosecarosierra.proyecto_actaplus.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.juanjosecarosierra.proyecto_actaplus.Clases.Liga;
import com.example.juanjosecarosierra.proyecto_actaplus.R;

import java.util.List;

public class LigasListAdapter extends ArrayAdapter<Liga> {

    private static class ViewHolder {
        private TextView text;
    }

    public LigasListAdapter(Context context, List<Liga> items) {
        super(context, R.layout.liga_item, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.liga_item, parent, false);

            //View view = super.getView(position, convertView, parent);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView)convertView.findViewById(R.id.nombreLiga);

            viewHolder.text.setTextColor(Color.WHITE);
            viewHolder.text.setTextSize(30);
            viewHolder.text.setBackgroundColor(999999);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Liga item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.text.setText( item.getNombre() );
        }

        return convertView;
    }

}