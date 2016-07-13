package com.andrelara.locadora;

/**
 * Created by André on 12/07/2016.
 */
public class Filme {
    private int id;
    private String titulo;
    private String genero;
    private int duracaoMin;
    private String atorPrincipal;
    private int ano;
    private String diretor;

    public Filme(){}

    public Filme(int id,String titulo, String genero, int duracaoMin, String atorPrincipal, int ano, String diretor){

        this.id = id ;
        this.titulo = titulo;
        this.genero = genero;
        this.duracaoMin = duracaoMin;
        this.atorPrincipal = atorPrincipal;
        this.ano = ano;
        this.diretor = diretor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAtorPrincipal() {
        return atorPrincipal;
    }

    public void setAtorPrincipal(String atorPrincipal) {
        this.atorPrincipal = atorPrincipal;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}
