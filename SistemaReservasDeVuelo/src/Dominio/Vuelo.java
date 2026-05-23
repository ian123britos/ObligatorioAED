package Dominio;

public class Vuelo implements Comparable<Vuelo> {

    private String codigoVuelo;

    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;

    private int capacidad;
    private int costoEnDolares;

    private EstadoVuelo estado;

    public Vuelo(String codigoVuelo,
                  Aeropuerto aeropuertoOrigen,
                  Aeropuerto aeropuertoDestino,
                  int capacidad,
                  int costoEnDolares) {

        this.codigoVuelo = codigoVuelo;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.capacidad = capacidad;
        this.costoEnDolares = costoEnDolares;

        // Todo vuelo nuevo inicia programado
        this.estado = EstadoVuelo.PROGRAMADO;
    }

    // GETTERS

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getCostoEnDolares() {
        return costoEnDolares;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }

    // SETTERS

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setCostoEnDolares(int costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public void setEstado(EstadoVuelo estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Vuelo)) {
            return false;
        }

        Vuelo v = (Vuelo) obj;

        return this.codigoVuelo.equals(v.codigoVuelo);
    }

    @Override
    public int compareTo(Vuelo o) {
        return this.codigoVuelo.compareTo(o.codigoVuelo);
    }

}