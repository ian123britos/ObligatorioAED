package Dominio;

import Tads.Cola;

public class Aeropuerto implements Comparable<Aeropuerto> {

    private String codigo;
    private String nombre;

     // Cola de vuelos esperando despegar
    private Cola<Vuelo> vuelosEnEspera;



    public Aeropuerto(String codigo, String nombre) {

        this.codigo = codigo;
        this.nombre = nombre;

        // Inicializar cola
        this.vuelosEnEspera = new Cola<>();
    }

    // GETTERS

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Cola<Vuelo> getVuelosEnEspera() {
        return vuelosEnEspera;
    }

    // SETTERS

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVuelosEnEspera(Cola<Vuelo> vuelosEnEspera) {
        this.vuelosEnEspera = vuelosEnEspera;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Aeropuerto)) {
            return false;
        }

        Aeropuerto a = (Aeropuerto) obj;

        return this.codigo.equals(a.codigo);
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }

    @Override
    public int compareTo(Aeropuerto o) {
        return this.codigo.compareTo(o.codigo);
    }
}