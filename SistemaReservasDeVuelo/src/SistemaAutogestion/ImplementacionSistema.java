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

        if (cedula == null || nombre == null || cedula.trim().isEmpty() || nombre.trim().isEmpty()) {

            return new Retorno(Retorno.Resultado.ERROR_1);

        }

        if (!cedula.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-\\d$")) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        if (edad < 0) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }

        //Si ya existe un pasajero registrado con esa cédula.
        for (int i = 0; i < pasajeros.Longitud(); i++) {
            Pasajero pasajeroExiste = pasajeros.Obtener(i);
            if (pasajeroExiste.getCedula().equalsIgnoreCase(cedula)) {
                return new Retorno(Retorno.Resultado.ERROR_4);
            }
        }

        //si pasa todos los errores entonces creamos el nuevo pasajero con esos datos
        Pasajero pasajero = new Pasajero(nombre, cedula, edad, categoria);

        //agregamos al nuevo pasajero recien creado a la lista de pasajeros
        //Usamos el adicionarOrdenado ya que este ordena automaticamente por el CompareTo de pasajero, osea cada pasajero que se regista
        // queda ordenado por la cedula
        pasajeros.adicionarOrdenado(pasajero);
        return new Retorno(Retorno.Resultado.OK);

    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        if (!cedula.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-\\d$")) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        for (int i = 0; i < pasajeros.Longitud(); i++) {
            //verificamos en la lista de pasajeros si hay un pasajero con la misma cedula que la dada por parametro
            Pasajero pasajero = pasajeros.Obtener(i);
            if (pasajero.getCedula().equals(cedula)) {
                //si hay un pasajero con esa cedula en la lista se devuelve en valorString con el formato dado.
                Retorno retorno = new Retorno(Retorno.Resultado.OK);

                retorno.valorString = pasajero.getCedula() + ";"
                        + pasajero.getNombre() + ";"
                        + pasajero.getEdad() + ";"
                        + pasajero.getCategoria();

                return retorno;
            }

        }

        //Si no existe un pasajero registrado con esa cédula.
        return new Retorno(Retorno.Resultado.ERROR_2);
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);

        String resultado = "";

        for (int i = 0; i < pasajeros.Longitud(); i++) {

            Pasajero pasajero = pasajeros.Obtener(i);

            resultado += pasajero.getCedula() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getEdad() + ";"
                    + pasajero.getCategoria();

            if (i < pasajeros.Longitud() - 1) {
                resultado += "|";
            }
        }

        retorno.valorString = resultado;

        return retorno;
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        String resultado = "";
        
        // No se si seria solo pasajeros.Longitud() o con el -1 como está
        for (int i = pasajeros.Longitud() - 1; i >= 0; i--) {
            Pasajero pasajero = pasajeros.Obtener(i);

            resultado += pasajero.getCedula() + ";"
                    + pasajero.getNombre() + ";"
                    + pasajero.getEdad() + ";"
                    + pasajero.getCategoria();

            if (i > 0) {
                resultado += "|";
            }
        }

        retorno.valorString = resultado;
        return retorno;
    }

}
