package com.andrelara.locadora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by André on 12/07/2016.
 */
public class CustomAdapter_ListaFilmesActivity extends ArrayAdapter<Filme> {

    CustomAdapter_ListaFilmesActivity(Context context, List<Filme> filmes){
        super(context, R.layout.custom_row_text_view, filmes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row_text_view, parent, false);

        Filme singleFilmeItem = getItem(position);

        TextView tvTitulo = (TextView) customView.findViewById(R.id.tvTitulo_row);
        TextView tvDiretor = (TextView) customView.findViewById(R.id.tvDiretor_row);
        TextView tvAno = (TextView) customView.findViewById(R.id.tvAno_row);

        tvTitulo.setText(singleFilmeItem.getTitulo());
        tvDiretor.setText(singleFilmeItem.getDiretor());
        tvAno.setText(Integer.toString(singleFilmeItem.getAno()));

        return customView;
    }
}
