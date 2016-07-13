package com.andrelara.locadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListaFilmesActivity extends AppCompatActivity {
    private ListView lvListaFilme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        lvListaFilme = (ListView) findViewById(R.id.lvListaFilmes);
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem m1 = menu.add(0,0,0,"Add");
        m1.setIcon(R.drawable.ic_add_black_24dp);
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case 0:
                Intent it = new Intent(ListaFilmesActivity.this, CadastraFilmeActivity.class );
                startActivity(it);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DbHelper dbhelper = new DbHelper(this);
        List<Filme> listaFilmes = dbhelper.selectTodosFilmes();

        ListAdapter adapter = new CustomAdapter_ListaFilmesActivity(this, listaFilmes);
        lvListaFilme.setAdapter(adapter);

        lvListaFilme.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaFilmesActivity.this, DetalheFilme.class);

                TextView component_row = (TextView) view.findViewById(R.id.tvTitulo_row);
                String titulo = component_row.getText().toString();
                intent.putExtra("titulo", titulo);
                Log.d("fei",titulo);
                startActivity(intent);
            }
        });
    }
}
