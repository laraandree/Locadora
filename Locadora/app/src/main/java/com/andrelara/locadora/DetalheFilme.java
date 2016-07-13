package com.andrelara.locadora;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by André on 12/07/2016.
 */
public class DetalheFilme extends Activity{
    private TextView titulo;
    private TextView genero;
    private TextView duracaoMin;
    private TextView atorPrincipal;
    private TextView ano;
    private TextView diretor;

    private DbHelper dbHelper = new DbHelper(this);

    private String _titulo;
    private String _genero;
    private String _duracaoMin;
    private String _atorPrincipal;
    private String _ano;
    private String _diretor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);

        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){

            _titulo = bundle.getString("titulo");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        titulo = (TextView) findViewById(R.id.tvTitulo_detalhe);
        ano = (TextView) findViewById(R.id.tvAno_detalhe);
        diretor = (TextView) findViewById(R.id.tvDiretor_detalhe);
        genero = (TextView) findViewById(R.id.tvGenero_detalhe);
        duracaoMin = (TextView) findViewById(R.id.tvDuracaoMin_detalhe);
        atorPrincipal = (TextView) findViewById(R.id.tvAtorPrincipal_detalhe);


        Filme filme = dbHelper.selectLivroByTitulo(_titulo);

        _titulo = filme.getTitulo();
        _ano = Integer.toString(filme.getAno());
        _diretor = filme.getDiretor();
        _atorPrincipal = filme.getAtorPrincipal();
        _genero = filme.getGenero();
        _duracaoMin = Integer.toString(filme.getDuracaoMin());


        titulo.setText(_titulo);
        ano.setText(_ano);
        diretor.setText(_diretor);
        genero.setText(_genero);
        atorPrincipal.setText(_atorPrincipal);
        duracaoMin.setText(_duracaoMin);
    }
    public void excluiFilme(View v){
        dbHelper.dropLivroByTitulo(titulo.getText().toString());
        finish();
    }
    public void alteraFilme(View v){

        LayoutInflater inflaterDialog = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = inflaterDialog.inflate(R.layout.view_detalhe_dialog, null);

        final EditText t = (EditText) viewDialog.findViewById(R.id.etTitulo_detalhe_dialog);
        final EditText d = (EditText) viewDialog.findViewById(R.id.etDiretor_detalhe_dialog);
        final EditText a = (EditText) viewDialog.findViewById(R.id.etAno_detalhe_dialog);
        final EditText g = (EditText) viewDialog.findViewById(R.id.etGenero_detalhe_dialog);
        final EditText aP = (EditText) viewDialog.findViewById(R.id.etAtorPrincipal_detalhe_dialog);
        final EditText dM = (EditText) viewDialog.findViewById(R.id.etDuracaoMin_detalhe_dialog);

        Button no = (Button) viewDialog.findViewById(R.id.btnCancelar_detalhe_dialog);
        Button yes = (Button) viewDialog.findViewById(R.id.btnAlterar_detalhe_dialog);

        t.setText(titulo.getText().toString());
        d.setText(diretor.getText().toString());
        a.setText(ano.getText().toString());
        g.setText(genero.getText().toString());
        aP.setText(atorPrincipal.getText().toString());
        dM.setText(duracaoMin.getText().toString());

        final Dialog dl = new Dialog(this);
        dl.setContentView(viewDialog);

        no.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dl.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.alteraFilmeDB(titulo.getText().toString(),t.getText().toString(), g.getText().toString(), Integer.parseInt(dM.getText().toString()),
                        aP.getText().toString(), Integer.parseInt(a.getText().toString()), d.getText().toString());
                _titulo = t.getText().toString();

                onResume();
                dl.dismiss();
            }
        });
        dl.show();
    }
}
