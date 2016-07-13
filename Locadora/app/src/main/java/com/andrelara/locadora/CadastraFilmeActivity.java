package com.andrelara.locadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by André on 12/07/2016.
 */
public class CadastraFilmeActivity extends Activity {
    DbHelper dbhelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_filme);

        Button cadastra = (Button) findViewById(R.id.btnCadastrar_cadastra);
        Button cancela = (Button) findViewById(R.id.btnCancelar_cadastra);
        final EditText titulo = (EditText) findViewById(R.id.etTitulo_cadastra);
        final EditText genero = (EditText) findViewById(R.id.etGenero_cadastra);
        final EditText ano = (EditText) findViewById(R.id.etAno_cadastra);
        final EditText atorPrincipal = (EditText) findViewById(R.id.etAtorPrincipal_cadastra);
        final EditText diretor = (EditText) findViewById(R.id.etDiretor_cadastra);
        final EditText duracaoMinutos = (EditText) findViewById(R.id.etDuracaoMin_cadastra);

        cadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filme filme = new Filme();
                filme.setTitulo(titulo.getText().toString());
                filme.setDiretor(diretor.getText().toString());
                filme.setAtorPrincipal(atorPrincipal.getText().toString());
                filme.setGenero(genero.getText().toString());
                filme.setAno(Integer.parseInt(ano.getText().toString()));
                filme.setDuracaoMin(Integer.parseInt(duracaoMinutos.getText().toString()));

                dbhelper.insertFilme(filme);
                finish();
            }
        });
        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
