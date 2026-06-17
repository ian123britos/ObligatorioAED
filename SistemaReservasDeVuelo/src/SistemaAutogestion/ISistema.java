package SistemaAutogestion;

import Dominio.Categoria;
import Dominio.Clase;


public interface ISistema {

    Retorno inicializarSistema();

    Retorno registrarPasajero(String cedula, String nombre, int edad, Categoria categoria);

    Retorno buscarPasajero(String cedula);

    Retorno listarPasajerosAscendente();

    Retorno listarPasajerosDescendente();

    Retorno listarPasajerosPorCategoría(Categoria unaCategoria);

    Retorno registrarAeropuerto(String codigo, String nombre);

    Retorno obtenerAeropuerto(String codigo);

    Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, int capacidad, int costoEnDolares);

    Retorno obtenerInformacionDeVuelo(String codigoDeVuelo);

    Retorno abrirVuelo(String codigoDeVuelo);

    Retorno cerrarVuelo(String codigoDeVuelo);

    Retorno realizarReserva(String codigoDeVuelo, String cedula);

    Retorno realizarCheckIn(String codigoDeVuelo, String cedula);

    Retorno embarqueYDespegueDeVuelo(String codigoAeropuerto);
     Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase);

}
