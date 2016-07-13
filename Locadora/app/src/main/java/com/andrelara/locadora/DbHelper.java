package com.andrelara.locadora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by André on 12/07/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String NAME_BASE = "Locadora";
    private static final int VERSION_BASE = 1;

    public DbHelper(Context context){
        super(context, NAME_BASE, null, VERSION_BASE);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTableFilme = "CREATE TABLE filme("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "titulo TEXT,"+
                "genero TEXT,"+
                "duracaoMin integer,"+
                "atorPrincipal TEXT,"+
                "ano integer,"+
                "diretor TEXT)";
        db.execSQL(queryCreateTableFilme);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryDropTableFilme = "DROP TABLE filme";
        db.execSQL(queryDropTableFilme);
        onCreate(db);
    }

    public void insertFilme(Filme filme){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("titulo", filme.getTitulo());
        contentValues.put("genero", filme.getGenero());
        contentValues.put("duracaoMin", filme.getDuracaoMin());
        contentValues.put("atorPrincipal", filme.getAtorPrincipal());
        contentValues.put("ano", filme.getAno());
        contentValues.put("diretor", filme.getDiretor());

        db.insert("filme", null, contentValues);
        db.close();
    }

    public void dropLivroByTitulo(String titulo){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("filme", "titulo=?", new String[]{titulo} );
    }

    public Filme selectFilmeById(String id){
        SQLiteDatabase db = getReadableDatabase();
        String querySelectById = "SELECT * FROM filme where id="+id;
        Cursor linha = db.rawQuery(querySelectById, null);
        Filme filme = new Filme();

        if (linha.moveToFirst()){
            do {
                filme.setTitulo(linha.getString(1));
                filme.setGenero(linha.getString(2));
                filme.setDuracaoMin(linha.getInt(3));
                filme.setAtorPrincipal(linha.getString(4));
                filme.setAno(linha.getInt(5));
                filme.setDiretor(linha.getString(6));

            }while(linha.moveToNext());
        }
        db.close();
        return filme;
    }

    public Filme selectLivroByTitulo(String titulo){
        SQLiteDatabase db = getReadableDatabase();
        String querySelectById = "SELECT * FROM filme where titulo= '"+titulo+"'";
        Cursor linha = db.rawQuery(querySelectById, null);
        Filme filme = new Filme();

        if (linha.moveToFirst()){
            do {
                filme.setTitulo(linha.getString(1));
                filme.setGenero(linha.getString(2));
                filme.setDuracaoMin(linha.getInt(3));
                filme.setAtorPrincipal(linha.getString(4));
                filme.setAno(linha.getInt(5));
                filme.setDiretor(linha.getString(6));

            }while(linha.moveToNext());
        }
        db.close();
        return filme;
    }

    public List<Filme> selectTodosFilmes(){
        List<Filme> listFilme = new ArrayList<Filme>();

        SQLiteDatabase db = getReadableDatabase();
        String querySelectAllMovies = "SELECT * FROM filme";
        Cursor linha = db.rawQuery(querySelectAllMovies, null);

        if (linha.moveToFirst()){
            do {
                Filme filme = new Filme();

                filme.setTitulo(linha.getString(1));
                filme.setGenero(linha.getString(2));
                filme.setDuracaoMin(linha.getInt(3));
                filme.setAtorPrincipal(linha.getString(4));
                filme.setAno(linha.getInt(5));
                filme.setDiretor(linha.getString(6));

                listFilme.add(filme);
            }while(linha.moveToNext());
        }
        db.close();
        return listFilme;
    }

    public void alteraFilmeDB (String titulo_antigo,String titulo_novo, String genero, int duracaoMin,
                               String atorPrincipal, int ano, String diretor){
        SQLiteDatabase db = getWritableDatabase();
        String queryUpdateMovie = "UPDATE filme set titulo= '"+titulo_novo+"'," +
                "genero = '"+genero+"'," +
                "duracaoMin = "+duracaoMin+"," +
                "atorPrincipal = '"+atorPrincipal+"'," +
                "ano = "+ano+"," +
                "diretor = '"+diretor+"' where titulo = '"+titulo_antigo+"'";
        db.execSQL(queryUpdateMovie);
    }
}
