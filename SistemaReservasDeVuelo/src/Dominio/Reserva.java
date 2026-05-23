package Dominio;

public class Reserva implements Comparable<Reserva> {

    private Pasajero pasajero;
    private Vuelo vuelo;

    // true -> hizo check-in
    // false -> solo tiene reserva
    private boolean checkIn;

    public Reserva(Pasajero pasajero, Vuelo vuelo) {

        this.pasajero = pasajero;
        this.vuelo = vuelo;

        // inicialmente NO hizo check-in
        this.checkIn = false;
    }

    // GETTERS

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    // SETTERS

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Reserva)) {
            return false;
        }

        Reserva r = (Reserva) obj;

        // misma persona en el mismo vuelo
        return this.pasajero.equals(r.pasajero)
                && this.vuelo.equals(r.vuelo);
    }

    @Override
    public String toString() {

        return pasajero.getCedula() + ";"
                + vuelo.getCodigoVuelo() + ";"
                + checkIn;
    }

    @Override
    public int compareTo(Reserva o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}