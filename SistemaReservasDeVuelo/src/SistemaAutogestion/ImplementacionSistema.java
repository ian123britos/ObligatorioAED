package SistemaAutogestion;

import Dominio.Categoria;
import Dominio.Aeropuerto;
import Dominio.Pasajero;
import Dominio.Reserva;
import Dominio.Vuelo;

import Tads.ILista;
import Tads.ListaSE;

public class ImplementacionSistema implements ISistema {

    private ILista<Pasajero> pasajeros;

    private ILista<Aeropuerto> aeropuertos;

    private ILista<Vuelo> vuelos;

    private ILista<Reserva> reservas;

    @Override
    public Retorno inicializarSistema() {

        pasajeros = new ListaSE<>();
        aeropuertos = new ListaSE<>();
        vuelos = new ListaSE<>();
        reservas = new ListaSE<>();

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, int edad, Categoria categoria) {

        try {
            //Aca falta arreglar y hacerlo con tad para que vaya a la lista con un tad (obtenerpasajero, registrar )
            Pasajero p = new Pasajero(nombre, cedula, edad, categoria);

            return new Retorno(Retorno.Resultado.OK);

        } catch (Exception e) {

            return new Retorno(Retorno.Resultado.valueOf(e.getMessage()));
        }
    }
}
